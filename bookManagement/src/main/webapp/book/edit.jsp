
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<h1>Edit Book</h1>
<h2>
    <a href="/books">Back to Book List</a>
</h2>
<form method="post">
    <div class="mb-3">
        <label class="form-label">Mã Sách</label>
        <input type="text" class="form-control" id="ID" name="id_book" value="${requestScope["bookEdit"].getId()}" readonly>
    </div>
    <div class="mb-3">
        <label class="form-label">Name</label>
        <input type="text" class="form-control" id="name" name="name"
        value="${requestScope["bookEdit"].getName()}">
    </div>
    <div class="mb-3">
        <label class="form-label">Kind</label>
        <input type="text" class="form-control" id="kind" name="kind"
               value="${requestScope["bookEdit"].getKind()}">
    </div>
    <div class="mb-3">
        <label class="form-label">Author</label>
        <input type="text" class="form-control" id="author" name="author"
               value="${requestScope["bookEdit"].getAuthor()}">
    </div>
    <div class="mb-3">
        <label class="form-label">Quantity</label>
        <input type="text" class="form-control" id="quantity" name="quantity"
               value="${requestScope["bookEdit"].getQuantity()}">
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
    <div>
        <c:if test= '${requestScope["errorEdit"] != null}'>
            ***${requestScope["errorEdit"]}
        </c:if>
    </div>
    <div>
        <c:if test= '${requestScope["errorQuantity"] != null}'>
            ***${requestScope["errorQuantity"]}
        </c:if>
    </div>
    <div>
        <c:if test= '${requestScope["successEdit"] != null}'>
            ***${requestScope["successEdit"]}
        </c:if>
    </div>
</form>
</div>
</body>
</html>
