<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update.jsp</title>
</head>
<body>
	<h3>update.jsp</h3>
	<jsp:include page="top_menu.jsp"></jsp:include>
	
	<form action="board_updateOK.do" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<select name="notice">
					    <option value="0">게시글</option>
					    <option value="1">공지</option>
					</select>
				</tr>
				<tr>
					<td><label for="title">title:</label>
					<td><input type="text" placeholder="title" id="title" name="title" value="${vo1.title}"></td>
					<td></td>
				</tr>
				<tr>
					<td><label for="content">content:</label></td>
					<td><input type="text" placeholder="content" id="content" name="content" value="${vo1.content}"></td>
					<td></td>
				</tr>
				<tr>
					<td><label for="upFile">upFile:</label></td>
					<td><input type="file" name="upFile" id="upFile" name="${vo1.fname}"></td>
					<td></td>
				</tr>
				<tr>
					<td><label for="club_id">${param.club_id}</label></td>
					<td><input type="hidden" placeholder="club_id" id="club_id" name="club_id" value="${param.club_id}"></td>
					<td></td>
				</tr>
				<tr>
					<td><label for="board_id">${param.board_id}</label></td>
					<td><input type="hidden" placeholder="board_id" id="board_id" name="board_id" value="${param.board_id}"></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="작성글 수정"></td>
				</tr>
			</tbody>
		</table>
	</form>
	
</body>
</html>