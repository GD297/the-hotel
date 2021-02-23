/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblfeedback;

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
public class TblFeedbackDAO implements Serializable {
    private List<TblFeedbackDTO> listFb;

    public List<TblFeedbackDTO> getListFb() {
        return listFb;
    }
    
    public void loadFeedback(String hotelID) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT content, rate, userid "
                    + "FROM tbl_Feedback "
                    + "WHERE hotelID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, hotelID);
            rs = ps.executeQuery();
            while(rs.next()){
                String content = rs.getString("content");
                int rate = rs.getInt("rate");
                String userID = rs.getString("userID");
                TblFeedbackDTO fbDTO = new TblFeedbackDTO(content, rate, hotelID, userID);
                if(this.listFb == null){
                    this.listFb = new ArrayList<>();
                }
                listFb.add(fbDTO);
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

    public boolean storeFeedback(String content, String rate, String orderID, String userid, String hotelID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Feedback "
                    + "VALUES (?,?,?,?,?) ";
            ps = conn.prepareStatement(query);
            ps.setString(1, content);
            ps.setString(2, rate);
            ps.setString(3, orderID);
            ps.setString(4, userid);
            ps.setString(5, hotelID);
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
