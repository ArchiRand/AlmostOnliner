<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/custom.css" type="text/css"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>User Profile</title>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h3>Личный кабинет <c:out value="${username}"/></h3>
        </div>
    </div>
    <div class="row margin-top30">
        <div class="col-md-4 offset-md-2">
            <div class="card" style="width: 18rem;">
                <div class="card-header">
                    Адрес:
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Город: <c:out value="${addressObj.city}"/></li>
                    <li class="list-group-item">Улица: <c:out value="${addressObj.street}"/></li>
                    <li class="list-group-item">Номер дома: <c:out value="${addressObj.buildingNumber}"/></li>
                    <li class="list-group-item">Почтовый индекс: <c:out value="${addressObj.postalCode}"/></li>
                </ul>
                <div class="card-footer">
                    <a href="user/address" class="btn btn-primary">Редактировать адрес</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card" style="width: 18rem;">
                <div class="card-header">
                    Номер телефона:
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Номер телефона: <c:out value="${phoneObj.number}"/></li>
                </ul>
                <div class="card-footer">
                    <a href="user/phone" class="btn btn-primary">Редактировать номер</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row margin-top30">
        <div class="col-md-8 offset-md-2">
            <h2 class="text-center">История покупок:</h2>
            <c:choose>
                <c:when test="${not empty userCarts}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Дата</th>
                            <th scope="col">Товары</th>
                            <th scope="col">Сумма</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userCarts}" var="cart">
                        <tr>
                            <td>${cart.date}</td>
                            <td>
                                <ul class="list-group">
                                    <c:forEach items="${cart.products}" var="product">
                                        <li class="list-group-item">${product.name} | $${product.price}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                            <td>$${cart.fullPrice}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="text-center">История покупок пуста</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
