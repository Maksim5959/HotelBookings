<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/hotelRegistrationPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registrationAnimation.css">
    <title>Registration hotel</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-flex">

    <form action="${pageContext.request.contextPath}/frontController" method="post">
        <input type="hidden" name="command" value="register_hotel">
        <input type="hidden" name="id" value="${sessionScope.user.id}">
        <div class="row login-form">
            <div class="col-12 col-lg-4 inner-form2">
                <!--            animation-->
                <div class="text-container">
                    <p class="parent registration-animation">Register your hotel</p>
                    <p class="registration-animation">on hotels booking</p>
                    <p class="registration-animation">service!</p>
                </div>
            </div>
            <div class="col-12 col-lg-4 inner-form1">
                <h4 class="email">Personal data:</h4>
                <div class="form-floating">
                    <input type="email" name="email" class="form-control email" id="floatingInput1"
                           placeholder="name@example.com" value="<c:out value="${sessionScope.user.email}"/>"
                           disabled required>
                    <label for="floatingInput1">Email address*</label>
                </div>
                <div class="form-floating">
                    <input type="text" name="firstName" class="form-control info" id="firstname1"
                           placeholder="Firstname"
                           value="<c:out value="${sessionScope.user.firstName}"/>" disabled required>
                    <label for="firstname1">Firstname*</label>
                </div>
                <div class="form-floating">
                    <input type="text" name="lastName" class="form-control info" id="lastname1"
                           placeholder="Lastname"
                           value="<c:out value="${sessionScope.user.lastName}"/>" disabled required>
                    <label for="lastname1">Lastname*</label>
                </div>
                <c:choose>
                    <c:when test="${sessionScope.user.gender == 'MALE'}">
                        <div class="gender info">
                            <div class="form-check form-check-inline">
                                <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox11"
                                       value="MALE"
                                       checked disabled required>
                                <label class="form-check-label" for="inlineCheckbox11">Male*</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox21"
                                       value="FEMALE"
                                       disabled required>
                                <label class="form-check-label" for="inlineCheckbox21">Female*</label>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="gender info">
                            <div class="form-check form-check-inline">
                                <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox12"
                                       value="MALE"
                                       disabled required>
                                <label class="form-check-label" for="inlineCheckbox12">Male*</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox22"
                                       value="FEMALE"
                                       checked disabled required>
                                <label class="form-check-label" for="inlineCheckbox22">Female*</label>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="form-floating">
                    <input name="dateOfBirth" type="date" class="form-control info" id="birthday1"
                           placeholder="Birthday"
                           value="<c:out value="${sessionScope.user.dateOfBirth}"/>" disabled required>
                    <label for="birthday1">Date of birth*</label>
                </div>
                <div class="form-floating">
                    <input name="phoneNumber" type="tel" id="Phone1" class="form-control info"
                           placeholder="+375290000000"
                           pattern="^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$"
                           value="<c:out value="${sessionScope.user.phoneNumber}"/>" disabled required>
                    <label for="Phone1">Phone number</label>
                </div>
            </div>
            <div class="col-12 col-lg-4 inner-form3">
                <h4 class="email">Hotel data:</h4>
                <div class="form-floating email">
                    <input name="legalName" type="text" class="form-control" id="legalName" placeholder="Legal name*"
                           required>
                    <label for="legalName">Legal name*</label>
                </div>
                <div class="form-floating info">
                    <input name="brand" type="text" class="form-control" id="brand" placeholder="Brand*" required>
                    <label for="brand">Brand*</label>
                </div>

                <div class="form-floating info">
                    <select name="stars" id="rating" class="form-select form-select-lg mb-3"
                            aria-label="Default select example"
                            required>
                        <option value="0">1 star</option>
                        <option value="1">2 stars</option>
                        <option value="2">3 stars</option>
                        <option value="3">4 stars</option>
                        <option value="4">5 stars</option>
                    </select>
                    <label for="rating">Rating</label>
                </div>
                <h4 class="info">Address:</h4>
                <div class="form-floating">
                    <input name="country" type="text" class="form-control info" id="country" placeholder="Country*"
                           required>
                    <label for="country">Country*</label>
                </div>
                <div class="form-floating">
                    <input name="city" type="text" class="form-control info" id="city" placeholder="City*" required>
                    <label for="city">City*</label>
                </div>
                <div class="form-floating">
                    <input name="street" type="text" class="form-control info" id="street" placeholder="Street*"
                           required>
                    <label for="street">Street*</label>
                </div>
                <div class="form-floating">
                    <input name="house" type="text" class="form-control info" id="house" placeholder="House*" required>
                    <label for="house">House*</label>
                </div>
                <div class="form-floating">
                    <input name="building" type="text" class="form-control info" id="building" placeholder="Building*"
                           required>
                    <label for="building">Building*</label>
                </div>
                <button onclick="validatePassword()" class="w-100 btn btn-lg btn-primary btn-register-hotel"
                        type="submit">Register hotel
                </button>
                <c:if test="${requestScope.registrationError}">
                    <div class="alert alert-danger alert-danger-hotel-custom" role="alert">
                        <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Hotel registration error!
                    </div>
                </c:if>
            </div>
        </div>
    </form>
</div>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/userPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
