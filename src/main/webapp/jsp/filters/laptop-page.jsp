<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.itclass.constants.AppConst" %>
<%@ page import="by.itclass.constants.JspConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Laptop page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=JspConst.MENU_JSP%>"/>
    <jsp:include page="/jsp/filters/laptop-filter.html"/>
    <c:choose>
        <c:when test="${not empty laptops}">
            <c:forEach var="laptop" items="${laptops}">
                <div class="stock-item-box">
                    <img class="small-image" src="/img/laptop/${laptop.vendor}-${laptop.model}.jpg" alt="img">
                    <p>Vendor ${laptop.vendor}</p>
                    <p>Model ${laptop.model}</p>
                    <p>Price ${laptop.price} byn.</p>
                    <form method="post" action="<%=AppConst.CART_CONTROLLER%>">
                        <input type="hidden" name="<%=JspConst.CART_ACTION_PARAM%>" value="add">
                        <input type="hidden" name="<%=JspConst.ITEM_TYPE_PARAM%>" value="2">
                        <input type="hidden" name="<%=JspConst.ITEM_ID_PARAM%>" value="${laptop.id}">
                        <input type="hidden" name="<%=JspConst.ITEM_VENDOR_PARAM%>" value="${laptop.vendor}">
                        <input type="hidden" name="<%=JspConst.ITEM_MODEL_PARAM%>" value="${laptop.model}">
                        <input type="hidden" name="<%=JspConst.ITEM_PRICE_PARAM%>" value="${laptop.price}">
                        <input type="hidden" name="<%=JspConst.QUANTITY_PARAM%>" value="1">
                        <input type="submit" value="Add to cart">
                    </form>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h1>Sorry, the search have returned empty result...
        </c:otherwise>
    </c:choose>
</body>
</html>
