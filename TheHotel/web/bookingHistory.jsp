<%-- 
    Document   : bookingHistory
    Created on : Nov 6, 2020, 10:50:00 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Hotel</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.FULLNAME}">
            <font color="red">
            Welcome ${sessionScope.FULLNAME}
            </font>
            <a href="logout">Logout</a><br/>
            <a href="load">Home</a>
        </c:if>
        <h1>Booking History</h1>
        <form action="searchhistory">
            Search
            Order ID<input type="text" name="txtOrderID" value="${param.txtOrderID}" />
            Date <input type="date" name="txtDate" value="${param.txtDate}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:set var="listHotel" value="${requestScope.LIST_ORDER}"/>
        <c:if test="${not empty listHotel}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Total Price</th>
                        <th>Order Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${listHotel}">
                        <tr>
                            <td>${order.orderid}</td>
                            <td>${order.totalPrice}</td>
                            <td>${order.dateorder}</td>
                            <td>
                                <c:url var="loaddetailbooking" value="LoadBookingDetailServlet">
                                    <c:param name="txtOrderID" value="${order.orderid}"/>
                                    <c:param name="txtDate" value="${order.dateorder}"/>
                                </c:url>
                                <a href="${loaddetailbooking}">Detail</a>
                            </td>
                            <td>
                                <c:url var="deletebooking" value="DeleteBookingServlet">
                                    <c:param name="txtOrderID" value="${order.orderid}"/>
                                    <c:param name="txtDate" value="${order.dateorder}"/>
                                </c:url>
                                <a href="${deletebooking}">Delete</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty listHotel}">
            No booking history!!
        </c:if>
    </body>
</html>
