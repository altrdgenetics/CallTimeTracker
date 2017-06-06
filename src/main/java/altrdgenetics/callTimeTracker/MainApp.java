package altrdgenetics.callTimeTracker;

import altrdgenetics.callTimeTracker.sql.SQLite;
import altrdgenetics.callTimeTracker.util.AlertDialog;
import altrdgenetics.callTimeTracker.util.FileUtilities;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        boolean approved = true;
        FileUtilities.setGlobalDBPath();

        //Setup the Database
        if (!SQLite.checkDatabaseExists()) {
            approved = AlertDialog.StaticAlert(1, "New Database",
                    "Database Connection Error",
                    "Unable to connect to the database. "
                    + "Would you like to create a new database.");
            if (approved) {
                SQLite.createNewDatabase();
                
            } else {
                System.exit(0);
            }
        }
        
        //Run the Application
        if (approved){
            Stages window = new Stages();
            window.mainStage(stage);
        }
    }

}