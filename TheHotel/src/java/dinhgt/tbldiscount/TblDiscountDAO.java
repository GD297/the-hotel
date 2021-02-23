/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tbldiscount;

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
public class TblDiscountDAO implements Serializable {

    private List<TblDiscountDTO> listDiscount;

    public List<TblDiscountDTO> getListDiscount() {
        return listDiscount;
    }

    public int checkDiscount(String discount) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT discountID, discount "
                    + "FROM tbl_Discount "
                    + "WHERE discountID=? AND status=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, discount);
            ps.setString(2, "Active");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("discount");
            }
        } finally {
            if(rs != null){
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

    public void loadAllDiscount() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT discountID, code, discount, status, datecreate "
                    + "FROM tbl_Discount ";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String discountID = rs.getString("discountID");
                String code = rs.getString("code");
                int discount = rs.getInt("discount");
                String status = rs.getString("status");
                String datecreate = rs.getString("datecreate");
                TblDiscountDTO discountDTO = new TblDiscountDTO(discountID, code, discount, status, datecreate);
                if (this.listDiscount == null) {
                    this.listDiscount = new ArrayList<>();
                }
                listDiscount.add(discountDTO);
            }

        } finally {
            if (rs != null) {
                rs.close();
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }

        }
    }

    public boolean createDiscount(String discountID, String name, String percent, Date date) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Discount "
                    + "VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, discountID);
            ps.setString(2, name);
            ps.setString(3, percent);
            ps.setString(4, "Active");
            ps.setDate(5, date);
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
