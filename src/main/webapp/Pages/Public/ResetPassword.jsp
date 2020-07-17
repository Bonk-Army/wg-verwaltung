<!--
Seite       :  Reset Password
Zweck       :  JSP für den Passwortreset
URL Mapping :  /resetPassword
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
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Password.css">
</head>
<body>
<%@include file="../../assets/Contents/ResetPassword.jsp" %>
<script>
    <%@include file="../../assets/Scripts/ResetPassword.js" %>
    <%@include file="../../assets/Scripts/Password.js" %>
</script>
</body>
</html>