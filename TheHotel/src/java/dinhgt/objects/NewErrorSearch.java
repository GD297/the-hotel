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
public class NewErrorSearch implements Serializable{
    
    private String errorAreaLength;
    private String errorHotelLength;
    private String errorCDateLength;
    private String errorODateLength;
    private String errorAmountLength;
    

    public NewErrorSearch() {
    }

    public NewErrorSearch(String errorAreaLength, String errorHotelLength, String errorCDateLength, String errorODateLength, String errorAmountLength) {
        this.errorAreaLength = errorAreaLength;
        this.errorHotelLength = errorHotelLength;
        this.errorCDateLength = errorCDateLength;
        this.errorODateLength = errorODateLength;
        this.errorAmountLength = errorAmountLength;
    }

    
    
    /**
     * @return the errorAreaLength
     */
    public String getErrorAreaLength() {
        return errorAreaLength;
    }

    /**
     * @param errorAreaLength the errorAreaLength to set
     */
    public void setErrorAreaLength(String errorAreaLength) {
        this.errorAreaLength = errorAreaLength;
    }

    /**
     * @return the errorHotelLength
     */
    public String getErrorHotelLength() {
        return errorHotelLength;
    }

    /**
     * @param errorHotelLength the errorHotelLength to set
     */
    public void setErrorHotelLength(String errorHotelLength) {
        this.errorHotelLength = errorHotelLength;
    }

    /**
     * @return the errorCDateLength
     */
    public String getErrorCDateLength() {
        return errorCDateLength;
    }

    /**
     * @param errorCDateLength the errorCDateLength to set
     */
    public void setErrorCDateLength(String errorCDateLength) {
        this.errorCDateLength = errorCDateLength;
    }

    /**
     * @return the errorODateLength
     */
    public String getErrorODateLength() {
        return errorODateLength;
    }

    /**
     * @param errorODateLength the errorODateLength to set
     */
    public void setErrorODateLength(String errorODateLength) {
        this.errorODateLength = errorODateLength;
    }

    /**
     * @return the errorAmountLength
     */
    public String getErrorAmountLength() {
        return errorAmountLength;
    }

    /**
     * @param errorAmountLength the errorAmountLength to set
     */
    public void setErrorAmountLength(String errorAmountLength) {
        this.errorAmountLength = errorAmountLength;
    }
    
    
    
    
}
