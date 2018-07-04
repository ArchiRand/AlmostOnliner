<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/custom.css" type="text/css"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SpringShop</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <script type="text/javascript">
        function applySort() {
            var categories = document.getElementById("categories");
            var selected = categories.selectedOptions[0].value;
            var url = "http://localhost:8080/" + selected;
            location.replace(url);
        }

        function sortingBy() {
            var currentUrl = location.href;
            var urlObj = new URL(currentUrl);
            var category = urlObj.searchParams.get("categoryName");
            return category;
        }

        function displayCurrentCategory() {
            var categoryName = sortingBy();
            console.log(categoryName);
            if (categoryName !== null) {
                var textField = document.getElementById("category-name");
                textField.innerHTML = categoryName;
            }
        }

        function addItemToCart(id) {
            // Объект XMLHttpRequest (или, как его кратко называют, «XHR»)
            // дает возможность из JavaScript делать
            // HTTP-запросы к серверу без перезагрузки страницы.
            var request = new XMLHttpRequest();
            // указываем URL контроллера обрабатывающего данный запрос
            var url = "http://localhost:8080/cart/add/" + id;
            var header = "${_csrf.headerName}";
            var token = "${_csrf.token}";
            // Пост запрос на данный урл
            request.open("POST", url, true);
            request.setRequestHeader(header, token);

            // Хранит функцию (или имя функции),
            // которая вызывается автоматически каждый раз,
            // когда изменяется свойство readyState
            request.onreadystatechange = function (event) {
                console.log("Test1");
                // Если код ответа 200(Ок) и readyState = 4 (запрос завершен и ответ готов) то...
                if (request.readyState === 4 && (request.status === 200)) {
                    console.log("Test2");
                    // ... парсим ответ из контроллера...
                    var responseJSON = JSON.parse(request.responseText);
                    if (responseJSON.completed = 'true') {
                        // ...перезагружаем документ по текущему URL
                        location.reload(true);
                    } else {
                        alert("Возникла ошибка при добавлении товара в корзину.");
                    }
                }
            }
            // Отсылаем запрос
            request.send();
        }

        $(document).ready(
            function () {
                displayCurrentCategory();

                $.getJSON('http://localhost:8080/json/categories.json', {
                    ajax: 'true'
                }, function (data) {
                    var html = '<option value="?categoryName=All">-- Все --</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="?categoryName=' + data[i].name + '">'
                            + data[i].name + '</option>';
                    }
                    html += '</option>';
                    $('#categories').html(html);
                });
            });
    </script>
</head>
<body>
<div class="container">
    <sec:authorize access="hasRole('USER') && !hasRole('ADMIN')">
        <div class="row">
            <div class="col-md-12">
                <ul class="nav justify-content-end">
                    <li class="nav-item">
                        <span class="nav-link">Корзина: ${cartSize} </span>
                    </li>
                    <li class="nav-item">
                        <span class="nav-link">Сумма: $${cart.fullPrice} </span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/cart/summary">Оформить покупку</a>
                    </li>
                </ul>
            </div>
        </div>
    </sec:authorize>
    <div class="row">
        <div class="col-md-3">
            <h2 href="/">Почти онлайнер</h2>
        </div>
        <div class="col-md-9">
            <ul class="nav justify-content-end">
                <sec:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin">Админка</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/logout">Выйти</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('USER') && !hasRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="/user">Кабинет пользователя</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/logout">Выйти</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="!hasAnyRole('USER', 'ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="/user/login">Войти</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/register">Регистрация</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>

    <div class="row margin-top30">
        <div class="col-md-3 offset-md-1 ">
            <h5>Показать по категориям: </h5>
            <select id="categories"></select>
            <button type="button" onclick="applySort(); return false;">Показать</button>
        </div>
        <div class="col-md-7 offset-md-1">
            <h5>Вы сейчас смотрите: <span id="category-name">Все</span></h5>
            <c:choose>
                <c:when test="${not empty productList}">
                    <c:forEach items="${productList}" var="product">
                        <div class="card w-75 margin-top10">
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text">${product.description}</p>
                                <sec:authorize access="hasRole('USER')">
                                    <a href="#" class="btn btn-primary"
                                       onclick="addItemToCart(${product.id})">Купить</a>
                                </sec:authorize>

                                <sec:authorize access="!hasRole('USER')">
                                    <a href="/user/login" class="btn btn-primary">Войдите в систему чтобы купить</a>
                                </sec:authorize>
                            </div>
                            <div class="card-footer">
                                    ${product.price} BYN
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="text-center">Список покупок пуст</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
