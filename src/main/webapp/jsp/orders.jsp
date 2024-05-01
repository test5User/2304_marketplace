<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.itclass.constants.AppConst" %>
<%@ page import="by.itclass.constants.JspConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=JspConst.MENU_JSP%>"/>
    <h2>Hello ${user.name}</h2>
    <c:choose>
        <c:when test="${not empty orders}">
            <h2>Yours orders:</h2>
            <c:forEach var="order" items="${orders}">
                <div class="order-list-container">
                    <h3>${order.date}, number: ${order.id}, address: ${order.address}</h3>
                    <form method="post" action="<%=AppConst.PRINT_ORDER_CONTROLLER%>">
                        <input type="hidden" name="<%=JspConst.ORDER_ID_PARAM%>" value="${order.id}">
                        <input type="submit" value="Print receipt">
                    </form>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>You have no orders at the moment</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
