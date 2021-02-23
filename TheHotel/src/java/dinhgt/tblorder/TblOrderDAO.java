/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblorder;

import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class TblOrderDAO implements Serializable {

    private List<TblOrderDTO> listOrder;

    public List<TblOrderDTO> getListOrder() {
        return listOrder;
    }

    public boolean updateOrderBooking(String userID, String orderID, Date date) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Order "
                    + "SET status=? "
                    + "WHERE userID=? AND orderID=? AND dateorder=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "inActive");
            ps.setString(2, userID);
            ps.setString(3, orderID);
            ps.setDate(4, date);
            int count = ps.executeUpdate();
            if(count > 0){
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

    public void loadOrder(String userid) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT orderID, totalprice, dateorder "
                    + "FROM tbl_Order "
                    + "WHERE userID=? AND status!=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, userid);
            ps.setString(2, "inActive");
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                double totalPrice = rs.getDouble("totalprice");
                Date dateorder = rs.getDate("dateorder");
                TblOrderDTO orderDTO = new TblOrderDTO(orderID, totalPrice, dateorder);
                if (this.listOrder == null) {
                    this.listOrder = new ArrayList<>();
                }
                listOrder.add(orderDTO);
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

    }

    public void loadOrder(Date date, String userid) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT orderID, totalprice, dateorder "
                    + "FROM tbl_Order "
                    + "WHERE dateorder=? AND status!=? AND userID=?";
            ps = conn.prepareStatement(query);
            ps.setDate(1, date);
            ps.setString(2, "inActive");
            ps.setString(3, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                double totalPrice = rs.getDouble("totalprice");
                Date dateorder = rs.getDate("dateorder");
                TblOrderDTO orderDTO = new TblOrderDTO(orderID, totalPrice, dateorder);
                if (this.listOrder == null) {
                    this.listOrder = new ArrayList<>();
                }
                listOrder.add(orderDTO);
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

    }

    public boolean updateOrderStatus(String userid, String orderid) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Order "
                    + "SET status=? "
                    + "WHERE orderID=? AND userID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "Confirm");
            ps.setString(2, orderid);
            ps.setString(3, userid);
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

    public int createNewOrder(String userID, double totalPrice, Date date, String discount) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Order "
                    + "VALUES (?,?,?,?,?,?) "
                    + "SELECT SCOPE_IDENTITY() as orderid";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userID);
            ps.setDouble(2, totalPrice);
            ps.setDate(3, date);
            ps.setString(4, "Pending");
            ps.setString(5, null);
            ps.setString(6, discount);
            int count = ps.executeUpdate();
            if (count > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
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

        return 0;

    }
    
    public int createNewOrder(String userID, double totalPrice, Date date) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Order "
                    + "VALUES (?,?,?,?,?,?) "
                    + "SELECT SCOPE_IDENTITY() as orderid";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userID);
            ps.setDouble(2, totalPrice);
            ps.setDate(3, date);
            ps.setString(4, "Pending");
            ps.setString(5, null);
            ps.setString(6, null);
            int count = ps.executeUpdate();
            if (count > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
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

        return 0;

    }

}
