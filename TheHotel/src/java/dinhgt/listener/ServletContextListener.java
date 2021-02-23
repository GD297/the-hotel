/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.listener;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       Map<String, String> siteMap = new HashMap<>();
        siteMap.put("", "LoadAllHotelAvailableServlet");
        siteMap.put("load", "LoadAllHotelAvailableServlet");
        siteMap.put("loadadmin", "LoadHotelAdminServlet");
        siteMap.put("loginpage", "loginPage1.html");
        siteMap.put("login", "LoginServlet");
        siteMap.put("registerpage", "registerPage.html");
        siteMap.put("register", "RegisterServlet");
        siteMap.put("search", "SearchServlet");
        siteMap.put("logout", "LogoutServlet");
        siteMap.put("code", "ActiveAccountServlet");
        siteMap.put("resend", "ResendOTPServlet");
        siteMap.put("registerconstructor", "registerPage.html");
        siteMap.put("reset", "ResetPasswordServlet");
        siteMap.put("forgot", "findPassword.jsp");
        siteMap.put("forget", "ForgotPasswordServlet");
        siteMap.put("repassword", "UpdatePasswordServlet");
        siteMap.put("areaSelect", "LoadHotelServlet");
        siteMap.put("book", "LoadHotelDetailServlet");
        siteMap.put("addcart", "AddCartServlet");
        siteMap.put("viewcart", "viewCartPage.jsp");
        siteMap.put("actionCart", "ActionCartServlet");
        siteMap.put("checkout", "CheckOutServlet");
        siteMap.put("confirm", "ConfirmOrderServlet");
        siteMap.put("history", "LoadHistoryServlet");
        siteMap.put("searchhistory", "LoadBookingDetailServlet");
        siteMap.put("loaddetailbooking", "LoadBookingDetailServlet");
        siteMap.put("searchadmin", "SearchAdminServlet");
        siteMap.put("discount", "createDiscount.jsp");
        siteMap.put("creatediscount", "CreateDiscountServlet");
        siteMap.put("showdiscount", "LoadAllDiscountServlet");
        siteMap.put("checkdiscount", "CheckDiscountServlet");
        siteMap.put("feedback", "feedBackPage.jsp");
        siteMap.put("feedbackOrder", "StoreFeedBackServlet");
        siteMap.put("viewfb", "LoadFeedbackServlet");

        ServletContext context = sce.getServletContext();
        context.setAttribute("SITE_MAP", siteMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
