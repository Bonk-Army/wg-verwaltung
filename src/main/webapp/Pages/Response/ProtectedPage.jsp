<!--
Seite       :  ProtectedPage
Zweck       :  Fehlermeldung, wenn Benutzer nicht eingeloggt ist
URL Mapping :  /protectedPage
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>

    <title>Authentifikation fehlgeschlagen</title>
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Status.css">
</head>
<body>
<div>
    <h1>Authentifikation fehlgeschlagen</h1>
    <p>Es scheint so, als ob du auf eine geschützte Seite willst. Das Türsteher-Lama findet deinen Ausweis aber nicht. Bitte logge dich erst
        ein.</p>
    <img src="../../assets/Images/sad-llama.png">
    <hr>
    <a href="./">Zur&uuml;ck zur Login-Seite</a>
</div>
</body>
</html>