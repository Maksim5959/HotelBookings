<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Admin all users page</title>
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
                            <span class="iconify" data-icon="gis:globe-users"></span>  All users accounts</h1></a></li>
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/frontController?command=get_admin_all_hotels_page"><h1 class="lead menu-text">
                            <span class="iconify" data-icon="clarity:building-solid-alerted"></span>  All hotels accounts</h1></a></li>
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/frontController?command=get_admin_confirm_hotel_registration_page"><h1 class="lead menu-text">
                            <span class="iconify" data-icon="line-md:circle-to-confirm-circle-transition"></span>  Confirm hotels</h1></a></li>
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/frontController?command=get_add_admin_page"><h1 class="lead menu-text">
                            <span class="iconify" data-icon="carbon:add-alt"></span>  Add new admin</h1></a></li>
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
                            <th scope="col">Firstname</th>
                            <th scope="col">Lastname</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Delete user</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.users}" var="user">
                            <tr>
                                <th scope="row">${user.value.id}</th>
                                <td>${user.value.firstName}</td>
                                <td>${user.value.lastName}</td>
                                <td>${user.value.email}</td>
                                <td>${user.value.phoneNumber}</td>
                                <td><a href="${pageContext.request.contextPath}/frontController?command=admin_delete_user&id=${user.value.id}"
                                       onclick="return confirm('Are you sure?')"
                                       class="btn btn-danger"><span class="iconify" data-icon="ep:delete"></span> Delete user</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <c:if test="${requestScope.userDeleteError}">
                            <div class="alert alert-danger alert-admin" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Delete error!
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
