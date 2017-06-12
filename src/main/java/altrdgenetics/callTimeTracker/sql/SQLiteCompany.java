/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.sql;

import altrdgenetics.callTimeTracker.model.sql.CompanyModel;
import altrdgenetics.callTimeTracker.model.table.CompanyMaintenanceTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author User
 */
public class SQLiteCompany {

    public static ObservableList<CompanyMaintenanceTableModel> searchCompanies(String[] param) {
        ObservableList<CompanyMaintenanceTableModel> list = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM company WHERE ";
        if (param.length > 0) {

            for (int i = 0; i < param.length; i++) {
                if (i > 0) {
                    sql += " AND";
                }
                sql += " name LIKE ?";
            }
        }
        try {
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < param.length; i++) {
                ps.setString((i + 1), "%" + param[i].trim() + "%");
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                CompanyModel item = new CompanyModel();
                item.setId(rs.getInt("id"));
                item.setActive(rs.getBoolean("active"));
                item.setName(rs.getString("name"));

                list.add(
                        new CompanyMaintenanceTableModel(
                                item,
                                rs.getBoolean("active"),
                                rs.getString("name")
                        ));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return list;
    }

    public static int insertCompany(CompanyModel item) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO company ("
                + "active, "
                + "name"
                + ") VALUES ("
                + "?, " //1
                + "?)";
        try {
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, item.isActive());
            ps.setString(2, item.getName());
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

    public static void updateCompanyInfoByID(CompanyModel item) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE company SET "
                + "active = ?, "
                + "name = ? "
                + "WHERE id = ?";
        try {
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, item.isActive());
            ps.setString (2, item.getName());
            ps.setInt    (3, item.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public static List<CompanyModel> getActiveCompanies() {
        List<CompanyModel> list = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM company WHERE active = 1";

        try {
            conn = DriverManager.getConnection(SQLiteConnection.getUrl());
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompanyModel item = new CompanyModel();
                item.setId(rs.getInt("id"));
                item.setActive(rs.getBoolean("active"));
                item.setName(rs.getString("name"));
                list.add(item);
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
