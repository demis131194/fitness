<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>
<html>
<head>
    <title><fmt:message key="login.title.login" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<a href="${pageContext.request.contextPath}"><fmt:message key="project.home" bundle="${rb}"/></a>
<br/>
<br/>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="login.login" bundle="${rb}"/>
    <br/>
    <input type="text" name="Login" value=""/>
    <br/>
    <fmt:message key="login.password" bundle="${rb}"/>
    <br/>
    <input type="password" name="Password" value=""/>
    <br/>
    <c:if test="${requestScope.wrongPassOrLogin}"><fmt:message key="wrong.login.or.pass" bundle="${err_rb}"/></c:if>
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="<fmt:message key="login.button.login" bundle="${rb}"/>">
</form>
</body>
</html>
