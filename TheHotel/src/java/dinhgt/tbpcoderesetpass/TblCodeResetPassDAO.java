/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tbpcoderesetpass;

import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class TblCodeResetPassDAO implements Serializable {

    static final Logger LOGGER = Logger.getLogger(TblCodeResetPassDAO.class);

    public boolean storeResetCode(String link, String email) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_CodeResetPass "
                    + "VALUES (?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, link);
            ps.setString(3, "Not Yet");

            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }
        }  finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return false;
    }

    public String findLinkCode(String link) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT Email "
                    + "FROM tbl_CodeResetPass "
                    + "WHERE Code=? AND Status=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, link);
            ps.setString(2, "Not Yet");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Email");
            }
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }

        }
        return null;
    }

    public boolean updateLinkCode(String link, String email) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_CodeResetPass "
                    + "SET Status=? "
                    + "WHERE Email=? AND Code=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "Active");
            ps.setString(2, email);
            ps.setString(3, link);

            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }
        }finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return false;
    }
}
