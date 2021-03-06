/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tblusers.NewUserError;
import dinhgt.tblusers.TblUsersDAO;
import dinhgt.tbpcoderesetpass.TblCodeResetPassDAO;
import dinhgt.utils.SendMail;
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
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPasswordServlet"})
public class ForgotPasswordServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ForgotPasswordServlet.class);
    private final String SEND_COMPLETE = "sendMaiilCompletePage.html";
    private final String FIND_PASSWORD_PAGE = "findPassword.jsp";
    private final String LOGIN_PAGE = "loginPage1.html";

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
        String email = request.getParameter("txtEmail");
        String url = LOGIN_PAGE;
        try {
            TblUsersDAO userDAO = new TblUsersDAO();
            boolean result = userDAO.checkUsername(email);
            if (result) {
                String link = SendMail.sendResetPassword(email);
                TblCodeResetPassDAO resetDAO = new TblCodeResetPassDAO();
                result = resetDAO.storeResetCode(link, email);
                if (result) {
                    url = SEND_COMPLETE;
                }
            } else {
                NewUserError error = new NewUserError();
                error.setUserErrorLength("User does not exits!!");
                request.setAttribute("ERROR", error);
                url = FIND_PASSWORD_PAGE;
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
