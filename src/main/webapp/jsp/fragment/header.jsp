<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.get('locale')}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<html>
<head>
    <title><fmt:message key="header.header" bundle="${rb}"/></title>
</head>
<body>
<form>
    <output><fmt:message key="index.project.name" bundle="${rb}"/></output>
    <br/>
    <br/>
    <output><fmt:message key="header.locale" bundle="${rb}"/></output>
    <select name="locale">
        <option selected name="en" value="en_EN">EN</option>
        <option name="ru" value="ru_RU">RU</option>
    </select>
</form>
<hr/>
</body>
</html>
