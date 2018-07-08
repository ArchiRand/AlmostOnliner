<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/custom.css" type="text/css"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Информация о покупках</title>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-3">
            <h2 class="text-center">Информация о покупках</h2>
        </div>
        <div class="col-md-3 offset-md-6">
            <a href="/admin" class="btn btn-light">Назад</a>
        </div>
    </div>
    <div class="row margin-top30">
        <div class="col-md-12">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Дата</th>
                        <th scope="col">Пользователь</th>
                        <th scope="col">Товары</th>
                        <th scope="col">Адрес</th>
                        <th scope="col">Номер телефона</th>
                        <th scope="col">Итого</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${adminCard.date}</td>
                        <td>${adminCard.email}</td>
                        <td>
                            <ul class="list-group">
                                <c:forEach items="${products}" var="product">
                                    <li class="list-group-item">${product.name} | ${product.price} BYN</li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td>
                            ${address.city} ${address.street}<br>
                            ${address.buildingNumber}<br>
                            ${address.postalCode}<br>
                        </td>
                        <td>${adminCard.phoneNumber}</td>
                        <td>${adminCard.fullPrice} BYN</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
