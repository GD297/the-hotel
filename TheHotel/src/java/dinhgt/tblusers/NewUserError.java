/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblusers;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class NewUserError implements Serializable{
    private String userErrorLength;
    private String userDuplicate;
    private String passErrorLength;
    private String cPassError;
    private String nameErrorLength;
    private String addressErrorLength;
    private String phoneErrorLength;

    /**
     * @return the userErrorLength
     */
    public String getUserErrorLength() {
        return userErrorLength;
    }

    /**
     * @param userErrorLength the userErrorLength to set
     */
    public void setUserErrorLength(String userErrorLength) {
        this.userErrorLength = userErrorLength;
    }

    /**
     * @return the passErrorLength
     */
    public String getPassErrorLength() {
        return passErrorLength;
    }

    /**
     * @param passErrorLength the passErrorLength to set
     */
    public void setPassErrorLength(String passErrorLength) {
        this.passErrorLength = passErrorLength;
    }

    /**
     * @return the cPassError
     */
    public String getcPassError() {
        return cPassError;
    }

    /**
     * @param cPassError the cPassError to set
     */
    public void setcPassError(String cPassError) {
        this.cPassError = cPassError;
    }

    /**
     * @return the nameErrorLength
     */
    public String getNameErrorLength() {
        return nameErrorLength;
    }

    /**
     * @param nameErrorLength the nameErrorLength to set
     */
    public void setNameErrorLength(String nameErrorLength) {
        this.nameErrorLength = nameErrorLength;
    }

    /**
     * @return the addressErrorLength
     */
    public String getAddressErrorLength() {
        return addressErrorLength;
    }

    /**
     * @param addressErrorLength the addressErrorLength to set
     */
    public void setAddressErrorLength(String addressErrorLength) {
        this.addressErrorLength = addressErrorLength;
    }

    /**
     * @return the phoneErrorLength
     */
    public String getPhoneErrorLength() {
        return phoneErrorLength;
    }

    /**
     * @param phoneErrorLength the phoneErrorLength to set
     */
    public void setPhoneErrorLength(String phoneErrorLength) {
        this.phoneErrorLength = phoneErrorLength;
    }

    /**
     * @return the userDuplicate
     */
    public String getUserDuplicate() {
        return userDuplicate;
    }

    /**
     * @param userDuplicate the userDuplicate to set
     */
    public void setUserDuplicate(String userDuplicate) {
        this.userDuplicate = userDuplicate;
    }
    
    
    
    
}
