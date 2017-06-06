/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sql;

import altrdgenetics.callTimeTracker.Global;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class SQLite {
    
    public static boolean checkDatabaseExists() {
        File dbFile = new File(Global.getDBPath() + File.separator + Global.getDBName() + ".db");
        return dbFile.exists();
    }
    
    public static void createNewDatabase() { 
        try (Connection conn = DriverManager.getConnection(SQLiteConnection.getUrl())) {
            if (conn != null) {
                SQLiteCreateTables.createTables();
                System.out.println("The new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
