<%-- 
    Document   : homeAdminPage
    Created on : Nov 3, 2020, 1:43:14 PM
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
        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
        <c:set var="role" value="${sessionScope.ROLE_USER}"/>
        <c:set var="Admin" value="1"/>
        <c:if test="${role ne Admin}">
            <c:redirect url="loginPage1.html"/>
        </c:if>
        <c:if test="${not empty fullname}">
            <font color="red">
            Welcome ${sessionScope.FULLNAME}
            </font>
            <a href="logout">Logout</a><br/>
            <a href="showdiscount">View all discount code</a>
            <a href="discount">Create discount code</a>
        </c:if>



        <c:if test="${empty sessionScope.FULLNAME}">
            <form action="loginpage">
                <input type="submit" value="Login" aame="btAction"/>
            </form>
        </c:if>



        <h1>Administrator </h1>
        <c:set var="listArea" value="${requestScope.LIST_AREA}"/>
        <c:set var="error" value="${requestScope.ERROR_HAPPEN}"/>
        <h1>The Hotel</h1>
        <form action="searchadmin">
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
        <c:set var="listAllHotel" value="${requestScope.LIST_ALL_HOTEL}"/>
        
        <!-- show all hotel -->
        <c:if test="${not empty listAllHotel}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Name Hotel</th>
                        <th>Address</th>
                        <th>Available Room</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="hotel" items="${listAllHotel}">
                        <tr>
                            <td>
                                ${hotel.hotelID}
                            </td>
                            <td>
                                ${hotel.name}
                            </td>
                            <td>
                                ${hotel.address}
                            </td>
                            <td>
                                ${hotel.status}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>    
        
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
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>            
    </body>
</html>
