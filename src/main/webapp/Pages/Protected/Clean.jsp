<%--
  Created by IntelliJ IDEA.
  User: kristin.agne
  Date: 20.06.2020
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Putzplan</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Clean.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
</head>
<body>
<%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
<%@include file="../../assets/Templates/Modal/createClean.jsp" %>
<%@include file="../../assets/Templates/Modal/removeClean.jsp" %>

<%@include file="../../assets/Contents/Clean.jsp" %>

<script><%@include file="../../assets/Scripts/Sidebar.js" %></script>
<script><%@include file="../../assets/Scripts/Clean.js" %></script>
</body>
</html>
