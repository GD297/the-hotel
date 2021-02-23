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
public class Item implements Serializable{
    private String hotelID;
    private String name;
    private String category;
    private int amount;
    private double price;
    private String cDate;
    private String oDate;

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
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
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
    
    
}
