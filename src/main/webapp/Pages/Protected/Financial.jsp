<%--
  Created by IntelliJ IDEA.
  User: krissi
  Date: 16.06.2020
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Finanzielles</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/ToDo.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Financial.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
</head>
<body>
<%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
<%@include file="../../assets/Templates/Modal/createFinancial.jsp" %>
<%@include file="../../assets/Templates/Modal/removeFinancial.jsp" %>

<%@include file="../../assets/Contents/Financial.jsp" %>

<script><%@include file="../../assets/Scripts/Sidebar.js" %></script>
<script><%@include file="../../assets/Scripts/Financial.js" %></script>
</body>
</html>
