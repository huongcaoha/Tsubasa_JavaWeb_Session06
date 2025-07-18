<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt Vé Xem Phim</title>
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
        .form-container {
            background: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            margin: 0 auto;
            transition: box-shadow 0.3s;
        }
        .form-container:hover {
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2);
        }
        label {
            display: inline-block;
            margin: 10px 10px 5px 0;
            font-weight: bold;
            color: #555;
            cursor: pointer;
            padding: 10px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        label.selected {
            background-color: #007bff; /* Màu xanh khi được chọn */
            color: white; /* Đổi màu chữ */
        }
        input[type="checkbox"] {
            display: none; /* Ẩn checkbox gốc */
        }
        input[type="checkbox"] + label {
            border: 2px solid #007bff; /* Viền cho label */
        }
        input[type="checkbox"]:checked + label {
            background-color: #007bff; /* Màu nền khi checkbox được chọn */
            color: white; /* Đổi màu chữ khi chọn */
        }
        input[type="number"] {
            width: 100%;
            padding: 12px;
            margin: 5px 0 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            text-align: center;
            margin-top: 10px;
            font-weight: bold;
        }

        #formTicket {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .disable {
            background-color: #333333;
            color: white;
            pointer-events: none; /* Không cho phép tương tác */
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Đặt Vé Xem Phim</h1>
    <h2>Tên Phòng Chiếu: ${screenRoom.screenRoomName}</h2>
    <h2>Tên Phim: ${schedule.movieTitle}</h2>
    <h2>Thời gian chiếu: ${schedule.showTime.format(formater)}</h2>
    <form action="/tickets/book" method="post" id="formTicket">
        <input type="hidden" name="scheduleId" value="${schedule.id}">
        <c:forEach var="row" items="${listRowSeat}">
           <div id="rowSeat">
               <c:forEach var="col" items="${row}">
                   <c:if test="${col.status == false}">
                       <input type="checkbox" id="${col.seatName}" name="listSeat" value="${col.seatName}">
                       <label for="${col.seatName}">${col.seatName}</label>
                   </c:if>
                   <c:if test="${col.status == true}">
                       <label class="disable" for="${col.seatName}">${col.seatName}</label>
                   </c:if>
               </c:forEach>
           </div>
        </c:forEach>

        <button type="submit">Đặt Vé</button>
        <div class="error">${error}</div>
    </form>
</div>
</body>
</html>