<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.header}</title>
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Main.css">
    <link rel="stylesheet" type="text/css" href="./assets/Styles/Status.css">
</head>
<body>
<c:choose>
    <c:when test="${requestScope.isSadLlama}">
        <img src="../../assets/Images/sad-llama.png">
    </c:when>
    <c:otherwise>
        <img src="../../assets/Images/success-alpacas.jpg">
    </c:otherwise>
</c:choose>
<h1>${requestScope.header}</h1>
${requestScope.message}
</body>
</html>