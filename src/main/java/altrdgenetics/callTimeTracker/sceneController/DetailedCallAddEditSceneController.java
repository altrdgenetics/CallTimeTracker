/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.model.sql.PhoneCallModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DetailedCallAddEditSceneController implements Initializable {

    Stage stage;
    PhoneCallModel phoneCallObject;
    
    @FXML
    private Label headerLabel;
    @FXML
    private ComboBox companyComboBox;
    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;
    @FXML
    private TextField duration;
    @FXML
    private TextArea description;
    @FXML
    private Button editButton;
    @FXML
    private Button closeButton;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loadDefaults(Stage stagePassed, PhoneCallModel companyObjectPassed){
        stage = stagePassed;
        phoneCallObject = companyObjectPassed;
        String title = "Add Phone Call";
        String buttonText = "Add";
        
        if (phoneCallObject != null){
            title = "Detailed Phone Call";
            buttonText = "Save";
            loadInformation();
        }
        stage.setTitle(title);
        headerLabel.setText(title);
        editButton.setText(buttonText);
    }
    
    @FXML
    private void handleClose() {
        stage.close();
    }
        
    @FXML
    private void editButtonAction() {
        if ("Save".equals(editButton.getText().trim())){
            updateInformation();
        } else if ("Add".equals(editButton.getText().trim())) {
            insertInformation();
        }
        stage.close();
    }
    
    private void loadInformation() {
        
    }
    
    private void updateInformation() {
        
    }
    
    private void insertInformation() {
        
    }
    
}
