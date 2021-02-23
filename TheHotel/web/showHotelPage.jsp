<%-- 
    Document   : showHotelPage
    Created on : Nov 3, 2020, 7:25:03 PM
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
            <form action="loginpage">
                <input type="submit" value="Login" aame="btAction"/>
            </form>
        </c:if>
        <h1>The Hotel</h1>
        <form action="search">
            Search <input type="text" name="txtSearchArea" value="" /><br/>
            <input type="text" name="txtSearchHotel" value="" /><br/>
            <input type="date" name="txtcDate" value="" />
            <input type="date" name="txtoDate" value="" />
            <input type="submit" value="Search" name="btAction" />
        </form>

        <c:set var="listHotel" value="${requestScope.LIST_HOTEL_AVAILABLE}"/>
        <c:if test="${not empty listHotel}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Hotel</th>
                        <th>Address</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="hotel" items="${listHotel}">
                        <tr>
                            <td>${hotel.getHotelID()}</td>
                            <td>${hotel.getName()}</td>
                            <td>${hotel.getAddress()}</td>
                            <td>
                                
                                <a href="">Book</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </body>
</html>
