<!--
Seite       :  Registrierung
Zweck       :  JSP für die Registrierung
URL Mapping :  /resetPassword
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Registrierung</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Login.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Password.css">

    <script><%@include file="../../assets/Scripts/Register.js" %></script>
    <script><%@include file="../../assets/Scripts/Password.js" %></script>
</head>
<body>
<%@include file="../../assets/Templates/Components/Register.jsp" %>
</body>
</html>