/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblarea;

import dinhgt.objects.SearchAreaObject;
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
public class TblAreaDAO implements Serializable {

    private List<TblAreaDTO> listArea;
    private List<SearchAreaObject> listHotel;

    public List<TblAreaDTO> getListArea() {
        return listArea;
    }

    public List<SearchAreaObject> getListHotel() {
        return listHotel;
    }

    public void searhHotelInArea(String area, String checkin, String checkout, int amount) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT hotelID, name, address, (\n"
                    + "SELECT \n"
                    + "sum(quantity) - ISNULL((\n"
                    + "SELECT Sum(quantity) \n"
                    + "FROm tbl_OrderDetails \n"
                    + "WHERE datecheckin >= ? AND datecheckout <= ? and  tbl_OrderDetails.hotelID=tbl_Room.hotelID),0)\n"
                    + "FROM tbl_Room\n"
                    + "WHERE status = 'Active' AND tbl_Hotel.hotelID=tbl_Room.hotelID\n"
                    + "GROUP BY hotelID) AS TotalRoom\n"
                    + "FROM tbl_Hotel\n"
                    + "WHERE	areaID = ?\n"
                    + "AND status = 'Active'\n"
                    + "AND		(\n"
                    + "SELECT \n"
                    + "sum(quantity) - ISNULL((\n"
                    + "SELECT Sum(quantity) \n"
                    + "FROm tbl_OrderDetails\n"
                    + "WHERE datecheckin >= ? AND datecheckout <= ? and tbl_OrderDetails.hotelID=tbl_Room.hotelID),0)\n"
                    + "FROM tbl_Room\n"
                    + "WHERE status = 'Active' AND tbl_Hotel.hotelID=tbl_Room.hotelID\n"
                    + "GROUP BY hotelID) > ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, checkin);
            ps.setString(2, checkout);
            ps.setString(3, area);
            ps.setString(4, checkin);
            ps.setString(5, checkout);
            ps.setInt(6, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hotelID = rs.getString("hotelID");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String totalRoom = rs.getString("totalRoom");
                SearchAreaObject searchResult = new SearchAreaObject(hotelID, name, address, totalRoom);
                if (this.listHotel == null) {
                    this.listHotel = new ArrayList<>();
                }
                listHotel.add(searchResult);
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

    public void loadArea() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT [name], areaID "
                    + "FROM tbl_Area";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String areaID = rs.getString("areaID");
                String nameArea = rs.getString("name");
                TblAreaDTO areaDTO = new TblAreaDTO(areaID, nameArea);
                if (this.listArea == null) {
                    this.listArea = new ArrayList<>();
                }
                listArea.add(areaDTO);
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

    public void loadAreaAvailableRoom() throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT [name], areaID "
                    + "FROM tbl_Area "
                    + "WHERE areaID IN ("
                    + "SELECT areaID "
                    + "FROM tbl_Hotel "
                    + "WHERE hotelID IN ("
                    + "SELECT COUNT(roomAvailable) "
                    + "FROM tbl_Room "
                    + "WHERE roomAvailable > 0))";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String areaID = rs.getString("areaID");
                String nameArea = rs.getString("name");
                TblAreaDTO areaDTO = new TblAreaDTO(areaID, nameArea);
                if (this.listArea == null) {
                    this.listArea = new ArrayList<>();
                }
                listArea.add(areaDTO);
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
