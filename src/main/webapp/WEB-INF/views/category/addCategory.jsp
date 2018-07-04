<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add category</title>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h2 class="text-center">Новая категория</h2>
            <form:form id="categoryForm" modelAttribute="categoryForm">
                <div class="form-group">
                    <form:errors path="name" element="div" cssClass="alert alert-danger"/>
                    <label for="category-name">Название</label>
                    <form:input id="category-name" path="name" cssClass="form-control"/>
                </div>
                <input type="submit" value="Добавить"/>
            </form:form>

            <p class="text-center"><a href="/admin/categorypanel">Вернуться назад</a></p>
        </div>
    </div>
</div>
</body>
</html>
