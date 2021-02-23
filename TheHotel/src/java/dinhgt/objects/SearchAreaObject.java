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
public class SearchAreaObject implements Serializable{
    
    private String hotelID;
    private String nameHotel;
    private String address;
    private String totalRoom;

    public SearchAreaObject() {
    }

    public SearchAreaObject(String hotelID, String nameHotel, String address, String totalRoom) {
        this.hotelID = hotelID;
        this.nameHotel = nameHotel;
        this.address = address;
        this.totalRoom = totalRoom;
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
     * @return the nameHotel
     */
    public String getNameHotel() {
        return nameHotel;
    }

    /**
     * @param nameHotel the nameHotel to set
     */
    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the totalRoom
     */
    public String getTotalRoom() {
        return totalRoom;
    }

    /**
     * @param totalRoom the totalRoom to set
     */
    public void setTotalRoom(String totalRoom) {
        this.totalRoom = totalRoom;
    }
    
    
}
