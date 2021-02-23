/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblfeedback;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblFeedbackDTO implements Serializable{
    private String content;
    private int rate;
    private String hotelID;
    private String userID;

    public TblFeedbackDTO() {
    }

    public TblFeedbackDTO(String content, int rate, String hotelID, String userID) {
        this.content = content;
        this.rate = rate;
        this.hotelID = hotelID;
        this.userID = userID;
    }

    
    
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return the hotelID
     */
    public String getHotelID() {
        return hotelID;
    }

    /**
     * @param hotelID the hotelID to set
     */
    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
