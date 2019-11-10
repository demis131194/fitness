<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="orders.title" bundle="${rb}" var="title"/>
<fmt:message key="orders.trainer.fio" bundle="${rb}" var="trainerFio"/>
<fmt:message key="orders.register.date" bundle="${rb}" var="registerDate"/>
<fmt:message key="orders.exercises" bundle="${rb}" var="exercises"/>
<fmt:message key="orders.nutrition" bundle="${rb}" var="nutrition"/>
<fmt:message key="orders.start.date" bundle="${rb}" var="startDate"/>
<fmt:message key="orders.end.date" bundle="${rb}" var="endDate"/>
<fmt:message key="orders.price" bundle="${rb}" var="price"/>
<fmt:message key="orders.client.comment" bundle="${rb}" var="clientComment"/>
<fmt:message key="orders.status" bundle="${rb}" var="status"/>
<fmt:message key="orders.accept" bundle="${rb}" var="accept"/>


<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
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
                    <div class="profile-title">
                        <h2>${title}</h2>
                    </div>
                    <table>
                        <c:forEach items="${requestScope.orders}" var="order">
                            <jsp:useBean id="order" type="by.epam.fitness.to.OrderTo"/>
                            <tbody class="order">
                            <tr>
                                <td>
                                    <table>
                                        <tr>
                                            <th>${registerDate}</th>
                                            <th>${trainerFio}</th>
                                            <th>${exercises}</th>
                                            <th>${nutrition}</th>
                                            <th>${startDate}</th>
                                            <th>${endDate}</th>
                                            <th>${price}</th>
                                            <th>${accept}</th>
                                            <th>${clientComment}</th>
                                            <th>${status}</th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <fmt:parseDate  value="${order.registerDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                                                <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy HH:mm:ss" var="regDate" />
                                                    ${regDate}
                                            </td>
                                            <td>${order.trainerName} ${order.trainerLastName}</td>
                                            <td>${order.exercises}</td>
                                            <td>${order.nutrition}</td>
                                            <td>${order.startDate}</td>
                                            <td>${order.endDate}</td>
                                            <td>${order.price}</td>
                                            <td>${order.accept}</td>
                                            <td>${order.clientComment}</td>
                                            <td>${order.orderStatus}</td>
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

<%--<html>--%>
<%--<head>--%>
<%--    <title>${title}</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<c:import url="/jsp/fragment/header.jsp"/>--%>
<%--<h1>CLIENT WELCOME</h1>--%>
<%--<section>--%>
<%--    <h3><a href="${pageContext.request.contextPath}">${home}</a></h3>--%>
<%--    <hr/>--%>
<%--    <h2>${orders}</h2>--%>
<%--    <table border="1" cellpadding="8" cellspacing="0">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>${orderId}</th>--%>
<%--            <th>${clientId}</th>--%>
<%--            <th>${trainerId}</th>--%>
<%--            <th>${registerDate}</th>--%>
<%--            <th>${exercises}</th>--%>
<%--            <th>${nutrition}</th>--%>
<%--            <th>${startDate}</th>--%>
<%--            <th>${endDate}</th>--%>
<%--            <th>${price}</th>--%>
<%--            <th>${clientComment}</th>--%>
<%--            <th>${status}</th>--%>
<%--            <th>${accept}</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <c:forEach items="${requestScope.orders}" var="order">--%>
<%--            <jsp:useBean id="order" type="by.epam.fitness.model.Order"/>--%>
<%--            <tr bgcolor="${order.isActive() ? "green": "red"}">--%>
<%--                <td>${order.id}</td>--%>
<%--                <td>${order.clientId}</td>--%>
<%--                <td>${order.trainerId}</td>--%>
<%--                <td>${order.registerDate}</td>--%>
<%--                <td>${order.exercises}</td>--%>
<%--                <td>${order.nutrition}</td>--%>
<%--                <td>${order.startDate}</td>--%>
<%--                <td>${order.endDate}</td>--%>
<%--                <td>${order.price}</td>--%>
<%--                <td>${order.clientComment}</td>--%>
<%--                <td>${order.orderStatus.name()}</td>--%>
<%--                <td>${order.isAccept()}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</section>--%>
<%--</body>--%>
<%--</html>--%>
