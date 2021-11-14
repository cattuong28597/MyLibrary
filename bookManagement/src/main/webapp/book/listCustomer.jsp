<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show List Book</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3/css/bootstrap.min.css">
</head>
<body>

<form action="showCustomer">
    <div class="container">
        <h6>
            <a href="/books">Back to Book List</a>
        </h6>
        <h1>Customers</h1>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Mã khách hàng</th>
                <th scope="col">Tên khách hàng</th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">SĐT</th>
                <th scope="col">Email</th>
                <th scope="col">Tên sách</th>
                <th scope="col">Số lượng</th>
                <th scope="col" colspan="3" style="text-align: center">Give Back</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items='${requestScope["listCustomer"]}' var="customer">
                <tr>
                    <th scope="row">${customer.getId()}</th>
                    <td>${customer.getName()}</td>
                    <td>${customer.getAddress()}</td>
                    <td>${customer.getPhoneNumber()}</td>
                    <td>${customer.getMail()}</td>
                    <td>${customer.getNameBook()}</td>
                    <td>${customer.getAmount()}</td>
                    <td><a href="/books?action=deleteCustomer&id=${customer.getId()}">Back</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <c:if test= '${requestScope["errorLend"] != null}'>
                ***${requestScope["errorLend"]}
            </c:if>
        </div>
        <div>
            <c:if test= '${requestScope["errorAmount"] != null}'>
                ***${requestScope["errorAmount"]}
            </c:if>
        </div>
        <div>
            <c:if test= '${requestScope["successLend"] != null}'>
                ***${requestScope["successLend"]}
            </c:if>
        </div>
    </div>
</form>
</body>
</html>
