<%-- 
    Document   : homePage1
    Created on : Oct 20, 2020, 4:28:30 PM
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
            <a href="history">Booking history</a>
        </c:if>

        <c:if test="${ empty sessionScope.FULLNAME}">
            <form action="loginpage">
                <input type="submit" value="Login" aame="btAction"/>
            </form>
        </c:if>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <a href="viewcart">View your cart</a>
        </c:if>
        <c:set var="listArea" value="${requestScope.LIST_AREA}"/>
        <c:set var="error" value="${requestScope.ERROR_HAPPEN}"/>
        <h1>The Hotel</h1>
        <form action="search">
            Search
            Area
            <select name="txtAreaID" va>
                <c:if test="${not empty listArea}">
                    <c:forEach var="area" items="${listArea}">
                        <option value="${area.areaID}" selected="<c:if test="${area.areaID eq param.txtAreaID}"> selected</c:if>">${area.nameArea}</option>
                    </c:forEach>
                </c:if>
            </select>
            <br/>
            Hotel Name<input type="text" name="txtSearchHotel" value="${param.txtSearchHotel}" /><br/>
            <c:if test="${not empty error.errorHotelLength}">
                <font 
                    color="red">${error.errorHotelLength} 
                </font><br/>
            </c:if>
            Check in Date<input type="date" name="txtcDate" value="${param.txtcDate}" /><br/>
            <c:if test="${not empty error.errorCDateLength}">
                <font 
                    color="red">${error.errorCDateLength} 
                </font><br/>
            </c:if>
            Check out Date<input type="date" name="txtoDate" value="${param.txtoDate}" /><br/>
            <c:if test="${not empty error.errorODateLength}">
                <font 
                    color="red">${error.errorODateLength} 
                </font><br/>
            </c:if>
            Amount Room<input type="text" name="txtAmountRoom" value="${param.txtAmountRoom}" /><br/>
            <c:if test="${not empty error.errorAmountLength}">
                <font 
                    color="red">${error.errorAmountLength} 
                </font><br/>
            </c:if>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:set var="listAreaSearch" value="${requestScope.LIST_AREA_SEARCH}"/>
        <c:set var="listHotelSearch" value="${requestScope.LIST_HOTEL_SEARCH}"/>

        <!--        show result search with area-->
        <c:if test="${not empty listAreaSearch}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Name Hotel</th>
                        <th>Address</th>
                        <th>Available Room</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="hotel" items="${listAreaSearch}">
                        <tr>
                            <td>
                                ${hotel.nameHotel}
                            </td>
                            <td>
                                ${hotel.address}
                            </td>
                            <td>
                                ${hotel.totalRoom}
                            </td>
                            <td>
                                <c:url var="book" value="book">
                                    <c:param name="txtHotelID" value="${hotel.hotelID}"/>
                                    <c:param name="txtHotelAddress" value="${hotel.address}"/>
                                    <c:param name="txtTotalRoom" value="${hotel.totalRoom}"/>
                                    <c:param name="txtHotelName" value="${hotel.nameHotel}"/>
                                </c:url>
                                <a href="${book}">Book</a>
                            </td>
                            <td>
                                <c:url var="viewfeedback" value="viewfb">
                                    <c:param name="txtHotelID" value="${hotel.hotelID}"/>
                                </c:url>
                                <a href="${viewfeedback}">View Feedback</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <!-- show result search with hotel name       -->
        <c:if test="${not empty listHotelSearch}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Name Hotel</th>
                        <th>Address</th>
                        <th>Available Room</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="hotel" items="${listHotelSearch}">
                        <tr>
                            <td>
                                ${hotel.hotelName}
                            </td>
                            <td>
                                ${hotel.hotelAddress}
                            </td>
                            <td>
                                ${hotel.availableRoom}
                            </td>
                            <td>
                                <c:url var="book" value="book">
                                    <c:param name="txtHotelID" value="${hotel.hotelID}"/>
                                    <c:param name="txtHotelAddress" value="${hotel.hotelAddress}"/>
                                    <c:param name="txtTotalRoom" value="${hotel.availableRoom}"/>
                                    <c:param name="txtHotelName" value="${hotel.hotelName}"/>
                                </c:url>
                                <a href="${book}">Book</a>
                            </td>
                            <td>
                                <c:url var="viewfeedback" value="viewfb">
                                    <c:param name="txtHotelID" value="${hotel.hotelID}"/>
                                </c:url>
                                <a href="${viewfeedback}">View Feedback</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>            
    </body>
</html>
