<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <title>${requestScope.header}</title>
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Status.css">
</head>
<body>
<div>
    <h1>${requestScope.header}</h1>
    <p>${requestScope.message}</p>
    <c:choose>
        <c:when test="${requestScope.isSadLlama}">
            <img src="../../assets/Images/sad-llama.png">
        </c:when>
        <c:otherwise>
            <img src="../../assets/Images/success-alpacas.jpg">
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${sessionBean.loggedIn}">
            <hr>
            <a href="./home">Zur Home-Seite</a>
        </c:when>
        <c:otherwise>
            <hr>
            <a href="./">Zur Login-Seite</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>