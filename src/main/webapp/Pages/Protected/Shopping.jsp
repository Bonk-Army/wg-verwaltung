<!--
Seite       :  Shopping
Zweck       :  JSP der Seite Shopping
URL Mapping :  /shoppingPage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<jsp:useBean id="shoppingBean" class="beans.ShoppingBean" scope="request"/>
<jsp:setProperty name="shoppingBean" property="userId" value="${sessionBean.userId}"/>
<jsp:setProperty name="shoppingBean" property="username" value="${sessionBean.username}"/>
<jsp:setProperty name="shoppingBean" property="wgId" value="${sessionBean.wgId}"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Einkaufsliste</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <c:if test="${sessionBean.loggedIn}">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Shopping.css">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
    </c:if>
</head>
<c:choose>
<c:when test="${sessionBean.loggedIn}">
<body onload="validDate()">
    <%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
    <%@include file="../../assets/Templates/Modal/createShopping.jsp" %>
    <%@include file="../../assets/Templates/Modal/doneShopping.jsp" %>
    <%@include file="../../assets/Templates/Modal/removeShopping.jsp" %>

    <%@include file="../../assets/Contents/Shopping.jsp" %>

<script>
    <%@include file="../../assets/Scripts/Sidebar.js" %>
    <%@include file="../../assets/Scripts/Modal.js" %>
    <%@include file="../../assets/Scripts/Shopping.js" %>
</script>

</c:when>
<c:otherwise>
<body>
<jsp:forward page="/protectedPage"/>
</c:otherwise>
</c:choose>

</body>
</html>
