<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userDropdownMenu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/background.css">
    <title>Main page</title>
</head>
<body>
<%@include file="/jsp/fragments/header.jsp" %>
<!--text-->
<div id="text-folder" class="container-fluid bg-light">
    <div class="text">
        <h1>Find your next stay</h1>
        <h3>Search low prices on hotels...</h3>
    </div>
</div>
<!--searcher-->
<div class="container-sm">
    <div class="row">
        <div class="input-win">
            <form class="input-group  mb-3" action="${pageContext.request.contextPath}/frontController" method="post">
                <input type="hidden" name="command" value="search_hotels">
                <input type="text" name="city" class="form-control" placeholder="Where are you going?"
                       aria-label="Where are you going?" aria-describedby="button-addon2" required>
                <input type="date" name="dateIn" class="form-control" placeholder="Check-in" aria-label="Check-in"
                       aria-describedby="button-addon2" id="check-in" required>
                <input type="date" name="dateOut" class="form-control" placeholder="Check-out" aria-label="Check-out"
                       aria-describedby="button-addon2" id="check-out" required>
                <select class="form-select" name="adults" aria-label="Example select with button addon" required>
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
                <select class="form-select" name="children" aria-label="Example select with button addon" required>
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
                <select class="form-select" name="rooms" aria-label="Example select with button addon" required>
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
                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Search</button>
            </form>
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
