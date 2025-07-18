<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Phim</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        .movie-details {
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 20px auto;
            transition: box-shadow 0.3s;
        }
        .movie-details:hover {
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        }
        p {
            font-size: 16px;
            line-height: 1.6;
            color: #555;
            margin: 10px 0;
        }
        strong {
            color: #333;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            transition: color 0.3s;
        }
        .back-link:hover {
            text-decoration: underline;
            color: #0056b3;
        }
        h2 {
            margin-top: 30px;
            color: #333;
        }
        .schedule-buttons {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin-top: 20px;
        }
        .schedule-button {
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            color: white;
            padding: 10px 15px;
            margin: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        .schedule-button a {
            color: white;
            text-decoration: none;
        }
        .schedule-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="movie-details">
    <h1>Chi Tiết Phim: ${movie.title}</h1>
    <p><strong>Đạo Diễn:</strong> ${movie.director}</p>
    <p><strong>Thể Loại:</strong> ${movie.genre}</p>
    <p><strong>Mô Tả:</strong> ${movie.description}</p>
    <p><strong>Thời Gian:</strong> ${movie.duration} phút</p>
    <p><strong>Ngôn Ngữ:</strong> ${movie.language}</p>

    <h2>Lịch Chiếu</h2>
    <div class="schedule-buttons">
        <c:forEach var="schedule" items="${schedules}">
            <button class="schedule-button">
                <a href="/tickets/book/${schedule.id}">${schedule.showTime.format(formater)}</a>
            </button>
        </c:forEach>
    </div>

    <a class="back-link" href="/">Quay lại Danh Sách Phim</a>
</div>
</body>
</html>