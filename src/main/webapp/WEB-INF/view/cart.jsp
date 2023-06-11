<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link href="/assets/img/favicon.png" rel="icon">
    <link href="/assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <link href="/assets/css/cart.css" rel="stylesheet">
    <link href="/assets/css/style.css" rel="stylesheet">
    <link href="/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<!-- ======= Header ======= -->
<jsp:include page="../layout/home/header.jsp"></jsp:include>
<!-- End Header -->
<div class="container padding-bottom-3x mb-1">
    <!-- Alert-->
    <div class="alert alert-info alert-dismissible fade show text-center" style="margin: 100px 0 30px 0;"><span
            class="alert-close" data-dismiss="alert"></span><img class="d-inline align-center"
                                                                 src="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iTGF5ZXJfMSIgeD0iMHB4IiB5PSIwcHgiIHZpZXdCb3g9IjAgMCA1MTIuMDAzIDUxMi4wMDMiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMi4wMDMgNTEyLjAwMzsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSIxNnB4IiBoZWlnaHQ9IjE2cHgiPgo8Zz4KCTxnPgoJCTxnPgoJCQk8cGF0aCBkPSJNMjU2LjAwMSw2NGMtNzAuNTkyLDAtMTI4LDU3LjQwOC0xMjgsMTI4czU3LjQwOCwxMjgsMTI4LDEyOHMxMjgtNTcuNDA4LDEyOC0xMjhTMzI2LjU5Myw2NCwyNTYuMDAxLDY0eiAgICAgIE0yNTYuMDAxLDI5OC42NjdjLTU4LjgxNiwwLTEwNi42NjctNDcuODUxLTEwNi42NjctMTA2LjY2N1MxOTcuMTg1LDg1LjMzMywyNTYuMDAxLDg1LjMzM1MzNjIuNjY4LDEzMy4xODQsMzYyLjY2OCwxOTIgICAgIFMzMTQuODE3LDI5OC42NjcsMjU2LjAwMSwyOTguNjY3eiIgZmlsbD0iIzUwYzZlOSIvPgoJCQk8cGF0aCBkPSJNMzg1LjY0NCwzMzMuMjA1YzM4LjIyOS0zNS4xMzYsNjIuMzU3LTg1LjMzMyw2Mi4zNTctMTQxLjIwNWMwLTEwNS44NTYtODYuMTIzLTE5Mi0xOTItMTkycy0xOTIsODYuMTQ0LTE5MiwxOTIgICAgIGMwLDU1Ljg1MSwyNC4xMjgsMTA2LjA2OSw2Mi4zMzYsMTQxLjE4NEw2NC42ODQsNDk3LjZjLTEuNTM2LDQuMTE3LTAuNDA1LDguNzI1LDIuODM3LDExLjY2OSAgICAgYzIuMDI3LDEuNzkyLDQuNTY1LDIuNzMxLDcuMTQ3LDIuNzMxYzEuNjIxLDAsMy4yNDMtMC4zNjMsNC43NzktMS4xMDlsNzkuNzg3LTM5Ljg5M2w1OC44NTksMzkuMjMyICAgICBjMi42ODgsMS43OTIsNi4xMDEsMi4yNCw5LjE5NSwxLjI4YzMuMDkzLTEuMDAzLDUuNTY4LTMuMzQ5LDYuNjk5LTYuNGwyMy4yOTYtNjIuMTQ0bDIwLjU4Nyw2MS43MzkgICAgIGMxLjA2NywzLjE1NywzLjU0MSw1LjYzMiw2LjY3Nyw2LjcyYzMuMTM2LDEuMDY3LDYuNTkyLDAuNjQsOS4zNjUtMS4yMTZsNTguODU5LTM5LjIzMmw3OS43ODcsMzkuODkzICAgICBjMS41MzYsMC43NjgsMy4xNTcsMS4xMzEsNC43NzksMS4xMzFjMi41ODEsMCw1LjEyLTAuOTM5LDcuMTI1LTIuNzUyYzMuMjY0LTIuOTIzLDQuMzczLTcuNTUyLDIuODM3LTExLjY2OUwzODUuNjQ0LDMzMy4yMDV6ICAgICAgTTI0Ni4wMTcsNDEyLjI2N2wtMjcuMjg1LDcyLjc0N2wtNTIuODIxLTM1LjJjLTMuMi0yLjExMi03LjMxNy0yLjM4OS0xMC42ODgtMC42NjFMOTQuMTg4LDQ3OS42OGw0OS41NzktMTMyLjIyNCAgICAgYzI2Ljg1OSwxOS40MzUsNTguNzk1LDMyLjIxMyw5My41NDcsMzUuNjA1TDI0Ni43LDQxMS4yQzI0Ni40ODcsNDExLjU2MywyNDYuMTY3LDQxMS44NCwyNDYuMDE3LDQxMi4yNjd6IE0yNTYuMDAxLDM2Mi42NjcgICAgIEMxNjEuOSwzNjIuNjY3LDg1LjMzNSwyODYuMTAxLDg1LjMzNSwxOTJTMTYxLjksMjEuMzMzLDI1Ni4wMDEsMjEuMzMzUzQyNi42NjgsOTcuODk5LDQyNi42NjgsMTkyICAgICBTMzUwLjEwMywzNjIuNjY3LDI1Ni4wMDEsMzYyLjY2N3ogTTM1Ni43NTksNDQ5LjEzMWMtMy40MTMtMS43MjgtNy41MDktMS40NzItMTAuNjg4LDAuNjYxbC01Mi4zNzMsMzQuOTIzbC0zMy42NDMtMTAwLjkyOCAgICAgYzQwLjM0MS0wLjg1Myw3Ny41ODktMTQuMTg3LDEwOC4xNi0zNi4zMzFsNDkuNTc5LDEzMi4yMDNMMzU2Ljc1OSw0NDkuMTMxeiIgZmlsbD0iIzUwYzZlOSIvPgoJCTwvZz4KCTwvZz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K"
                                                                 width="18" height="18" alt="Medal icon">&nbsp;&nbsp;With
        this purchase you will earn <strong>290</strong> Reward Points.

    </div>
    <!-- Shopping Cart-->
    <div class="table-responsive shopping-cart">
        <form action="/home/cart/pay" method="post">
            <table class="table">
                <thead>
                <tr>
                    <th>Product Name</th>
                    <th class="text-center">Quantity</th>
                    <th class="text-center">Price</th>
                    <th class="text-center">Subtotal</th>
                    <th class="text-center"><a class="btn btn-sm btn-outline-danger" href="#">Clear Cart</a></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${gioHangList}" var="x">

                    <tr>
                        <td>
                            <div class="product-item">
                                <a class="product-thumb" href="#"><img src="${x.chiTietSp.listHinh.get(0).url}"
                                                                       style="width: 100px; height: 100px;object-fit: cover"
                                                                       alt="Product"></a>
                                <div class="product-info">
                                    <h4 class="product-title"><a href="#">${x.chiTietSp.sanPham.ten}</a></h4>
                                    <span><em>Quantity:</em> ${x.soLuong}</span><span><em>Color:</em> ${x.chiTietSp.mauSac.ten}</span>
                                </div>
                            </div>
                        </td>

                        <td class="text-center">
                            <input type="checkbox" name="selectedItems"
                                   value="${x.id}"  ${checked.id == (x.chiTietSp.id) ? 'checked="checked"' : ''}/>
                            <div class="count-input">

                                <input type="number" name="quantity" class="form-control form-control-user"
                                       value="${x.soLuong}"
                                       placeholder=""/>
                            </div>
                        </td>
                        <td class="text-center text-lg text-medium">${x.donGia} $</td>
                        <td class="text-center text-lg text-medium">${x.soLuong * x.donGia} $</td>
                        <td class="text-center"><a class="remove-from-cart" style="cursor: pointer"
                                                   onclick="confirmRemoveCart(${x.id})"
                                                   data-toggle="tooltip" title=""
                                                   data-original-title="Remove item"><i class="fa fa-trash"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

                <button class="btn btn-outline-primary btn-sm" type="submit">Apply</button>
            </table>
        </form>
    </div>
    <div class="shopping-cart-footer">
        <div class="column">
            <form class="coupon-form" method="post">
                <input class="form-control form-control-sm" type="text" placeholder="Coupon code" required="">
                <button class="btn btn-outline-primary btn-sm">Apply Coupon</button>
            </form>
        </div>
        <fmt:formatNumber value="${total}" var="formattedTotal"/>
        <div class="column text-lg">Subtotal: <span class="text-medium"><span style="color: red; font-size: 25px"><c:out
                value="${formattedTotal}"/> </span>$</span>
        </div>
    </div>
    <div class="shopping-cart-footer">
        <div class="column"><a class="btn btn-outline-secondary" href="/home"><i class="icon-arrow-left"></i>&nbsp;Back
            to
            Shopping</a></div>
        <div class="column"><a class="btn btn-primary">Update Cart</a>
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal"
            >Checkout
            </button>
        </div>
    </div>
