<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добавление продукта</title>
    <!-- Подключение Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header">
            <h3>Добавление нового продукта</h3>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/admin-products" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Название продукта</label>
                    <input type="text" class="form-control" id="name" name="name" value="${param.name}" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Описание</label>
                    <textarea class="form-control" id="description" name="description" rows="3" required>${param.description}</textarea>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Цена</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" value="${param.price}" required>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="isAvailable" name="isAvailable"
                           <c:if test="${param.isAvailable eq 'on'}">checked</c:if>>
                    <label class="form-check-label" for="isAvailable">Доступен</label>
                </div>
                <button type="submit" class="btn btn-primary">Добавить продукт</button>
            </form>
        </div>
    </div>
</div>
<!-- Подключение Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
