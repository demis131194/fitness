<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ctg" uri="customtags"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.contacts" bundle="${rb}" var="projectContacts"/>
<fmt:message key="user.box.fio" bundle="${rb}" var="fio"/>
<fmt:message key="comment.user.trainer" bundle="${rb}" var="trainer"/>
<fmt:message key="paging.next" bundle="${rb}" var="pagingNext"/>
<fmt:message key="paging.previous" bundle="${rb}" var="pagingPrevious"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <c:import url="/jsp/fragment/navigation.jsp"/>

        <div class="main-section">
            <table>
                <c:forEach items="${requestScope.comments}" var="comment">
                    <jsp:useBean id="comment" type="by.epam.fitness.model.Comment"/>
                    <tbody class="comment">
                    <tr>
                        <td>
                            <div class="comment-client">
                                <div class="comment-client-image">
                                    <img src="${comment.clientProfileImagePath}" alt="Avatar image">
                                    <p>${comment.clientName} ${comment.clientLastName}</p>
                                </div>
                                <div class="comment-client-trainer">
                                    <span>${trainer}</span>
                                    <span>${comment.trainerName} ${comment.trainerLastName}</span>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="comment-wrap">
                                <div class="comment-date">
                                    <ctg:date-time-parse dateTime="${comment.registerDate}"/>
                                </div>
                                <div class="comment-text">
                                    <p>${comment.comment}</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:choose>
                        <c:when test="${requestScope.currentPage > 1}">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${requestScope.currentPage - 1}">${pagingPrevious}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=1">${pagingPrevious}</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                        <c:choose>
                            <c:when test="${requestScope.currentPage eq i}">
                                <li class="page-item"><a class="page-link page-focus" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${i}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${requestScope.currentPage < requestScope.numberOfPages}">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${requestScope.currentPage + 1}">${pagingNext}</a></li>

                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments&page=${requestScope.numberOfPages}">${pagingNext}</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
