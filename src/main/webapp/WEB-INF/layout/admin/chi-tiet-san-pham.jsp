<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta charset="utf-8">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">ChiTietSanPham</h1>
    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
        For more information about DataTables, please visit the
        <a target="_blank"
           href="https://datatables.net">official DataTables
            documentation</a>.</p>
    <br>
    <a href="/admin/productDetail/add" class="btn btn-success" style="width: 200px; margin-bottom: 50px">Add New</a>
    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ma San Pham</th>
                        <th>Ten</th>
                        <th>Danh Muc</th>
                        <th>NSX</th>
                        <th>Mau Sac</th>
                        <th>Nam BH</th>
                        <th>SL Ton</th>
                        <th>Gia Nhap</th>
                        <th>Gia Ban</th>
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
                    <c:forEach items="${chiTietSpPage.content}" var="sanPham">
                        <tr>
                            <td>${sanPham.id}</td>
                            <td>${sanPham.getSanPham().getMa()}</td>
                            <td>${sanPham.getSanPham().getTen()}</td>
                            <td>${sanPham.getDanhMuc().getTen()}</td>
                            <td>${sanPham.getNsx().getTen()}</td>
                            <td>${sanPham.getMauSac().getTen()}</td>
                            <td>${sanPham.getNamBh()}</td>
                            <td>${sanPham.getSoLuongTon()}</td>
                            <td>${sanPham.getGiaNhap()} $</td>
                            <td>${sanPham.getGiaBan()} $</td>

                            <td><a href="/admin/productDetail/detail?id=${sanPham.id}" class="btn btn-primary">Detail</a>
                                <a onclick="confirmRemove(${sanPham.id})" class="btn btn-danger">Remove</a>
                                <a href="/admin/productDetail/update?id=${sanPham.id}" class="btn btn-warning">Update</a>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div>
                    <ul class="nav tm-paging-links">
                        <c:forEach begin="1" end="${numPage}" var="i">
                            <li class="nav-item active"><a
                                    href="${pageContext.request.contextPath}/admin/table/chi-tiet-san-pham?curent_page=${i-1}"
                                    class="nav-link tm-paging-link">${i}</a></li>
                        </c:forEach>

                    </ul>
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
        function confirmRemove(itemId) {
            if (confirm("Are you sure you want to remove productDetail " + itemId + "?")) {
                window.location.href = "/admin/productDetail/remove?id=" + itemId;
            } else {
                window.location.href = "/admin/table/chi-tiet-san-pham";
            }
        }
    </script>

</div>