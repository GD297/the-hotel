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
@WebServlet(name = "UpdatePasswordServlet", urlPatterns = {"/UpdatePasswordServlet"})
public class UpdatePasswordServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage1.html";
    private final String RESET_PASS_PAGE = "resetPassword.jsp";
    static final Logger LOGGER = Logger.getLogger(UpdatePasswordServlet.class);

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
        String password = request.getParameter("txtPassword");
        String cpassword = request.getParameter("txtComfirmPassword");
        String email = request.getParameter("txtEmail");
        boolean foundErr = false;
        NewUserError error = new NewUserError();
        String url = LOGIN_PAGE;
        try {
            if (!password.equals(cpassword)) {
                error.setcPassError("Confirm password not match!!");
                foundErr = true;
            }
            if (password.length() < 6) {
                error.setPassErrorLength("Password must be greater than 6 character!!");
            }

            if (foundErr) {
                url = RESET_PASS_PAGE;
                request.setAttribute("ERROR", error);
                request.setAttribute("USERNAME", email);
            } else {
                password = HashSHA265.startHash(password);
                TblUsersDAO userDAO = new TblUsersDAO();
                boolean result = userDAO.updatePassword(password, email);
                if (result) {
                    url = LOGIN_PAGE;
                }

            }
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.info("NoSuchAlgorithmException" + ex.getMessage());
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
