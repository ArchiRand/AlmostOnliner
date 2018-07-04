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
    <title>Login</title>
</head>
<body>
<div class="container">
    <div>
        <a href="/">На главную</a>
    </div>
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <sec:authorize access="isAuthenticated()">
                <c:redirect url="/"/>
            </sec:authorize>

            <c:if test="${not empty error}">
                <div class="">
                    Неверный логин: ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
                </div>
            </c:if>
            <form action="/login" name="f" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label for="login" class="text-center">Email</label>
                    <input type="text" class="form-control" id="login" name="username"/>
                </div>
                <div class="form-group">
                    <label for="password" class="text-center">Пароль</label>
                    <input type="password" class="form-control" id="password" name="password"/>
                </div>
                <input type="submit" value="Войти"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
