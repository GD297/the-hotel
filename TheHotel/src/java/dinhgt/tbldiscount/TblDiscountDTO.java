/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tbldiscount;

/**
 *
 * @author Admin
 */
public class TblDiscountDTO {
    private String discountID;
    private String name;
    private int discount;
    private String status;
    private String createDate;

    public TblDiscountDTO() {
    }

    public TblDiscountDTO(String discountID, String name, int discount, String status, String createDate) {
        this.discountID = discountID;
        this.name = name;
        this.discount = discount;
        this.status = status;
        this.createDate = createDate;
    }

    
    
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
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
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

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
}
