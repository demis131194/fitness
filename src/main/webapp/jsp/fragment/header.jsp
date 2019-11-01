<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="header.header" bundle="${rb}" var="header"/>
<fmt:message key="index.project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="header.locale" bundle="${rb}" var="locale"/>
<fmt:message key="name" bundle="${rb}" var="name"/>
<fmt:message key="last.name" bundle="${rb}" var="lastName"/>
<fmt:message key="header.role" bundle="${rb}" var="role"/>
<fmt:message key="heder.sign.in" bundle="${rb}" var="signIn"/>
<fmt:message key="heder.sign.up" bundle="${rb}" var="signUp"/>
<fmt:message key="header.logout" bundle="${rb}" var="logOut"/>

<html>
<head>
    <title>${header}</title>
</head>
<body>
<form>
    <h3>${projectName}</h3>
    <output>${locale}</output>
    <span>
        <select name="locale">
        <option selected name="en" value="en_EN">EN</option>
        <option name="ru" value="ru_RU">RU</option>
    </select>
    </span>
    <table align="right" border="2">
        <tr>
            <th>${name}</th>
            <th>${lastName}</th>
            <th>${role}</th>
        </tr>
        <c:if test="${sessionScope.authorization}">
        <tr>
            <td>${sessionScope.userName}</td>
            <td>${sessionScope.userLastName}</td>
            <td>${sessionScope.userRole}</td>
        </tr>
            <tr>
                <td colspan="3"><a href="${pageContext.request.contextPath}/controller?command=Logout">${logOut}</a></td>
            </tr>
        </c:if>
        <c:if test="${!sessionScope.authorization}">
            <tr>
                <td align="center">Guest</td>
                <td align="center"><a href="${pageContext.request.contextPath}/jsp/pages/sign-in.jsp">${signIn}</a></td>
                <td align="center"><a href="${pageContext.request.contextPath}/jsp/pages/sign-up.jsp">${signUp}</a></td>
            </tr>
        </c:if>
    </table>
</form>
<br/>
<hr/>
</body>
</html>
