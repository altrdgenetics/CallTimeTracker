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
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author User
 */
public class SQLiteCreateTables {

    public static void createTables() {
        Connection conn = null;
        PreparedStatement ps = null;

        //companies
        String sql = "CREATE TABLE IF NOT EXISTS company ("
                + "id integer PRIMARY KEY,"
                + "active boolean NOT NULL,"
                + "name text NOT NULL"
                + ");";
        
        //calls
        sql += " CREATE TABLE IF NOT EXISTS phonecall ("
                + "id integer PRIMARY KEY,"
                + "companyid integer NOT NULL,"
                + "callstarttime DATETIME NOT NULL,"
                + "callendtime DATETIME NOT NULL,"
                + "calldescription text NULL"
                + ");";

        try {
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
        }
    }

}
