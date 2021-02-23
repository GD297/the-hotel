/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblorderdetail;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblOrderDetailDTO implements Serializable{
    
    private String hotelID;
    private String detailID;
    private String orderID;
    private String dateOrder;
    private String cDate;
    private String oDate;
    private String hotelName;
    private int quantity;

    public TblOrderDetailDTO() {
    }

    public TblOrderDetailDTO(String hotelID, String detailID, String orderID, String dateOrder, String cDate, String oDate, String hotelName, int quantity) {
        this.hotelID = hotelID;
        this.detailID = detailID;
        this.orderID = orderID;
        this.dateOrder = dateOrder;
        this.cDate = cDate;
        this.oDate = oDate;
        this.hotelName = hotelName;
        this.quantity = quantity;
    }

 
 
    
    
    /**
     * @return the detailID
     */
    public String getDetailID() {
        return detailID;
    }

    /**
     * @param detailID the detailID to set
     */
    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    /**
     * @return the cDate
     */
    public String getcDate() {
        return cDate;
    }

    /**
     * @param cDate the cDate to set
     */
    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    /**
     * @return the oDate
     */
    public String getoDate() {
        return oDate;
    }

    /**
     * @param oDate the oDate to set
     */
    public void setoDate(String oDate) {
        this.oDate = oDate;
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
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the dateOrder
     */
    public String getDateOrder() {
        return dateOrder;
    }

    /**
     * @param dateOrder the dateOrder to set
     */
    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
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
    
    
    
    
}
