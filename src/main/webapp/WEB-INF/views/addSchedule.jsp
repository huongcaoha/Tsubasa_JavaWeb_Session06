<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Mới Lịch Chiếu</title>
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
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Thêm Mới Lịch Chiếu</h1>
    <form action="/schedules/add" method="post">
        <input type="hidden" name="id">
        <label for="movieTitle">Tiêu Đề Phim:</label>
        <select name="movieTitle" id="movieTitle" required>
            <option value="">Chọn phim</option>
            <c:forEach var="movie" items="${movies}">
                <option value="${movie.title}">${movie.title}</option>
            </c:forEach>
        </select>
        <label for="showTime">Thời Gian:</label>
        <input type="datetime-local" id="showTime" name="showTime" required>
        <label for="screenRoomId">Phòng Chiếu:</label>
        <select name="screenRoomId" id="screenRoomId" required>
            <option value="">Chọn phòng chiếu</option>
            <c:forEach var="screenRoom" items="${screenRooms}">
                <option class="screenOption" totalSeat="${screenRoom.totalSeat}" value="${screenRoom.id}">
                        ${screenRoom.screenRoomName}
                </option>
            </c:forEach>
        </select>
        <label for="availableSeats">Số Ghế Có Sẵn:</label>
        <input type="number" id="availableSeats" name="availableSeats" readonly>
        <label for="format">Định Dạng:</label>
        <select id="format" name="format" required>
            <option value="2D">2D</option>
            <option value="3D">3D</option>
        </select>
        <button type="submit">Thêm Lịch Chiếu</button>
        <div class="error">${error}</div>
    </form>
</div>
<script>
    const screenSelect = document.querySelector("#screenRoomId");
    const availableSeats = document.querySelector("#availableSeats");

    screenSelect.addEventListener("change", () => {
        const selectedOption = screenSelect.options[screenSelect.selectedIndex];
        availableSeats.value = selectedOption.getAttribute("totalSeat");
    });
</script>
</body>
</html>