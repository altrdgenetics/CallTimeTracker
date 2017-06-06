/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker;

import altrdgenetics.callTimeTracker.util.AlertDialog;
import altrdgenetics.callTimeTracker.util.FileUtilities;
import altrdgenetics.callTimeTracker.util.SQLite;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Stages {

    public void mainStage(Stage stage) {
        FileUtilities.setGlobalDBPath();

        if (!SQLite.checkDatabaseExists(Global.getDBPath(), Global.getDBName())) {
            boolean approved = AlertDialog.StaticAlert(1, "New Database",
                    "Database Connection Error",
                    "Unable to connect to the database. "
                    + "Would you like to create a new database.");
            if (approved) {
                SQLite.createNewDatabase(Global.getDBPath(), Global.getDBName());
            } else {
                System.exit(0);
            }
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("Call Time Tracker");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Stages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
