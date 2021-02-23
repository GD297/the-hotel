/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tblusers.NewUserError;
import dinhgt.tblusers.TblUsersDAO;
import dinhgt.utils.HashSHA265;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);
    private final String LOGIN_PAGE = "loginPage1.html";
    private final String ERROR_FIELD_PAGE = "registerPage.jsp";
    private final String SEND_MAIL_CONSTRUCTOR = "SendMailServlet";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String cpassword = request.getParameter("txtConfirmPassword");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        String fullname = request.getParameter("txtFullname");
        boolean foundErr = false;
        boolean result;
        NewUserError error = new NewUserError();
        String url = LOGIN_PAGE;
        try {

            if (username.trim().length() < 8) {
                error.setUserErrorLength("Username must be greater than 8 character!!");
                foundErr = true;
            }
            if(!username.trim().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")){
                 error.setUserErrorLength("Please enter right format email");
                foundErr = true;
            }
            if (!password.equals(cpassword)) {
                error.setcPassError("Confirm password not match!!");
                foundErr = true;
            }
            if (password.length() < 6) {
                error.setPassErrorLength("Password must be greater than 6 character!!");
            }
            if (!phone.matches("0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                error.setPhoneErrorLength("Phone number must be start with 0 and 10 digit!!");
            }
            if (address.trim().length() < 10) {
                error.setAddressErrorLength("Please enter true address!!");
            }
            if (fullname.trim().length() < 10) {
                error.setNameErrorLength("Please enter your fullname");
            }
            if (foundErr) {
                url = ERROR_FIELD_PAGE;
                request.setAttribute("ERROR", error);
            } else {
                password = HashSHA265.startHash(password);
                TblUsersDAO userDAO = new TblUsersDAO();
                long millis = System.currentTimeMillis();
                Date currentDate = new Date(millis);
                result = userDAO.registerAccount(username, password, fullname, phone, address, currentDate, "NotActive", "2");
                if (result) {
                    url = SEND_MAIL_CONSTRUCTOR + "?txtEmail=" + username;
                }

            }

        } catch (SQLException ex) {
            String msgErr = ex.getMessage();
            if (msgErr.contains("duplicate")) {
                error.setUserDuplicate("Email dose exits!!!");
                request.setAttribute("ERROR", error);
                url = ERROR_FIELD_PAGE;
                LOGGER.info("SQLException" + ex.getMessage());
            } else {
                LOGGER.info("SQLException" + ex.getMessage());
            }

        } catch (NamingException ex) {
            LOGGER.info("NamingException" + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.info("NoSuchAlgorithmException" + ex.getMessage());
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
