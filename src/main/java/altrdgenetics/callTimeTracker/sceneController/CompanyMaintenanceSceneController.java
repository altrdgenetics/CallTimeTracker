/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.StageLauncher;
import altrdgenetics.callTimeTracker.model.sql.CompanyModel;
import altrdgenetics.callTimeTracker.model.table.CompanyMaintenanceTableModel;
import altrdgenetics.callTimeTracker.sql.SQLiteActiveStatus;
import altrdgenetics.callTimeTracker.sql.SQLiteCompany;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CompanyMaintenanceSceneController implements Initializable {

    Stage stage;

    @FXML
    private TextField searchTextField;
    @FXML
    private Button editButton;
    @FXML
    private Button closeButton;
    @FXML
    private TableView<CompanyMaintenanceTableModel> searchTable;
    @FXML
    private TableColumn<CompanyMaintenanceTableModel, Object> iDColumn;
    @FXML
    private TableColumn<CompanyMaintenanceTableModel, Boolean> activeColumn;
    @FXML
    private TableColumn<CompanyMaintenanceTableModel, String> companyColumn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setup Table
        searchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        iDColumn.setCellValueFactory(cellData -> cellData.getValue().getObject());        
        activeColumn.setCellValueFactory(cellData -> cellData.getValue().checkedProperty());
        activeColumn.setCellFactory((TableColumn<CompanyMaintenanceTableModel, Boolean> param) -> {
            CheckBoxTableCell cell = new CheckBoxTableCell<>();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                if (cell.getIndex() > -1) {
                    checkboxlistener(cell.getIndex());
                }
            });
            return cell;
        });
        companyColumn.setCellValueFactory(cellData -> cellData.getValue().getCompanyName());
    }

    public void loadDefaults(Stage stagePassed) {
        stage = stagePassed;
        stage.setTitle("Company Maintenance");
        search();
    }

    private void checkboxlistener(int cellIndex) {
        CompanyMaintenanceTableModel row = searchTable.getItems().get(cellIndex);
        if (row != null) {
            CompanyModel item = (CompanyModel) row.getObject().getValue();
            SQLiteActiveStatus.setActive("Company", item.getId(), row.getChecked());
        }
    }

    @FXML
    private void search(){
        String[] searchParam = searchTextField.getText().trim().split(" ");
        ObservableList<CompanyMaintenanceTableModel> list = SQLiteCompany.searchCompanies(searchParam);
        loadTable(list);
    }
    
    private void loadTable(ObservableList<CompanyMaintenanceTableModel> list) {
        searchTable.getItems().removeAll();
        if (list != null) {
            searchTable.setItems(list);
        }
    }
    
    @FXML
    private void handleClose() {
        stage.close();
    }
    
    @FXML
    private void tableListener(MouseEvent event) {
        CompanyMaintenanceTableModel row = searchTable.getSelectionModel().getSelectedItem();

        if (row != null) {
            if (event.getClickCount() >= 2) {
                editCompanyButtonAction();                
            }
        }
    }

    @FXML
    private void addNewCompanyButtonAction() {
        StageLauncher stageClass = new StageLauncher();
        stageClass.companyAddEditStage(stage, null);
        search();
    }
    
    @FXML
    private void editCompanyButtonAction() {
        CompanyMaintenanceTableModel row = searchTable.getSelectionModel().getSelectedItem();
        StageLauncher stageClass = new StageLauncher();
        stageClass.companyAddEditStage(stage, (CompanyModel) row.getObject().getValue());
        search();
    }
    
}
