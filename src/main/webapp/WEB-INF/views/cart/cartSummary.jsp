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
    <title>Cart Summary</title>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-3">
            <h2 class="text-center">Cart Summary</h2>
        </div>
    </div>
    <div class="alert alert-danger" style="display:none;" id="error">
        <p class="text-center">Не указан адрес пользователя! Невозможно законить операцию.</p>
    </div>
    <div class="row margin-top30">
        <div class="col-md-8 offset-md-2">
            <c:choose>
                <c:when test="${not empty cart.products}">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Название</th>
                            <th scope="col">Цена</th>
                            <th scope="col">Действие</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart.products}" var="product">
                            <tr>
                                <td>${product.name}</td>
                                <td>${product.price} BYN</td>
                                <td><a href="#" class="btn btn-danger" onclick="removeItemFromCart(${product.id})">Удалить</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </c:when>
                <c:otherwise>
                    <p class="text-center">Ваша корзина пуста</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="row margin-top30">
        <div class="col-md-6 offset-md-2">
            <c:choose>
                <c:when test="${not empty cart.products}">
                    <a href="#" onclick="finalize()" class="btn btn-success">Завершение покупки</a>
                </c:when>
                <c:otherwise>
                    <a href="#" class="btn btn-secondary disabled">Завершение покупки</a>
                </c:otherwise>
            </c:choose>

            <a href="/" class="btn btn-light">Назад</a>
        </div>
        <div class="col-md-4">
            <p>Итого: ${cart.fullPrice} BYN</p>
        </div>
    </div>
</div>
<script type="application/javascript">
    function removeItemFromCart(id) {
        var request = new XMLHttpRequest();
        var url="http://localhost:8080/cart/remove/" + id;
        var header ="${_csrf.headerName}";
        var token = "${_csrf.token}";


        request.open("POST", url, true);
        request.setRequestHeader(header, token);
        request.onreadystatechange = function(event) {
            if (request.readyState === 4 && (request.status === 200)) {
                var responseJSON = JSON.parse(request.responseText);
                if (responseJSON.completed = 'true') {
                    location.reload(true);
                } else {
                    alert("Возникла ошибка при попытке удалить товар из вашей корзины");
                }
            }
        }
        request.send();
    }

    function finalize() {
        var request = new XMLHttpRequest();
        var url="http://localhost:8080/cart/finalize";
        var header ="${_csrf.headerName}";
        var token = "${_csrf.token}";


        request.open("POST", url, true);
        request.setRequestHeader(header, token);
        request.onreadystatechange = function(event) {
            if (request.readyState === 4 && (request.status === 200)) {
                var responseJSON = JSON.parse(request.responseText);
                if (responseJSON.completed == "true") {
                    location.replace("http://localhost:8080/user")
                } else {
                    document.getElementById("error").style.display= 'block';
                }
            }
        }
        request.send();
    }
</script>
</body>
</html>
