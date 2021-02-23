<%-- 
    Document   : detailBooking
    Created on : Nov 6, 2020, 11:32:48 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            Order ID<input type="text" name="txtSearchHistory" value="${param.txtSearchHistory}" />
            Date <input type="date" name="txtDate" value="${param.txtDate}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:set var="listBooking" value="${requestScope.LIST_DETAIL}"/>
        <c:if test="${not empty listBooking}">
            <p><b>Order ID:</b> ${listBooking.get(0).orderID}</p>
            <p><b>Date Order:</b> ${listBooking.get(0).dateOrder}</p><br/>
            <table border="1">
                <thead>
                    <tr>
                        <th>Detail ID</th>
                        <th>Hotel Name</th>
                        <th>Quantity Room</th>
                        <th>Check-in Date</th>
                        <th>Check-out Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${listBooking}">
                        <tr>
                            <td>${order.detailID}</td>
                            <td>${order.hotelName}</td>
                            <td>${order.quantity}</td>
                            <td>${order.cDate}</td>
                            <td>${order.oDate}</td>
                            <td>
                                <c:url var="feedback" value="feedback">
                                    <c:param name="txtOrderID" value="${order.orderID}"/>
                                    <c:param name="txtODate" value="${order.oDate}"/>
                                    <c:param name="txtHotelID" value="${order.hotelID}"/>
                                </c:url>
                                <a href="${feedback}">FeedBack</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty listBooking}">
            No booking history!!
        </c:if>
    </body>
</html>
