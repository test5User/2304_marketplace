<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.itclass.constants.AppConst" %>
<%@ page import="by.itclass.constants.JspConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Receipt</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=JspConst.MENU_JSP%>"/>
    <div class="receipt-container">
        <h2>Order id: ${receipt.order.id}</h2>
        <h2>Order date: ${receipt.order.date}</h2>
        <h2>Delivery address: ${receipt.order.address}</h2>
        <h2 class="underline">You ordered:</h2>
        <c:forEach var="item" items="${receipt.receiptItems}">
            <h2>${item.quantity} ${item.itemInfo} by ${item.itemPrice} byn. Amount: ${item.itemAmount} byn.</h2>
        </c:forEach>
        <h2 class="underline">Total amount: ${receipt.total} byn.</h2>
    </div>
</body>
</html>
