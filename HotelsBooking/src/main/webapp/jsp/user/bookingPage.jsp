<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bookingPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Booking page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-lg parent-container">
    <c:if test="${requestScope.bookingError}">
        <div class="alert alert-danger alert-hotel-manager" role="alert">
            <span class="iconify" data-icon="akar-icons:triangle-alert"></span>Booking error!
        </div>
    </c:if>
    <div class="row">
        <div class="col-12 col-lg-5">
            <div class="container-fluid booking-rooms-info">
                <div class="container inner-booking-rooms-info">
                    <h4>Your booking details:</h4>
                    <div class="row">
                        <div class="col-6">
                            <h5>Chek-in</h5>
                        </div>
                        <div class="col-6">
                            <h5>Chek-out</h5>
                        </div>
                    </div>
                    <div class="container-fluid line-bottom"></div>
                    <div class="row">
                        <div class="col-6 line-right">
                            <h6>${requestScope.checkInDate}</h6>
                            <h6>15:00 - 00:00</h6>
                        </div>
                        <div class="col-6">
                            <h6>${requestScope.checkOutDate}</h6>
                            <h6>01:00 - 12:00</h6>
                        </div>
                    </div>
                    <div class="container-fluid line-bottom"></div>
                    <h5>Your selected:</h5>
                    <div class="row">
                        <div class="col-8"><h6><c:out value="${requestScope.apartment.category.name}"/>:</h6>
                        </div>
                        <div class="col-4">
                            <h6 class="room-price-text">
                                ${requestScope.apartment.price}
                            </h6>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <c:if test="${requestScope.apartment.comfort.tv}">
                                <b><span class="iconify" data-icon="fluent:tv-usb-24-regular"></span>TV</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.conditioner}">
                                <b><span class="iconify" data-icon="iconoir:air-conditioner"></span>Conditioner</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.bar}">
                                <b><span class="iconify" data-icon="carbon:bar"></span>Mini bar</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.refrigerator}">
                                <b><span class="iconify" data-icon="gg:smart-home-refrigerator"></span>Refrigerator</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.balcony}">
                                <b><span class="iconify" data-icon="mdi:balcony"></span>Balcony</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.jacuzzi}">
                                <b><span class="iconify" data-icon="cil:bathroom"></span>Jacuzzi</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.breakfast}">
                                <b><span class="iconify" data-icon="ic:outline-free-breakfast"></span>Breakfast</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.wifi}">
                                <b><span class="iconify" data-icon="akar-icons:wifi"></span>Wi-fi</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.transfer}">
                                <b><span class="iconify" data-icon="bx:taxi"></span>Transfer</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.parking}">
                                <b><span class="iconify" data-icon="fa-solid:parking"></span>Parking</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.swimmingPool}">
                                <b><span class="iconify" data-icon="fa-solid:swimming-pool"></span>Swimming pool</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.gym}">
                                <b><span class="iconify" data-icon="gg:gym"></span>GYM</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.restaurant}">
                                <b><span class="iconify" data-icon="carbon:restaurant-fine"></span>Restaurant</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.pets}">
                                <b><span class="iconify" data-icon="dashicons:pets"></span>Pets</b>
                            </c:if>
                            <c:if test="${requestScope.apartment.comfort.accessibilityFeatures}">
                                <b><span class="iconify" data-icon="el:wheelchair"></span>Accessibility features</b>
                            </c:if>
                        </div>
                    </div>

                    <div class="container-fluid line-bottom"></div>
                    <div class="row total-price-row">
                        <div class="col-8">
                            <h6>Total price from ${requestScope.nightsCount} nights:</h6>
                        </div>
                        <div class="col-4">
                            <h6>$${requestScope.totalPrice}</h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 col-lg-7">
            <div class="container-fluid booking-hotel-info">
                <div class="container inner-booking-hotel-info">
                    <div class="row">
                        <div class="col-4">
                            <img src="https://pix10.agoda.net/hotelImages/124/1246280/1246280_16061017110043391702.jpg?ca=6&ce=1&s=1024x768"
                                 class="img-fluid" style="width: 200px;height: 200px" alt="...">
                        </div>
                        <div class="col-4">
                            <h4><c:out value="${requestScope.hotel.brand}"/></h4>
                            <h6><c:out value="${requestScope.hotel.address.street}"/> street,
                                house <c:out value="${requestScope.hotel.address.house}"/>,
                                building <c:out value="${requestScope.hotel.address.building}"/></h6>
                            <h6>Rating:
                                <c:forEach begin="1" end="${requestScope.hotel.stars.starsCount}" varStatus="loop">
                                    <span class="iconify" data-icon="emojione:star"></span>
                                </c:forEach>
                            </h6>
                        </div>
                        <div class="col-4">
                            <div class="row">
                                <div class="col-6">
                                    <h6>Total price:</h6>
                                </div>
                                <div class="col-6">
                                    <h6>${requestScope.totalPrice}$</h6>
                                </div>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1"
                                       value="option1" checked>
                                <label class="form-check-label" for="inlineRadio1">Without sale</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2"
                                       value="option2">
                                <label class="form-check-label" for="inlineRadio2">Use promo-code</label>
                            </div>
                            <div id="hide" style="display:none;">
                                <div class="col-auto">
                                    <label for="promo" class="visually-hidden">Password</label>
                                    <input type="text" class="form-control" id="promo" placeholder="PROMOCODE" required>
                                </div>
                                <div class="col-auto">
                                    <button type="submit" class="btn btn-primary mb-3">Confirm promo code</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid booking-hotel-info">
                <form action="${pageContext.request.contextPath}/frontController" method="post">
                    <input type="hidden" name="command" value="booking_room">
                    <input type="hidden" name="totalPrice" value="${requestScope.totalPrice}">
                    <input type="hidden" name="apartmentId" value="${requestScope.apartment.id}">
                    <input type="hidden" name="hotelId" value="${requestScope.hotel.id}">
                    <input type="hidden" name="checkInDate" value="${requestScope.checkInDate}">
                    <input type="hidden" name="checkOutDate" value="${requestScope.checkOutDate}">
                    <div class="container inner-booking-hotel-info">
                        <h4>Enter your details:</h4>
                        <div class="row">
                            <div class="col-6">
                                <div class="form-floating">
                                    <input name="firstName" value="${sessionScope.user.firstName}"
                                           type="text" class="form-control"
                                           id="firstname" placeholder="Firstname" disabled required>
                                    <label for="firstname">Firstname*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="email" value="<c:out value="${sessionScope.user.email}"/>"
                                           type="email" class="form-control" id="floatingInput"
                                           placeholder="name@example.com" disabled required>
                                    <label for="floatingInput">Email address*</label>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-floating">
                                    <input name="lastName" value="<c:out value="${sessionScope.user.lastName}"/>"
                                           type="text" class="form-control"
                                           id="lastname" placeholder="Lastname" disabled required>
                                    <label for="lastname">Lastname*</label>
                                </div>
                                <div class="form-floating">
                                    <input name="phoneNumber" value="<c:out value="${sessionScope.user.phoneNumber}"/>"
                                           type="tel" id="Phone" class="form-control info"
                                           placeholder="+375290000000"
                                           pattern="^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$" disabled required/>
                                    <label for="Phone">Phone number*</label>
                                </div>
                            </div>
                        </div>
                        <h4 class="form-floating">Enter your payment data:</h4>
                        <div class="row">
                            <div class="col-5">
                                <div class="form-floating">
                                    <input name="cardName" type="text" class="form-control"
                                           id="cardName" placeholder="JOHN DOE"
                                           pattern="^[A-Z ]*$" required>
                                    <label for="cardName">Card name*</label>
                                </div>
                            </div>
                            <div class="col-5">
                                <div class="form-floating">
                                    <input name="cardNumber" type="text" class="form-control"
                                           id="cardNumber" placeholder="0000111122223333"
                                           pattern="^[0-9]{4}[0-9]{4}[0-9]{4}[0-9]{4}$" required>
                                    <label for="cardNumber">Card number*</label>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="form-floating">
                                    <input name="cvv" type="password" class="form-control" id="cvv" placeholder="CVV" required>
                                    <label for="cvv">CVV*</label>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary booking-button">Booking this room</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bookingPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/userPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
