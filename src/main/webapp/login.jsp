<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход в систему</title>
    <!-- Подключение Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-card {
            max-width: 400px;
            margin: 100px auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="card login-card shadow">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">Вход</h3>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" name="email" id="email" value="${param.email}" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" class="form-control" name="password" id="password" required>
                </div>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger" role="alert">
                        Email или пароль указаны неверно.
                    </div>
                </c:if>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Войти</button>
                </div>
            </form>
            <div class="mt-3 text-center">
                <a href="${pageContext.request.contextPath}/registration" class="btn btn-link">Регистрация</a>
            </div>
        </div>
    </div>
</div>
<!-- Подключение Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
