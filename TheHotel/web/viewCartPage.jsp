<%-- 
    Document   : viewCartPage
    Created on : Nov 6, 2020, 2:42:39 PM
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
        <c:if test="${ empty sessionScope.FULLNAME}">
            <c:redirect url="loginPage1.html"/>
        </c:if>

        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:set var="items" value="${cart.items}"/>
        <h1>Your cart</h1>
        <c:if test="${not empty cart}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Hotel Name</th>
                        <th>Room Type</th>
                        <th>Check In Date</th>
                        <th>Check Out Date</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="totalCart" value="0"/>
                    <c:forEach var="item" items="${items}">
                    <form action="actionCart" method="POST">
                        <input type="hidden" name="txtHotelID" value="${item.hotelID}" />
                        <tr>
                            <td>
                                ${item.name}
                            </td>
                            <td>
                                ${item.category}
                            </td>
                            <td> <input type="date" name="txtCDate" value="${item.cDate}" /> </td>
                            <td> <input type="date" name="txtODate" value="${item.oDate}" /></td>
                            <td> 
                                <input type="text" name="txtAmount" value="${item.amount}" /> 
                            </td>
                            <td>
                                ${item.price}
                            </td>
                            <td>
                                ${item.price * item.amount}
                            </td>
                            <c:set var="totalCart" value="${totalCart + item.price * item.amount}"/>
                            <td><input type="submit" value="Update" name="btAction" /></td>
                            <td><input type="submit" value="Remove" name="btAction" onclick="return confirm('Are you sure you want to delete this Product?');" /></td>
                        </tr>
                    </form>
                </c:forEach>
                <tr>
                    <td colspan="2">
                        <a href="load">Click here back to shop</a>
                    </td>
                    <th colspan="2">
                        Total Cart: 
                    </th >
                    <td colspan="2">
                        <c:set var="discount" value="${requestScope.DISCOUNT}"/>
                        <c:if test="${not empty discount}">
                            <c:set var="totalCart" value="${totalCart-(totalCart * (discount/100))}"/>
                        </c:if>
                        ${totalCart}
                    </td>
                    <c:if test="${not empty discount}">
                        <td>
                            <font color="green">
                            Apply discount ${discount}%  
                            <font/>
                        </td>
                    </c:if>
                </tr>
            </tbody>
        </table>
        <c:set var="errorHotel" value="${requestScope.ERROR_HOTEL}"/>
        <c:set var="errorDate" value="${requestScope.ERROR_HAPPEN}"/>
        <c:if test="${not empty errorDate.errorDate}">
            <font color="red"> 
            ${errorDate.errorDate}
            </font></br>
        </c:if>
        <c:if test="${not empty errorHotel}">
            <font color="red"> 
            Hotel ${errorHotel.name} is not enough available room!! 
            </font></br>
        </c:if>
        <c:url var="checkout" value="checkout">
            <c:param name="txtTotalPrice" value="${totalCart}"/>
        </c:url><br/>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <form action="checkdiscount" method="post">
            Discount (if any) <input type="text" name="txtDiscount" value="${param.txtDiscount}" />
            <c:if test="${not empty error.discountID}">
                <font color="red">
                ${error.discountID}
                </font>
            </c:if>
            <input type="submit" value="Check" />
        </form>
        <c:url var="checkout" value="checkout">
            <c:param name="txtDiscount" value="${param.txtDiscount}"/>
            <c:param name="txtPercentDiscount" value="${requestScope.DISCOUNT}"/>
        </c:url>
        <a href="${checkout}">Click here to check out</a>
    </c:if>
    <c:if test="${empty cart}">
        Your cart is empty!!
    </c:if>

</body>
</html>
