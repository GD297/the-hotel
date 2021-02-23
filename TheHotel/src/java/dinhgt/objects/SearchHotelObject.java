/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.objects;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class SearchHotelObject implements Serializable{
    
    private String hotelID;
    private int availableRoom;
    private String hotelName;
    private String hotelAddress;

    public SearchHotelObject() {
    }

    public SearchHotelObject(String hotelID, int availableRoom, String hotelName, String hotelAddress) {
        this.hotelID = hotelID;
        this.availableRoom = availableRoom;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
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
     * @return the availableRoom
     */
    public int getAvailableRoom() {
        return availableRoom;
    }

    /**
     * @param availableRoom the availableRoom to set
     */
    public void setAvailableRoom(int availableRoom) {
        this.availableRoom = availableRoom;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @return the hotelAddress
     */
    public String getHotelAddress() {
        return hotelAddress;
    }

    /**
     * @param hotelAddress the hotelAddress to set
     */
    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }
    
}
