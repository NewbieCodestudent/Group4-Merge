<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모임상세페이지</title>
</head>
<body>
<h3>회원정보</h3>
	<jsp:include page="../top_menu.jsp"></jsp:include>

	<table>
		<tbody>

			<tr>
				<td><label for="club_id">club_id:</label></td>
				<td colspan="2">${param.club_id}</td>
			</tr>
			<tr>
				<td><label>club_name:</label></td>
				<td colspan="2">${vo2.club_name}</td>
			</tr>
			<tr>
				<td><label>club_desc:</label></td>
				<td colspan="2">${vo2.club_desc}</td>
			</tr>
			<tr>
				<td><label>gender:</label></td>
				<td colspan="2">${vo2.gender}</td>
			</tr>
			<tr>
				<td><label>close:</label></td>
				<td colspan="2">${vo2.close}</td>
			</tr>
			<tr>
				<td><label>CLUB_IMAGE:</label></td>
				<td colspan="2"><img width="100" alt="${vo2.club_img}" src="upload/${vo2.club_img}"></td>
			
			</tr>
			<tr>
				<td colspan="3">
					<a href="club_update.do?club_id=${param.club_id}">모임정보수정</a>
					<a href="club_delete.do?club_id=${param.club_id}">모임삭제</a>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>