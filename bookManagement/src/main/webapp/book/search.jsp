<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show List Book</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<h1>Book</h1>
<h2>
    <a href="/books">Back to List Book</a>
</h2>
<h2>
    Danh sách tìm kiếm
</h2>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">Mã sách</th>
        <th scope="col">Tên</th>
        <th scope="col">Thể loại</th>
        <th scope="col">Tác giả</th>
        <th scope="col">Số lượng</th>
        <th scope="col" colspan="3" style="text-align: center">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items='${requestScope["bookList"]}' var="book">
        <tr>
            <th scope="row">${book.getId()}</th>
            <td>${book.getName()}</td>
            <td>${book.getKind()}</td>
            <td>${book.getAuthor()}</td>
            <td>${book.getQuantity()}</td>
            <td><a href="/books?action=edit&id=${book.getId()}">edit</a></td>
            <td><a href="/books?action=delete&id=${book.getId()}">delete</a></td>
            <td><a href="/books?action=delete&id=${book.getId()}">lend</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>

