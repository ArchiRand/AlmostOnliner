<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Категории</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <script type="application/javascript">
        function deleteCategoryWithId(id) {
            var confirmed = confirm("Вы уверены что хотите удалить эту категорию?");
            var request = new XMLHttpRequest();
            var url = "http://localhost:8080/admin/category/" + id + ".json";
            var header = "${_csrf.headerName}";
            var token = "${_csrf.token}";

            if (confirmed) {
                request.open("DELETE", url, true);
                request.setRequestHeader(header, token);
                request.onreadystatechange = function (event) {
                    if (request.readyState === 4 && (request.status === 200)) {
                        var responseJSON = JSON.parse(request.responseText);
                        if (responseJSON.deleted = 'true') {
                            location.reload(true);
                        } else {
                            alert("Возникла ошибка при попытке удаления категории.");
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
            <h2 class="text-center">Категории</h2>
        </div>
        <div class="col-md-3 offset-md-6">
            <a href="/admin/category" class="btn btn-success">Добавить категорию</a>
        </div>
    </div>
    <div class="row margin-top30">
        <div class="col-md-8 offset-md-2">
            <h4 class>Все категории: </h4>
            <c:choose>
                <c:when test="${not empty categoryList}">
                    <c:forEach items="${categoryList}" var="category">
                        <div class="card margin-top10">
                            <div class="card-header">
                                Категория: ${category.name}
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${category.name}</h5>
                                <a href="#" onclick="deleteCategoryWithId(${category.id})"
                                   class="btn btn-danger">Удалить</a>
                                <a href="/admin/edit_category/${category.id}"
                                   class="btn btn-success">Редактировать</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="text-center">Нет категории. Вы можете добавить их используя кнопку "Добавить категорию".</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <p class="text-center"><a href="/admin/">Вернуться в админку</a></p>
</div>
</body>
</html>
