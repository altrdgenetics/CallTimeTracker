package altrdgenetics.callTimeTracker;

import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        Stages window = new Stages();
        window.mainStage(stage);
    }

}