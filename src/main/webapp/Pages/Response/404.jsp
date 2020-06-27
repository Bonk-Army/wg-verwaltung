<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <title>404</title>
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Status.css">
</head>
<body>
<div>
    <h1>404</h1>
    <p>Das angefragte Lama wurde nicht gefunden.</p>
    <img src="../../assets/Images/404-llama.jpg">
    <hr>
    <c:choose>
        <c:when test="${sessionBean.loggedIn}">
            <a href="./home">Zur&uuml; zur Home-Seite</a>
        </c:when>
        <c:otherwise>
            <a href="./">Zur&uuml; zur Login-Seite</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>