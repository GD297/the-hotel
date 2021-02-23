/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tblfeedback.TblFeedbackDAO;
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
@WebServlet(name = "StoreFeedBackServlet", urlPatterns = {"/StoreFeedBackServlet"})
public class StoreFeedBackServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(StoreFeedBackServlet.class);
    private final String LOGIN_PAGE = "loginPage1.html";
    private final String FEEDBACK_PAGE = "feedBackPage.jsp";
    private final String LOAD_HISTORY_CONSTRUCTOR = "LoadHistoryServlet";

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
        String context = request.getParameter("txtContent");
        String rate = request.getParameter("txtRate");
        String orderid = request.getParameter("txtOrderID");
        String date = request.getParameter("txtDate");
        String hotelID = request.getParameter("txtHotelID");
        String url = LOGIN_PAGE;
        try {
            Date checkOutDate = Date.valueOf(date);
            long millis = System.currentTimeMillis();
            Date currentDate = new Date(millis);
            if (!currentDate.before(checkOutDate)) {
                HttpSession session = request.getSession();
                String userid = (String) session.getAttribute("USERID");
                TblFeedbackDAO fbDAO = new TblFeedbackDAO();
                boolean result = fbDAO.storeFeedback(context, rate, orderid, userid, hotelID);
                if (result) {
                    url = LOAD_HISTORY_CONSTRUCTOR;
                }
            } else {
                request.setAttribute("ERROR", "You cannot feedback before Booking check out date");
                url = FEEDBACK_PAGE;
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
