
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Book</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<h1>Delete</h1>
<p>
    <a href="/books">Back to Book List</a>
</p>
<form method="post">
    <div class="mb-3">
        <label class="form-label">Name</label>
        <input type="text" class="form-control" value="${requestScope["bookDelete"].getName()}">
    </div>
    <div class="mb-3">
        <label class="form-label">Kind</label>
        <input type="text" class="form-control" value="${requestScope["bookDelete"].getKind()}">
    </div>
    <div class="mb-3">
        <label class="form-label">Author</label>
        <input type="text" class="form-control" value="${requestScope["bookDelete"].getAuthor()}">
    </div>
    <button type="submit" class="btn btn-primary">Delete</button>
</form>
</div>
</body>
</html>
