/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblarea;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblAreaDTO implements Serializable{
    private String areaID;
    private String nameArea;

    public TblAreaDTO() {
    }

    public TblAreaDTO(String areaID, String nameArea) {
        this.areaID = areaID;
        this.nameArea = nameArea;
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
     * @return the nameArea
     */
    public String getNameArea() {
        return nameArea;
    }

    /**
     * @param nameArea the nameArea to set
     */
    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }
    
    
}
