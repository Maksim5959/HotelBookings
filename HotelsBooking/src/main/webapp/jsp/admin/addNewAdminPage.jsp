<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Add new admin page</title>
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
                <form action="${pageContext.request.contextPath}/frontController" method="post">
                    <input type="hidden" name="command" value="add_new_admin">
                    <div class="container-fluid inner-manage-result-con">
                        <h4 class="form-floating">Enter admin details:</h4>
                        <div class="row">
                            <div class="col-6">
                                <div class="form-floating">
                                    <input name="firstName" type="text" class="form-control" id="firstname" placeholder="Firstname" required>
                                    <label for="firstname">Firstname*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="email" type="email" class="form-control" id="floatingInput"
                                           placeholder="name@example.com" required>
                                    <label for="floatingInput">Email address*</label>
                                </div>
                                <div class="gender">
                                    <div class="form-check form-check-inline">
                                        <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox11"
                                               value="MALE" required>
                                        <label class="form-check-label" for="inlineCheckbox11">Male</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox21"
                                               value="FEMALE"
                                               required>
                                        <label class="form-check-label" for="inlineCheckbox21">Female</label>
                                    </div>
                                </div>
                                <div class="form-floating">
                                    <input onChange="onChange()" type="password" name="password" class="form-control info" id="password"
                                           placeholder="Password" required>
                                    <label for="password">Password*</label>
                                </div>
                                <div class="checkbox mb-3">
                                    <label>
                                        <input class="form-check-input show-password" type="checkbox"> Show password
                                    </label>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-floating">
                                    <input name="lastName" type="text" class="form-control" id="lastname" placeholder="Lastname" required>
                                    <label for="lastname">Lastname*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="phoneNumber" type="tel" id="Phone" class="form-control info"
                                           placeholder="+375290000000"
                                           pattern="^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$" required/>
                                    <label for="Phone">Phone number*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="dateOfBirth" type="date" class="form-control info" id="birthday"
                                           placeholder="Birthday" required>
                                    <label for="birthday">Birthday*</label>
                                </div>
                                <div class="form-floating">
                                    <input onChange="onChange()" name="confirm" type="password" class="form-control info"
                                           id="confirmPassword"
                                           placeholder="Confirm password" required>
                                    <label for="confirmPassword">Confirm password*</label>
                                </div>
                            </div>
                        </div>
                        <button class="btn btn-primary" type="submit"><span class="iconify" data-icon="carbon:add-alt"></span> Add new
                            admin
                        </button>
                        <c:if test="${requestScope.addAdminError}">
                            <div class="alert alert-danger alert-admin" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span> Add admin error!
                            </div>
                        </c:if>
                        <c:if test="${requestScope.adminAdded}">
                            <div class="alert alert-success alert-admin" role="alert">
                                <span class="iconify" data-icon="clarity:success-standard-line"></span>  Admin added!
                            </div>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/registerUser.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/userPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
