/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.objects.NewErrorSearch;
import dinhgt.objects.SearchAreaObject;
import dinhgt.objects.SearchHotelObject;
import static dinhgt.servlet.CheckDiscountServlet.LOGGER;
import dinhgt.tblarea.TblAreaDAO;
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
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage1.html";
    private final String HOME_PAGE = "homePage1.jsp";
    static final Logger LOGGER = Logger.getLogger(SearchServlet.class);

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
        String searchArea = request.getParameter("txtAreaID");
        String searchHotel = request.getParameter("txtSearchHotel");
        String cDate = request.getParameter("txtcDate");
        String oDate = request.getParameter("txtoDate");
        String amount = request.getParameter("txtAmountRoom");
        int amountRoom = 0;
        boolean foundErr = false;
        NewErrorSearch error = new NewErrorSearch();
        String url = LOGIN_PAGE;
        try {
            if (searchArea.isEmpty() && searchHotel.isEmpty()) {
                error.setErrorAreaLength("Please enter area or hotel search!");
                foundErr = true;
            }
            if (cDate.isEmpty()) {
                error.setErrorCDateLength("Please enter check in date!");
                foundErr = true;
            }
            if (oDate.isEmpty()) {
                error.setErrorODateLength("Please enter check out date!");
                foundErr = true;
            }
            if (!foundErr) {
                Date checkInDate = Date.valueOf(cDate);
                Date checkOutDate = Date.valueOf(oDate);

                long millis = System.currentTimeMillis();
                Date currentDate = new Date(millis);

                if (checkInDate.before(currentDate)) {
                    error.setErrorCDateLength("check in date must be after " + currentDate.toString());
                    foundErr = true;
                } else if (checkInDate.after(checkOutDate)) {
                    error.setErrorODateLength("check in date must be after check out date");
                    foundErr = true;
                }
            }
            try {
                amountRoom = Integer.parseInt(amount);
            } catch (NumberFormatException ex) {
                foundErr = true;
                error.setErrorAmountLength("Please enter amount is number!");
            }
            if (foundErr) {
                request.setAttribute("ERROR_HAPPEN", error);
                url = HOME_PAGE;
            } else {
                if (searchHotel.isEmpty()) {
                    TblAreaDAO areaDAO = new TblAreaDAO();
                    areaDAO.searhHotelInArea(searchArea, cDate, cDate, amountRoom);
                    List<SearchAreaObject> listResult = areaDAO.getListHotel();
                    request.setAttribute("LIST_AREA_SEARCH", listResult);
                } else if (searchArea.isEmpty()) {
                    TblHotelDAO hotelDAO = new TblHotelDAO();
                    hotelDAO.searchHotel(searchHotel, cDate, oDate, amountRoom);
                    List<SearchHotelObject> listResult = hotelDAO.getListSearchResult();
                    request.setAttribute("LIST_HOTEL_SEARCH", listResult);
                } else {
                    TblHotelDAO hotelDAO = new TblHotelDAO();
                    hotelDAO.searchHotel(searchHotel, searchArea, cDate, oDate, amountRoom);
                    List<SearchHotelObject> listResult = hotelDAO.getListSearchResult();
                    request.setAttribute("LIST_HOTEL_SEARCH", listResult);
                }
                url = HOME_PAGE;
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
