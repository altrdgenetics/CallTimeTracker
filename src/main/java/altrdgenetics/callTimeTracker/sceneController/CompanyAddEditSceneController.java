/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sceneController;

import altrdgenetics.callTimeTracker.Global;
import altrdgenetics.callTimeTracker.model.CompanyModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CompanyAddEditSceneController implements Initializable {

    Stage stage;
    CompanyModel companyObject;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loadDefaults(Stage stagePassed, CompanyModel companyObjectPassed){
        stage = stagePassed;
        companyObject = companyObjectPassed;
        if (companyObject == null){
            stage.setTitle("Add Company");
        } else {
            stage.setTitle("Edit Company");
        }
    }
    
}
