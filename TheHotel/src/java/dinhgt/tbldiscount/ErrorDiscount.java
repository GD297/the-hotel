/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tbldiscount;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class ErrorDiscount implements Serializable{
    private String discountID;
    private String name;
    private String percent;

    
    /**
     * @return the discountID
     */
    public String getDiscountID() {
        return discountID;
    }

    /**
     * @param discountID the discountID to set
     */
    public void setDiscountID(String discountID) {
        this.discountID = discountID;
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
     * @return the percent
     */
    public String getPercent() {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(String percent) {
        this.percent = percent;
    }
    
    
}
