<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${sessionScope.user == null}">
    <nav class="navbar navbar-light bg-light fixed-top">
        <div class="container-sm">
            <a href="${pageContext.request.contextPath}/jsp/main.jsp" class="navbar-brand">Navbar</a>
            <form class="d-flex">
                <a id="btn-reg" class="btn btn-primary" href="${pageContext.request.contextPath}/jsp/registrationUser.jsp">
                    <span class="iconify" data-icon="ic:baseline-app-registration"></span> Register</a>
                <a id="btn-log" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/jsp/login.jsp">
                    <span class="iconify" data-icon="uiw:login"></span> Login</a>
            </form>
        </div>
    </nav>
</c:if>
<c:if test="${sessionScope.user != null && sessionScope.user.role.name() == 'USER'}">
    <nav class="navbar navbar-light bg-light fixed-top">
        <div class="container-sm">
            <a href="${pageContext.request.contextPath}/jsp/main.jsp" class="navbar-brand">Navbar</a>
            <div class="menu">
                <c:choose>
                    <c:when test="${sessionScope.user.gender == 'MALE'}">
                        <div class="title" onclick="f()">
                            <span class="iconify" data-icon="et:profile-male"></span>  ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                            <span class="fa fa-bars"></span>
                            <div class="arrow"></div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="title" onclick="f()">
                            <span class="iconify" data-icon="et:profile-female"></span>  ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                            <span class="fa fa-bars"></span>
                            <div class="arrow"></div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="dropdown">
                    <a class="link-flash" href="${pageContext.request.contextPath}/jsp/user/userPersonalDetails.jsp"><p class="user-menu">Profile</p><span class="fa fa-bars"></span></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/frontController?command=get_user_bookings_page"><p class="user-menu">Bookings<span class="fa fa-bars"></span></p></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/jsp/user/registrationHotel.jsp"><p class="user-menu">Register hotel</p><span class="fa fa-bars"></span></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/frontController?command=logout"><p class="user-menu">Logout</p><span  class="fa fa-bars"></span></a>
                </div>
            </div>
        </div>
    </nav>
</c:if>
<c:if test="${sessionScope.user != null && sessionScope.user.role.name() == 'MANAGER'}">
    <nav class="navbar navbar-light bg-light fixed-top">
        <div class="container-sm">
            <a href="${pageContext.request.contextPath}/jsp/main.jsp" class="navbar-brand">HOTEL BOOKINGS</a>
            <div class="menu">
                <c:choose>
                    <c:when test="${sessionScope.user.gender == 'MALE'}">
                        <div class="title" onclick="f()">
                            <span class="iconify" data-icon="et:profile-male"></span>  ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                            <span class="fa fa-bars"></span>
                            <div class="arrow"></div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="title" onclick="f()">
                            <span class="iconify" data-icon="et:profile-female"></span>  ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                            <span class="fa fa-bars"></span>
                            <div class="arrow"></div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="dropdown">
                    <a class="link-flash" href="${pageContext.request.contextPath}/jsp/user/userPersonalDetails.jsp"><p class="user-menu">Profile</p><span class="fa fa-bars"></span></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/frontController?command=get_user_bookings_page"><p class="user-menu">Bookings<span class="fa fa-bars"></span></p></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/frontController?command=get_hotel_manager_update_page"><p class="user-menu">Manage hotel</p><span class="fa fa-bars"></span></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/frontController?command=logout"><p class="user-menu">Logout</p><span  class="fa fa-bars"></span></a>
                </div>
            </div>
        </div>
    </nav>
</c:if>
<c:if test="${sessionScope.user != null && sessionScope.user.role.name() == 'ADMIN'}">
    <nav class="navbar navbar-light bg-light fixed-top">
        <div class="container-sm">
            <a href="${pageContext.request.contextPath}/jsp/main.jsp" class="navbar-brand">Navbar</a>
            <div class="menu">
                <c:choose>
                    <c:when test="${sessionScope.user.gender == 'MALE'}">
                        <div class="title" onclick="f()">
                            <span class="iconify" data-icon="et:profile-male"></span>  ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                            <span class="fa fa-bars"></span>
                            <div class="arrow"></div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="title" onclick="f()">
                            <span class="iconify" data-icon="et:profile-female"></span>  ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                            <span class="fa fa-bars"></span>
                            <div class="arrow"></div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="dropdown">
                    <a class="link-flash" href="${pageContext.request.contextPath}/jsp/user/userPersonalDetails.jsp"><p class="user-menu">Profile</p><span class="fa fa-bars"></span></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/frontController?command=get_user_bookings_page"><p class="user-menu">Bookings<span class="fa fa-bars"></span></p></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/frontController?command=get_admin_all_users_page"><p class="user-menu">Admin dashboard</p><span class="fa fa-bars"></span></a>
                    <a class="link-flash" href="${pageContext.request.contextPath}/frontController?command=logout"><p class="user-menu">Logout</p><span  class="fa fa-bars"></span></a>
                </div>
            </div>
        </div>
    </nav>
</c:if>
