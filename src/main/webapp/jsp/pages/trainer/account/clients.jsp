<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="users.title" bundle="${rb}" var="title"/>
<fmt:message key="users.id" bundle="${rb}" var="id"/>
<fmt:message key="orders.filter.client.name" bundle="${rb}" var="clientName"/>
<fmt:message key="orders.filter.client.last.name" bundle="${rb}" var="clientLastNameName"/>
<fmt:message key="form.phone" bundle="${rb}" var="clientPhone"/>
<fmt:message key="form.mail" bundle="${rb}" var="clientEmail"/>
<fmt:message key="users.role" bundle="${rb}" var="role"/>
<fmt:message key="users.active" bundle="${rb}" var="active"/>


<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filter.js"></script>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <c:import url="/jsp/fragment/navigation.jsp"/>
        <div class="row">
            <div class="col-lg-2">
                <c:import url="/jsp/fragment/account-menu.jsp"/>
            </div>

            <div class="col-lg-10">
                <div class="main-section">
                    <div class="users-title">
                        <h2>${title}</h2>
                    </div>

                    <table>
                        <c:forEach items="${requestScope.users}" var="user">
                            <jsp:useBean id="user" type="by.epam.fitness.model.user.User"/>
                            <tbody class="users">
                            <tr>
                                <td>
                                    <table>
                                        <tr>
                                            <th>${id}</th>
                                            <th>${clientName}</th>
                                            <th>${clientLastNameName}</th>
                                            <th>${clientPhone}</th>
                                            <th>${clientEmail}</th>
                                        </tr>
                                        <tr>
                                            <td>${user.id}</td>
                                            <td>${user.name}</td>
                                            <td>${user.lastName}</td>
                                            <td>${user.phone}</td>
                                            <td>${user.mail}</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
