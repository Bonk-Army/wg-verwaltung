<%--
  Created by IntelliJ IDEA.
  User: kristin.agne
  Date: 19.06.2020
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Einstellungen</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Settings.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Password.css">

</head>
<body>
<%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
<%@include file="../../assets/Contents/Settings.jsp" %>
<%@include file="../../assets/Templates/Modal/leaveWG.jsp" %>

<script><%@include file="../../assets/Scripts/Sidebar.js"%></script>
<script><%@include file="../../assets/Scripts/Settings.js" %></script>
<script><%@include file="../../assets/Scripts/Password.js" %></script>
</body>
</html>
