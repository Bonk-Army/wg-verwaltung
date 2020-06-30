<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>

    <title>Unerwarteter Fehler</title>
    <link rel="stylesheet" type="text/css" href="../../assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="../../assets/Styles/Status.css">
</head>
<body>
<div>
    <h1>Unerwarteter Fehler</h1>
    <p>Dem Lama ist ein Fehler gemeldet worden, bitte melde dich beim Team Lama.</p>
    <img src="../../assets/Images/exception-llama.jpg">
    <hr>
    <a href="./contact">Zur Kontakt-Seite</a>
</div>
</body>
</html>
