<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>selectOne.jsp</h3>
	<jsp:include page="top_menu.jsp"></jsp:include>
	<h3>회원삭제</h3>
	<table>
		<tbody>
			<tr>
				<td><label for=board_id>board_id:</label></td>
				<td colspan="2">${param.board_id}</td>
			</tr>
			<tr>
				<td colspan="2">정말 삭제하시겠습니까?</td>
				<td><a href="board_deleteOK.do?club_id=20&&board_id=${param.board_id}">삭제완료</a></td>
			</tr>
		</tbody>
	</table>
		
</body>
</html>