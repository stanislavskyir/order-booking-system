<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <!-- Подключение Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .registration-card {
            max-width: 500px;
            margin: 50px auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="card registration-card shadow">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">Регистрация</h3>
            <form action="${pageContext.request.contextPath}/registration" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Имя</label>
                    <input type="text" name="name" id="name" class="form-control" placeholder="Введите ваше имя">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" name="email" id="email" class="form-control" placeholder="Введите ваш email">
                </div>
                <div class="mb-3">
                    <label for="pwd" class="form-label">Пароль</label>
                    <input type="password" name="pwd" id="pwd" class="form-control" placeholder="Введите пароль">
                </div>
                <div class="mb-3">
                    <label for="role" class="form-label">Роль</label>
                    <select name="role" id="role" class="form-select">
                        <c:forEach var="role" items="${requestScope.roles}">
                            <option value="${role}">${role}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                </div>
            </form>
            <c:if test="${not empty requestScope.errors}">
                <div class="alert alert-danger mt-3" role="alert">
                    <c:forEach var="error" items="${requestScope.errors}">
                        <div>${error.message}</div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</div>
<!-- Подключение Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
