<%--
  Created by IntelliJ IDEA.
  User: Lama
  Date: 14.06.20
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Einloggen</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Login.css">
</head>
<body>
<%@include file="../../assets/Templates/Components/Login.jsp" %>
<%@include file="../../assets/Templates/Modal/Login.jsp" %>
<script><%@include file="../../assets/Scripts/Modal.js" %></script>
</body>
</html>