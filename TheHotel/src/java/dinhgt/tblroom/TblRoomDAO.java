/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblroom;

import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
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
public class TblRoomDAO implements Serializable {

    private List<TblRoomDTO> listRoomDTO;

    public List<TblRoomDTO> getListRoomDTO() {
        return listRoomDTO;
    }

    public void loadHotelDetails(String hotelID) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT descriptioin, \n"
                    + "quantity, \n"
                    + "price,\n"
                    + "(SELECT type FROM tbl_Category WHERE tbl_Category.categoryID=tbl_Room.categoryID ) as Catagory\n"
                    + "FROM tbl_Room\n"
                    + "WHERE hotelID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, hotelID);

            rs = ps.executeQuery();
            while (rs.next()) {

                String descrip = rs.getString("descriptioin");
                String category = rs.getString("Catagory");
                int address = rs.getInt("quantity");
                double price = rs.getDouble("price");
                TblRoomDTO roomDTO = new TblRoomDTO(descrip, address, price, category);
                if (this.listRoomDTO == null) {
                    this.listRoomDTO = new ArrayList<>();
                }
                listRoomDTO.add(roomDTO);
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

    public void searchRoom(String area, String hotelid, String checkin, String checkout, int amount) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT sum(quantity) - ISNULL(("
                    + "SELECT Sum(quantity)"
                    + "FROm tbl_OrderDetails "
                    + "WHERE datecheckin=? and orderID in ("
                    + "SELECT orderID "
                    + "FROM tbl_Order"
                    + "WHERE tbl_Order.hotelID=tbl_Room.hotelID)),0)as availableRoom"
                    + "FROM tbl_Room"
                    + "WHERE status =? AND hotelID=?"
                    + "GROUP BY hotelID";
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

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

}
