/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sql;

import altrdgenetics.callTimeTracker.Global;
import altrdgenetics.callTimeTracker.model.sql.PhoneCallModel;
import altrdgenetics.callTimeTracker.model.table.MainWindowTableModel;
import altrdgenetics.callTimeTracker.util.DateTimeUtilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author User
 */
public class SQLitePhoneCall {

    public static int insertPhoneCall(PhoneCallModel item) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO phonecall ("
                + "active, "
                + "companyid, "
                + "callstarttime, "
                + "callendtime, "
                + "calldescription"
                + ") VALUES ("
                + "?, " //1
                + "?, " //2
                + "?, " //3
                + "?, " //4
                + "?)"; //5
        try {
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean  (1, item.isActive());
            ps.setInt      (2, item.getCompanyid());
            ps.setTimestamp(3, item.getCallstarttime());
            ps.setTimestamp(4, item.getCallendtime());
            ps.setString   (5, item.getCalldescription());
            ps.executeUpdate();

            ResultSet newRow = ps.getGeneratedKeys();
            if (newRow.next()) {
                return newRow.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
        }
        return 0;
    }

    public static void updatePhoneCallInfoByID(PhoneCallModel item) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE phonecall SET "
                + "active = ?, "
                + "companyid = ?, "
                + "callstarttime = ?, "
                + "callendtime = ?, "
                + "calldescription = ? "
                + "WHERE id = ?";
        try {
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql);
            ps.setBoolean  (1, item.isActive());
            ps.setInt      (2, item.getCompanyid());
            ps.setTimestamp(3, item.getCallstarttime());
            ps.setTimestamp(4, item.getCallendtime());
            ps.setString   (5, item.getCalldescription());
            ps.setInt      (6, item.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public static ObservableList<MainWindowTableModel> loadActivePhoneCalls() {
        ObservableList<MainWindowTableModel> list = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT phonecall.*, company.name FROM phonecall "
                + "INNER JOIN company ON "
                + "phonecall.companyid = company.id "
                + "WHERE phonecall.active = 1";

        try {
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //create Object Model
                PhoneCallModel item = new PhoneCallModel();
                item.setId(rs.getInt("id"));
                item.setActive(rs.getBoolean("active"));
                item.setCompanyid(rs.getInt("companyid"));
                item.setCompanyname(rs.getString("name"));
                item.setCallstarttime(rs.getTimestamp("callstarttime"));
                item.setCallendtime(rs.getTimestamp("callendtime"));
                item.setCalldescription(rs.getString("calldescription"));

                long timeDiff = item.getCallendtime().getTime() - item.getCallstarttime().getTime();
                                
                list.add(
                        new MainWindowTableModel(
                                item, 
                                Global.getMmddyyyyhhmma().format(item.getCallstarttime()), 
                                item.getCompanyname(), 
                                item.getCalldescription(), 
                                DateTimeUtilities.convertLongToTime(timeDiff)
                        ));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return list;
    }
    
}
