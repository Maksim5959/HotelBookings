<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profilePage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Profile page</title>
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
                        <input type="hidden" name="command" value="save_user_data">
                        <input type="hidden" name="id" value="${sessionScope.user.id}">
                        <h4>User personal details:</h4>
                        <div class="row">
                            <div class="col-4">
                                <div class="form-floating">
                                    <div class="form-floating">
                                        <input name="firstName" type="text" class="form-control" id="firstname" placeholder="Firstname"
                                               value="<c:out value="${sessionScope.user.firstName}"/>" required>
                                        <label for="firstname">Firstname*</label>
                                    </div>
                                </div>
                                <div class="form-floating">
                                    <input name="phoneNumber" type="tel" id="Phone" class="form-control info" placeholder="+375290000000"
                                           pattern="^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$"
                                           value="<c:out value="${sessionScope.user.phoneNumber}"/>" required/>
                                    <label for="Phone">Phone number</label>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-floating">
                                    <input name="lastName" type="text" class="form-control" id="lastname" placeholder="Lastname"
                                           value="<c:out value="${sessionScope.user.lastName}"/>" required>
                                    <label for="lastname">Lastname*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="dateOfBirth" type="date" class="form-control info" id="birthday" placeholder="Birthday"
                                           value="<c:out value="${sessionScope.user.dateOfBirth}"/>" required>
                                    <label for="birthday">Birthday*</label>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-floating">
                                    <input name="email" type="email" class="form-control" id="floatingInput"
                                           value="<c:out value="${sessionScope.user.email}"/>" placeholder="name@example.com" required>
                                    <label for="floatingInput">Email address*</label>
                                </div>
                                <c:choose>
                                    <c:when test="${sessionScope.user.gender == 'MALE'}">
                                        <div class="gender info">
                                            <div class="form-check form-check-inline">
                                                <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox1" value="MALE"
                                                       checked required>
                                                <label class="form-check-label" for="inlineCheckbox1">Male</label>
                                            </div>
                                            <div  class="form-check form-check-inline">
                                                <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox2" value="FEMALE" required>
                                                <label class="form-check-label" for="inlineCheckbox2">Female</label>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="gender info">
                                            <div class="form-check form-check-inline">
                                                <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox11" value="MALE" required>
                                                <label class="form-check-label" for="inlineCheckbox11">Male</label>
                                            </div>
                                            <div  class="form-check form-check-inline">
                                                <input name="gender" class="form-check-input" type="radio" id="inlineCheckbox21" value="FEMALE"
                                                       checked required>
                                                <label class="form-check-label" for="inlineCheckbox21">Female</label>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Save personal details</button>
                        <a href="${pageContext.request.contextPath}/frontController?command=delete_user"
                           onclick="return confirm('Are you sure you want to delete your account?')" class="btn btn-danger">Delete account</a>
                        <c:if test="${requestScope.saveError}">
                            <div class="alert alert-danger alert-update-user-data" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Duplicate email or save error!
                            </div>
                        </c:if>
                        <c:if test="${requestScope.userDeleteError}">
                            <div class="alert alert-danger alert-update-user-data" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Delete error!
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
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
