<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lend Book</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<h1>Lend Book</h1>
<h2>
    <a href="/books">Back to Book List</a>
</h2>
<form method="post">
    <div class="mb-3">
        <label class="form-label">Tên khách hàng</label>
        <input type="text" class="form-control" name="nameCustomer">
        <div class="form-text"></div>
    </div>
    <div class="mb-3">
        <label class="form-label">Địa chỉ</label>
        <input type="text" class="form-control" name="address">
    </div>
    <div class="mb-3">
        <label class="form-label">Số điện thoại</label>
        <input type="text" class="form-control" name="phone">
    </div>
    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="text" class="form-control" name="mail">
    </div>
    <div class="mb-3">
        <label class="form-label">Tên sách</label>
        <input type="text" class="form-control" name="nameBook" value="${requestScope["bookLend"].getName()}" readonly>
    </div>
    <div class="mb-3">
        <label class="form-label">Số lượng</label>
        <input type="number" class="form-control" name="amount" value="1" min="1" max="${requestScope["bookLend"].getQuantity()}">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
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
</div>
</body>
</html>
