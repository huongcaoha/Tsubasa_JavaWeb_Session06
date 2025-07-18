<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Lịch Chiếu</title>
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
        a {
            display: inline-block;
            margin: 10px 0;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }
        a:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .action-links a {
            margin-right: 10px;
            color: #007bff;
            text-decoration: none;
        }
        .action-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Danh Sách Lịch Chiếu Phim</h1>
<a href="/schedules/add">Thêm Mới Lịch Chiếu</a>
<table>
    <tr>
        <th>Tiêu Đề Phim</th>
        <th>Thời Gian</th>
        <th>Phòng Chiếu</th>
        <th>Số Ghế Có Sẵn</th>
        <th>Định Dạng</th>
        <th>Hành Động</th>
    </tr>
    <c:forEach var="schedule" items="${schedules}">
        <tr>
            <td>${schedule.movieTitle}</td>
            <td>${schedule.showTime.format(formater)}</td>
            <td>${schedule.screenRoomId}</td>
            <td>${schedule.availableSeats}</td>
            <td>${schedule.format}</td>
            <td class="">
                <a href="/schedules/edit/${schedule.id}">Sửa</a>
                <a href="/schedules/delete/${schedule.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa lịch chiếu này không !')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>