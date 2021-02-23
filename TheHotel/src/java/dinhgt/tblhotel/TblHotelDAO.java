/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblhotel;

import dinhgt.objects.SearchHotelObject;
import dinhgt.tblarea.TblAreaDTO;
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
public class TblHotelDAO implements Serializable {

    private List<TblHotelDTO> listHotel;
    private List<SearchHotelObject> listSearchResult;

    public List<TblHotelDTO> getHotelDTO() {
        return listHotel;
    }

    public List<SearchHotelObject> getListSearchResult() {
        return listSearchResult;
    }

    public void loadAllHotel() throws SQLException, NamingException{
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT hotelID, name, address, status, areaID "
                    + "FROM tbl_Hotel ";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String areaID = rs.getString("areaID");
                String hotelID = rs.getString("hotelID");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String status = rs.getString("status");
                TblHotelDTO hotelDTO = new TblHotelDTO(areaID, hotelID, name, address, status);
                if (this.listHotel == null) {
                    this.listHotel = new ArrayList<>();
                }
                listHotel.add(hotelDTO);
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
    
    public int loadQuantityRoom(String id, String hotelName, String checkInDate, String checkOutDate) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT  (\n"
                    + "SELECT sum(quantity) - ISNULL((\n"
                    + "SELECT Sum(quantity) \n"
                    + "FROm tbl_OrderDetails \n"
                    + "WHERE datecheckin >= ? AND datecheckout <= ? and  tbl_OrderDetails.hotelID=tbl_Room.hotelID),0)\n"
                    + "FROM tbl_Room\n"
                    + "WHERE status = 'Active' AND tbl_Hotel.hotelID=tbl_Room.hotelID\n"
                    + "GROUP BY hotelID) AS TotalRoom\n"
                    + "FROM tbl_Hotel\n"
                    + "WHERE	name=? AND status = 'Active' "
                    + "AND hotelID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, checkInDate);
            ps.setString(2, checkOutDate);
            ps.setString(3, hotelName);
            ps.setString(4, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalRoom");
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

    public void searchHotel(String hotelName, String checkindate, String checkoutdate, int amount) throws NamingException, SQLException {
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
                    + "WHERE	name LIKE ?\n"
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
            ps.setString(1, checkindate);
            ps.setString(2, checkoutdate);
            ps.setString(3, checkindate);
            ps.setString(4, checkoutdate);
            ps.setInt(5, amount);
            ps.setString(6, "%" + hotelName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String hotelID = rs.getString("hotelID");
                int availableRoom = rs.getInt("availableRoom");
                String name = rs.getString("HotelName");
                String HotelAddress = rs.getString("HotelAddress");
                SearchHotelObject resultSeach = new SearchHotelObject(hotelID, availableRoom, name, HotelAddress);
                if (this.listSearchResult == null) {
                    this.listSearchResult = new ArrayList<>();
                }
                listSearchResult.add(resultSeach);
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

    public void loadHotel(String areaID) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT hotelID, name, address, status "
                    + "FROM tbl_Hotel "
                    + "WHERE areaID=? and status=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, areaID);
            ps.setString(2, "Active");
            rs = ps.executeQuery();
            while (rs.next()) {
                String hotelID = rs.getString("hotelID");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String status = rs.getString("status");
                TblHotelDTO hotelDTO = new TblHotelDTO(areaID, hotelID, name, address, status);
                if (this.listHotel == null) {
                    this.listHotel = new ArrayList<>();
                }
                listHotel.add(hotelDTO);
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

    public void loadAvailableHotel(Date currentDate) throws NamingException, SQLException {
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
                    + "WHERE    datecheckin >= ?  AND tbl_OrderDetails.hotelID=tbl_Room.hotelID),0)\n"
                    + "FROM tbl_Room\n"
                    + "WHERE status = 'Active' AND tbl_Hotel.hotelID=tbl_Room.hotelID\n"
                    + "GROUP BY hotelID) AS TotalRoom\n"
                    + "FROM tbl_Hotel\n"
                    + "WHERE status = 'Active'\n"
                    + "AND		(\n"
                    + "SELECT \n"
                    + "sum(quantity) - ISNULL((\n"
                    + "SELECT Sum(quantity) \n"
                    + "FROm tbl_OrderDetails\n"
                    + "WHERE  tbl_OrderDetails.hotelID=tbl_Room.hotelID),0)\n"
                    + "FROM tbl_Room\n"
                    + "WHERE status = 'Active' AND tbl_Hotel.hotelID=tbl_Room.hotelID\n"
                    + "GROUP BY hotelID) > 5";
            ps = conn.prepareStatement(query);
            ps.setDate(1, currentDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String hotel = rs.getString("hotelID");
                String address = rs.getString("address");
                int TotalRoom = rs.getInt("TotalRoom");
                SearchHotelObject loadResult = new SearchHotelObject(hotel, TotalRoom, name, address);
                if (this.listSearchResult == null) {
                    this.listSearchResult = new ArrayList<>();
                }
                listSearchResult.add(loadResult);
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

    public void searchHotel(String hotelName, String area, String checkindate, String checkoutdate, int amount) throws SQLException, NamingException {
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
                    + "WHERE	name LIKE ? AND areaID = ? AND status = 'Active'\n"
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
            ps.setString(1, checkindate);
            ps.setString(2, checkoutdate);
            ps.setString(3, "%" + hotelName + "%");
            ps.setString(4, area);
            ps.setString(5, checkindate);
            ps.setString(6, checkoutdate);
            ps.setInt(7, amount);

            rs = ps.executeQuery();
            while (rs.next()) {
                String hotelID = rs.getString("hotelID");
                int availableRoom = rs.getInt("TotalRoom");
                String name = rs.getString("name");
                String HotelAddress = rs.getString("address");
                SearchHotelObject resultSeach = new SearchHotelObject(hotelID, availableRoom, name, HotelAddress);
                if (this.listSearchResult == null) {
                    this.listSearchResult = new ArrayList<>();
                }
                listSearchResult.add(resultSeach);
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
