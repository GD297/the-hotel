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
public class ErrorOTPCode implements Serializable{
    private String wrongOTPCode;

    /**
     * @return the wrongOTPCode
     */
    public String getWrongOTPCode() {
        return wrongOTPCode;
    }

    /**
     * @param wrongOTPCode the wrongOTPCode to set
     */
    public void setWrongOTPCode(String wrongOTPCode) {
        this.wrongOTPCode = wrongOTPCode;
    }
    
    
}
