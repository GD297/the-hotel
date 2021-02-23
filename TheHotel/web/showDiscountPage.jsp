<%-- 
    Document   : showDiscountPage
    Created on : Nov 7, 2020, 4:06:44 PM
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
            </font><br/>
            <a href="logout">Logout</a><br/>
            <a href="loadadmin">Home</a><br/>
            <a href="discount">Create discount code</a><br/>
        </c:if>

        <c:set var="listDiscount" value="${requestScope.LIST_DISCOUNT}"/>
        <c:if test="${not empty listDiscount}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Discount(%)</th>
                        <th>Status</th>
                        <th>Create Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="discount" items="${listDiscount}">
                        <tr>
                            <td>${discount.discountID}</td>
                            <td>${discount.name}</td>
                            <td>${discount.discount}</td>
                            <td>${discount.status}</td>
                            <td>${discount.createDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </body>
</html>
