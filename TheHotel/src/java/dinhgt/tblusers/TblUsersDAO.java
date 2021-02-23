/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblusers;

import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class TblUsersDAO implements Serializable {

    private TblUsersDTO userDTO;

    public TblUsersDTO getUserDTO() {
        return userDTO;
    }

    public boolean checkCodeLink(String code, String userid) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT userID, generateCode "
                    + "FROM tbl_Users "
                    + "WHERE userID=? AND generateCode=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, userid);
            ps.setString(2, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;

    }

    public boolean createGenerateCode(String code, String userid) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Users  "
                    + "SET generateCode=? "
                    + "WHERE userID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, code);
            ps.setString(2, userid);
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }

        } finally {

            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    public boolean activeAccount(String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Users  "
                    + "SET Status=? "
                    + "WHERE userID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "Active");
            ps.setString(2, username);
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }

        } finally {

            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    public boolean registerAccount(String username, String password, String name, String phone, String address, Date date, String status, String roleid) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Users "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setDate(6, date);
            ps.setString(7, roleid);
            ps.setString(8, status);
            ps.setString(9, null);
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;

    }

    public boolean checkUsername(String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT userID "
                    + "FROM tbl_Users "
                    + "WHERE userID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT userID, name, phone, address, createDate, roleID, status "
                    + "FROM tbl_Users "
                    + "WHERE userID=? AND password=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String userid = rs.getString("userID");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Date createDate = rs.getDate("createDate");
                String roleid = rs.getString("roleid");
                String status = rs.getString("status");
                userDTO = new TblUsersDTO(userid, name, phone, address, createDate, roleid, status);
                return true;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    public boolean updatePassword(String password, String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Users "
                    + "SET password=? "
                    + "WHERE userID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, username);
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

}
