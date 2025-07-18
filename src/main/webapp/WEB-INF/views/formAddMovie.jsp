<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Mới Phim</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .form-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"],
        input[type="number"],
        textarea {
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
    <h2>Thêm Mới Phim</h2>
    <form action="/movies/add" method="post">
        <label for="title">Tiêu Đề:</label>
        <input type="text" id="title" name="title" required>
        <label for="director">Đạo Diễn:</label>
        <input type="text" id="director" name="director" required>
        <label for="genre">Thể Loại:</label>
        <input type="text" id="genre" name="genre" required>
        <label for="description">Mô Tả:</label>
        <textarea id="description" name="description"></textarea>
        <label for="duration">Thời Gian (phút):</label>
        <input type="number" id="duration" name="duration" required>
        <label for="language">Ngôn Ngữ:</label>
        <input type="text" id="language" name="language" required>
        <button type="submit">Thêm Phim</button>
        <div class="error">${error}</div>
    </form>
</div>
</body>
</html>