<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profilePage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>User payment data page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-lg top-user-container">
    <div class="row">
        <div class="col-12 col-lg-3">
            <div class="container-fluid user-profile-menu">
                <div class="container-fluid inner-user-menu">
                    <ul class="list-group">
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/jsp/user/userPersonalDetails.jsp">
                            <h1 class="lead menu-text">
                                <span class="iconify" data-icon="bi:person-bounding-box"></span> Personal details</h1>
                        </a></li>
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/jsp/user/userLocalDataPage.jsp">
                            <h1 class="lead menu-text">
                                <span class="iconify" data-icon="entypo:address"></span> User local data</h1>
                        </a></li>
                        <li class="list-group-item"><a class="link-flash"
                                                       href="${pageContext.request.contextPath}/jsp/user/userSecurityPage.jsp">
                            <h1 class="lead menu-text">
                                <span class="iconify" data-icon="arcticons:eset-security"></span> Security</h1></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-12 col-lg-9">
            <div class="container-fluid manage-result-con">
                <div class="container-fluid inner-manage-result-con">
                    <form action="${pageContext.request.contextPath}/frontController" method="post">
                        <input type="hidden" name="command" value="save_user_address">
                        <h4>User local data:</h4>
                        <div class="row">
                            <div class="col-4">
                                <div class="form-floating">
                                    <input name="country" type="text" class="form-control" id="country"
                                           placeholder="Country*"
                                           value="<c:out value="${sessionScope.user.address.country}"/>" required>
                                    <label for="country">Country*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="house" type="text" class="form-control" id="house" placeholder="House*"
                                           value="<c:out value="${sessionScope.user.address.house}"/>" required>
                                    <label for="house">House*</label>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-floating">
                                    <input name="city" type="text" class="form-control" id="city" placeholder="City*"
                                           value="<c:out value="${sessionScope.user.address.city}"/>" required>
                                    <label for="city">City*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="building" type="text" class="form-control" id="building"
                                           placeholder="Building*"
                                           value="<c:out value="${sessionScope.user.address.building}"/>" required>
                                    <label for="building">Building*</label>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-floating">
                                    <input name="street" type="text" class="form-control" id="street"
                                           placeholder="Street*"
                                           value="<c:out value="${sessionScope.user.address.street}"/>" required>
                                    <label for="street">Street*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="apartmentNumber" type="text" class="form-control" id="apartmentNumber"
                                           placeholder="Apartment number*"
                                           value="<c:out value="${sessionScope.user.address.apartmentNumber}"/>"
                                           required>
                                    <label for="apartmentNumber">Apartment number*</label>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Save address</button>
                        <c:choose>
                            <c:when test="${sessionScope.user.address.country != null}">
                                <a href="${pageContext.request.contextPath}/frontController?command=delete_user_address"
                                   class="btn btn-danger">Delete address</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/frontController?command=delete_user_address"
                                   class="btn btn-danger disabled">Delete address</a>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${requestScope.saveAddressError}">
                            <div class="alert alert-danger alert-address-user" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span> Save address error!
                            </div>
                        </c:if>
                        <c:if test="${requestScope.deleteAddressError}">
                            <div class="alert alert-danger alert-address-user" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span> Delete address
                                error!
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
