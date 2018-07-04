<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add product</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(document).ready(
            function () {
                $.getJSON('http://localhost:8080/json/categories.json', {
                    ajax: 'true'
                }, function (data) {
                    var html = '<option value="">--Выбрать--</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].id + '">'
                            + data[i].name + '</option>';
                    }
                    html += '</option>';
                    $('#product-categories').html(html);
                });
            });
    </script>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h2 class="text-center">Новый товар</h2>
            <form:form id="productForm" modelAttribute="product">
                <div class="form-group">
                    <form:errors path="name" element="div" cssClass="alert alert-danger"/>
                    <label for="product-name">Название</label>
                    <form:input id="product-name" path="name" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:errors path="description" element="div" cssClass="alert alert-danger"/>
                    <label for="product-description">Описание</label>
                    <form:textarea id="product-description" path="description" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:errors path="categoryId" element="div" cssClass="alert alert-danger"/>
                    <label for="product-categories">Категория</label>
                    <form:select id="product-categories" path="categoryId" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:errors path="price" element="div" cssClass="alert alert-danger"/>
                    <label for="product-price">Цена</label>
                    <form:input type="number" step="0.01" min="0" id="product-price" path="price"
                                cssClass="form-control"/>
                </div>
                <input type="submit" value="Добавить"/>
            </form:form>

            <p class="text-center"><a href="/admin/productpanel">Вернуться назад</a></p>
        </div>
    </div>
</div>
</body>
</html>