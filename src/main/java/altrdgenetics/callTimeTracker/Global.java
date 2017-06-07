/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Global {
        
    private static String DBPath = "";
    private static final String DBName = "CallLogDB";
    private static Parent root;    
    private static Stage mainPanel;

    public static String getDBPath() {
        return DBPath;
    }

    public static void setDBPath(String DBPath) {
        Global.DBPath = DBPath;
    }

    public static String getDBName() {
        return DBName;
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Global.root = root;
    }

    public static Stage getMainPanel() {
        return mainPanel;
    }

    public static void setMainPanel(Stage mainPanel) {
        Global.mainPanel = mainPanel;
    }
        
}
