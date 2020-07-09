<!--
Seite       :  Putzplan
Zweck       :  JSP der Seite Clean
URL Mapping :  /cleanPage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<jsp:useBean id="cleanBean" class="beans.CleanBean" scope="request"/>
<jsp:setProperty name="cleanBean" property="userId" value="${sessionBean.userId}"/>
<jsp:setProperty name="cleanBean" property="wgId" value="${sessionBean.wgId}"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>Putzplan</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
<c:if test="${sessionBean.loggedIn}">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Clean.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
</c:if>
</head>
<body>
<c:choose>
    <c:when test="${sessionBean.loggedIn}">
        <%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
        <%@include file="../../assets/Templates/Modal/createClean.jsp" %>
        <%@include file="../../assets/Templates/Modal/removeClean.jsp" %>

        <%@include file="../../assets/Contents/Clean.jsp" %>

        <script>
            <%@include file="../../assets/Scripts/Sidebar.js" %>
            <%@include file="../../assets/Scripts/Clean.js" %>
        </script>
    </c:when>
    <c:otherwise>
        <jsp:forward page="/protectedPage"/>
    </c:otherwise>
</c:choose>
</body>
</html>
