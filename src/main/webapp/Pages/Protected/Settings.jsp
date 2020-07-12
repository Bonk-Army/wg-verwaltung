<!--
Seite       :  Settings
Zweck       :  JSP der Seite Settings
URL Mapping :  /settingsPage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<jsp:useBean id="settingsBean" class="beans.SettingsBean" scope="request"/>
<jsp:setProperty name="settingsBean" property="userId" value="${sessionBean.userId}"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Einstellungen</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <c:if test="${sessionBean.loggedIn}">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Settings.css">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Password.css">
    </c:if>

</head>
<body>
<c:choose>
    <c:when test="${sessionBean.loggedIn}">
        <%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
        <%@include file="../../assets/Contents/Settings.jsp" %>
        <%@include file="../../assets/Templates/Modal/leaveWG.jsp" %>

        <script>
            <%@include file="../../assets/Scripts/Sidebar.js"%>
            <%@include file="../../assets/Scripts/Settings.js" %>
            <%@include file="../../assets/Scripts/Password.js" %>
        </script>

    </c:when>
    <c:otherwise>
        <body>
        <jsp:forward page="/protectedPage"/>
    </c:otherwise>
</c:choose>

</body>
</html>
