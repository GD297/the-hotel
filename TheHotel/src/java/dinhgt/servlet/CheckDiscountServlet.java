/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import static dinhgt.servlet.ActiveAccountServlet.LOGGER;
import dinhgt.tbldiscount.ErrorDiscount;
import dinhgt.tbldiscount.TblDiscountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckDiscountServlet", urlPatterns = {"/CheckDiscountServlet"})
public class CheckDiscountServlet extends HttpServlet {

    private final String VIEW_CART_PAGE = "viewCartPage.jsp";
    private final String LOGIN_PAGE = "loginPage.html";
    static final Logger LOGGER = Logger.getLogger(CheckDiscountServlet.class);

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
        String discount = request.getParameter("txtDiscount");
        ErrorDiscount error = new ErrorDiscount();
        boolean foundErr = false;
        String url = LOGIN_PAGE;
        try {
            if (discount.isEmpty()) {
                error.setDiscountID("Please enter code");
                foundErr = true;
            }
            if (foundErr) {
                request.setAttribute("ERROR", error);
                url = VIEW_CART_PAGE;;
            } else {
                TblDiscountDAO discountDAO = new TblDiscountDAO();
                int result = discountDAO.checkDiscount(discount);
                if (result > 0) {
                    request.setAttribute("DISCOUNT", result);
                    url = VIEW_CART_PAGE;
                } else {
                    error.setDiscountID("Error with discount ID");
                    request.setAttribute("ERROR", error);
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
