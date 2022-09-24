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
	<h3>게시글</h3>
	<jsp:include page="top_menu.jsp"></jsp:include>
	
	<form action="board_searchListOK.do?club_id=20" method="get">
		<table>
			<tr>
				<td><select name="searchKey" id="searchKey">
						<option value="title">title</option>
						<option value="content">content</option>
						<option value= "writer">writer</option></td>
				<td></select> 
				<input type="text" name="searchWord" id="searchWord" value="title"></td>
				<input type="hidden" name="club_id" id="club_id" value="20"></td>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form>
	
	<a href="board_selectAll.do?club_id=20&&order=desc">작성일 내림차순</a>
	<a href="board_selectAll.do?club_id=20&&order=asc"">작성일 오름</a>
	<h1>selectAll_notice</h1>
	<c:forEach var="notice" items="${notices}">
		<a href="board_selectOne.do?club_id=${param.club_id}&&board_id=${notice.board_id}">${notice.board_id}</a>
		${notice}
		<br>
	</c:forEach>
	
	<h1>selectAll_common</h1>
	<c:forEach var="common" items="${commons}">
		<a href="board_selectOne.do?club_id=${param.club_id}&&board_id=${common.board_id}">${common.board_id}</a>
		${common}
		<br>
	</c:forEach>
	
</body>
</html>