/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblhotel;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblHotelDTO implements Serializable{
    
    private String areaID;
    private String hotelID;
    private String name;
    private String address;
    private String status;

    public TblHotelDTO() {
    }

    public TblHotelDTO(String areaID, String hotelID, String name, String address, String status) {
        this.areaID = areaID;
        this.hotelID = hotelID;
        this.name = name;
        this.address = address;
        this.status = status;
    }
    
    

    /**
     * @return the areaID
     */
    public String getAreaID() {
        return areaID;
    }

    /**
     * @param areaID the areaID to set
     */
    public void setAreaID(String areaID) {
        this.areaID = areaID;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
