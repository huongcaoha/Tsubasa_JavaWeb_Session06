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
    h2 {
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
<h2>Danh Sách Phim</h2>
<a href="/movies/add">Thêm Mới Phim</a>
<table>
  <tr>
    <th>Mã phim</th>
    <th>Tiêu Đề</th>
    <th>Đạo Diễn</th>
    <th>Thể Loại</th>
    <th>Thời Lượng</th>
    <th>Ngôn Ngữ</th>
    <th>Hành Động</th>
  </tr>
  <c:forEach var="movie" items="${movies}">
    <tr>
      <td>${movie.id}</td>
      <td>${movie.title}</td>
      <td>${movie.director}</td>
      <td>${movie.genre}</td>
      <td>${movie.duration}</td>
      <td>${movie.language}</td>
      <td class="">
        <a href="/movies/edit/${movie.id}">Sửa</a>
        <a href="/movies/delete/${movie.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa phim này !')">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
<c:if test="${not empty message}">
  <script>
    alert("${message}");
  </script>
</c:if>
</body>
</html>