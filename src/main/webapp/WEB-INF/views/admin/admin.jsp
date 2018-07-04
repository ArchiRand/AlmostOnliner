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
    <title>Admin Panel</title>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-3">
            <h2 class="text-center">Админка</h2>
        </div>
        <div class="col-md-4 offset-md-5">
            <div class="btn-group">
                <a href="/admin/categorypanel" class="btn btn-info">Категории</a>
                <a href="/admin/productpanel" class="btn btn-info">Товары</a>
            </div>

        </div>
    </div>
    <div class="row margin-top30">
        <div class="col-md-10 offset-md-1">
            <h2 class="text-center">Совершенные покупки:</h2>
            <c:choose>
                <c:when test="${not empty allReports}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Дата</th>
                            <th scope="col">Пользователь</th>
                            <th scope="col">№ телефона</th>
                            <th scope="col">Итого</th>
                            <th scope="col">Действие</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${allReports}" var="report">
                            <tr>
                                <td>${report.date}</td>
                                <td>${report.email}</td>
                                <td>${report.phoneNumber}</td>
                                <td>${report.fullPrice} BYN</td>
                                <td><a href="/admin/cart/${report.id}" class="btn btn-primary">Просмотреть карзину</a></td>
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
