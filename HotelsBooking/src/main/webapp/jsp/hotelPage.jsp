<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/hotelPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Hotel page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<c:if test="${requestScope.bookingPageError}">
    <div class="alert alert-danger alert-hotel-manager" role="alert">
        <span class="iconify" data-icon="akar-icons:triangle-alert"></span>Room does not exist!
    </div>
</c:if>
<div class="container-fluid name-info">
    <div class="row">
        <div class="col-12">
            <div class="container-fluid info-into">
                <div class="row">
                    <div class="col-12 col-lg-2 line">
                        <h5>Rating:</h5>
                        <h5>
                            <c:forEach begin="1" end="${requestScope.hotel.stars.starsCount}" varStatus="loop">
                                <span class="iconify" data-icon="emojione:star"></span>
                            </c:forEach>
                        </h5>
                    </div>
                    <div class="col-12 col-lg-7 line">
                        <h4><c:out value="${requestScope.hotel.brand}"/></h4>
                        <h6><c:out value="${requestScope.hotel.address.street}"/> street,
                            house <c:out value="${requestScope.hotel.address.house}"/>,
                            building <c:out value="${requestScope.hotel.address.building}"/></h6>
                    </div>
                    <div class="col-12 col-lg-3">
                        <h5>Price from <c:out value="${requestScope.minHotelPrice}"/>$</h5>
                        <a href="#bottom" class="btn btn-primary">Show rooms</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid con-slider">
    <div class="row">
        <div class="col-12 col-lg-4  form-slider">
            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide-to="0" class="active" aria-current="true"
                            aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="https://pix10.agoda.net/hotelImages/124/1246280/1246280_16061017110043391702.jpg?ca=6&ce=1&s=1024x768"
                             class="d-block w-100" alt="hotel" style="width: 300px;height: 400px">
                    </div>
                    <div class="carousel-item">
                        <img src="https://media-cdn.tripadvisor.com/media/photo-s/16/1a/ea/54/hotel-presidente-4s.jpg"
                             class="d-block w-100" alt="hotel" style="width: 300px;height: 400px">
                    </div>
                    <div class="carousel-item">
                        <img src="https://assets.hyatt.com/content/dam/hyatt/hyattdam/images/2022/04/12/1329/MUMGH-P0765-Inner-Courtyard-Hotel-Exterior-Evening.jpg/MUMGH-P0765-Inner-Courtyard-Hotel-Exterior-Evening.16x9.jpg"
                             class="d-block w-100" alt="hotel" style="width: 300px;height: 400px">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button"
                        data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button"
                        data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
        <div class="col-12 col-lg-8 form-filter">
            <div class="row info-into">
                <div class="col-12 col-lg-6 line">
                    <div class="filter">
                        <form action="${pageContext.request.contextPath}/frontController" method="post">
                            <input type="hidden" name="command" value="get_hotel_page">
                            <input type="hidden" name="minHotelPrice" value="${requestScope.minHotelPrice}">
                            <input type="hidden" name="maxHotelPrice" value="${requestScope.maxHotelPrice}">
                            <input type="hidden" name="id" value="${requestScope.hotel.id}">
                            <h4>Search in this hotel:</h4>
                            <select name="category" class="form-select search-inner input"
                                    aria-label="Example select with button addon">
                                <c:choose>
                                    <c:when test="${requestScope.apartmentSearchData.category == 'all'}">
                                        <option value="all" selected>All categories</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="all">All categories</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${requestScope.apartmentSearchData.category == 'standard'}">
                                        <option value="standard" selected>Standard rooms</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="standard">Standard rooms</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${requestScope.apartmentSearchData.category == 'double'}">
                                        <option value="double" selected>Double rooms</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="double">Double rooms</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${requestScope.apartmentSearchData.category == 'triple'}">
                                        <option value="triple" selected>Triple rooms</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="triple">Triple rooms</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${requestScope.apartmentSearchData.category == 'lux'}">
                                        <option value="lux" selected>Luxury rooms</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="lux">Luxury rooms</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${requestScope.apartmentSearchData.category == 'president_lux'}">
                                        <option value="president_lux" selected>President rooms</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="president_lux">President rooms</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <div class="row">
                                <div class="col-1">
                                    <h6>Price:</h6>
                                </div>
                                <div class="col-11 price-input">
                                    <div class="container">
                                        <div class="filter-item__range">
                                            <div class="multi-range">
                                                <input name="inputMinPrice" type="range"
                                                       min="<c:out value="${requestScope.minRangeHotelPrice}"/>"
                                                       max="<c:out value="${requestScope.maxRangeHotelPrice}"/>"
                                                       value="<c:out value="${requestScope.apartmentSearchData.minHotelPrice}"/>"
                                                       class="lower"
                                                       aria-label="">
                                                <input name="inputMaxPrice" type="range"
                                                       min="<c:out value="${requestScope.minRangeHotelPrice}"/>"
                                                       max="<c:out value="${requestScope.maxRangeHotelPrice}"/>"
                                                       value="<c:out value="${requestScope.apartmentSearchData.maxHotelPrice}"/>"
                                                       class="upper"
                                                       aria-label="">
                                            </div>
                                            <div class="result">
                                                <p>$<span class="result-l"><c:out
                                                        value="${requestScope.apartmentSearchData.minHotelPrice}"/></span>
                                                </p>
                                                <p>$<span class="result-u"><c:out
                                                        value="${requestScope.apartmentSearchData.maxHotelPrice}"/></span>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input name="dateIn" type="date" class="form-control input" placeholder="Check-in"
                                   aria-label="Check-in" value="${requestScope.apartmentSearchData.dateIn}"
                                   aria-describedby="button-addon2" required>
                            <input name="dateOut" type="date" class="form-control input" placeholder="Check-out"
                                   aria-label="Check-out" value="${requestScope.apartmentSearchData.dateOut}"
                                   aria-describedby="button-addon2" required>
                            <button type="submit" class="btn btn-primary input">Search rooms</button>
                        </form>
                    </div>
                </div>
                <div class="col-12 col-lg-6">
                    <div class="filter">
                        <h4>Check-in terms and conditions:</h4>
                        <br>
                        <div class="row">
                            <div class="col-6">
                                <h5>Check-in</h5>
                                <h5>15:00 — 00:00</h5>
                                <br>
                                <h5><span class="iconify" data-icon="ci:add-row"></span>  Additional bed:</h5>
                                <br>
                                <h5><span class="iconify" data-icon="mdi:cradle-outline"></span>  Cradle:</h5>
                            </div>
                            <div class="col-6">
                                <h5>Check-out</h5>
                                <h5>01:00 — 12:00</h5>
                                <br>
                                <h5>Needs to be clarified on the spot</h5>
                                <br>
                                <h5>Needs to be clarified on the spot</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a name="bottom"></a>
