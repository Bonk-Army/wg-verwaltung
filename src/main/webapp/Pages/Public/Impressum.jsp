<!--
Seite       :  Impressum
Zweck       :  JSP für die Impressumsseite
URL Mapping :  /impressumPage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Impressum</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Impressum.css">
    <c:if test="${sessionBean.loggedIn}">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
    </c:if>
</head>
<body>
<%@include file="../../assets/Contents/Impressum.jsp" %>

<c:if test="${sessionBean.loggedIn}">
    <%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
    <script>
        <%@include file="../../assets/Scripts/Sidebar.js" %>
    </script>
</c:if>
</body>
</html>