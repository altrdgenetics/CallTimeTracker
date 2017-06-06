/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker;

import altrdgenetics.callTimeTracker.sceneController.CompanyMaintenanceWindowController;
import altrdgenetics.callTimeTracker.sceneController.MainWindowSceneController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Stages {
    
    public void mainStage(Stage stage) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/MainWindow.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle("Call Time Tracker");
            stage.setScene(scene);
            
            // Set the persons into the controller.
            MainWindowSceneController controller = loader.getController();
            controller.loadDefaults(stage);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Stages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void companyMaintenaceStage(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/CompanyMaintenanceWindow.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Company Maintenance");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.show();

        } catch (IOException ex) {
            Logger.getLogger(Stages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
