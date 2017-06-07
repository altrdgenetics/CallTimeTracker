/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.model.sql.CompanyModel;
import altrdgenetics.callTimeTracker.sql.SQLiteCompany;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CompanyAddEditSceneController implements Initializable {

    Stage stage;
    CompanyModel companyObject;
    
    @FXML
    private Label headerLabel;
    @FXML
    private TextField companyNameTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button closeButton;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setListeners();
    }    
    
    public void loadDefaults(Stage stagePassed, CompanyModel companyObjectPassed){
        stage = stagePassed;
        companyObject = companyObjectPassed;
        String title = "Add Company";
        String buttonText = "Add";
        
        if (companyObject != null){
            title = "Edit Company";
            buttonText = "Save";
            loadInformation();
        }
        stage.setTitle(title);
        headerLabel.setText(title);
        saveButton.setText(buttonText);
        
    }
    
    private void setListeners() {
        saveButton.disableProperty().bind(
                companyNameTextField.textProperty().isEmpty()
        );
    }
    
    private void loadInformation(){
        companyNameTextField.setText(companyObject.getName());
    }
    
    @FXML
    private void handleClose() {
        stage.close();
    }
        
    @FXML
    private void saveButtonAction() {
        if ("Save".equals(saveButton.getText().trim())){
            updateCompany();
        } else if ("Add".equals(saveButton.getText().trim())) {
            insertCompany();
        }
        stage.close();
    }
    
    private void insertCompany() {
        companyObject = new CompanyModel();
        companyObject.setActive(true);
        companyObject.setName(companyNameTextField.getText().trim());
        int id = SQLiteCompany.insertCompany(companyObject);
        System.out.println("New Company ID: " + id);
    }
    
    private void updateCompany() {
        companyObject.setName(companyNameTextField.getText().trim());
        SQLiteCompany.updateCompanyInfoByID(companyObject);
    }
}
