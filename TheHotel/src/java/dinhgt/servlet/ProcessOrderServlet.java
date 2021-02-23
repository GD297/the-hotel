/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.objects.Cart;
import dinhgt.objects.Item;
import dinhgt.tblorder.TblOrderDAO;
import dinhgt.tblorderdetail.TblOrderDetailDAO;
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
@WebServlet(name = "ProcessOrderServlet", urlPatterns = {"/ProcessOrderServlet"})
public class ProcessOrderServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage1.html";
    private final String SEND_CONFIRM_MAIL = "SendConfirmServlet";
    static final Logger LOGGER = Logger.getLogger(ProcessOrderServlet.class);

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
        String url = LOGIN_PAGE;
        String discount = request.getParameter("txtDiscount");
        String percent = request.getParameter("txtPercentDiscount");
        int orderid;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            String userid = (String) session.getAttribute("USERID");
            if (cart != null) {
                List<Item> listItem = cart.getItems();
                double totalCash = 0;
                for (Item item : listItem) {
                    totalCash += item.getPrice() * item.getAmount();
                }
                if (discount != null && !discount.isEmpty()) {
                    totalCash = totalCash - (totalCash * Integer.parseInt(percent) / 100);
                }
                long millis = System.currentTimeMillis();
                Date currentDate = new Date(millis);
                TblOrderDAO orderDAO = new TblOrderDAO();
                if (discount != null && !discount.isEmpty()) {
                    orderid = orderDAO.createNewOrder(userid, totalCash, currentDate, discount);
                } else {
                    orderid = orderDAO.createNewOrder(userid, totalCash, currentDate);
                }

                if (orderid > 0) {
                    TblOrderDetailDAO detailDAO = new TblOrderDetailDAO();
                    boolean result = detailDAO.storeOrderDetail(orderid, listItem);
                    if (result) {
                        request.setAttribute("ORDERID_NUMBER", String.valueOf(orderid));
                        url = SEND_CONFIRM_MAIL;
                    }
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
