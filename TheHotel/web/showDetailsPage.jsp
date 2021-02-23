<%-- 
    Document   : showDetailsPage
    Created on : Nov 6, 2020, 1:20:45 PM
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
            <a href="logout">Logout</a>
        </c:if>
        <c:if test="${ empty sessionScope.FULLNAME}">
            <c:redirect url="loginPage1.html"/>
        </c:if>

        <c:set var="roomDTO" value="${requestScope.ROOM_DTO}"/>
        <h1> Booking Hotel</h1>
        <p><b>Name</b>${param.txtHotelName}</p>
        <p><b>Address</b>${param.txtHotelAddress}</p>
        <c:if test="${not empty roomDTO}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="room" items="${roomDTO}">
                        <tr>
                            <td>${room.descrip}</td>
                            <td>${room.category}</td>
                            <td>${room.price}</td>
                            <td>
                                <c:url var="addcart" value="addcart">
                                    <c:param name="txtHotelID" value="${param.txtHotelID}"/>
                                    <c:param name="txtHotelName" value="${param.txtHotelName}"/>
                                    <c:param name="txtCategory" value="${room.category}"/>
                                    <c:param name="txtPrice" value="${room.price}"/>
                                </c:url>
                                <a href="${addcart}">Add to cart</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </body>
</html>
