/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.objects.Cart;
import dinhgt.objects.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddCartServlet", urlPatterns = {"/AddCartServlet"})
public class AddCartServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage1.html";
    private final String LOAD_AREA_CONSTRUCTOR = "LoadAllHotelAvailableServlet";

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
        PrintWriter out = response.getWriter();;
        String hotelID = request.getParameter("txtHotelID");
        String hotelName = request.getParameter("txtHotelName");
        String category = request.getParameter("txtCategory");
        String price = request.getParameter("txtPrice");
        int quantity = 1;
        List<Item> items;
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession();
            String fullname = (String) session.getAttribute("FULLNAME");
            if (fullname != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null) {
                    cart = new Cart();
                    Item hotel = new Item();
                    hotel.setName(hotelName);
                    hotel.setPrice(Double.parseDouble(price));
                    hotel.setAmount(quantity);
                    hotel.setHotelID(hotelID);
                    hotel.setCategory(category);
                    cart.addItemToCart(hotel);
                    session.setAttribute("CART", cart);
                } else {
                    items = cart.getItems();
                    boolean check = false;
                    for (Item item : items) {
                        if (item.getHotelID().equals(hotelID)) {
                            item.setAmount(item.getAmount() + 1);
                            check = true;
                        }
                    }
                    if (!check) {
                        Item hotel = new Item();
                        hotel.setName(hotelName);
                        hotel.setPrice(Double.parseDouble(price));
                        hotel.setHotelID(hotelID);
                        hotel.setAmount(quantity);
                        hotel.setCategory(category);
                        items.add(hotel);
                    }
                    session.setAttribute("CART", cart);
                }
                url = LOAD_AREA_CONSTRUCTOR;
            }

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
