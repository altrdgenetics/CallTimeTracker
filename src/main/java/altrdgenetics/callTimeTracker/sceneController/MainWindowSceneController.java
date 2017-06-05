package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.util.StringUtilities;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MainWindowSceneController implements Initializable {
    
    @FXML
    private Button RecordButton;
    @FXML
    private ComboBox CompanyComboBox;
    @FXML
    private Label TimerLabel;
    
    private long callStartTime;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleCallButtonAction(ActionEvent event) {
        switch (RecordButton.getText()) {
            case "Start Call":
                startCall();
                break;
            case "End Call":
                endCall();
                break;
            
        }
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
