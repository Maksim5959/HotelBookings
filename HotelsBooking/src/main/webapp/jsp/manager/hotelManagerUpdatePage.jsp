<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/hotelManager.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Hotel manager update page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-lg top-manager-container">
    <div class="row">
        <div class="col-12 col-lg-3">
            <div class="container-fluid manager-menu">
                <div class="container-fluid inner-manager-menu">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a class="link-flash"
                               href="${pageContext.request.contextPath}/frontController?command=get_hotel_manager_update_page">
                                <h1 class="lead menu-text">
                                    <span class="iconify" data-icon="fluent:dual-screen-update-20-filled"></span> Update
                                    hotel data
                                </h1>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a class="link-flash"
                               href="${pageContext.request.contextPath}/frontController?command=get_hotel_manager_all_rooms_page">
                                <h1 class="lead menu-text">
                                    <span class="iconify" data-icon="fluent:conference-room-28-regular"></span> All
                                    rooms
                                </h1>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a class="link-flash"
                               href="${pageContext.request.contextPath}/frontController?command=get_hotel_manager_add_new_room_page">
                                <h1 class="lead menu-text">
                                    <span class="iconify" data-icon="carbon:add-alt"></span> Add new room
                                </h1>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a class="link-flash"
                               href="${pageContext.request.contextPath}/frontController?command=get_hotel_manager_bookings_page">
                                <h1 class="lead menu-text">
                                    <span class="iconify" data-icon="simple-line-icons:calender"></span> Bookings
                                </h1>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-12 col-lg-9">
            <div class="container-fluid manage-result-con">
                <div class="container-fluid inner-manage-result-con">
                    <form action="${pageContext.request.contextPath}/frontController" method="post">
                        <input type="hidden" name="command" value="update_hotel_data">
                        <input type="hidden" name="hotelId" value="${requestScope.hotel.id}">
                        <input type="hidden" name="legalNameOld" value="${requestScope.hotel.legalName}">
                        <input type="hidden" name="brandOld" value="${requestScope.hotel.brand}">
                        <input type="hidden" name="starsOld" value="${requestScope.hotel.stars}">
                        <input type="hidden" name="countryOld" value="${requestScope.hotel.address.country}">
                        <input type="hidden" name="cityOld" value="${requestScope.hotel.address.city}">
                        <input type="hidden" name="streetOld" value="${requestScope.hotel.address.street}">
                        <input type="hidden" name="houseOld" value="${requestScope.hotel.address.house}">
                        <input type="hidden" name="buildingOld" value="${requestScope.hotel.address.building}">
                        <h4>Hotel information:</h4>
                        <div class="row">
                            <div class="col-4">
                                <div class="form-floating">
                                    <input value="<c:out value="${requestScope.hotel.legalName}"/>" name="legalName"
                                           type="text" class="form-control" id="legalName" placeholder="Legal name*"
                                           required>
                                    <label for="legalName">Legal name*</label>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-floating">
                                    <input value="<c:out value="${requestScope.hotel.brand}"/>" name="brand" type="text"
                                           class="form-control" id="brand" placeholder="Brand*" required>
                                    <label for="brand">Brand*</label>
                                </div>
                            </div>
                            <div class="col-4">
                                <c:choose>
                                    <c:when test="${requestScope.hotel.stars.starsCount == 1}">
                                        <div class="form-floating">
                                            <select name="stars" id="rating1" class="form-select form-select-lg mb-3"
                                                    aria-label="Default select example" required>
                                                <option selected value="0">1</option>
                                                <option value="1">2</option>
                                                <option value="2">3</option>
                                                <option value="3">4</option>
                                                <option value="4">5</option>
                                            </select>
                                            <label for="rating1">Rating</label>
                                        </div>
                                    </c:when>
                                    <c:when test="${requestScope.hotel.stars.starsCount == 2}">
                                        <div class="form-floating">
                                            <select name="stars" id="rating2" class="form-select form-select-lg mb-3"
                                                    aria-label="Default select example" required>
                                                <option value="0">1</option>
                                                <option selected value="1">2</option>
                                                <option value="2">3</option>
                                                <option value="3">4</option>
                                                <option value="4">5</option>
                                            </select>
                                            <label for="rating2">Rating</label>
                                        </div>
                                    </c:when>
                                    <c:when test="${requestScope.hotel.stars.starsCount == 3}">
                                        <div class="form-floating">
                                            <select name="stars" id="rating3" class="form-select form-select-lg mb-3"
                                                    aria-label="Default select example" required>
                                                <option value="0">1</option>
                                                <option value="1">2</option>
                                                <option selected value="2">3</option>
                                                <option value="3">4</option>
                                                <option value="4">5</option>
                                            </select>
                                            <label for="rating3">Rating</label>
                                        </div>
                                    </c:when>
                                    <c:when test="${requestScope.hotel.stars.starsCount == 4}">
                                        <div class="form-floating">
                                            <select name="stars" id="rating4" class="form-select form-select-lg mb-3"
                                                    aria-label="Default select example" required>
                                                <option value="0">1</option>
                                                <option value="1">2</option>
                                                <option value="2">3</option>
                                                <option selected value="3">4</option>
                                                <option value="4">5</option>
                                            </select>
                                            <label for="rating4">Rating</label>
                                        </div>
                                    </c:when>
                                    <c:when test="${requestScope.hotel.stars.starsCount == 5}">
                                        <div class="form-floating">
                                            <select name="stars" id="rating5" class="form-select form-select-lg mb-3"
                                                    aria-label="Default select example" required>
                                                <option value="0">1</option>
                                                <option value="1">2</option>
                                                <option value="2">3</option>
                                                <option value="3">4</option>
                                                <option selected value="4">5</option>
                                            </select>
                                            <label for="rating5">Rating</label>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <h4>Hotel address:</h4>
                        <div class="row">
                            <div class="col-2">
                                <div class="form-floating">
                                    <input value="<c:out value="${requestScope.hotel.address.country}"/>" name="country"
                                           type="text" class="form-control" id="country" placeholder="Country*" required>
                                    <label for="country">Country*</label>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="form-floating">
                                    <input value="<c:out value="${requestScope.hotel.address.city}"/>" name="city"
                                           type="text" class="form-control" id="city" placeholder="City*" required>
                                    <label for="city">City*</label>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-floating">
                                    <input value="<c:out value="${requestScope.hotel.address.street}"/>" name="street"
                                           type="text" class="form-control" id="street" placeholder="Street*" required>
                                    <label for="street">Street*</label>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="form-floating">
                                    <input value="<c:out value="${requestScope.hotel.address.house}"/>" name="house"
                                           type="text" class="form-control" id="house" placeholder="House*" required>
                                    <label for="house">House*</label>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="form-floating">
                                    <input value="<c:out value="${requestScope.hotel.address.building}"/>" name="building"
                                           type="text" class="form-control" id="building" placeholder="Building*" required>
                                    <label for="building">Building*</label>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary form-floating">Update hotel</button>
                        <a href="${pageContext.request.contextPath}/frontController?command=delete_hotel&hotelId=${requestScope.hotel.id}"
                           class="btn btn-danger form-floating">Delete hotel</a>
                        <c:if test="${requestScope.updateHotelError}">
                            <div class="alert alert-danger alert-hotel-manager" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Hotel update error!
                            </div>
                        </c:if>
                        <c:if test="${requestScope.deleteHotelError}">
                            <div class="alert alert-danger alert-hotel-manager" role="alert">
                                <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Hotel delete error!
                            </div>
                        </c:if>
                    </form>
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
