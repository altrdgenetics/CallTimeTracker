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
import java.net.URL;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

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
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Spinner<Integer> startTimeHourSpinner;
    @FXML
    private Spinner<Integer> startTimeMinuteSpinner;
    @FXML
    private Spinner<Integer> startTimeSecondSpinner;
    @FXML
    private Spinner<Integer> endTimeHourSpinner;
    @FXML
    private Spinner<Integer> endTimeMinuteSpinner;
    @FXML
    private Spinner<Integer> endTimeSecondSpinner;
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
        spinnerFormatter();
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
    
    private void spinnerFormatter() {
        NumberFormat format = NumberFormat.getIntegerInstance();
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.isContentChange()) {
                ParsePosition parsePosition = new ParsePosition(0);
                // NumberFormat evaluates the beginning of the text
                format.parse(c.getControlNewText(), parsePosition);
                if (parsePosition.getIndex() == 0
                        || parsePosition.getIndex() < c.getControlNewText().length()) {
                    // reject parsing the complete text failed
                    return null;
                }
            }
            return c;
        };
        
        startTimeHourSpinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, filter));
        startTimeMinuteSpinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, filter));
        startTimeSecondSpinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, filter));
        endTimeHourSpinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, filter));
        endTimeMinuteSpinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, filter));
        endTimeSecondSpinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, filter));
    }
    
    private void initPanelBinding() {
        editButton.disableProperty().bind(
                (companyComboBox.valueProperty().isNull())
                        .or(startDatePicker.valueProperty().isNull())
                        .or(startTimeHourSpinner.valueProperty().isNull())
                        .or(startTimeMinuteSpinner.valueProperty().isNull())
                        .or(startTimeSecondSpinner.valueProperty().isNull())
                        .or(endDatePicker.valueProperty().isNull())
                        .or(endTimeHourSpinner.valueProperty().isNull())
                        .or(endTimeMinuteSpinner.valueProperty().isNull())
                        .or(endTimeSecondSpinner.valueProperty().isNull())
        );

        startDatePicker.setOnAction((e) -> {
            endDatePicker.setValue(startDatePicker.getValue());
            setDurationTextfield();
        });
        
    }
    
    public void loadDefaults(Stage stagePassed, PhoneCallModel companyObjectPassed){
        stage = stagePassed;
        phoneCallObject = companyObjectPassed;
        String title = "Add Phone Call";
        String buttonText = "Add";
        loadCompanyComboBox();
                
        if (phoneCallObject != null){
            title = "Detailed Phone Call";
            buttonText = "Update";
            loadInformation();
            setPanelInformationDisabled(true);
        }
        
        stage.setTitle(title);
        headerLabel.setText(title);
        editButton.setText(buttonText);
    }
    
    private void loadCompanyComboBox() {
        List<CompanyModel> list = SQLiteCompany.getActiveCompanies();
        
        companyComboBox.getItems().clear();
        
        if (!list.isEmpty()){
            for (CompanyModel item : list){
                companyComboBox.getItems().addAll(item);
            }
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
                editButton.setText("Save");
                setPanelInformationDisabled(false);
                break;
            default:
                break;
        }
    }
    
    private void loadInformation() {       
        CompanyModel company = new CompanyModel();
        company.setId(phoneCallObject.getCompanyid());
        company.setActive(phoneCallObject.isActive());
        company.setName(phoneCallObject.getCompanyname());
        
        companyComboBox.setValue(company);
        startDatePicker.setValue(phoneCallObject.getCallstarttime().toLocalDateTime().toLocalDate());
        startTimeHourSpinner.getValueFactory().setValue(phoneCallObject.getCallstarttime().toLocalDateTime().toLocalTime().getHour());
        startTimeMinuteSpinner.getValueFactory().setValue(phoneCallObject.getCallstarttime().toLocalDateTime().toLocalTime().getMinute());
        startTimeSecondSpinner.getValueFactory().setValue(phoneCallObject.getCallstarttime().toLocalDateTime().toLocalTime().getSecond());
        endDatePicker.setValue(phoneCallObject.getCallendtime().toLocalDateTime().toLocalDate());
        endTimeHourSpinner.getValueFactory().setValue(phoneCallObject.getCallendtime().toLocalDateTime().toLocalTime().getHour());
        endTimeMinuteSpinner.getValueFactory().setValue(phoneCallObject.getCallendtime().toLocalDateTime().toLocalTime().getMinute());
        endTimeSecondSpinner.getValueFactory().setValue(phoneCallObject.getCallendtime().toLocalDateTime().toLocalTime().getSecond());
        descriptionTextarea.setText(phoneCallObject.getCalldescription() == null ? "" : phoneCallObject.getCalldescription().trim());
        setDurationTextfield();
    }
    
    private void insertInformation() {
        //Get Calculated Values
        CompanyModel company = (CompanyModel) companyComboBox.getValue();
        Timestamp startTime = DateTimeUtilities.generateTimeStamp(startDatePicker.getValue(), 
                startTimeHourSpinner.getValue(), 
                startTimeMinuteSpinner.getValue(),
                startTimeSecondSpinner.getValue());
        Timestamp endTime = DateTimeUtilities.generateTimeStamp(endDatePicker.getValue(), 
                endTimeHourSpinner.getValue(), 
                endTimeMinuteSpinner.getValue(),
                endTimeSecondSpinner.getValue());     
               
        //Fill Out Model
        PhoneCallModel item = new PhoneCallModel();
        item.setActive(true);
        item.setCompanyid(company.getId());
        item.setCallstarttime(startTime);
        item.setCallendtime(endTime);
        item.setCalldescription(descriptionTextarea.getText().trim().equals("") ? null : descriptionTextarea.getText().trim());
        
        SQLitePhoneCall.insertPhoneCall(item);
    }
    
    private void updateInformation() {
        //Get Calculated Values
        CompanyModel company = (CompanyModel) companyComboBox.getValue();
        Timestamp startTime = DateTimeUtilities.generateTimeStamp(startDatePicker.getValue(), 
                startTimeHourSpinner.getValue(), 
                startTimeMinuteSpinner.getValue(),
                startTimeSecondSpinner.getValue());
        Timestamp endTime = DateTimeUtilities.generateTimeStamp(endDatePicker.getValue(), 
                endTimeHourSpinner.getValue(), 
                endTimeMinuteSpinner.getValue(),
                endTimeSecondSpinner.getValue());  
               
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
        startTimeHourSpinner.setDisable(disabled);
        startTimeMinuteSpinner.setDisable(disabled);
        startTimeSecondSpinner.setDisable(disabled);
        endDatePicker.setDisable(disabled);
        endTimeHourSpinner.setDisable(disabled);
        endTimeMinuteSpinner.setDisable(disabled);
        endTimeSecondSpinner.setDisable(disabled);
        descriptionTextarea.setDisable(disabled);
    }

    @FXML
    private void setDurationTextfield() {
        if (startDatePicker.getValue() != null
                && startTimeHourSpinner.getValue() != null
                && startTimeMinuteSpinner.getValue() != null
                && startTimeSecondSpinner.getValue() != null
                && endDatePicker.getValue() != null
                && endTimeHourSpinner.getValue() != null
                && endTimeMinuteSpinner.getValue() != null
                && endTimeSecondSpinner.getValue() != null) {
            Timestamp startTime = DateTimeUtilities.generateTimeStamp(startDatePicker.getValue(),
                    startTimeHourSpinner.getValue(),
                    startTimeMinuteSpinner.getValue(),
                    startTimeSecondSpinner.getValue());
            Timestamp endTime = DateTimeUtilities.generateTimeStamp(endDatePicker.getValue(),
                    endTimeHourSpinner.getValue(),
                    endTimeMinuteSpinner.getValue(),
                    endTimeSecondSpinner.getValue());
            long timeDiff = endTime.getTime() - startTime.getTime();

            durationTextField.setText(DateTimeUtilities.convertLongToTime(timeDiff));
        }
    }

}
