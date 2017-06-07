package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.Global;
import altrdgenetics.callTimeTracker.Stages;
import altrdgenetics.callTimeTracker.util.StringUtilities;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindowSceneController implements Initializable {
    
    Stage stage;
    
    @FXML
    private Button RecordButton;
    @FXML
    private ComboBox CompanyComboBox;
    @FXML
    private Label TimerLabel;
    @FXML
    private MenuItem CompanyMaintenanceMenuItem;
    
    private long callStartTime;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        Stages stageClass = new Stages();
        stageClass.companyMaintenaceStage(stage);
    }
    
    public void loadDefaults(Stage stagePassed) {
        stage = stagePassed;
        stage.setTitle("Call Time Tracker");
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
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
