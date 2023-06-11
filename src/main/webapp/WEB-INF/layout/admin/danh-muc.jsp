<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="utf-8">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Danh Muc</h1>
    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
        For more information about DataTables, please visit the
        <a target="_blank"
           href="https://datatables.net">official DataTables
            documentation</a>.</p>
    <form:form action="/admin/category/add" method="post" modelAttribute="danhMuc">
        ID : <form:input path="id" class="input-group-text" disabled="true"/>
        <form:errors path="id" cssStyle="color: red"/>
        <br>
        Ma : <form:input path="ma" class="input-group-text"/>
        <form:errors path="ma" cssStyle="color: red"/>
        <span style="color: red">${ma_err}</span>
        <br>
        Ten : <form:input path="ten" class="input-group-text"/>
        <form:errors path="ten" cssStyle="color: red"/>
        <br>
        <form:button type="submit" class="btn btn-success">Add</form:button>
    </form:form>
    <br>
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
                        <th>Ma</th>
                        <th>Ten</th>
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
                    <c:forEach items="${danhMucPage.content}" var="dm">
                        <tr>
                            <td>${dm.id}</td>
                            <td>${dm.ma}</td>
                            <td>${dm.ten}</td>
                            <td><a href="/admin/category/detail?id=${dm.id}" class="btn btn-primary">Detail</a>
                                <a onclick="confirmRemove(${dm.id})" class="btn btn-danger">Remove</a>
                                <a href="/admin/category/update?id=${dm.id}" class="btn btn-warning">Update</a>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div>
                    <ul class="nav tm-paging-links">
                        <c:forEach begin="1" end="${numPage}" var="i">
                            <li class="nav-item active"><a
                                    href="${pageContext.request.contextPath}/admin/table/danh-muc?curent_page=${i-1}"
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
            if (confirm("Are you sure you want to remove category " + itemId + "?")) {
                window.location.href = "/admin/category/remove?id=" + itemId;
            } else {
                window.location.href = "/admin/table/danh-muc";
            }
        }
    </script>


</div>