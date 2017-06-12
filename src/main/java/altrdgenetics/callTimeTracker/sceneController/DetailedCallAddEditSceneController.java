/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.model.sql.CompanyModel;
import altrdgenetics.callTimeTracker.model.sql.PhoneCallModel;
import altrdgenetics.callTimeTracker.sql.SQLiteCompany;
import altrdgenetics.callTimeTracker.sql.SQLitePhoneCall;
import altrdgenetics.callTimeTracker.util.DateTimeUtilities;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Timestamp;
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
    private JFXDatePicker startDatePicker;
    @FXML
    private JFXDatePicker endDatePicker;
    @FXML
    private JFXTimePicker startTimePicker;
    @FXML
    private JFXTimePicker endTimePicker;
    @FXML
    private TextField durationTextField;
    @FXML
    private TextArea descriptionTextarea;
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
        initializePanel();
    }    
    
    private void initializePanel() {
        initComboBox();
        initPanelBinding();
    }
    
    private void initComboBox() {
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
    
    private void initPanelBinding() {
        editButton.disableProperty().bind(
                (companyComboBox.valueProperty().isNull())
                        .or(startDatePicker.valueProperty().isNull())
                        .or(startTimePicker.valueProperty().isNull())
                        .or(endDatePicker.valueProperty().isNull())
                        .or(endTimePicker.valueProperty().isNull())
        );
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
        //Get Calculated Values
        CompanyModel company = (CompanyModel) companyComboBox.getValue();
        Timestamp startTime = DateTimeUtilities.generateTimeStamp(startDatePicker.getValue(), startTimePicker.getValue());
        Timestamp endTime = DateTimeUtilities.generateTimeStamp(endDatePicker.getValue(), endTimePicker.getValue());      
               
        //Fill Out Model
        PhoneCallModel item = new PhoneCallModel();
        item.setActive(true);
        item.setCompanyid(company.getId());
        item.setCallstarttime(startTime);
        item.setCallendtime(endTime);
        item.setCalldescription(descriptionTextarea.getText().trim().equals("") ? null : descriptionTextarea.getText().trim());
    }
    
    private void insertInformation() {
        //Get Calculated Values
        CompanyModel company = (CompanyModel) companyComboBox.getValue();
        Timestamp startTime = DateTimeUtilities.generateTimeStamp(startDatePicker.getValue(), startTimePicker.getValue());
        Timestamp endTime = DateTimeUtilities.generateTimeStamp(endDatePicker.getValue(), endTimePicker.getValue());      
               
        //Fill Out Model
        PhoneCallModel item = new PhoneCallModel();
        item.setId(phoneCallObject.getId());
        item.setActive(phoneCallObject.isActive());
        item.setCompanyid(company.getId());
        item.setCallstarttime(startTime);
        item.setCallendtime(endTime);
        item.setCalldescription(descriptionTextarea.getText().trim().equals("") ? null : descriptionTextarea.getText().trim());
        
        SQLitePhoneCall.updatePhoneCallInfoByID(item);
    }
    
    private void setPanelInformationDisabled(boolean disabled) {
        companyComboBox.setDisable(disabled);
        startDatePicker.setDisable(disabled);
        endDatePicker.setDisable(disabled);
        startTimePicker.setDisable(disabled);
        endTimePicker.setDisable(disabled);
        descriptionTextarea.setDisable(disabled);
    }
    
}
