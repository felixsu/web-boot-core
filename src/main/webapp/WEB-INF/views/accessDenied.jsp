<%--
  Created by IntelliJ IDEA.
  User: fsoewito
  Date: 2/25/2016
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>AccessDenied page</title>
</head>
<body>
Dear <strong>${user}</strong>, You are not authorized to access this page
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
