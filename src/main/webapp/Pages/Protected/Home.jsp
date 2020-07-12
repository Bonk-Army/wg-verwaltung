<!--
Seite       :  Home
Zweck       :  JSP der Seite Home
URL Mapping :  /homePage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<jsp:useBean id="overviewBean" class="beans.OverviewBean"/>
<jsp:setProperty name="overviewBean" property="userId" value="${sessionBean.userId}"/>
<jsp:setProperty name="overviewBean" property="wgId" value="${sessionBean.wgId}"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>
    <%@include file="../../assets/Templates/Imports/ChartJS.jsp" %>

    <title>Home</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <c:if test="${sessionBean.loggedIn}">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Home.css">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
    </c:if>
</head>
<body>
<c:choose>
    <c:when test="${sessionBean.loggedIn}">
        <%@include file="../../assets/Templates/Components/Sidebar.jsp" %>

        <%@include file="../../assets/Contents/Home.jsp" %>

        <script>
            <%@include file="../../assets/Scripts/Sidebar.js" %>
            <%@include file="../../assets/Scripts/ChartJS/ChartJS_Guthaben.js" %>
            <%@include file="../../assets/Scripts/ChartJS/ChartJS_ToDo.js" %>
        </script>
    </c:when>
    <c:otherwise>
        <jsp:forward page="/protectedPage"/>
    </c:otherwise>
</c:choose>
</body>
</html>