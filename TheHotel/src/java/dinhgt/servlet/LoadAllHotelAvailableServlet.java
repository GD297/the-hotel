/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.objects.SearchHotelObject;
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
@WebServlet(name = "LoadAllHotelAvailableServlet", urlPatterns = {"/LoadAllHotelAvailableServlet"})
public class LoadAllHotelAvailableServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage1.html";
    private final String HOME_PAGE = "homePage1.jsp";
    static final Logger LOGGER = Logger.getLogger(LoadAllHotelAvailableServlet.class);

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
        try {
            long millis = System.currentTimeMillis();
            Date currentDate = new Date(millis);
            TblHotelDAO hotelDAO = new TblHotelDAO();
            hotelDAO.loadAvailableHotel(currentDate);
            List<SearchHotelObject> listHotel = hotelDAO.getListSearchResult();
            HttpSession session = request.getSession();
            request.setAttribute("LIST_HOTEL_SEARCH", listHotel);
            String roleUser = (String) session.getAttribute("ROLE_USER");
            if (roleUser != null) {
                if (roleUser.equals("1")) {

                } else if (!roleUser.equals("1")) {

                }
            }
            url = HOME_PAGE;

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
