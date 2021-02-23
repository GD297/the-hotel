/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblorder;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class TblOrderDTO {
    
    private int orderid;
    private double totalPrice;
    private Date dateorder;

    public TblOrderDTO() {
    }

    public TblOrderDTO(int orderid, double totalPrice, Date dateorder) {
        this.orderid = orderid;
        this.totalPrice = totalPrice;
        this.dateorder = dateorder;
    }

    
    
    /**
     * @return the orderid
     */
    public int getOrderid() {
        return orderid;
    }

    /**
     * @param orderid the orderid to set
     */
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the dateorder
     */
    public Date getDateorder() {
        return dateorder;
    }

    /**
     * @param dateorder the dateorder to set
     */
    public void setDateorder(Date dateorder) {
        this.dateorder = dateorder;
    }
    
    
}
