<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert.jsp</title>
</head>
<body>
	<h3>insert.jsp</h3>
	<jsp:include page="top_menu.jsp"></jsp:include>
	
<!-- 	<form action="board_insertOK.do?club_id=20" method="post" enctype="multipart/form-data"> -->
<!-- 		<select name="notice"> -->
<!-- 		    <option value="0">게시글</option> -->
<!-- 		    <option value="1">공지</option> -->
<!-- 		</select> -->
<!-- 		<label for="title">title:</label> -->
<!-- 		<input type="text" placeholder="title" id="title" name="title" value="title1"><br>  -->
<!-- 		<label for="content">content:</label> -->
<!-- 		<input type="text" placeholder="content" id="content" name="content" value="content"><br>  -->
<!-- 		<label for="upFile">upFile:</label> -->
<!-- 		<input type="file" id="upFile" name="upFile"><br>  -->
<!-- 		<input type="submit" value="게시"> -->
<!-- 	</form>	 -->
	
	<form action="board_insertOK.do?club_id=20" method="post" enctype="multipart/form-data">
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
					<td><input type="text" placeholder="title" id="title" name="title" value="title1"></td>
					<td></td>
				</tr>
				<tr>
					<td><label for="content">content:</label></td>
					<td><input type="text" placeholder="content" id="content" name="content" value="content"></td>
					<td></td>
				</tr>
				<tr>
					<td><label for="upFile">upFile:</label></td>
					<td><input type="file" name="upFile" id="upFile" name="upFile"></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="작성글 게시"></td>
				</tr>
			</tbody>
		</table>
	</form>
	
	
	
	
</body>
</html>