<c:forEach items="${requestScope.hotel.apartments}" var="apartment">
    <form action="${pageContext.request.contextPath}/frontController" method="post">
        <input type="hidden" name="command" value="get_booking_room_page">
        <input type="hidden" name="totalPrice" value="${requestScope.totalPrices[apartment.value.id]}">
        <input type="hidden" name="apartmentId" value="${apartment.value.id}">
        <input type="hidden" name="checkInDate" value="${requestScope.apartmentSearchData.dateIn}">
        <input type="hidden" name="checkOutDate" value="${requestScope.apartmentSearchData.dateOut}">
        <input type="hidden" name="hotelId" value="${requestScope.hotel.id}">
        <input type="hidden" name="nightsCount" value="${requestScope.nightsCount}">
        <div class="container-fluid con-rooms">
            <div class="row rooms-inner">
                <div class="col-12 col-lg-3 line">
                    <h3 class="text-block">${apartment.value.category.name}</h3>
                    <h4>Comfort:</h4>
                    <c:if test="${apartment.value.comfort.tv}">
                        <h5><span class="iconify" data-icon="fluent:tv-usb-24-regular"></span>TV</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.conditioner}">
                        <h5><span class="iconify" data-icon="iconoir:air-conditioner"></span>Conditioner</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.bar}">
                        <h5><span class="iconify" data-icon="carbon:bar"></span>Mini bar</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.refrigerator}">
                        <h5><span class="iconify" data-icon="gg:smart-home-refrigerator"></span>Refrigerator</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.balcony}">
                        <h5><span class="iconify" data-icon="mdi:balcony"></span>Balcony</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.jacuzzi}">
                        <h5><span class="iconify" data-icon="cil:bathroom"></span>Jacuzzi</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.breakfast}">
                        <h5><span class="iconify" data-icon="ic:outline-free-breakfast"></span>Breakfast</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.wifi}">
                        <h5><span class="iconify" data-icon="akar-icons:wifi"></span>Wi-fi</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.transfer}">
                        <h5><span class="iconify" data-icon="bx:taxi"></span>Transfer</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.parking}">
                        <h5><span class="iconify" data-icon="fa-solid:parking"></span>Parking</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.swimmingPool}">
                        <h5><span class="iconify" data-icon="fa-solid:swimming-pool"></span>Swimming pool</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.gym}">
                        <h5><span class="iconify" data-icon="gg:gym"></span>GYM</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.restaurant}">
                        <h5><span class="iconify" data-icon="carbon:restaurant-fine"></span>Restaurant</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.pets}">
                        <h5><span class="iconify" data-icon="dashicons:pets"></span>Pets</h5>
                    </c:if>
                    <c:if test="${apartment.value.comfort.accessibilityFeatures}">
                        <h5><span class="iconify" data-icon="el:wheelchair"></span>Accessibility features</h5>
                    </c:if>
                </div>
                <div class="col-12 col-lg-3 line">
                    <h3 class="text-block">Sleeps:</h3>
                    <h4>
                        Adults:
                        <c:forEach begin="1"
                                   end="${apartment.value.doubleBedCount * 2 + apartment.value.singleBedCount}"
                                   varStatus="loop">
                            <span class="iconify" data-icon="akar-icons:person"></span>
                        </c:forEach>
                    </h4>
                    <h6>
                        Children:
                        <c:forEach begin="1" end="${apartment.value.doubleBedCount + apartment.value.singleBedCount}"
                                   varStatus="loop">
                            <span class="iconify" data-icon="akar-icons:person"></span>
                        </c:forEach>
                    </h6>
                    <c:if test="${apartment.value.singleBedCount != 0}">
                        <h4>
                            Single bed count:
                            <c:forEach begin="1" end="${apartment.value.singleBedCount}"
                                       varStatus="loop">
                                <span class="iconify" data-icon="mdi:bed-single"></span>
                            </c:forEach>
                        </h4>
                    </c:if>
                    <c:if test="${apartment.value.doubleBedCount != 0}">
                        <h4>
                            Double bed count:
                            <c:forEach begin="1" end="${apartment.value.doubleBedCount}"
                                       varStatus="loop">
                                <span class="iconify" data-icon="icon-park-solid:double-bed"></span>
                            </c:forEach>
                        </h4>
                    </c:if>
                </div>
                <div class="col-12 col-lg-3 line">
                    <h3 class="text-block">
                        Price: ${requestScope.totalPrices[apartment.value.id]}$ from ${requestScope.nightsCount}
                        <c:choose>
                            <c:when test="${requestScope.nightsCount == 1}">
                                night
                            </c:when>
                            <c:otherwise>
                                nights
                            </c:otherwise>
                        </c:choose>
                    </h3>
                    <h6>Includes taxes and charges</h6>
                </div>
                <div class="col-12 col-lg-3">
                    <c:choose>
                        <c:when test="${sessionScope.user == null}">
                            <h5 class="text-block">Sing in to start booking!</h5>
                            <a href="${pageContext.request.contextPath}/jsp/registrationUser.jsp"><h6>sing up</h6></a>
                            <a href="${pageContext.request.contextPath}/jsp/login.jsp"><h6>sing in</h6></a>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-primary text-block">Booking this room now!</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </form>
</c:forEach>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/hotelPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/userPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
