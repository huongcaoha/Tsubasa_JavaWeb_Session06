<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Phim</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .grid-container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
            gap: 20px;
            margin-top: 50px;
        }
        .item {
            width: 20%;
            background: #fff;
            border-radius: 8px;
            padding: 15px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .item h3 {
            margin: 10px 0;
            color: #007bff;
        }
        .item a {
            color: #007bff;
            text-decoration: none;
        }
        .item a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Danh Sách Phim Đang Chiếu</h1>
<div class="grid-container">
    <c:forEach var="movie" items="${movies}">
        <div class="item">
            <h3>${movie.title}</h3>
            <p>Đạo Diễn: ${movie.director}</p>
            <p>Thể Loại: ${movie.genre}</p>
            <a href="/detailMovie/${movie.id}">Xem Chi Tiết</a>
        </div>
    </c:forEach>
</div>
<c:if test="${not empty message}">
    <script>
        alert("${message}")
    </script>
</c:if>
</body>
</html>