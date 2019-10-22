<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="message" var="rb"/>

<html>
<head>
    <title>Header</title>
</head>
<body>
<h2><fmt:message key="index.project.name" bundle="${rb}"/></h2>
<hr/>
</body>
</html>
