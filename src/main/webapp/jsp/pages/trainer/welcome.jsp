<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="welcome.title" bundle="${rb}" var="title"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>
<fmt:message key="welcome.label.welcome" bundle="${rb}" var="welcome"/>
<fmt:message key="welcome.message.hello" bundle="${rb}" var="hello"/>
<fmt:message key="welcome.message.role" bundle="${rb}" var="role"/>
<fmt:message key="last.name" bundle="${rb}" var="lastName"/>
<fmt:message key="orders.register.date" bundle="${rb}" var="regDate"/>
<fmt:message key="phone" bundle="${rb}" var="phone"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<h2><a href="${pageContext.request.contextPath}">${home}</a></h2>
<hr/>
<h3>${welcome}</h3>
<hr/>
<h1>TRAINER WELCOME</h1>
${sessionScope.userName}:${hello}
<br/>
${role} - ${sessionScope.userRole}
<br/>
${lastName} - ${sessionScope.userLastName}
<br/>
${regDate} ${sessionScope.userRegisterDate}
<br/>
${phone} - ${sessionScope.userPhone}
<hr/>
</body>
</html>
