<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa Lịch Chiếu</title>
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
        .form-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"],
        input[type="number"],
        input[type="datetime-local"],
        select {
            width: 100%;
            padding: 10px;
            margin: 5px 0 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Sửa Lịch Chiếu</h1>
    <form action="/schedules/edit/${schedule.id}" method="post">
        <label for="movieTitle">Tiêu Đề Phim:</label>
        <input type="text" id="movieTitle" name="movieTitle" value="${schedule.movieTitle}" required>
        <label for="showTime">Thời Gian:</label>
        <input type="datetime-local" id="showTime" name="showTime" value="${schedule.showTime}" required>
        <label for="screenRoomId">Phòng Chiếu:</label>
        <input type="number" id="screenRoomId" name="screenRoomId" value="${schedule.screenRoomId}" required>
        <label for="availableSeats">Số Ghế Có Sẵn:</label>
        <input type="number" id="availableSeats" name="availableSeats" value="${schedule.availableSeats}" required>
        <label for="format">Định Dạng:</label>
        <select id="format" name="format" required>
            <option value="2D" ${schedule.format == '2D' ? 'selected' : ''}>2D</option>
            <option value="3D" ${schedule.format == '3D' ? 'selected' : ''}>3D</option>
        </select>
        <button type="submit">Cập Nhật Lịch Chiếu</button>
        <div class="error">${error}</div>
    </form>
</div>
</body>
</html>