</div>

<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content container">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Pay</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="/home/cart/buy" method="post">
                <div class="modal-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Product Name</th>
                            <th class="text-center">Quantity</th>
                            <th class="text-center">Price</th>
                            <th class="text-center">Subtotal</th>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listGh}" var="x">
                            <tr>
                                <td>
                                    <div class="product-item">
                                        <a class="product-thumb" href="#"><img src="${x.chiTietSp.listHinh.get(0).url}"
                                                                               style="width: 100px; height: 100px;object-fit: cover"
                                                                               alt="Product"></a>
                                        <div class="product-info">
                                            <h4 class="product-title"><a href="#">${x.chiTietSp.sanPham.ten}</a></h4>
                                            <span><em>Color:</em> ${x.chiTietSp.mauSac.ten}</span>
                                        </div>
                                    </div>
                                </td>

                                <td class="text-center">
                                    <p>${x.soLuong}</p>
                                </td>
                                <td class="text-center text-lg text-medium">${x.donGia} $</td>
                                <td class="text-center text-lg text-medium">${x.soLuong * x.donGia} $</td>
                                <td class="text-center">
                                </td>
                            </tr>
                            <input type="hidden" name="id" value="${x.id}">
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                Payment Method
                <br>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="paymentMethod"
                           id="paymentMethodCOD"
                           value="COD" checked>
                    <label class="form-check-label" for="paymentMethodCOD">
                        COD
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="paymentMethod"
                           id="paymentMethodTransfer"
                           value="Transfer">
                    <label class="form-check-label" for="paymentMethodTransfer">
                        Transfer
                    </label>
                </div>

                <br>
                Receiver's Name <input type="text" class="form-control" id="receiverNameInput"
                                       placeholder="${user.ten}" name="tenNguoiNhan" value="${user.ten}">
                Phone Number <input type="text" class="form-control" id="phoneNumberInput"
                                    placeholder="${user.sdt}" name="sdt" value="${user.sdt}">
                Address <input type="text" class="form-control" id="addressInput"
                               placeholder="${user.diaChi}" name="diaChi" value="${user.diaChi}">
                <div class="modal-footer">
                    <p>Total : <span style="color: red">${total}</span> $ </p>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Buy</button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../layout/home/footer.jsp"></jsp:include>
