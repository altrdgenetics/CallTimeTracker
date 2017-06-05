package altrdgenetics.callTimeTracker.sceneController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class FXMLController implements Initializable {
    
    @FXML
    private Button RecordButton;
    @FXML
    private ComboBox CompanyComboBox;
    
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
        
    }
    
    private void endCall() {
        
    }
    
    
}
