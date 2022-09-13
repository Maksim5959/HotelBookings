<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registrationAnimation.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registrationPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Register user</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-flex">
    <div class="row login-form">
        <div class="col-12 col-lg-6 inner-form2">
            <!--            animation-->
            <div class="text-container">
                <p class="parent registration-animation">Register your account</p>
                <p class="registration-animation">on hotels booking</p>
                <p class="registration-animation">service!</p>
            </div>
        </div>
        <div class="col-12 col-lg-6 inner-form1">
            <form action="${pageContext.request.contextPath}/frontController" method="post">
                <input type="hidden" name="command" value="register">
                <%--                Email--%>
                <div class="form-floating">
                    <input type="email" name="email" class="form-control email" id="floatingInput"
                           placeholder="name@example.com" required>
                    <label for="floatingInput">Email address*</label>
                </div>
                <%--                Firstname--%>
                <div class="form-floating">
                    <input type="text" name="firstName" class="form-control info" id="firstname" placeholder="Firstname"
                           required>
                    <label for="firstname">Firstname*</label>
                </div>
                <%--                Lastname--%>
                <div class="form-floating">
                    <input type="text" name="lastName" class="form-control info" id="lastname" placeholder="Lastname"
                           required>
                    <label for="lastname">Lastname*</label>
                </div>
                <%--                Gender--%>
                <div class="gender info">
                    <div class="form-check form-check-inline">
                        <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox1" value="MALE"
                               required>
                        <label class="form-check-label" for="inlineCheckbox1">Male*</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox2" value="FEMALE"
                               required>
                        <label class="form-check-label" for="inlineCheckbox2">Female*</label>
                    </div>
                </div>
                <%--                Date of birth--%>
                <div class="form-floating">
                    <label for="birthday"></label><input name="dateOfBirth" type="date" class="form-control info"
                                                         id="birthday"
                                                         placeholder="Birthday" required>
                    <label for="lastname">Date of birth*</label>
                </div>
                <%--                Phone number--%>
                <div class="form-floating">
                    <input name="phoneNumber" type="tel" id="Phone" class="form-control info"
                           placeholder="+375290000000"
                           pattern="^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$" required>
                    <label for="Phone">Phone number*</label>
                </div>
                <%--                Password--%>
                <div class="form-floating">
                    <input onChange="onChange()" type="password" name="password" class="form-control info" id="password"
                           placeholder="Password" required>
                    <label for="password">Password*</label>
                </div>
                <%--                Confirm password--%>
                <div class="form-floating">
                    <input onChange="onChange()" name="confirm" type="password" class="form-control info"
                           id="confirmPassword"
                           placeholder="Confirm password" required>
                    <label for="confirmPassword">Confirm password*</label>
                </div>
                <%--                Show password--%>
                <div class="checkbox mb-3">
                    <label>
                        <input class="form-check-input show-password" type="checkbox"> Show password
                    </label>
                </div>
                <button onclick="validatePassword()" class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
                <c:if test="${requestScope.registrationError}">
                    <div class="alert alert-danger alert-danger-custom" role="alert">
                        <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Duplicate email or registration error!
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/registerUser.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
