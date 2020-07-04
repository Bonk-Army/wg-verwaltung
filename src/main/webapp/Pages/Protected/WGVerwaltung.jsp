<!--
Seite       :  WGVerwaltung
Zweck       :  JSP der WGVerwaltung
URL Mapping :  /wgverwaltungPage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>WG - Verwaltung</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <c:if test="${sessionBean.loggedIn}">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Login.css">
    </c:if>
</head>
<body>
<c:choose>
    <c:when test="${sessionBean.loggedIn}">
        <%@include file="../../assets/Templates/Components/WG-Verwaltung.jsp" %>
        <%@include file="../../assets/Templates/Modal/WG.jsp" %>
        <script>
            <%@include file="../../assets/Scripts/Modal.js" %>
        </script>
    </c:when>
    <c:otherwise>
        <jsp:forward page="/protectedPage"/>
    </c:otherwise>
</c:choose>
</body>
</html>