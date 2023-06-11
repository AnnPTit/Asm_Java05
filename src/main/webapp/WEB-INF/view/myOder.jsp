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
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/home/user/myOder?trangThai=1">Cho xac
                            nhan</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/home/user/myOder?trangThai=2">Van chuyen</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/home/user/myOder?trangThai=3">Dang giao</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/home/user/myOder?trangThai=4">Hoan Thanh</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/home/user/myOder?trangThai=5">Da Nhan</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/home/user/myOder?trangThai=0">Da Huy</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Shopping Cart-->
    <div class="table-responsive shopping-cart">

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

            <c:forEach items="${hoaDonChiTietList}" var="x">

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
                        <input type="checkbox" name="selectedItems" value="${x.id}"/>
                        <div class="count-input">

                            <input type="number" name="quantity" class="form-control form-control-user"
                                   value="${x.soLuong}"
                                   placeholder=""/>
                        </div>
                    </td>
                    <td class="text-center text-lg text-medium">${x.donGia} $</td>
                    <td class="text-center text-lg text-medium">${x.soLuong * x.donGia} $</td>
                    <td class="text-center">
                        <c:if test="${x.trangThai ==1}">
                            <a class="remove-from-cart" style="cursor: pointer"
                               onclick="confirmCancelProduct(${x.id})"
                               data-toggle="tooltip" title=""
                               data-original-title="Remove item"><i class="fa fa-trash"></i></a></c:if>
                        <c:if test="${x.trangThai ==4}">
                            <a class="remove-from-cart" style="cursor: pointer"
                               onclick="confirmTakeProduct(${x.hoaDon.id})"
                               data-toggle="tooltip" title=""
                               data-original-title="Remove item"><i class="fa fa-trash"></i></a></c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%--    <div class="shopping-cart-footer">--%>
    <%--        <div class="column">--%>
    <%--            <form class="coupon-form" method="post">--%>
    <%--                <input class="form-control form-control-sm" type="text" placeholder="Coupon code" required="">--%>
    <%--                <button class="btn btn-outline-primary btn-sm">Apply Coupon</button>--%>
    <%--            </form>--%>
    <%--        </div>--%>
    <fmt:formatNumber value="${total}" var="formattedTotal"/>
    <div class="column text-lg">Subtotal: <span class="text-medium"><c:out value="${formattedTotal}"/> $</span>
    </div>
</div>
<div class="shopping-cart-footer">
    <div class="column"><a class="btn btn-outline-secondary" href="/home"><i class="icon-arrow-left"></i>&nbsp;Back to
        Shopping</a></div>
    <%--        <div class="column"><a class="btn btn-primary">Update Cart</a>--%>
    <%--            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal"--%>
    <%--                    onclick="showSelectedItems()">Checkout--%>
    <%--            </button>--%>
</div>
</div>
</div>


<jsp:include page="../layout/home/footer.jsp"></jsp:include>
<script>
    function confirmCancelProduct(item) {
        if (confirm("Are you sure you want to confirm cancel bill  " + item + "?")) {
            window.location.href = "/home/myOder/huy-don-hang?id=" + item;
        } else {
            window.location.href = "/home/user/myOder?trangThai=1";
        }
    }
</script>
<script>
    function confirmTakeProduct(item) {
        if (confirm("This product in bill have ID  : " + item + ", Do you want confirm get this bill?")) {
            window.location.href = "/home/myOder/nhan-don-hang?id=" + item;
        } else {
            window.location.href = "/home/user/myOder?trangThai=5";
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
</body>
</html>