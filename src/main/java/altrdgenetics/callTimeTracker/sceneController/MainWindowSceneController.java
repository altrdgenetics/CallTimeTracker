package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.StageLauncher;
import altrdgenetics.callTimeTracker.model.sql.CompanyModel;
import altrdgenetics.callTimeTracker.model.table.CompanyMaintenanceTableModel;
import altrdgenetics.callTimeTracker.model.table.MainWindowTableModel;
import altrdgenetics.callTimeTracker.sql.SQLiteCompany;
import altrdgenetics.callTimeTracker.util.StringUtilities;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

public class MainWindowSceneController implements Initializable {
    
    Stage stage;
    private long callStartTime;
    
    @FXML
    private Button RecordButton;
    @FXML
    private ComboBox<CompanyModel> CompanyComboBox;
    @FXML
    private Label TimerLabel;
    @FXML
    private MenuItem CompanyMaintenanceMenuItem;
    @FXML
    private MenuItem NewPhoneCallMenuItem;
    @FXML
    private TableView<CompanyMaintenanceTableModel> mainTable;
    @FXML
    private TableColumn<MainWindowTableModel, Object> objectColumn;
    @FXML
    private TableColumn<MainWindowTableModel, String> startTimeColumn;
    @FXML
    private TableColumn<MainWindowTableModel, String> companyColumn;
    @FXML
    private TableColumn<MainWindowTableModel, String> descriptionColumn;
    @FXML
    private TableColumn<MainWindowTableModel, String> durationColumn;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setup Table
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        objectColumn.setCellValueFactory(cellData -> cellData.getValue().getObject()); 
        startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
        companyColumn.setCellValueFactory(cellData -> cellData.getValue().getCompany());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        durationColumn.setCellValueFactory(cellData -> cellData.getValue().getDuration());
        
        //Setup ComboBox
        StringConverter<CompanyModel> converter = new StringConverter<CompanyModel>() {
            @Override
            public String toString(CompanyModel object) {
                return object.getName();
            }

            @Override
            public CompanyModel fromString(String string) {
                return null;
            }
        };
        CompanyComboBox.setConverter(converter);
    }    
    
    @FXML
    private void handleCallButtonAction() {
        switch (RecordButton.getText()) {
            case "Start Call":
                startCall();
                break;
            case "End Call":
                endCall();
                break;
        }
    }
    
    @FXML
    private void handleCompanyMaintenanceMenuItem(){
        StageLauncher stageClass = new StageLauncher();
        stageClass.companyMaintenaceStage(stage);
        loadCompanyComboBox();
    }
    
    @FXML
    private void handleNewPhoneCallMenuItem(){
        StageLauncher stageClass = new StageLauncher();
        stageClass.detailedCallAddEditStage(stage, null);
        loadCallTable();
    }
    
    public void loadDefaults(Stage stagePassed) {
        stage = stagePassed;
        stage.setTitle("Call Time Tracker");
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        loadCompanyComboBox();
        loadCallTable();
    }

    private void loadCompanyComboBox() {
        List<CompanyModel> list = SQLiteCompany.getActiveCompanies();
        
        CompanyComboBox.getItems().clear();
        CompanyComboBox.getItems().addAll(FXCollections.observableArrayList(new CompanyModel()));
        
        for (CompanyModel item : list){
            CompanyComboBox.getItems().addAll(item);
        }
    }
    
    private void loadCallTable() {
        //TODO: pull information from Call Table
    }
    
    private void startCall() {
        callStartTime = System.currentTimeMillis();    
        TimerLabel.setText("");
        RecordButton.setText("End Call");
    }
    
    private void endCall() {
        long callEndTime = System.currentTimeMillis();
        TimerLabel.setText(StringUtilities.convertLongToTime(callEndTime - callStartTime));
        
        //Record call To Database
        RecordButton.setText("Start Call");
    }
    
    
}
