<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bookingHistory.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>User booking page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-lg top-booking-history-con">
    <c:if test="${requestScope.deleteBookingError}">
        <div class="alert alert-danger alert-admin" role="alert">
            <span class="iconify" data-icon="akar-icons:triangle-alert"></span> Delete booking error!
        </div>
    </c:if>
    <c:choose>
        <c:when test="${fn:length(requestScope.bookings) == 0}">
            <div class="container-fluid booking-history-inner-con">
                <h1 class="display-2 not-found-text">
                    <span class="iconify" data-icon="iconoir:file-not-found"></span>
                    Bookings not found!
                    <span class="iconify" data-icon="iconoir:file-not-found"></span>
                </h1>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${requestScope.bookings}" var="booking">
                <div class="container-fluid booking-history-inner-con">
                    <div class="row inner-row-booking-history">
                        <div class="col-3">
                            <img src="https://pix10.agoda.net/hotelImages/124/1246280/1246280_16061017110043391702.jpg?ca=6&ce=1&s=1024x768"
                                 class="img-fluid" style="width: 200px;height: 200px" alt="...">
                        </div>
                        <div class="col-4">
                            <h4><c:out value="${booking.value.hotel.brand}"/></h4>
                            <h6><c:out value="${booking.value.hotel.address.street}"/> street,
                                house <c:out value="${booking.value.hotel.address.house}"/>,
                                building <c:out value="${booking.value.hotel.address.building}"/></h6>
                            <h6>Rating:
                                <c:forEach begin="1" end="${booking.value.hotel.stars.starsCount}" varStatus="loop">
                                    <span class="iconify" data-icon="emojione:star"></span>
                                </c:forEach>
                            </h6>
                            <div class="row">
                                <div class="col-6">
                                    <h6>Total price:</h6>
                                </div>
                                <div class="col-6">
                                    <h6>${booking.value.totalPrice}$</h6>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <h6>Number of booking:</h6>
                                </div>
                                <div class="col-6">
                                    <h6>${booking.value.id}</h6>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <h6>Booking status:</h6>
                                </div>
                                <div class="col-6">
                                    <c:choose>
                                        <c:when test="${booking.value.confirm}">
                                            <h6><span class="badge bg-success">Active</span></h6>
                                        </c:when>
                                        <c:otherwise>
                                            <h6><span class="badge bg-danger">No active</span></h6>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="col-5">
                            <div class="row">
                                <div class="col-6">
                                    <div class="row">
                                        <div class="col-6">
                                            <h6>Check in date:</h6>
                                        </div>
                                        <div class="col-6">
                                            <h6>${booking.value.dateIn}</h6>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6">
                                            <h6>Check out date:</h6>
                                        </div>
                                        <div class="col-6">
                                            <h6>${booking.value.dateOut}</h6>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:choose>
                                            <c:when test="${booking.value.confirmTime != null}">
                                                <div class="col-6">
                                                    <h6>Booking confirm time:</h6>
                                                </div>
                                                <div class="col-6">
                                                    <h6>${booking.value.confirmTime}</h6>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-6">
                                                    <h6>Booking add time:</h6>
                                                </div>
                                                <div class="col-6">
                                                    <h6>${booking.value.addTime}</h6>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <a href="#">Free booking cancel in 24 hours of booking confirm</a>
                                </div>
                                <div class="col-6">
                                    <c:choose>
                                        <c:when test="${booking.value.confirm}">
                                            <a href="${pageContext.request.contextPath}/frontController?command=user_delete_booking&bookingId=${booking.value.id}&confirmTime=${booking.value.confirmTime}"
                                               class="btn btn-danger"><span class="iconify"
                                                                            data-icon="material-symbols:cancel-outline"></span>
                                                Cancel booking
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <button
                                                    class="btn btn-danger" disabled><span class="iconify"
                                                                                          data-icon="material-symbols:cancel-outline"></span>
                                                Cancel booking
                                            </button>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="/jsp/fragments/background.jsp" %>
<script src="https://code.iconify.design/2/2.1.2/iconify.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/userPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
