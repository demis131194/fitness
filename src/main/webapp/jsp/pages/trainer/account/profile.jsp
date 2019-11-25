<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="account.profile.title" bundle="${rb}" var="title"/>
<fmt:message key="account.profile.name" bundle="${rb}" var="name"/>
<fmt:message key="account.profile.last.name" bundle="${rb}" var="lastName"/>
<fmt:message key="account.profile.reg.date" bundle="${rb}" var="registrDate"/>
<fmt:message key="account.profile.edit.button" bundle="${rb}" var="editProfile"/>
<fmt:message key="account.profile.change.password" bundle="${rb}" var="changePassword"/>
<fmt:message key="account.profile.phone" bundle="${rb}" var="phone"/>
<fmt:message key="account.profile.email" bundle="${rb}" var="email"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
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
                            <tr>
                                <td class="profile-table-td-key">${registrDate}</td>
                                <td class="profile-table-td-value">
                                    <fmt:parseDate  value="${sessionScope.userRegisterDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                                    <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy HH:mm:ss" var="regDate" />
                                    ${regDate}
                                </td>
                            </tr>
                            <tr>
                                <td class="profile-table-td-key">${phone}</td>
                                <td class="profile-table-td-value">${sessionScope.userPhone}</td>
                            </tr>
                            <tr>
                                <td class="profile-table-td-key">${email}</td>
                                <td class="profile-table-td-value">${sessionScope.userMail}</td>
                            </tr>
                        </table>
                        <div>
                            <a href="${pageContext.request.contextPath}/jsp/pages/trainer/account/profile-edit.jsp" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">${editProfile}</a>
                            <a href="${pageContext.request.contextPath}/jsp/pages/trainer/account/password-edit.jsp" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">${changePassword}</a>
                        </div>
                        <div>
                            <form method="post" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" class="upload-form">
                                <div class="form-group">
                                    <label class="col-form-label col-lg-3">Load your own img</label>
                                    <div class="col-lg-4">
                                        <input type="file" name="multiPartServlet" id="file" onchange="checkUpload()" accept="image/jpeg,image/png,image/gif"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-secondary" disabled id="upload-submit">Upload</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
