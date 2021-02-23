/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.objects.ErrorOTPCode;
import dinhgt.tblotpcode.OTPCodeDAO;
import dinhgt.tblusers.TblUsersDAO;
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
@WebServlet(name = "ActiveAccountServlet", urlPatterns = {"/ActiveAccountServlet"})
public class ActiveAccountServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage1.html";
    private final String REGISTER_COMPLETE_PAGE = "registerComplete.html";
    private final String ACTIVE_ACCOUNT_PAGE = "activeAccount.jsp";
    static final Logger LOGGER = Logger.getLogger(ActiveAccountServlet.class);

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
        String otp = request.getParameter("txtOTPCode");
        String url = LOGIN_PAGE;
        try {
            String username = request.getParameter("txtEmail");
            OTPCodeDAO codeDAO = new OTPCodeDAO();
            boolean result = codeDAO.findOTPCode(otp, username);
            if (result) {
                TblUsersDAO userDAO = new TblUsersDAO();
                result = userDAO.activeAccount(username);
                if (result) {
                    url = REGISTER_COMPLETE_PAGE;
                }
            } else {
                ErrorOTPCode wrongCode = new ErrorOTPCode();
                wrongCode.setWrongOTPCode("Wrong otp code, please try again");
                request.setAttribute("ERROR", wrongCode);
                url = ACTIVE_ACCOUNT_PAGE;
            }

        } catch (SQLException ex) {
            LOGGER.info("SQLException" + ex.getMessage());
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
