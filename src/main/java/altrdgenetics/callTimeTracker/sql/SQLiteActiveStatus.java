/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author User
 */
public class SQLiteActiveStatus {
    
    public static void setActive(String table, int id, boolean active) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE " + table + " SET active = ? WHERE id = ?";
            
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, active);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteCompany.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }
    
}
