<!--
Seite       :  ToDo
Zweck       :  JSP der Seite ToDo
URL Mapping :  /todoPage
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<jsp:useBean id="todoBean" class="beans.ToDoBean" scope="request"/>
<jsp:setProperty name="todoBean" property="userId" value="${sessionBean.userId}"/>
<jsp:setProperty name="todoBean" property="wgId" value="${sessionBean.wgId}"/>
<html>
<head>
    <%@include file="../../assets/Templates/Imports/Global.jsp" %>
    <%@include file="../../assets/Templates/Imports/Fonts.jsp" %>
    <%@include file="../../assets/Templates/Imports/Bootstrap.jsp" %>

    <title>ToDo</title>

    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <c:if test="${sessionBean.loggedIn}">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/ToDo.css">
        <link rel="stylesheet" type="text/css" href="./assets/Styles/Sidebar.css">
    </c:if>
</head>
<c:choose>
<c:when test="${sessionBean.loggedIn}">
<body onload="validDate()">
    <%@include file="../../assets/Templates/Components/Sidebar.jsp" %>
    <%@include file="../../assets/Templates/Modal/createToDo.jsp" %>
    <%@include file="../../assets/Templates/Modal/doneToDo.jsp" %>
    <%@include file="../../assets/Templates/Modal/removeToDo.jsp" %>

    <%@include file="../../assets/Contents/ToDo.jsp" %>

<script>
    <%@include file="../../assets/Scripts/Sidebar.js" %>
    <%@include file="../../assets/Scripts/Modal.js" %>
    <%@include file="../../assets/Scripts/ToDo.js" %>
</script>

</c:when>
<c:otherwise>
<body>
<jsp:forward page="/protectedPage"/>
</c:otherwise>
</c:choose>

</body>
</html>