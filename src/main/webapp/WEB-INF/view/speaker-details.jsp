<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>TheEvent Bootstrap Template - Speaker Details</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="/assets/img/favicon.png" rel="icon">
    <link href="/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Raleway:300,400,500,700,800"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <!-- Vendor CSS Files -->
    <link href="/assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="/assets/css/style.css" rel="stylesheet">

    <!-- =======================================================
    * Template Name: TheEvent
    * Updated: Mar 10 2023 with Bootstrap v5.2.3
    * Template URL: https://bootstrapmade.com/theevent-conference-event-bootstrap-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>

<!-- ======= Header ======= -->
<jsp:include page="../layout/home/header.jsp"></jsp:include>
<!-- End Header -->

<main id="main" class="main-page">

    <!-- ======= Speaker Details Sectionn ======= -->
    <section id="speakers-details">
        <div class="container">
            <div class="section-header">
                <h2>Speaker Details</h2>
                <p>Praesentium ut qui possimus sapiente nulla.</p>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <%--                    <img src="../assets/img/speakers/1.jpg" alt="Speaker 1" class="img-fluid">--%>
                    <div id="carouselExample" class="carousel slide">
                        <div class="carousel-inner">
                            <c:forEach items="${chiTietSpList}" var="ctsp">
                                <c:forEach items="${ctsp.getListHinh()}" var="x">
                                    <div class="carousel-item active">
                                        <img src="../..${x.url}" class="d-block w-100" alt="con chim"
                                             style=" height: 400px ; object-fit: cover">
                                    </div>
                                </c:forEach>
                            </c:forEach>

                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample"
                                data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample"
                                data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="details">
                        <h2>${sanpham.getTen()}</h2>
                        Classify :
                        <c:forEach items="${chiTietSpList}" var="ctsp">
                            <br>
                            <input type="radio" name="phanLoai" id="radio_${ctsp.getId()}" data-ctsp-id="${ctsp.id}"
                                   data-ctsp-soluong="${ctsp.soLuongTon}" data-ctsp-gia="${ctsp.giaBan}"/>
                            <span class="btn btn-secondary" style="margin: 10px 0"
                                  onclick="selectRadio('radio_${ctsp.getId()}')">
                                ${ctsp.getDanhMuc().getTen()} - ${ctsp.getMauSac().getTen()} - ${ctsp.getGiaBan()} $
                            </span>
                        </c:forEach>
                        <br>
                        <span style="line-height: 40px; padding: 0 10px ; color: #1cc88a ; font-size: 20px">${sanpham.getListCtsp().get(0).getMota()}</span>
                    </div>


                    <a type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#addToCart"
                       onclick="addToCart()" style="margin: 20px 0">
                        <i class="fa-solid fa-cart-shopping"></i> Add to cart
                    </a>
                    <a type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#buyNow"
                       onclick="buyNow()"
                       style="margin: 20px 0">
                        <i class="fa-solid fa-dollar-sign"></i> Buy now
                    </a>


                    <!-- Modal add to cart -->
                    <div class="modal fade" id="addToCart" tabindex="-1" aria-labelledby="addToCartLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 style="font-size: 24px">${sanpham.getTen()}</h1>
                                    <input type="hidden" id="ctspId" value="${ctsp.getId()}">
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <img src="../../${listHinh.get(0).url}" class="d-block w-100" alt="con chim"
                                         style="width: 100px; height: 400px;object-fit: cover"/>
                                    <br>
                                    Classify: <h2 id="addToCartLabel" class="modal-title fs-5"></h2>
                                    Quantity in stock: <h2 id="QuantityInStock" class="modal-title fs-5"></h2>
                                    Price: <h2 id="price" class="modal-title fs-5" style="color: red"></h2>

                                    <br>
                                    Quantity <input type="number" class="form-control"
                                                    id="quantityInput"
                                                    placeholder="Enter the quantity you want to buy">

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <a type="button" id="addToCartButton" class="btn btn-primary" onclick="addToCart()">Add
                                        to
                                        cart</a>
                                </div>
                            </div>
                        </div>

                    </div>
                    <%--   End modal add to cảrt--%>
                    <!-- Modal buy now -->
                    <div class="modal fade" id="buyNow" tabindex="-1" aria-labelledby="buyNowLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 style="font-size: 24px">${sanpham.getTen()}</h1>

                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <img src="../../${listHinh.get(0).url}" class="d-block w-100" alt="con chim"
                                         style="width: 100px; height: 400px; object-fit: cover"/>
                                    <br>
                                    <form action="/home/product/detail/buyNow" method="post">
                                        Classify: <h2 id="buyNowLabel" class="modal-title fs-5"></h2>
                                        Quantity in stock: <h2 id="buyNowQuantityInStock" class="modal-title fs-5"></h2>
                                        Price: <h2 id="buyNowPrice" class="modal-title fs-5" style="color: red"></h2>
                                        <input type="hidden" id="buyNowCtspId"  name="id">
                                        <br>
                                        Quantity <input type="number" class="form-control" id="buyNowQuantityInput"
                                                        placeholder="Enter the quantity you want to buy"
                                                        name="quantity">

<%--                                        Payment Method--%>
<%--                                        <br>--%>
<%--                                        <div class="form-check">--%>
<%--                                            <input class="form-check-input" type="radio" name="paymentMethod"--%>
<%--                                                   id="paymentMethodCOD"--%>
<%--                                                   value="COD" checked>--%>
<%--                                            <label class="form-check-label" for="paymentMethodCOD">--%>
<%--                                                COD--%>
<%--                                            </label>--%>
<%--                                        </div>--%>
<%--                                        <div class="form-check">--%>
<%--                                            <input class="form-check-input" type="radio" name="paymentMethod"--%>
<%--                                                   id="paymentMethodTransfer"--%>
<%--                                                   value="Transfer">--%>
<%--                                            <label class="form-check-label" for="paymentMethodTransfer">--%>
<%--                                                Transfer--%>
<%--                                            </label>--%>
<%--                                        </div>--%>

<%--                                        <br>--%>
<%--                                        Receiver's Name <input type="text" class="form-control" id="receiverNameInput"--%>
<%--                                                               placeholder="Enter receiver's name" name="tenNguoiNhan">--%>
<%--                                        Phone Number <input type="text" class="form-control" id="phoneNumberInput"--%>
<%--                                                            placeholder="Enter phone number" name="sdt">--%>
<%--                                        Address <input type="text" class="form-control" id="addressInput"--%>
<%--                                                       placeholder="Enter address" name="diaChi">--%>
                                        <button type="submit"
                                                id="buyNowButton"
                                                class="btn btn-primary"
                                        >Buy
                                            now
                                        </button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>

                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- End modal buy now -->


                </div>

            </div>
        </div>

    </section>

</main><!-- End #main -->

<!-- ======= Footer ======= -->
<jsp:include page="../layout/home/footer.jsp"></jsp:include>
<!-- End  Footer -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="/assets/vendor/aos/aos.js"></script>
<script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="/assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="/assets/js/main.js"></script>
<script src="/assets/js/custom.js"></script>
<script>
    function selectRadio(inputId) {
        var inputElement = document.getElementById(inputId);
        if (inputElement) {
            inputElement.checked = true;
        }
    }
</script>

<c:if test="${not empty message}">
    <script>
        window.addEventListener("load", function () {
            alert("${message}");
        });
    </script>
</c:if>

</body>

</html>