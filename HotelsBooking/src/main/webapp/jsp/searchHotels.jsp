<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/searchHotels.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Search page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-sm search-group">
    <div class="row">
        <div class="col-12 col-lg-3 search">
            <h5 class="search-inner">Search</h5>
            <form action="${pageContext.request.contextPath}/frontController" method="post">
                <input type="hidden" name="command" value="search_hotels">
                <input name="city" type="text" class="form-control search-inner" placeholder="Where are you going?"
                       aria-label="Where are you going?" aria-describedby="button-addon2"
                       value="<c:out value="${requestScope.hotelsSearchData.city}"/>" required>
                <input name="dateIn" type="date" class="form-control search-inner" placeholder="Check-in"
                       aria-label="Check-in" aria-describedby="button-addon2"
                       value="<c:out value="${requestScope.hotelsSearchData.dateIn}"/>"
                       required>
                <input name="dateOut" type="date" class="form-control search-inner" placeholder="Check-out"
                       aria-label="Check-out"
                       aria-describedby="button-addon2"
                       value="<c:out value="${requestScope.hotelsSearchData.dateOut}"/>" required>
                <select name="adults" class="form-select search-inner" aria-label="Example select with button addon"
                        required>
                    <option value="" disabled selected>Adults</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                </select>
                <select name="children" class="form-select search-inner" aria-label="Example select with button addon"
                        required>
                    <option value="" disabled selected>Children</option>
                    <option value="1">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                </select>
                <select name="rooms" class="form-select search-inner" aria-label="Example select with button addon"
                        required>
                    <option value="" disabled selected>Rooms</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                </select>
                <button class="btn btn-primary search-inner btn-search" type="submit">Search</button>
            </form>
        </div>
        <div class=" col-12 col-lg-9">
            <div class="result-header">
                <h2 class="hed-text"><c:out
                        value="${requestScope.hotelsSearchData.city}"/>: ${fn:length(requestScope.hotels)} properties
                    found</h2>
                <form action="${pageContext.request.contextPath}/frontController" method="post">
                    <input type="hidden" name="command" value="search_hotels">
                    <input type="hidden" name="city" value="${requestScope.hotelsSearchData.city}">
                    <input type="hidden" name="dateIn" value="${requestScope.hotelsSearchData.dateIn}">
                    <input type="hidden" name="dateOut" value="${requestScope.hotelsSearchData.dateOut}">
                    <input type="hidden" name="adults" value="${requestScope.hotelsSearchData.adultsCount}">
                    <input type="hidden" name="children" value="${requestScope.hotelsSearchData.childrenCount}">
                    <input type="hidden" name="rooms" value="${requestScope.hotelsSearchData.roomsCount}">
                    <div class="row">
                        <div class="col-9">
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${requestScope.hotelsSearchData.hotelsFilter == 'PRICE_UP'}">
                                        <input class="form-check-input" type="radio" name="hotelsFilter"
                                               id="inlineRadio1"
                                               value="price_up" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="radio" name="hotelsFilter"
                                               id="inlineRadio1"
                                               value="price_up">
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label" for="inlineRadio1">Price up</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${requestScope.hotelsSearchData.hotelsFilter == 'PRICE_DOWN'}">
                                        <input class="form-check-input" type="radio" name="hotelsFilter"
                                               id="inlineRadio2"
                                               value="price_down" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="radio" name="hotelsFilter"
                                               id="inlineRadio2"
                                               value="price_down">
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label" for="inlineRadio2">Price down</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${requestScope.hotelsSearchData.hotelsFilter == 'NAME_UP'}">
                                        <input class="form-check-input" type="radio" name="hotelsFilter" id="inlineRadio7"
                                               value="name_up" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="radio" name="hotelsFilter" id="inlineRadio7"
                                               value="name_up">
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label" for="inlineRadio7">Name up</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${requestScope.hotelsSearchData.hotelsFilter == 'NAME_DOWN'}">
                                        <input class="form-check-input" type="radio" name="hotelsFilter" id="inlineRadio8"
                                               value="name_down" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="radio" name="hotelsFilter" id="inlineRadio8"
                                               value="name_down">
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label" for="inlineRadio8">Name down</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${requestScope.hotelsSearchData.hotelsFilter == 'RATING_UP'}">
                                        <input class="form-check-input" type="radio" name="hotelsFilter"
                                               id="inlineRadio13"
                                               value="rating_up" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="radio" name="hotelsFilter"
                                               id="inlineRadio13"
                                               value="rating_up">
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label" for="inlineRadio13">Rating up</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${requestScope.hotelsSearchData.hotelsFilter == 'RATING_DOWN'}">
                                        <input class="form-check-input" type="radio" name="hotelsFilter"
                                               id="inlineRadio14"
                                               value="rating_down" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="radio" name="hotelsFilter"
                                               id="inlineRadio14"
                                               value="rating_down">
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label" for="inlineRadio14">Rating down</label>
                            </div>
                        </div>
                        <div class="col-3 filter-links-group">
                            <button id="clear-button" type="submit" class="btn btn-link filter-links">Clear filters
                            </button>
                            <button type="submit" class="btn btn-link filter-links">Apply filters</button>
                        </div>
                    </div>
                </form>
            </div>
            <c:forEach items="${requestScope.hotels}" var="hotel">
                <div class="result">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-4 col-12">
                                <div id="carouselExampleIndicators${hotel.value.id}" class="carousel slide"
                                     data-bs-ride="true">
                                    <div class="carousel-indicators">
                                        <button type="button"
                                                data-bs-target="#carouselExampleIndicators${hotel.value.id}"
                                                data-bs-slide-to="0" class="active" aria-current="true"
                                                aria-label="Slide 1"></button>
                                        <button type="button"
                                                data-bs-target="#carouselExampleIndicators${hotel.value.id}"
                                                data-bs-slide-to="1" aria-label="Slide 2"></button>
                                        <button type="button"
                                                data-bs-target="#carouselExampleIndicators${hotel.value.id}"
                                                data-bs-slide-to="2" aria-label="Slide 3"></button>
                                    </div>
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                            <img src="https://pix10.agoda.net/hotelImages/124/1246280/1246280_16061017110043391702.jpg?ca=6&ce=1&s=1024x768"
                                                 class="d-block w-100" alt="hotel"
                                                 style="width: 300px;height: 300px">
                                        </div>
                                        <div class="carousel-item">
                                            <img src="https://media-cdn.tripadvisor.com/media/photo-s/16/1a/ea/54/hotel-presidente-4s.jpg"
                                                 class="d-block w-100" alt="hotel"
                                                 style="width: 300px;height: 300px">
                                        </div>
                                        <div class="carousel-item">
                                            <img src="https://assets.hyatt.com/content/dam/hyatt/hyattdam/images/2022/04/12/1329/MUMGH-P0765-Inner-Courtyard-Hotel-Exterior-Evening.jpg/MUMGH-P0765-Inner-Courtyard-Hotel-Exterior-Evening.16x9.jpg"
                                                 class="d-block w-100" alt="hotel"
                                                 style="width: 300px;height: 300px">
                                        </div>
                                    </div>
                                    <button class="carousel-control-prev" type="button"
                                            data-bs-target="#carouselExampleIndicators${hotel.value.id}"
                                            data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button"
                                            data-bs-target="#carouselExampleIndicators${hotel.value.id}"
                                            data-bs-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Next</span>
                                    </button>
                                </div>
                            </div>
                            <div class="col-8 result-info">
                                <a href="#" class="brand">
                                    <h5><c:out value="${hotel.value.brand}"/></h5>
                                </a>
                                <h6 class="address"><c:out value="${hotel.value.address.street}"/> street,
                                    house <c:out value="${hotel.value.address.house}"/>,
                                    building <c:out value="${hotel.value.address.building}"/></h6>
                                <h6 class="address">rating
                                    <c:forEach begin="1" end="${hotel.value.stars.starsCount}" varStatus="loop">
                                        <span class="iconify" data-icon="emojione:star"></span>
                                    </c:forEach>
                                </h6>
                                <hr>
                                <h4>Price: from <c:out value="${requestScope.minHotelsPrices[hotel.value.id]}"/>$</h4>
                                <hr>
                                <a href="#">
                                    <h6>Enter the dates to see the current room rates</h6>
                                    <form action="${pageContext.request.contextPath}/frontController" method="post">
                                        <input type="hidden" name="command" value="get_hotel_page">
                                        <input type="hidden" name="id" value="${hotel.value.id}">
                                        <input type="hidden" name="dateIn"
                                               value="${requestScope.hotelsSearchData.dateIn}">
                                        <input type="hidden" name="dateOut"
                                               value="${requestScope.hotelsSearchData.dateOut}">
                                        <input type="hidden" name="minHotelPrice"
                                               value="${requestScope.minHotelsPrices[hotel.value.id]}">
                                        <input type="hidden" name="maxHotelPrice"
                                               value="${requestScope.maxHotelsPrices[hotel.value.id]}">
                                        <input type="hidden" name="category" value="all">
                                        <button class="btn btn-primary show-rooms-btn" type="submit">Show all rooms
                                        </button>
                                    </form>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/userPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/searchHotels.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
