<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="login.login" bundle="${rb}" var="login"/>
<fmt:message key="login.password" bundle="${rb}" var="password"/>
<fmt:message key="wrong.login.or.pass" bundle="${err_rb}" var="wrondLogOrPass"/>
<fmt:message key="login.button.login" bundle="${rb}" var="buttonLogin"/>


<html>
<head>
    <title><fmt:message key="login.title.login" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<a href="${pageContext.request.contextPath}"><fmt:message key="project.home" bundle="${rb}"/></a>
<br/>
<br/>
<form name="signUpForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="sign_up"/>
    <label for="login">${login}</label>
    <br/>
    <input id="login" type="text" name="Login" value="" pattern="\w{4,}"/>
    <br/>
    ${password}
    <br/>
    <input type="password" name="Password" value="" pattern="\w{4,}"/>
    <br/>
    Name
    <br/>
    <input type="text" name="Name" value=""/>
    <br/>
    LastName
    <br/>
    <input type="text" name="LastName" value=""/>
    <br/>
    <c:if test="${requestScope.wrongLogin}">Invalid Login!</c:if>
    <br/>
    <input type="submit" value="Sign up">
</form>
</body>
</html>
