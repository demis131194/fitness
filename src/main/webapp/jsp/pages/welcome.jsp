<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.get('locale')}"/>
<fmt:setBundle basename="message" var="rb"/>

<html>
<head>
    <title><fmt:message key="welcome.title" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<h2><a href="${pageContext.request.contextPath}"><fmt:message key="project.home" bundle="${rb}"/></a></h2>
<hr/>
<h3><fmt:message key="welcome.lable.walcome" bundle="${rb}"/></h3>
<hr/>
${user.name}<fmt:message key="welcome.message.hello" bundle="${rb}"/>
<br/>
<fmt:message key="welcome.message.role" bundle="${rb}"/>${sessionScope.get("role")}
<hr/>
</body>
</html>
