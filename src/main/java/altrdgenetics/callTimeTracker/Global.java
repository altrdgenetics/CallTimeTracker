/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker;

/**
 *
 * @author User
 */
public class Global {
    
    
    private static String DBPath = "";
    private static final String DBName = "CallLogDB";

    public static String getDBPath() {
        return DBPath;
    }

    public static void setDBPath(String DBPath) {
        Global.DBPath = DBPath;
    }

    public static String getDBName() {
        return DBName;
    }
    
}
