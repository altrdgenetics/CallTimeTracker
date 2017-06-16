/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sql;

import altrdgenetics.callTimeTracker.Global;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author User
 */
public class SQLiteTest {
    
    @Test
    public void testGetConnection() throws SQLException {
        Global.setDBPath("C:\\Users\\User\\Documents\\Coding Projects\\CallTimeTracker\\Target");
        try (Connection con = DriverManager.getConnection(SQLiteConnection.getUrl())) {
            Assert.assertNotNull(con);
            Assert.assertTrue(con.isValid(0));
        }
    }
}

