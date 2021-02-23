/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.objects.Cart;
import dinhgt.objects.Item;
import dinhgt.objects.NewErrorCart;
import dinhgt.tblhotel.TblHotelDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage1.html";
    private final String VIEW_CART_PAGE = "viewCartPage.jsp";
    private final String PROCESS_ORDER = "ProcessOrderServlet";
    static final Logger LOGGER = Logger.getLogger(CheckOutServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean foundErr = false;
        NewErrorCart error = new NewErrorCart();
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            List<Item> listItem = cart.getItems();
            for (Item item : listItem) {
                String cDate = item.getcDate();
                String oDate = item.getoDate();
                if (cDate != null && oDate != null) {
                    
                    Date checkInDate = Date.valueOf(cDate);
                    Date checkOutDate = Date.valueOf(oDate);
                    
                    long millis = System.currentTimeMillis();
                    Date currentDate = new Date(millis);
                    if (checkInDate.before(currentDate)) {
                        error.setErrorDate("check in date must be after " + currentDate.toString());
                        foundErr = true;
                        break;
                    } else if (checkInDate.after(checkOutDate)) {
                        error.setErrorDate("check in date must be after check out date");
                        foundErr = true;
                        break;
                    }
                } else {
                    error.setErrorDate("Please enter Date for check in and check out date");
                    foundErr = true;
                    break;
                }
            }
            if (foundErr) {
                request.setAttribute("ERROR_HAPPEN", error);
                url = VIEW_CART_PAGE;
            } else {
                TblHotelDAO hotelDAO = new TblHotelDAO();
                for (Item item : listItem) {
                    int availableRoom = hotelDAO.loadQuantityRoom(item.getHotelID(), item.getName(), item.getcDate(), item.getoDate());
                    if (availableRoom < item.getAmount()) {
                        request.setAttribute("ERROR_HOTEL", item);
                        foundErr = true;
                        break;
                    }
                }
                if (foundErr) {
                    url = VIEW_CART_PAGE;
                } else {
                   url = PROCESS_ORDER;
                }

            }

        } catch (SQLException ex) {
            LOGGER.info("NamingException" + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
