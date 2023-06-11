<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta charset="utf-8">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">San Pham</h1>
    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
        For more information about DataTables, please visit the
        <a target="_blank"
           href="https://datatables.net">official DataTables
            documentation</a>.</p>
    <br>
    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Don Hang Moi
                <form action="/admin/table/don-hang" method="GET">
                    <select class="form-select" aria-label="Default select example" name="trangThaiDonHang">
                        <option selected value="-1">Trang Thai</option>
                        <option value="-1">Tat ca</option>
                        <option value="1">Cho Xac Nhan</option>
                        <option value="2">Da xac nhan</option>
                        <option value="3">Dang ship</option>
                        <option value="4">Hoan Thanh</option>
                        <option value="5">Da Nhan</option>
                        <option value="0">Da Huy</option>
                    </select>
                    <button type="submit">Submit</button>
                </form>
            </h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ma</th>
                        <th>Ten Khach Hang</th>
                        <th>Thoi Gian Dat Hang</th>
                        <th>Loai Thanh Toan</th>
                        <th>Ten Nguoi Nhan</th>
                        <th>SDT</th>
                        <th>Dia Chi</th>
                        <th>Trang Thai</th>
                        <th>Action</th>

                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Office</th>

                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach items="${hoaDonList}" var="hoaDon">
                        <tr>
                            <td>${hoaDon.id}</td>
                            <td>${hoaDon.maHD}</td>
                            <td>${hoaDon.taiKhoan.ten}</td>
                            <td>${hoaDon.ngayTao}</td>
                            <td>${hoaDon.loaiThanhToan}</td>
                            <td>${hoaDon.tenNguoiNhan}</td>
                            <td>${hoaDon.sdt}</td>
                            <td>${hoaDon.diaChi}</td>


                            <td>
                                <c:if test="${hoaDon.trangThai == 1}">
                                    <a onclick="OrderConfirmation(${hoaDon.id})" class="btn btn-primary">Cho Xac
                                        Nhan</a>
                                </c:if>

                                <c:if test="${hoaDon.trangThai == 2}">
                                    <a class="btn btn-success">Da Xac Nhan</a>
                                    <a onclick="ShipOrder(${hoaDon.id})" class="btn btn-primary">Ship Hang</a>
                                </c:if>
                                <c:if test="${hoaDon.trangThai == 3}">
                                    <a class="btn btn-success">Dang Ship</a>
                                    <a onclick="confirmBill(${hoaDon.id})" class="btn btn-primary">Hoan Thanh</a>
                                </c:if>
                                <c:if test="${hoaDon.trangThai == 4}">
                                    <a class="btn btn-success">Hoan Thanh</a>

                                </c:if>
                                <c:if test="${hoaDon.trangThai == 0}">
                                    <a class="btn btn-danger">Da Huy</a>

                                </c:if>
                                <c:if test="${hoaDon.trangThai == 5}">
                                    <a class="btn btn-info">Da Nhan</a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${hoaDon.trangThai < 2 && hoaDon.trangThai != 0}">
                                    <a class="btn btn-danger" onclick="confirmCancel(${hoaDon.id})">Huy</a>
                                </c:if>

                                <a href="/admin/table/don-hang/chi-tiet?id=${hoaDon.id}" class="btn btn-warning">Chi
                                    Tiet</a>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


                <div>
                    Total Items: ${totalItems} - Page ${currentPage} of ${totalPages}
                    &nbsp;
                    <c:forEach var="i" begin="0" end="${totalPages-1}">
                                <span>
                                        <a href="/admin/table/don-hang?pageNumber=${i}">${i}</a>
                                </span> &nbsp;&nbsp;
                    </c:forEach>

                    <c:choose>
                        <c:when test="${currentPage < totalPages}">
                            <a href="/admin/table/don-hang?pageNumber=${currentPage + 1}">Next</a>
                        </c:when>
                        <c:otherwise>
                            <span>Next</span>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${currentPage > 0}">
                            <a href="/admin/table/don-hang?pageNumber=${currentPage - 1}">Previous</a>
                        </c:when>
                        <c:otherwise>
                            <span>Previous</span>
                        </c:otherwise>
                    </c:choose>
                    <br><br>
                </div>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Chi Tiet Don Hang </h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Ma Hoa Don</th>
                                    <th>San Pham</th>
                                    <th>Danh Muc</th>
                                    <th>Mau Sac</th>
                                    <th>NSX</th>
                                    <th>So Luong</th>
                                    <th>Don Gia</th>
                                    <th>Trang Thai</th>


                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Name</th>
                                    <th>Position</th>
                                    <th>Office</th>
                                    <th>Total :${tongTien}$</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${hoaDonChiTietList}" var="hdct">
                                    <tr>
                                        <td>${hdct.id}</td>
                                        <td>${hdct.hoaDon.maHD}</td>
                                        <td>${hdct.chiTietSp.sanPham.ten}</td>
                                        <td>${hdct.chiTietSp.danhMuc.ten}</td>
                                        <td>${hdct.chiTietSp.mauSac.ten}</td>
                                        <td>${hdct.chiTietSp.nsx.ten}</td>
                                        <td>${hdct.soLuong}</td>
                                        <td>${hdct.donGia}</td>
                                        <c:choose>
                                            <c:when test="${hdct.chiTietSp.soLuongTon > 0}">
                                                <td>Con hang</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td style="color: red">Het hang</td>
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
    </div>
</div>
<c:if test="${not empty message}">
    <script>
        window.addEventListener("load", function () {
            alert("${message}");
        });
    </script>
</c:if>

<script>
    function OrderConfirmation(itemId) {
        if (confirm("Are you sure you want to confirm bill " + itemId + "?")) {
            window.location.href = "/admin/don-hang/xac-nhan-don-hang?id=" + itemId;
        } else {
            window.location.href = "/admin/table/don-hang";
        }
    }
</script>
<script>
    function ShipOrder(itemId) {
        if (confirm("Are you sure you want to confirm ship " + itemId + "?")) {
            window.location.href = "/admin/don-hang/van-chuyen-don-hang?id=" + itemId;
        } else {
            window.location.href = "/admin/table/don-hang";
        }
    }
</script>
<script>
    function confirmBill(itemId) {
        if (confirm("Are you sure you want to confirm finish bill " + itemId + "?")) {
            window.location.href = "/admin/don-hang/hoan-thanh-don-hang?id=" + itemId;
        } else {
            window.location.href = "/admin/table/don-hang";
        }
    }
</script>

<script>
    function confirmCancel(itemId) {
        if (confirm("Are you sure you want to confirm cancel  bill " + itemId + "?")) {
            window.location.href = "/admin/don-hang/huy-don-hang?id=" + itemId;
        } else {
            window.location.href = "/admin/table/don-hang";
        }
    }
</script>