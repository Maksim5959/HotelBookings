<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/hotelManager.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Hotel manager all rooms page</title>
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
        <div class="col-12 col-lg-8">
            <div class="container-fluid manage-result-con">
                <div class="container-fluid inner-manage-result-con">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Room number</th>
                            <th scope="col">Price</th>
                            <th scope="col">Category</th>
                            <th scope="col">Delete room</th>
                            <th scope="col">Update room</th>
                        </tr>
                        </thead>
                        <c:if test="${fn:length(requestScope.hotel.apartments) > 0}">
                        <tbody>
                            <c:forEach items="${requestScope.hotel.apartments}" var="apartment">
                                <tr>
                                    <th scope="row"><c:out value="${apartment.value.name}"/></th>
                                    <td><c:out value="${apartment.value.price}"/></td>
                                    <td><c:out value="${apartment.value.category.name}"/></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/frontController?command=delete_room&roomId=${apartment.value.id}"
                                           class="btn btn-danger"><span class="iconify" data-icon="ep:delete"></span>
                                            Delete room
                                        </a>
                                    </td>
                                    <td><!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                                data-bs-target="#exampleModal${apartment.value.id}">
                                            <span class="iconify" data-icon="icon-park-outline:update-rotation"></span>
                                            Update room
                                        </button>

                                        <!-- Modal -->
                                        <form action="${pageContext.request.contextPath}/frontController" method="post">
                                            <input type="hidden" name="command" value="update_room">
                                            <input type="hidden" name="hotelId" value="${requestScope.hotel.id}">
                                            <input type="hidden" name="apartmentId" value="${apartment.value.id}">
                                            <input type="hidden" name="comfortId" value="${apartment.value.comfort.id}">
                                            <div class="modal fade" id="exampleModal${apartment.value.id}" tabindex="-1"
                                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Room information</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                    aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="row">
                                                                <div class="col-6">
                                                                    <div class="form-floating">
                                                                        <input name="roomName" value="<c:out value="${apartment.value.name}"/>"
                                                                               type="text" class="form-control" id="roomName"
                                                                               placeholder="Room name*" required>
                                                                        <label for="roomName">Room name*</label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="form-floating">
                                                                        <input name="price" value="<c:out value="${apartment.value.price}"/>"
                                                                               type="text" class="form-control" id="price"
                                                                               placeholder="Price*" required>
                                                                        <label for="price">Price*</label>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                            <div class="row">
                                                                <div class="col-12">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.category == 'STANDARD'}">
                                                                            <div class="form-floating">
                                                                                <select name="category" id="category"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option selected value="0">Standard room</option>
                                                                                    <option value="1">Double bed room</option>
                                                                                    <option value="2">Triple bed room</option>
                                                                                    <option value="3">Lux apartments</option>
                                                                                    <option value="4">President lux apartments
                                                                                    </option>
                                                                                </select>
                                                                                <label for="category">Category</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.category == 'DOUBLE'}">
                                                                            <div class="form-floating">
                                                                                <select name="category" id="category1"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">Standard room</option>
                                                                                    <option selected value="1">Double bed room
                                                                                    </option>
                                                                                    <option value="2">Triple bed room</option>
                                                                                    <option value="3">Lux apartments</option>
                                                                                    <option value="4">President lux apartments
                                                                                    </option>
                                                                                </select>
                                                                                <label for="category1">Category</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.category == 'TRIPLE'}">
                                                                            <div class="form-floating">
                                                                                <select name="category" id="category2"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">Standard room</option>
                                                                                    <option value="1">Double bed room</option>
                                                                                    <option selected value="2">Triple bed room
                                                                                    </option>
                                                                                    <option value="3">Lux apartments</option>
                                                                                    <option value="4">President lux apartments
                                                                                    </option>
                                                                                </select>
                                                                                <label for="category2">Category</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.category == 'LUX'}">
                                                                            <div class="form-floating">
                                                                                <select name="category" id="category3"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">Standard room</option>
                                                                                    <option value="1">Double bed room</option>
                                                                                    <option value="2">Triple bed room</option>
                                                                                    <option selected value="3">Lux apartments
                                                                                    </option>
                                                                                    <option value="4">President lux apartments
                                                                                    </option>
                                                                                </select>
                                                                                <label for="category3">Category</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.category == 'PRESIDENT_LUX'}">
                                                                            <div class="form-floating">
                                                                                <select name="category" id="category4"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">Standard room</option>
                                                                                    <option value="1">Double bed room</option>
                                                                                    <option value="2">Triple bed room</option>
                                                                                    <option value="3">Lux apartments</option>
                                                                                    <option selected value="4">President lux
                                                                                        apartments
                                                                                    </option>
                                                                                </select>
                                                                                <label for="category4">Category</label>
                                                                            </div>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-6">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.singleBedCount == 0}">
                                                                            <div class="form-floating">
                                                                                <select name="singleBedCount" id="singleBedCount0"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option selected value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="singleBedCount0">Single bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.singleBedCount == 1}">
                                                                            <div class="form-floating">
                                                                                <select name="singleBedCount" id="singleBedCount"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option selected value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="singleBedCount">Single bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.singleBedCount == 2}">
                                                                            <div class="form-floating">
                                                                                <select name="singleBedCount" id="singleBedCount1"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option selected value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="singleBedCount1">Single bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.singleBedCount == 3}">
                                                                            <div class="form-floating">
                                                                                <select name="singleBedCount" id="singleBedCount2"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option selected value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="singleBedCount2">Single bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.singleBedCount == 4}">
                                                                            <div class="form-floating">
                                                                                <select name="singleBedCount" id="singleBedCount3"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option selected value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="singleBedCount3">Single bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.singleBedCount == 5}">
                                                                            <div class="form-floating">
                                                                                <select name="singleBedCount" id="singleBedCount4"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option selected value="5">5</option>
                                                                                </select>
                                                                                <label for="singleBedCount4">Single bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </div>
                                                                <div class="col-6">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.doubleBedCount == 0}">
                                                                            <div class="form-floating">
                                                                                <select name="doubleBedCount" id="doubleBedCount0"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option selected value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="doubleBedCount0">Double bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.doubleBedCount == 1}">
                                                                            <div class="form-floating">
                                                                                <select name="doubleBedCount" id="doubleBedCount"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option selected value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="doubleBedCount">Double bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.doubleBedCount == 2}">
                                                                            <div class="form-floating">
                                                                                <select name="doubleBedCount" id="doubleBedCount1"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option selected value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="doubleBedCount1">Double bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.doubleBedCount == 3}">
                                                                            <div class="form-floating">
                                                                                <select name="doubleBedCount" id="doubleBedCount2"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option selected value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="doubleBedCount2">Double bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.doubleBedCount == 4}">
                                                                            <div class="form-floating">
                                                                                <select name="doubleBedCount" id="doubleBedCount3"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option selected value="4">4</option>
                                                                                    <option value="5">5</option>
                                                                                </select>
                                                                                <label for="doubleBedCount3">Double bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${apartment.value.doubleBedCount == 5}">
                                                                            <div class="form-floating">
                                                                                <select name="doubleBedCount" id="doubleBedCount4"
                                                                                        class="form-select form-select-lg mb-3"
                                                                                        aria-label="Default select example"
                                                                                        required>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>
                                                                                    <option value="3">3</option>
                                                                                    <option value="4">4</option>
                                                                                    <option selected value="5">5</option>
                                                                                </select>
                                                                                <label for="doubleBedCount4">Double bed
                                                                                    count</label>
                                                                            </div>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.tv}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="tv" class="form-check-input" type="checkbox"
                                                                                       id="tv"
                                                                                       value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="tv">tv</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="tv" class="form-check-input" type="checkbox"
                                                                                       id="tv1"
                                                                                       value="true">
                                                                                <label class="form-check-label"
                                                                                       for="tv1">tv</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.conditioner}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="conditioner" class="form-check-input" type="checkbox"
                                                                                       id="conditioner" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="conditioner">conditioner</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="conditioner" class="form-check-input" type="checkbox"
                                                                                       id="conditioner1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="conditioner1">conditioner</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.bar}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="bar" class="form-check-input" type="checkbox"
                                                                                       id="bar"
                                                                                       value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="bar">bar</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="bar" class="form-check-input" type="checkbox"
                                                                                       id="bar1"
                                                                                       value="true">
                                                                                <label class="form-check-label"
                                                                                       for="bar1">bar</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.refrigerator}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="refrigerator" class="form-check-input" type="checkbox"
                                                                                       id="refrigerator" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="refrigerator">refrigerator</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="refrigerator" class="form-check-input" type="checkbox"
                                                                                       id="refrigerator1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="refrigerator1">refrigerator</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.balcony}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="balcony" class="form-check-input" type="checkbox"
                                                                                       id="balcony" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="balcony">balcony</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="balcony" class="form-check-input" type="checkbox"
                                                                                       id="balcony1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="balcony1">balcony</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.jacuzzi}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="jacuzzi" class="form-check-input" type="checkbox"
                                                                                       id="jacuzzi" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="jacuzzi">jacuzzi</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="jacuzzi" class="form-check-input" type="checkbox"
                                                                                       id="jacuzzi1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="jacuzzi1">jacuzzi</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.breakfast}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="breakfast" class="form-check-input" type="checkbox"
                                                                                       id="breakfast" value="true" checked>
                                                                                <label class="form-check-label" for="breakfast">breakfast</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="breakfast" class="form-check-input" type="checkbox"
                                                                                       id="breakfast1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="breakfast1">breakfast</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.wifi}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="wifi" class="form-check-input" type="checkbox"
                                                                                       id="wifi" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="wifi">wi-fi</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="wifi" class="form-check-input" type="checkbox"
                                                                                       id="wifi1" value="true">
                                                                                <label class="form-check-label" for="wifi1">wi-fi</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.transfer}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="transfer" class="form-check-input" type="checkbox"
                                                                                       id="transfer" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="transfer">transfer</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="transfer" class="form-check-input" type="checkbox"
                                                                                       id="transfer1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="transfer1">transfer</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.parking}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="parking" class="form-check-input" type="checkbox"
                                                                                       id="parking" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="parking">parking</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="parking" class="form-check-input" type="checkbox"
                                                                                       id="parking1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="parking1">parking</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.swimmingPool}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="swimmingPool" class="form-check-input" type="checkbox"
                                                                                       id="swimmingPool" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="swimmingPool">swimming
                                                                                    pool</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="swimmingPool" class="form-check-input" type="checkbox"
                                                                                       id="swimmingPool1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="swimmingPool1">swimming
                                                                                    pool</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.gym}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="gym" class="form-check-input" type="checkbox"
                                                                                       id="gym"
                                                                                       value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="gym">gym</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="gym" class="form-check-input" type="checkbox"
                                                                                       id="gym1"
                                                                                       value="true">
                                                                                <label class="form-check-label"
                                                                                       for="gym1">gym</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.restaurant}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="restaurant" class="form-check-input" type="checkbox"
                                                                                       id="restaurant" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="restaurant">restaurant</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="resturant" class="form-check-input" type="checkbox"
                                                                                       id="restaurant1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="restaurant1">restaurant</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.pets}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="pets" class="form-check-input" type="checkbox"
                                                                                       id="pets" value="true" checked>
                                                                                <label class="form-check-label"
                                                                                       for="pets">pets</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="pets" class="form-check-input" type="checkbox"
                                                                                       id="pets1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="pets1">pets</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                                <div class="col-4">
                                                                    <c:choose>
                                                                        <c:when test="${apartment.value.comfort.accessibilityFeatures}">
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="accessibilityFeatures" class="form-check-input" type="checkbox"
                                                                                       id="accessibilityFeatures" value="true"
                                                                                       checked>
                                                                                <label class="form-check-label"
                                                                                       for="accessibilityFeatures">accessibility
                                                                                    features</label>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="form-check form-check-inline">
                                                                                <input name="accessibilityFeatures" class="form-check-input" type="checkbox"
                                                                                       id="accessibilityFeatures1" value="true">
                                                                                <label class="form-check-label"
                                                                                       for="accessibilityFeatures1">accessibility
                                                                                    features</label>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary"
                                                                    data-bs-dismiss="modal">
                                                                Close
                                                            </button>
                                                            <button type="submit" class="btn btn-primary">Update room</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        </c:if>
                    </table>
                    <c:if test="${requestScope.updateApartmentError}">
                        <div class="alert alert-danger alert-hotel-manager" role="alert">
                            <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Update apartment error!
                             The apartment must not be booked!
                        </div>
                    </c:if>
                    <c:if test="${requestScope.deleteApartmentError}">
                        <div class="alert alert-danger alert-hotel-manager" role="alert">
                            <span class="iconify" data-icon="akar-icons:triangle-alert"></span>  Delete apartment error!
                        </div>
                    </c:if>
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
