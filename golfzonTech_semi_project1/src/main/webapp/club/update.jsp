<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원수정</h3>

	<jsp:include page="../top_menu.jsp"></jsp:include>

	<form action="club_updateOK.do" method="post"
		enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td><label for="club_id">CLUB_ID:</label></td>
					<td>${param.club_id}<input type="hidden"
						placeholder="CLUB_ID:" id="club_id" name="club_id"
						value="${param.club_id}"></td>

				</tr>
				<tr>
					<td><label for="club_name">CLUB_NAME:</label></td>
					<td><input type="${vo2.club_name}" placeholder="CLUB_NAME"
						id="club_name" name="club_name" value="${vo2.club_name}"></td>

				</tr>
				<tr>
					<td><label for="club_desc">클럽설명:</label></td>
					<td><input type="${vo2.club_desc}" placeholder="CLUB_DESC"
						id="club_desc" name="club_desc" value="${vo2.club_desc}"></td>

				</tr>
				<tr>
					<td><label for="gender">성별:</label></td>
					<td><input type="${vo2.gender}" placeholder="GENDER"
						id="gender" name="gender" value="${vo2.gender}"></td>

				</tr>
				<tr>
					<td><label for="age">연령대:</label></td>
					<td><input type="text" placeholder="AGE" id="age" name="age"
						value="${vo2.age}"></td>

				</tr>
				<tr>
					<td><label for="location">지역:</label></td>
					<td><input type="text" placeholder="LOCATION" id="location"
						name="location" value="${vo2.location}"></td>

				</tr>
				<tr>
					<td><label for="close">CLOSE:</label></td>
					<td><input type="text" placeholder="CLOSE" id="close" name="close"
						value="${vo2.close}"></td>
				</tr>
				<tr>
					<td><label for="upFile">CLUB_IMG:</label></td>
					<td><input type="file" id="upFile" name="upFile"></td>

				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="수정완료"></td>

				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>