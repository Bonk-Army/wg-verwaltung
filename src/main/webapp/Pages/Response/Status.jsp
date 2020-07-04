<!--
Seite       :  Status
Zweck       :  Response, wie die Anfrage verlaufen ist
URL Mapping :  /status
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <title>Status</title>
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Status.css">
</head>
<body>
<div>
    <h1>${requestScope.header}</h1>
    <c:choose>
        <c:when test="${requestScope.isSadLlama}">
            <img src="../../assets/Images/sad-llama.png">
        </c:when>
        <c:otherwise>
            <img src="../../assets/Images/success-alpacas.jpg">
        </c:otherwise>
    </c:choose>
    <hr>
    <c:choose>
        <c:when test="${sessionBean.loggedIn}">
            <a href="./home">Zur&uuml;ck zur Home-Seite</a>
        </c:when>
        <c:otherwise>
            <a href="./">Zur&uuml;ck zur Login-Seite</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>