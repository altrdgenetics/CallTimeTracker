/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class SQLite {
    
    public static void createNewDatabase(String filePath, String fileName) {
        String url = "jdbc:sqlite:" + filePath + File.separator + fileName + ".db";
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean checkDatabaseExists(String filePath, String fileName) {
        File dbFile = new File(filePath + File.separator + fileName + ".db");
        return dbFile.exists();
    }
}
