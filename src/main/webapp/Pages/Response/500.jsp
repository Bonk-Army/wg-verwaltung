<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>

    <title>500</title>
    <link rel="stylesheet" type="text/css" href="../../assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="../../assets/Styles/Status.css">
</head>
<body>
<div>
    <h1>500</h1>
    <p>Dem Lama ist vom Server ein unerwarteter Fehler gemeldet worden.</p>
    <img src="../../assets/Images/500-llama.png">
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