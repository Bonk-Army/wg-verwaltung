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
    <%@include file="../../assets/Templates/Imports/ChartJS.jsp" %>

    <title>Home</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Home.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
</head>
<body>
<%@include file="../../assets/Templates/Components/Sidebar.jsp" %>

<%@include file="../../assets/Contents/Home.jsp" %>

<script><%@include file="../../assets/Scripts/Sidebar.js" %></script>

<script><%@include file="../../assets/Scripts/ChartJS/ChartJS_Guthaben.js" %></script>
<script><%@include file="../../assets/Scripts/ChartJS/ChartJS_ToDo.js" %></script>
</body>
</html>