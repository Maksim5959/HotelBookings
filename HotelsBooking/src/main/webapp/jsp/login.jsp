<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loginAnimation.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loginPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Login</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-flex">
    <div class="row login-form">
        <div class="col-12 col-lg-6 inner-form2">
            <!--            animation-->
            <div class="text-container">
                <p class="parent registration-animation">Login</p>
                <p class="registration-animation">on hotels booking</p>
                <p class="registration-animation">service!</p>
            </div>
        </div>
        <div class="col-12 col-lg-6 inner-form1">
            <form action="${pageContext.request.contextPath}/frontController" method="post">
                <input type="hidden" name="command" value="login">
                <%--                Email--%>
                <div class="form-floating">
                    <input type="email" name="email" class="form-control email" id="floatingInput" placeholder="name@example.com"
                           required>
                    <label for="floatingInput">Email address*</label>
                </div>

                <%--                Password--%>
                <div class="form-floating">
                    <input type="password" name="password" class="form-control password" id="password" placeholder="Password"
                           required>
                    <label for="password">Password*</label>
                </div>
                <div class="checkbox mb-3">
                    <label>
                        <input class="form-check-input show-password" type="checkbox"> Show password
                    </label>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
                <c:if test="${requestScope.loginError}">
                    <div class="alert alert-danger custom-login-alert" role="alert">
                        <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Invalid email or password!
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
