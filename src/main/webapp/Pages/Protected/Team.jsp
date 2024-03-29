<!--
Seite       :  Teamseite
Zweck       :  JSP für die Teamseite
URL Mapping :  /teamPage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Team</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <c:if test="${sessionBean.loggedIn}">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Team.css">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
    </c:if>
</head>
<body>
<c:choose>
    <c:when test="${sessionBean.loggedIn}">
        <%@include file="../../assets/Contents/Team.jsp" %>
        <%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
        <script>
            <%@include file="../../assets/Scripts/Sidebar.js" %>
        </script>
    </c:when>
    <c:otherwise>
        <jsp:forward page="/protectedPage"/>
    </c:otherwise>
</c:choose>
</body>
</html>