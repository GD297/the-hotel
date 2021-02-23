/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblorderdetail;

import dinhgt.objects.Item;
import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class TblOrderDetailDAO implements Serializable {

    private List<TblOrderDetailDTO> listDetails;

    public List<TblOrderDetailDTO> getListDetails() {
        return listDetails;
    }

    public void loadOrderDetail(String orderid, String userid) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT tbl_Order.orderID, tbl_Order.dateorder, tbl_OrderDetails.detailsID, quantity, datecheckin, datecheckout,  tbl_Hotel.name, tbl_Hotel.hotelID "
                    + "FROM tbl_OrderDetails "
                    + "INNER JOIN tbl_Order "
                    + "ON tbl_Order.orderID=tbl_OrderDetails.orderID "
                    + "INNER JOIN tbl_Hotel "
                    + "ON tbl_Hotel.hotelID=tbl_OrderDetails.hotelID "
                    + "WHERE tbl_Order.orderID=? AND tbl_Order.userID=? AND tbl_Order.status!=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, orderid);
            ps.setString(2, userid);
            ps.setString(3, "inActive");
            rs = ps.executeQuery();
            while (rs.next()) {
                String detailID = rs.getString("detailsID");
                int quantity = rs.getInt("quantity");
                String cDate = rs.getString("datecheckin");
                String oDate = rs.getString("datecheckout");
                String hotelName = rs.getString("name");
                String dateOrder = rs.getString("dateorder");
                String hotelID = rs.getString("hotelID");
                TblOrderDetailDTO detailDTO = new TblOrderDetailDTO(hotelID, detailID, orderid, dateOrder, cDate, oDate, hotelName, quantity);
                if (this.listDetails == null) {
                    this.listDetails = new ArrayList<>();
                }
                listDetails.add(detailDTO);
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

    public void loadOrderDetail(Date date, String userid) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT tbl_Order.orderID, tbl_Order.dateorder, tbl_OrderDetails.detailsID, quantity, datecheckin, datecheckout,  tbl_Hotel.name, tbl_Hotel.hotelID "
                    + "FROM tbl_OrderDetails "
                    + "INNER JOIN tbl_Order "
                    + "ON tbl_Order.orderID=tbl_OrderDetails.orderID "
                    + "INNER JOIN tbl_Hotel "
                    + "ON tbl_Hotel.hotelID=tbl_OrderDetails.hotelID "
                    + "WHERE tbl_Order.dateorder=? AND tbl_Order.userID=? AND tbl_Order.status!=?";
            ps = conn.prepareStatement(query);
            ps.setDate(1, date);
            ps.setString(2, userid);
            ps.setString(3, "inActive");
            rs = ps.executeQuery();
            while (rs.next()) {
                String detailID = rs.getString("detailsID");
                int quantity = rs.getInt("quantity");
                String cDate = rs.getString("datecheckin");
                String oDate = rs.getString("datecheckout");
                String hotelName = rs.getString("name");
                String orderID = rs.getString("orderID");
                String dateOrder = rs.getString("dateorder");
                String hotelID = rs.getString("hotelID");
                TblOrderDetailDTO detailDTO = new TblOrderDetailDTO(hotelID, detailID, orderID, dateOrder, cDate, oDate, hotelName, quantity);
                if (this.listDetails == null) {
                    this.listDetails = new ArrayList<>();
                }
                listDetails.add(detailDTO);
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

    public void loadOrderDetail(String orderID, Date date, String userid) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT tbl_Order.orderID, tbl_Order.dateorder, tbl_OrderDetails.detailsID, quantity, datecheckin, datecheckout,  tbl_Hotel.name, tbl_Hotel.hotelID "
                    + "FROM tbl_OrderDetails "
                    + "INNER JOIN tbl_Order "
                    + "ON tbl_Order.orderID=tbl_OrderDetails.orderID "
                    + "INNER JOIN tbl_Hotel "
                    + "ON tbl_Hotel.hotelID=tbl_OrderDetails.hotelID "
                    + "WHERE tbl_Order.orderID=? AND tbl_Order.dateorder=? AND tbl_Order.userID=? AND tbl_Order.status!=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, orderID);
            ps.setDate(2, date);
            ps.setString(3, userid);
            ps.setString(4, "inActive");
            rs = ps.executeQuery();
            while (rs.next()) {
                String detailID = rs.getString("detailsID");
                int quantity = rs.getInt("quantity");
                String cDate = rs.getString("datecheckin");
                String oDate = rs.getString("datecheckout");
                String hotelName = rs.getString("name");
                String dateOrder = rs.getString("dateorder");
                String hotelID = rs.getString("hotelID");
                TblOrderDetailDTO detailDTO = new TblOrderDetailDTO(hotelID, detailID, orderID, dateOrder, cDate, oDate, hotelName, quantity);
                if (this.listDetails == null) {
                    this.listDetails = new ArrayList<>();
                }
                listDetails.add(detailDTO);
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

    public boolean storeOrderDetail(int orderID, List<Item> listHotel) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_OrderDetails "
                    + "VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            for (Item item : listHotel) {
                ps.setInt(1, orderID);
                ps.setInt(2, item.getAmount());
                ps.setString(3, item.getcDate());
                ps.setString(4, item.getoDate());
                ps.setString(5, item.getHotelID());
                ps.addBatch();
            }
            int[] count = ps.executeBatch();
            conn.commit();
            for (int i : count) {
                if (i < 0) {
                    break;
                }
                ps.clearBatch();
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
