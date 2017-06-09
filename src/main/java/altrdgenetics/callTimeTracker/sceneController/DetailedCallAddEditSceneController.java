/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.model.sql.CompanyModel;
import altrdgenetics.callTimeTracker.model.sql.PhoneCallModel;
import altrdgenetics.callTimeTracker.sql.SQLiteCompany;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXTimePicker startTime;
    @FXML
    private JFXTimePicker endTime;
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
        companyComboBox.setConverter(converter);
    }    
    
    public void loadDefaults(Stage stagePassed, PhoneCallModel companyObjectPassed){
        stage = stagePassed;
        phoneCallObject = companyObjectPassed;
        String title = "Add Phone Call";
        String buttonText = "Add";
        loadCompanyComboBox();
        stage.setTitle(title);
        headerLabel.setText(title);
        editButton.setText(buttonText);
        
        if (phoneCallObject != null){
            title = "Detailed Phone Call";
            buttonText = "Update";
            loadInformation();
            setPanelInformationDisabled(true);
        }
    }
    
    private void loadCompanyComboBox() {
        List<CompanyModel> list = SQLiteCompany.getActiveCompanies();
        
        companyComboBox.getItems().clear();
        companyComboBox.getItems().addAll(FXCollections.observableArrayList(new CompanyModel()));
        
        for (CompanyModel item : list){
            companyComboBox.getItems().addAll(item);
        }
    }
    
    @FXML
    private void handleClose() {
        stage.close();
    }
        
    @FXML
    private void editButtonAction() {
        if (null != editButton.getText().trim())
            switch (editButton.getText().trim()) {
            case "Save":
                updateInformation();
                stage.close();
                break;
            case "Add":
                insertInformation();
                stage.close();
                break;
            case "Update":
                setPanelInformationDisabled(false);
                break;
            default:
                break;
        }
    }
    
    private void loadInformation() {
        
    }
    
    private void updateInformation() {
        
    }
    
    private void insertInformation() {
        
    }
    
    private void setPanelInformationDisabled(boolean disabled) {
        companyComboBox.setDisable(disabled);
        startDate.setDisable(disabled);
        endDate.setDisable(disabled);
        startTime.setDisable(disabled);
        endTime.setDisable(disabled);
        description.setDisable(disabled);
    }
    
}
