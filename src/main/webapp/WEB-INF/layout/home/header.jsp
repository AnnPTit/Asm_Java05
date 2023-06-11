<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="header" class="d-flex align-items-center ">
    <div class="container-fluid container-xxl d-flex align-items-center">

        <div id="logo" class="me-auto">

            <a href="index.html" class="scrollto"><img src="/assets/img/logo.png" alt="" title=""></a>
        </div>

        <nav id="navbar" class="navbar order-last order-lg-0">
            <ul>
                <li><a class="nav-link scrollto active" href="/home">Home</a></li>
                <li><a class="nav-link scrollto" href="#about">About</a></li>
                <li><a class="nav-link scrollto" href="#speakers">Speakers</a></li>
                <li><a class="nav-link scrollto" href="#schedule">Schedule</a></li>
                <li><a class="nav-link scrollto" href="#venue">Venue</a></li>
                <li><a class="nav-link scrollto" href="#hotels">Hotels</a></li>
                <li><a class="nav-link scrollto" href="#gallery">Gallery</a></li>
                <li><a class="nav-link scrollto" href="#supporters">Sponsors</a></li>
                <li><a class="nav-link scrollto" href="#contact">Contact</a></li>
            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav><!-- .navbar -->
        <a class="buy-tickets scrollto" href="/home/user/myOder">My Order</a>
        <a href="/home/cart" type="button" class="buy-tickets scrollto position-relative">
            <i class="fa-solid fa-cart-shopping" style="color: white"></i>
            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                ${sessionScope.numOfProductInCart}
            </span>
        </a>
        <c:if test="${empty sessionScope.user}">
            <a class="buy-tickets scrollto" href="/login">Login</a>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <a class="buy-tickets scrollto" href="/logout">Logout</a>
            <span style="color: white ; font-size: 15px ; margin: 0 5px">Hello <span
                    style="color: #1cc88a;font-size: 15px">${sessionScope.name}</span></span>
        </c:if>

    </div>
</header>