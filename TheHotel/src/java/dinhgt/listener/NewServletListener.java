/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.listener;

import dinhgt.tblarea.TblAreaDAO;
import dinhgt.tblarea.TblAreaDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class NewServletListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        try {
            TblAreaDAO areaDAO = new TblAreaDAO();
            areaDAO.loadArea();
            List<TblAreaDTO> listArea = areaDAO.getListArea();
            sre.getServletRequest().setAttribute("LIST_AREA", listArea);
        } catch (SQLException ex) {
            Logger.getLogger(NewServletListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(NewServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
