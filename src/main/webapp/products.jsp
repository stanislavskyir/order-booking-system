<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список продуктов</title>
    <!-- Подключение Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Кастомная стилизация таблицы с синей окантовкой */
        .blue-table {
            border: 2px solid #0d6efd;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1 class="mb-4">Список продуктов</h1>
    <table class="table table-bordered blue-table">
        <thead class="table-primary">
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Доступность</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${requestScope.products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>
                    <c:choose>
                        <c:when test="${product.isAvailable}">
                            <span class="badge bg-success">Доступен</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-danger">Нет в наличии</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Подключение Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
