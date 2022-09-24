<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체모임목록</title>
</head>
<body>
	<h1>selectAll</h1>
	<table border="1">
		<tr>
			<th>club_id</th>
			<th>club_name</th>
			<th>club_leader</th>
			<th>개설일</th>
			<th>모임이미지</th>
		</tr>
		<c:forEach var="vo" items="${vos}">
			<tr>
				<td><a href="club_selectOne.do?club_id=${vo.club_id}">${vo.club_id}</a></td>
				<td>${vo.club_name}</td>
				<td>${vo.club_leader}</td>
				<td>${vo.cdate}</td>
				<td><img width="50px" alt="${vo.club_name}" src="upload/${vo.club_img}"></td>
			</tr>
		</c:forEach>
	</table>
	
	
</body>
</html>