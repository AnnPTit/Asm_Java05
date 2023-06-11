<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="utf-8">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">${tilte}</h1>
    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
        For more information about DataTables, please visit the
        <a target="_blank"
           href="https://datatables.net">official DataTables
            documentation</a>.</p>
    <form:form action="/admin/${itemName}/update" method="post" modelAttribute="item">
        ID : <form:input path="id" class="input-group-text" readonly="true"/>
        <form:errors path="id" cssStyle="color: red"/>
        <br>
        Ma : <form:input path="ma" class="input-group-text" readonly="true"/>
        <form:errors path="ma" cssStyle="color: red"/>
        <br>
        Ten : <form:input path="ten" class="input-group-text"/>
        <form:errors path="ten" cssStyle="color: red"/>
        <br>
        <form:button type="submit" class="btn btn-success">Update</form:button>
    </form:form>
    <br>
</div>