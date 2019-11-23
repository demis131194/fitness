<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="account.profile.edit.button" bundle="${rb}" var="editProfile"/>
<fmt:message key="account.profile.change.password" bundle="${rb}" var="changePassword"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="account.profile.title" bundle="${rb}" var="title"/>
<fmt:message key="account.profile.name" bundle="${rb}" var="name"/>
<fmt:message key="account.profile.last.name" bundle="${rb}" var="lastName"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
                    <div class="profile-table">
                        <table>
                            <tr>
                                <td class="profile-table-td-key">${name}</td>
                                <td class="profile-table-td-value">${sessionScope.userName}</td>
                            </tr>
                            <tr>
                                <td class="profile-table-td-key">${lastName}</td>
                                <td class="profile-table-td-value">${sessionScope.userLastName}</td>
                            </tr>
                        </table>
                        <a href="${pageContext.request.contextPath}/jsp/pages/admin/account/profile-edit.jsp" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">${editProfile}</a>
                        <a href="${pageContext.request.contextPath}/jsp/pages/admin/account/password-edit.jsp" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">${changePassword}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
