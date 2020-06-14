<%--
  Created by IntelliJ IDEA.
  User: patrick
  Date: 14.06.20
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utilities.ErrorCodes" %>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <title>Login mit JSP HURENSOHNTECHNOLOGIE</title>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Login.css">
</head>
<body>
    <jsp:useBean id="login" class="beans.LoginBean"/>
    <c:if test="${login.getStatus() == ErrorCodes.SUCCESS}">
        <%@include file="../../assets/Contents/Response/Success.jsp" %>
    </c:if>
    <c:if test="${login.getStatus() == ErrorCodes.FAILURE}">
        <%@include file="../../assets/Contents/Response/Failure.jsp" %>
    </c:if>
    <c:if test="${login.getStatus() == ErrorCodes.WRONGENTRY}">
        <%@include file="../../assets/Contents/Response/WrongEntry.jsp" %>
    </c:if>
    <c:if test="${login.getStatus() == ErrorCodes.WRONGUNAME}">
        <%@include file="../../assets/Contents/Response/WrongUName.jsp" %>
    </c:if>
    <c:if test="${login.getStatus() == ErrorCodes.WRONGPASSWORD}">
        <%@include file="../../assets/Contents/Response/WrongPassword.jsp" %>
    </c:if>
    <c:if test="${login.getStatus() == ErrorCodes.WRONGEMAIL}">
        <%@include file="../../assets/Contents/Response/WrongEMail.jsp" %>
    </c:if>
    <c:if test="${login.getStatus() == ErrorCodes.WRONGEMAIL}">
        <%@include file="../../assets/Contents/Response/TryAgain.jsp" %>
    </c:if>
</body>
</html>