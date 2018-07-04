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
    <title>Product panel</title>
    <script type="application/javascript">
        function deleteProductWithId(id) {
            var confirmed = confirm("Вы уверены что хотите удалить этот товар?");
            var request = new XMLHttpRequest();
            var url="http://localhost:8080/admin/product/" + id + ".json";
            var header ="${_csrf.headerName}";
            var token = "${_csrf.token}";

            if (confirmed) {
                request.open("DELETE", url, true);
                request.setRequestHeader(header, token);
                request.onreadystatechange = function(event) {
                    if (request.readyState === 4 && (request.status === 200)) {
                        var responseJSON = JSON.parse(request.responseText);
                        if (responseJSON.deleted = 'true') {
                            location.reload(true);
                        } else {
                            alert("Возникли проблемы с удалением товара.");
                        }
                    }
                };
                request.send();
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-3">
            <h2 class="text-center">Товары</h2>
        </div>
        <div class="col-md-3 offset-md-6">
            <a href="/admin/product" class="btn btn-success">Новый продукт</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h4 class>Все товары: </h4>
            <c:choose>
                <c:when test="${not empty productList}">
                    <c:forEach items="${productList}" var="product">
                        <div class="card margin-top10">
                            <div class="card-header">
                                Категория: ${product.category.name} | Цена: ${product.price} BYN
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text">${product.description}</p>
                                <a href="#" onclick="deleteProductWithId(${product.id})"
                                   class="btn btn-danger">Удалить</a>
                                <a href="/admin/edit_product/${product.id}"
                                   class="btn btn-success">Редактировать</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="text-center">Список товаров пуст.</p>
                </c:otherwise>
            </c:choose>
            <p class="text-center"><a href="/admin/">Вернуться в админку</a></p>
        </div>
    </div>
</div>
</body>
</html>
