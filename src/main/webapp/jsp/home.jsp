<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.itclass.constants.AppConst" %>
<%@ page import="by.itclass.constants.JspConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <h2>User Info:</h2>
    <p>User name: ${user.name}</p>
    <p>User email: ${user.email}</p>
    <p>User login: ${user.login}</p>
    <p>Password: ${user.password}</p>
</body>
</html>
