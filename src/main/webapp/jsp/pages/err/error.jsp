<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="error" value="${requestScope.error}"/>
<jsp:useBean id="error" class="by.epam.fitness.exception.CommandException"/>

<html>
<head>
    <title>Error ...</title>
</head>
<body>
<h2>Error ...</h2>
<hr/>
<table bgcolor="#ffe4c4" border="2">
    <tr>
        <td>Error:</td>
        <td>error</td>
    </tr>
    <tr>
        <td>URI:</td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>

    <tr>
        <td>Status code:</td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>
    <tr>
        <td>Servlet name:</td>
        <td>${pageContext.errorData.servletName}</td>
    </tr>

    <tr>
        <td>Exception:</td>
        <td>${pageContext.exception}</td>
    </tr>
    <tr>
        <td>Message:</td>
        <td>${pageContext.exception.message}</td>
    </tr>
</table>
</body>
</html>
