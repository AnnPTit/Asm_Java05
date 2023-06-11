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


    <form:form action="/admin/productDetail/update" method="post" modelAttribute="chiTietSp"
               enctype="multipart/form-data">
        <div class="container" style="display: flex ; margin: 0 20px">
            <div style="margin: 0 40px">
                ID :
                <br>
                <br>
                <form:input path="id" class="input-group-text" readonly="true"/>
                <form:errors path="id"/>
                San Pham :
                <br>
                <br>
                <form:select path="sanPham"
                             cssStyle="background-color:#d4ced3 ; min-width: 300px ; height: 40px ; border-radius: 15px">
                    <c:forEach items="${listSanPham}" var="sp">
                        <form:option value="${sp}" cssStyle="line-height: 30px">
                            ${sp.ma} - ${sp.ten}
                        </form:option>
                    </c:forEach>
                </form:select>

                <br>
                <br>
                Danh Muc :
                <br>
                <form:select path="danhMuc"
                             cssStyle="background-color:#d4ced3 ; min-width: 300px ; height: 40px ; border-radius: 15px">
                    <c:forEach items="${listDanhMuc}" var="dm">
                        <form:option value="${dm}" cssStyle="line-height: 30px"> ${dm.ma} - ${dm.ten}</form:option>
                    </c:forEach>
                </form:select>
                <br>
                <br>
                NSX :
                <br>
                <form:select path="nsx"
                             cssStyle="background-color:#d4ced3 ; min-width: 300px ; height: 40px ; border-radius: 15px">
                    <c:forEach items="${listNSX}" var="nsx">
                        <form:option value="${nsx}" cssStyle="line-height: 30px"> ${nsx.ma} - ${nsx.ten}</form:option>
                    </c:forEach>
                </form:select>
                <br>
                <br>
                Mau Sac :
                <br>
                <form:select path="mauSac"
                             cssStyle="background-color:#d4ced3 ; min-width: 300px ; height: 40px ; border-radius: 15px">
                    <c:forEach items="${listMauSac}" var="mau">
                        <form:option value="${mau}" cssStyle="line-height: 30px"> ${mau.ma} - ${mau.ten}</form:option>
                    </c:forEach>
                </form:select>
                <br>
                <br>
            </div>

            <div style="margin: 0 20px">
                Nam BH :
                <br>
                <br>
                <form:input path="namBh" class="input-group-text"/>
                <form:errors path="namBh"/>
                Mo Ta :
                <br>
                <br>
                <form:input path="mota" class="input-group-text"/>
                <form:errors path="mota"/>
                So Luong Ton :
                <br>
                <br>
                <form:input path="soLuongTon" class="input-group-text"/>
                <form:errors path="soLuongTon"/>
                Gia Nhap :
                <br>
                <br>
                <form:input path="giaNhap" class="input-group-text"/>
                <form:errors path="giaNhap"/>
                Gia Ban :
                <br>
                <br>
                <form:input path="giaBan" class="input-group-text"/>
                <form:errors path="giaBan"/>

            </div>
            <div>
                Hinh :
                <br>
                <br>
                <input type="file" name="hinh" multiple onchange="previewImage(event)"/>

                <div style="display: flex">
                    <div id="preview-container"></div>
                    <br>
                    <div>
                        <c:forEach items="${listHinh}" var="hinh">
                            <img src="${hinh.url}" alt="" style="width: 100px ; height: 100px">
                            <a href="/admin/productDetail/update/removePicture?id=${hinh.id}">x</a>
                            <input type="hidden" name="defaultImage" value="${hinh.url}">
                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>
        <form:button type="submit" class="btn btn-success">Update</form:button>
    </form:form>
    <c:if test="${not empty message}">
        <script>
            window.addEventListener("load", function () {
                alert("${message}");
            });
        </script>
    </c:if>

</div>