<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="users.title" bundle="${rb}" var="title"/>
<fmt:message key="users.id" bundle="${rb}" var="id"/>
<fmt:message key="users.fio" bundle="${rb}" var="userFio"/>
<fmt:message key="users.role" bundle="${rb}" var="role"/>
<fmt:message key="users.active" bundle="${rb}" var="active"/>

<fmt:message key="users.detail" bundle="${rb}" var="detail"/>
<fmt:message key="users.update" bundle="${rb}" var="update"/>
<fmt:message key="users.delete" bundle="${rb}" var="delete"/>
<fmt:message key="users.restore" bundle="${rb}" var="restore"/>

<fmt:message key="users.filter.show.filter" bundle="${rb}" var="showFilter"/>
<fmt:message key="users.filter.name" bundle="${rb}" var="filterUserName"/>
<fmt:message key="users.filter.last.name" bundle="${rb}" var="filterUserLastName"/>
<fmt:message key="users.filter.role" bundle="${rb}" var="filterRole"/>
<fmt:message key="users.filter.role.client" bundle="${rb}" var="roleClient"/>
<fmt:message key="users.filter.role.trainer" bundle="${rb}" var="roleTrainer"/>
<fmt:message key="users.filter.role.any" bundle="${rb}" var="roleAny"/>
<fmt:message key="users.filter.submit" bundle="${rb}" var="btnFilter"/>


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

                    <button type="button" class="btn btn-info filter-button-show" onclick="clickFilter()">${showFilter}</button>
                    <form class="filter-form" id="filterButton" action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="FIND_USERS_BY_FILTER">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputUserName">${filterUserName}</label>
                                <input type="text" class="form-control" id="inputUserName" name="userName" placeholder="Name">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputUserLastName">${filterUserLastName}</label>
                                <input type="text" class="form-control" id="inputUserLastName" name="userLastName" placeholder="Last name">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="inputStatus">${filterRole}</label>
                                <select id="inputStatus" class="form-control" name="userRole">
                                    <option value="CLIENT">${roleClient}</option>
                                    <option value="TRAINER">${roleTrainer}</option>
                                    <option value="" selected>${roleAny}</option>
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-success">${btnFilter}</button>
                    </form>

                    <table>
                        <c:forEach items="${requestScope.users}" var="user">
                            <jsp:useBean id="user" type="by.epam.fitness.model.user.User"/>
                            <tbody class="users">
                            <tr>
                                <td>
                                    <table>
                                        <tr>
                                            <th>${id}</th>
                                            <th>${userFio}</th>
                                            <th>${role}</th>
                                            <th>${active}</th>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                        <tr>
                                            <td>${user.id}</td>
                                            <td>${user.name} ${user.lastName}</td>
                                            <td>${user.role}</td>
                                            <td>${user.active}</td>
                                            <td><a href="${pageContext.request.contextPath}/controller?command=FIND_USER_BY_ADMIN&userId=${user.id}&userRole=${user.role}">${detail}</a></td>
                                            <td><a href="${pageContext.request.contextPath}/controller?command=UPDATE_USER_BY_ADMIN&userId=${user.id}&userRole=${user.role}">${update}</a></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${user.active}">
                                                        <a href="${pageContext.request.contextPath}/controller?command=DELETE_USER_BY_ADMIN&userId=${user.id}">${delete}</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="${pageContext.request.contextPath}/controller?command=RESTORE_USER_BY_ADMIN&userId=${user.id}">${restore}</a>
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
