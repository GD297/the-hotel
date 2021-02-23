/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tbldiscount.ErrorDiscount;
import dinhgt.tbldiscount.TblDiscountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
@WebServlet(name = "CreateDiscountServlet", urlPatterns = {"/CreateDiscountServlet"})
public class CreateDiscountServlet extends HttpServlet {

    private final String CREATE_DISCOUNT_PAGE = "createDiscount.jsp";
    private final String LOGIN_PAGE = "loginPage1.html";
    private final String LOAD_ALL_DISCOUNT = "LoadAllDiscountServlet";
    static final Logger LOGGER = Logger.getLogger(CreateDiscountServlet.class);

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
        String discountID = request.getParameter("txtDiscountID");
        String name = request.getParameter("txtDiscountName");
        String percent = request.getParameter("txtPercent");
        ErrorDiscount error = new ErrorDiscount();
        boolean foundErr = false;
        String url = LOGIN_PAGE;
        try {
            if (discountID.trim().length() < 6) {
                error.setDiscountID("Please enter discountID greater than 6");
                foundErr = true;
            }

            if (name.trim().length() < 6) {
                error.setName("Please enter discountID greater than 6");
                foundErr = true;
            }
            try {
                int tempPercent = Integer.parseInt(percent);
                if (tempPercent < 0 || tempPercent > 100) {
                    error.setPercent("Please enter percent greater than 0 and less than 100");
                    foundErr = true;
                }
            } catch (Exception e) {
                error.setPercent("Please enter number for percent");
                foundErr = true;
            }
            if (foundErr) {
                request.setAttribute("ERROR_HAPPEN", error);
                url = CREATE_DISCOUNT_PAGE;
            } else {
                HttpSession session = request.getSession();
                String roleID = (String) session.getAttribute("ROLE_USER");
                if (roleID.equals("1")) {
                    TblDiscountDAO discountDAO = new TblDiscountDAO();
                    long millis = System.currentTimeMillis();
                    Date currentDate = new Date(millis);
                    boolean result = discountDAO.createDiscount(discountID, name, percent, currentDate);
                    if (result) {
                        url = LOAD_ALL_DISCOUNT;
                    }
                }
            }

        } catch (SQLException ex) {
            String msgErr = ex.getMessage();
            if (msgErr.contains("duplicate")) {
                error.setDiscountID("discount id dose exits!!!");
                request.setAttribute("ERROR", error);
                url = CREATE_DISCOUNT_PAGE;

            } else {
                LOGGER.info("NamingException" + ex.getMessage());
            }
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
