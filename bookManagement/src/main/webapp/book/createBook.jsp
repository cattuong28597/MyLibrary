<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Book</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3/css/bootstrap.min.css">
</head>
<body>
<h1>Create Book</h1>
<h2>
    <a href="/books">Back to Book List</a>
</h2>
<form method="post">
    <div class="mb-3">
        <label class="form-label">Mã Sách</label>
        <input type="text" class="form-control" id="ID" name="id_book">
        <div class="form-text">**VD: AA001</div>
    </div>
    <div class="mb-3">
        <label class="form-label">Name</label>
        <input type="text" class="form-control" id="name" name="name">
    </div>
    <div class="mb-3">
        <label class="form-label">Kind</label>
        <input type="text" class="form-control" id="kind" name="kind">
    </div>
    <div class="mb-3">
        <label class="form-label">Author</label>
        <input type="text" class="form-control" id="author" name="author">
    </div>
    <div class="mb-3">
        <label class="form-label">Quantity</label>
        <input type="text" class="form-control" id="quantity" name="quantity">
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
    <div>
        <c:if test= '${requestScope["error"] != null}'>
            ***${requestScope["error"]}
        </c:if>
    </div>
    <div>
        <c:if test= '${requestScope["errorID"] != null}'>
            ***${requestScope["errorID"]}
        </c:if>
    </div>
    <div>
        <c:if test= '${requestScope["errorQuantity"] != null}'>
            ***${requestScope["errorQuantity"]}
        </c:if>
    </div>
</form>
</body>
</html>
