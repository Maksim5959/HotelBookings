<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profilePage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>User security page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-lg top-user-container">
    <div class="row">
        <div class="col-12 col-lg-3">
            <div class="container-fluid user-profile-menu">
                <div class="container-fluid inner-user-menu">
                    <ul class="list-group">
                        <li class="list-group-item"><a class="link-flash" href="${pageContext.request.contextPath}/jsp/user/userPersonalDetails.jsp"><h1 class="lead menu-text">
                            <span class="iconify" data-icon="bi:person-bounding-box"></span> Personal details</h1></a></li>
                        <li class="list-group-item"><a class="link-flash" href="${pageContext.request.contextPath}/jsp/user/userLocalDataPage.jsp"><h1 class="lead menu-text">
                            <span class="iconify" data-icon="entypo:address"></span> User local data</h1>
                        </a></li>
                        <li class="list-group-item"><a class="link-flash" href="${pageContext.request.contextPath}/jsp/user/userSecurityPage.jsp"><h1 class="lead menu-text">
                            <span class="iconify" data-icon="arcticons:eset-security"></span>  Security</h1></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-12 col-lg-9">
            <div class="container-fluid manage-result-con">
                <div class="container-fluid inner-manage-result-con">
                    <form action="${pageContext.request.contextPath}/frontController" method="post">
                        <input type="hidden" name="command" value="update_password">
                        <h4>Enter new password:</h4>
                        <div class="row">
                            <div class="col-6">
                                <div class="form-floating">
                                    <input onChange="onChange()" type="password" name="password" class="form-control info" id="password"
                                           placeholder="Password" required>
                                    <label for="password">Password*</label>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-floating">
                                    <input onChange="onChange()" name="confirm" type="password" class="form-control info"
                                           id="confirmPassword"
                                           placeholder="Confirm password" required>
                                    <label for="confirmPassword">Confirm password*</label>
                                </div>
                            </div>
                        </div>
                        <div class="checkbox mb-3">
                            <label>
                                <input class="form-check-input show-password" type="checkbox"> Show password
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary">Save password</button>
                        <c:if test="${requestScope.updatePasswordError}">
                            <div class="alert alert-danger alert-password-update" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span> Update password error!
                            </div>
                        </c:if>
                        <c:if test="${requestScope.successUpdate}">
                            <div class="alert alert-success alert-password-update" role="alert">
                                <span class="iconify" data-icon="clarity:success-standard-line"></span>  Password update successful!
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/userPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/registerUser.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
