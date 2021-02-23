/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tblorderdetail.TblOrderDetailDAO;
import dinhgt.tblorderdetail.TblOrderDetailDTO;
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
@WebServlet(name = "LoadBookingDetailServlet", urlPatterns = {"/LoadBookingDetailServlet"})
public class LoadBookingDetailServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage1.html";
    private final String BOOKING_DETAILS = "detailBooking.jsp";
    private final String LOAD_ORDER_BY_DATE = "LoadBookingDateServlet";
    static final Logger LOGGER = Logger.getLogger(LoadBookingDetailServlet.class);

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
        String orderID = request.getParameter("txtOrderID");
        String date = request.getParameter("txtDate");
        List<TblOrderDetailDTO> listDetail;
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("USERID");
            TblOrderDetailDAO orderDAO = new TblOrderDetailDAO();
            if (orderID == null || orderID.isEmpty()) {
                url = LOAD_ORDER_BY_DATE;
            } else if (date.isEmpty()) {
                orderDAO.loadOrderDetail(orderID, userID);
                listDetail = orderDAO.getListDetails();
                request.setAttribute("LIST_DETAIL", listDetail);
                url = BOOKING_DETAILS;
            } else {
                Date orderDate = Date.valueOf(date);
                orderDAO.loadOrderDetail(orderID, orderDate, userID);
                listDetail = orderDAO.getListDetails();
                request.setAttribute("LIST_DETAIL", listDetail);
                url = BOOKING_DETAILS;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
