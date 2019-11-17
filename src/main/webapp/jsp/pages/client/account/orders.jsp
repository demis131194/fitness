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
<fmt:message key="orders.detail" bundle="${rb}" var="detail"/>
<fmt:message key="orders.update.order" bundle="${rb}" var="response"/>

<fmt:message key="orders.filter.show.filter" bundle="${rb}" var="showFilter"/>
<fmt:message key="orders.filter.trainer.name" bundle="${rb}" var="trainerName"/>
<fmt:message key="orders.filter.trainer.last.name" bundle="${rb}" var="trainerLastName"/>
<fmt:message key="orders.filter.start.date" bundle="${rb}" var="filterSartDate"/>
<fmt:message key="orders.filter.end.date" bundle="${rb}" var="filterEndDate"/>
<fmt:message key="orders.filter.status" bundle="${rb}" var="filterStatus"/>
<fmt:message key="orders.filter.status.new" bundle="${rb}" var="statusNew"/>
<fmt:message key="orders.filter.status.reviewed" bundle="${rb}" var="statusReviewed"/>
<fmt:message key="orders.filter.status.rejected" bundle="${rb}" var="statusRejected"/>
<fmt:message key="orders.filter.status.accepted" bundle="${rb}" var="statusAccepted"/>
<fmt:message key="orders.filter.status.process" bundle="${rb}" var="statusInProcess"/>
<fmt:message key="orders.filter.status.terminated" bundle="${rb}" var="statusTerminated"/>
<fmt:message key="orders.filter.btn.filter" bundle="${rb}" var="btnFilter"/>


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
                    <div class="orders-title">
                        <h2>${title}</h2>
                    </div>

                    <button type="button" class="btn btn-info filter-button-show" onclick="clickFilter()">${showFilter}</button>
                    <form class="filter-form" id="filterButton" action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="FIND_ORDERS_BY_FILTER">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputTrainerName">${trainerName}</label>
                                <input type="text" class="form-control" id="inputTrainerName" name="trainerName" placeholder="Name">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputTrainerLastName">${trainerLastName}</label>
                                <input type="text" class="form-control" id="inputTrainerLastName" name="trainerLastName" placeholder="Last name">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="inputStartDate">${filterSartDate}</label>
                                <input type="date" class="form-control" id="inputStartDate" name="startDate" placeholder="Start date">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputEndDate">${filterEndDate}</label>
                                <input type="date" class="form-control" id="inputEndDate" name="endDate" placeholder="End date">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputStatus">${filterStatus}</label>
                                <select id="inputStatus" class="form-control" name="status">
                                    <option value="0">${statusNew}</option>
                                    <option value="1">${statusReviewed}</option>
                                    <option value="2">${statusRejected}</option>
                                    <option value="3">${statusAccepted}</option>
                                    <option value="4">${statusInProcess}</option>
                                    <option value="5">${statusTerminated}</option>
                                    <option selected value="">ANY</option>
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-success">${btnFilter}</button>
                    </form>

                    <table>
                        <c:forEach items="${requestScope.orders}" var="order">
                            <jsp:useBean id="order" type="by.epam.fitness.model.Order"/>
                            <tbody class="order">
                            <tr>
                                <td>
                                    <table>
                                        <tr>
                                            <th>${registerDate}</th>
                                            <th>${trainerFio}</th>
                                            <th>${startDate}</th>
                                            <th>${endDate}</th>
                                            <th>${status}</th>
                                            <th></th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <fmt:parseDate  value="${order.registerDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                                                <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy HH:mm:ss" var="regDate" />
                                                    ${regDate}
                                            </td>
                                            <td>${order.trainerName} ${order.trainerLastName}</td>
                                            <td>${order.startDate}</td>
                                            <td>${order.endDate}</td>
                                            <td>${order.orderStatus}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.orderStatus.ordinal() == 1}">
                                                        <a href="${pageContext.request.contextPath}/controller?command=CLIENT_SHOW_UPDATED_ORDER&orderId=${order.id}">${response}</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="${pageContext.request.contextPath}/controller?command=FIND_ORDER_BY_CLIENT&orderId=${order.id}">${detail}</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
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