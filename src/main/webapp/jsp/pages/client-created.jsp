
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>

<html>
<head>
    <title>${projectName}</title>
</head>
<body>
<h2>NEW CLIENT CREATED!!!!!</h2>
<h2><a href="${pageContext.request.contextPath}">${home}</a></h2>
</body>
</html>
