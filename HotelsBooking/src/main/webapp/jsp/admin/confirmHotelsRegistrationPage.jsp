<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Confirm hotels registration page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-lg top-admin-container">
    <div class="row">
        <div class="col-12 col-lg-3">
            <div class="container-fluid admin-menu">
                <div class="container-fluid inner-admin-menu">
                    <ul class="list-group">
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/frontController?command=get_admin_all_users_page">
                            <h1 class="lead menu-text">
                                <span class="iconify" data-icon="gis:globe-users"></span> All users accounts</h1></a>
                        </li>
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/frontController?command=get_admin_all_hotels_page">
                            <h1 class="lead menu-text">
                                <span class="iconify" data-icon="clarity:building-solid-alerted"></span> All hotels
                                accounts</h1></a></li>
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/frontController?command=get_admin_confirm_hotel_registration_page">
                            <h1 class="lead menu-text">
                                <span class="iconify" data-icon="line-md:circle-to-confirm-circle-transition"></span>
                                Confirm hotels</h1></a></li>
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/frontController?command=get_add_admin_page">
                            <h1 class="lead menu-text">
                                <span class="iconify" data-icon="carbon:add-alt"></span> Add new admin</h1></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-12 col-lg-9">
            <div class="container-fluid manage-result-con">
                <div class="container-fluid inner-manage-result-con">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Legal name</th>
                            <th scope="col">Brand</th>
                            <th scope="col">Address</th>
                            <th scope="col">Confirm registration</th>
                            <th scope="col">Cancel registration</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.hotels}" var="hotel">
                            <tr>
                                <th scope="row">${hotel.value.id}</th>
                                <td>${hotel.value.legalName}</td>
                                <td>${hotel.value.brand}</td>
                                <td>${hotel.value.address.country}, ${hotel.value.address.city} city,
                                        ${hotel.value.address.street} street, house ${hotel.value.address.house},
                                    building ${hotel.value.address.building}</td>
                                <c:choose>
                                    <c:when test="${hotel.value.registered}">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/frontController?command=confirm_hotel_registration&id=${hotel.value.id}"
                                               class="btn btn-success disabled"><span class="iconify"
                                                                             data-icon="line-md:confirm-circle"></span>
                                                Confirm
                                                registration</a></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/frontController?command=cancel_hotel_registration&id=${hotel.value.id}"
                                               class="btn btn-danger">
                                                <span class="iconify"
                                                      data-icon="fluent:calendar-cancel-24-regular"></span>
                                                Cancel registration
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/frontController?command=confirm_hotel_registration&id=${hotel.value.id}"
                                               class="btn btn-success"><span class="iconify"
                                                                             data-icon="line-md:confirm-circle"></span>
                                                Confirm
                                                registration</a></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/frontController?command=cancel_hotel_registration&id=${hotel.value.id}"
                                               class="btn btn-danger disabled">
                                                <span class="iconify"
                                                      data-icon="fluent:calendar-cancel-24-regular"></span>
                                                Cancel registration
                                            </a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <c:if test="${requestScope.confirmRegistrationError}">
                            <div class="alert alert-danger alert-admin" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Confirm registration error!
                            </div>
                        </c:if>
                        <c:if test="${requestScope.cancelRegistrationError}">
                            <div class="alert alert-danger alert-admin" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Cancel registration error!
                            </div>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/userPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
