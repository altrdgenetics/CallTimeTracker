/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sql;

import altrdgenetics.callTimeTracker.Global;
import java.io.File;

/**
 *
 * @author User
 */
public class SQLiteConnection {
    
    private static final String url = "jdbc:sqlite:" + Global.getDBPath() + File.separator + Global.getDBName() + ".db";

    public static String getUrl() {
        return url;
    }
        
}