<script>
    function confirmRemoveCart(item) {
        if (confirm("Remove cart " + item)) {
            window.location.href = "/home/cart/remove?id=" + item;
        } else {
            window.location.href = "/home/cart ";
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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
        integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
        crossorigin="anonymous"></script>
<script>
    function updateSelectedItems() {
        var checkboxes = document.getElementsByName('selectedItems');
        var selectedItems = [];

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selectedItems.push(checkboxes[i].value);
            }
        }
        document.getElementById('selectedItemsInput').value = selectedItems.join(',');
    }

    var checkboxes = document.getElementsByName('selectedItems');
    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].addEventListener('change', updateSelectedItems);
    }
</script>

<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        // Kiểm tra nếu URL hiện tại chứa "/home/cart" (được chuyển hướng từ việc thêm sản phẩm vào giỏ hàng)--%>
<%--        if (window.location.href.indexOf("/home/cart") > -1) {--%>
<%--            // Lấy giá trị của "selectedItems" từ URL (nếu có)--%>
<%--            const selectedItems = new URLSearchParams(window.location.search).getAll("selectedItems");--%>

<%--            // Duyệt qua tất cả các checkbox có tên "selectedItems"--%>
<%--            $("input[name='selectedItems']").each(function () {--%>
<%--                // Kiểm tra xem giá trị của checkbox có tồn tại trong danh sách selectedItems hay không--%>
<%--                if (selectedItems.includes($(this).val())) {--%>
<%--                    // Chọn checkbox nếu giá trị tồn tại trong danh sách selectedItems--%>
<%--                    $(this).prop("checked", true);--%>
<%--                }--%>
<%--            });--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>

</body>
</html>