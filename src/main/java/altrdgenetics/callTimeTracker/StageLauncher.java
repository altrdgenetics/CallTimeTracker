/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker;

import altrdgenetics.callTimeTracker.model.sql.CompanyModel;
import altrdgenetics.callTimeTracker.sceneController.*;
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
public class StageLauncher {

    public void mainStage(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
            Global.setRoot((Parent) loader.load());
            MainWindowSceneController controller = loader.getController();
            
            Scene scene = new Scene(Global.getRoot());
            stage.setScene(scene);
                        
            controller.loadDefaults(stage);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(StageLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void companyMaintenaceStage(Stage stagePassed) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/CompanyMaintenanceScene.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(stagePassed);
            stage.setScene(scene);

            CompanyMaintenanceSceneController controller = loader.getController();
            controller.loadDefaults(stage);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StageLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void companyAddEditStage(Stage stagePassed, CompanyModel companyObjectPassed) {
        Stage stage = new Stage();
        try { 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/CompanyAddEditScene.fxml"));
            Scene scene = new Scene(loader.load());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(stagePassed);
            stage.setScene(scene);

            CompanyAddEditSceneController controller = loader.getController();
            controller.loadDefaults(stage, companyObjectPassed);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StageLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
