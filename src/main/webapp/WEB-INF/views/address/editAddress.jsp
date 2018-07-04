<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Редактировать адрес</title>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h2 class="text-center">Редактировать адрес</h2>
            <form:form modelAttribute="addressForm">
                <div class="form-group">
                    <form:errors path="city" element="div" cssClass="alert alert-danger"/>
                    <label for="city">Город:</label>
                    <form:input id="city" path="city" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:errors path="street" element="div" cssClass="alert alert-danger"/>
                    <label for="street">Улица:</label>
                    <form:input id="street" path="street" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:errors path="buildingNumber" element="div" cssClass="alert alert-danger"/>
                    <label for="buildingNumber">Номер дома</label>
                    <form:input id="buildingNumber" path="buildingNumber" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:errors path="postalCode" element="div" cssClass="alert alert-danger"/>
                    <label for="postalCode">Почтовый индекс</label>
                    <form:input id="postalCode" path="postalCode" cssClass="form-control"/>
                </div>
                <input type="submit" value="Сохранить"/>
            </form:form>
            <p class="text-center"><a href="/user">Назад</a></p>
        </div>
    </div>
</div>
</body>
</html>
