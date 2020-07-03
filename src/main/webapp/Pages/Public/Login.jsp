<!--
Seite       :  Login
Zweck       :  JSP für die Loginseite
URL Mapping :  /login
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Einloggen</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Login.css">
</head>
<body>
<%@include file="../../assets/Templates/Components/Login.jsp" %>
<%@include file="../../assets/Templates/Modal/Login.jsp" %>
<script><%@include file="../../assets/Scripts/Modal.js" %></script>
</body>
</html>