<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Register</title>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <sec:authorize access="isAuthenticated()">
                <c:redirect url="/"/>
            </sec:authorize>

            <form:form modelAttribute="registerForm">
                <form:errors path="*" element="div" cssClass="alert alert-danger"/>
                <div class="form-group">
                    <label for="email">Email</label>
                    <form:input type="email" id="email" path="email" cssClass="form-control"/><br>
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <form:input type="password" id="password" path="password" cssClass="form-control"/><br>
                </div>
                <div class="form-group">
                    <label for="password2">Повторить пароль</label>
                    <form:input type="password" id="password2" path="matchingPassword" cssClass="form-control"/><br>
                </div>
                <input type="submit" value="Зарегистрироваться"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>