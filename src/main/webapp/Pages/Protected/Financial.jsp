<!--
Seite       :  Financial
Zweck       :  JSP der Seite Financial
URL Mapping :  /financialPage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<jsp:useBean id="financialBean" class="beans.FinancialBean" scope="request"/>
<jsp:setProperty name="financialBean" property="userId" value="${sessionBean.userId}"/>
<jsp:setProperty name="financialBean" property="wgId" value="${sessionBean.wgId}"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Finanzielles</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <c:if test="${sessionBean.loggedIn}">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Financial.css">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
    </c:if>
</head>
<c:choose>
<c:when test="${sessionBean.loggedIn}">
<body onload="validDate()">
    <%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
    <%@include file="../../assets/Templates/Modal/createFinancial.jsp" %>
    <%@include file="../../assets/Templates/Modal/removeFinancial.jsp" %>

    <%@include file="../../assets/Contents/Financial.jsp" %>

<script>
    <%@include file="../../assets/Scripts/Sidebar.js" %>
    <%@include file="../../assets/Scripts/Financial.js" %>
</script>
</c:when>
<c:otherwise>
<body>
<jsp:forward page="/protectedPage"/>
</c:otherwise>
</c:choose>
</body>
</html>
