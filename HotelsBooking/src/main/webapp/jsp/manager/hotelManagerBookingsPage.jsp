<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/hotelManager.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Hotel manager bookings page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<div class="container-lg top-manager-container">
    <c:if test="${requestScope.deleteBookingError}">
        <div class="alert alert-danger alert-admin" role="alert">
            <span class="iconify" data-icon="akar-icons:triangle-alert"></span> Delete booking error!
        </div>
    </c:if>
    <c:if test="${requestScope.confirmError}">
        <div class="alert alert-danger alert-admin" role="alert">
            <span class="iconify" data-icon="akar-icons:triangle-alert"></span> Confirm booking error!
        </div>
    </c:if>
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
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Room name</th>
                            <th scope="col">Check in date</th>
                            <th scope="col">Check out date</th>
                            <th scope="col">Personal data</th>
                            <th scope="col">Confirm</th>
                            <th scope="col">Cancel</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.bookings}" var="bookings">
                            <tr>
                                <th scope="row">${bookings.value.apartment.name}</th>
                                <td>${bookings.value.dateIn}</td>
                                <td>${bookings.value.dateOut}</td>
                                <td>
                                    <!-- Button trigger modal -->
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                            data-bs-target="#exampleModal${bookings.value.id}">
                                        <span class="iconify" data-icon="carbon:user-avatar"></span> Personal data
                                    </button>

                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModal${bookings.value.id}" tabindex="-1"
                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">User personal data</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <h6>Firstname: ${bookings.value.user.firstName}</h6>
                                                    <h6>Lastname: ${bookings.value.user.lastName}</h6>
                                                    <h6>Email: ${bookings.value.user.email}</h6>
                                                    <h6>Phone number: ${bookings.value.user.phoneNumber}</h6>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">Close
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <c:choose>
                                    <c:when test="${bookings.value.confirm}">
                                        <td>
                                            <button class="btn btn-success" disabled><span class="iconify" data-icon="line-md:confirm-circle"></span>
                                                Confirm booking
                                            </button>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/frontController?command=hotel_manager_delete_booking&bookingId=${bookings.value.id}"
                                               class="btn btn-danger" disabled><span class="iconify" data-icon="material-symbols:cancel-outline"></span>
                                                Cancel booking
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/frontController?command=confirm_booking&checkInDate=${bookings.value.dateIn}&checkOutDate=${bookings.value.dateOut}&apartmentId=${bookings.value.apartment.id}&bookingId=${bookings.value.id}"
                                               class="btn btn-success"><span class="iconify" data-icon="line-md:confirm-circle"></span>
                                                Confirm booking
                                            </a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/frontController?command=hotel_manager_delete_booking&bookingId=${bookings.value.id}"
                                               class="btn btn-danger" disabled><span class="iconify" data-icon="material-symbols:cancel-outline"></span>
                                                Cancel booking
                                            </a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
