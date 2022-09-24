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
	
	<h3>게시글 출력</h3>
	<table>
		<tbody>
			<tr>
				<td colspan="3">
				<a href="board_update.do?club_id=${param.club_id}&&board_id=${param.board_id}">게시글 수정</a>
				<a href="board_delete.do?club_id=${param.club_id}&&board_id=${param.board_id}">게시글 삭제</a>
				</td>
			</tr>
			<tr>
				<td><label for="notice">카테고리:</label></td>
				<td colspan="2">${vo1.notice}</td>
			</tr>
			<tr>
			<tr>
				<td><label for="board_id">게시글 번호:</label></td>
				<td colspan="2">${param.board_id}</td>
			</tr>
			<tr>
				<td><label for="title">제목:</label></td>
				<td colspan="2">${vo1.title}</td>
			</tr>
			<tr>
				<td><label for="writer">작성자:</label></td>
				<td colspan="2">${vo1.writer}</td>
			</tr>
			<tr>
				<td><label for="wdate">작성일자:</label></td>
				<td colspan="2">${vo1.wdate}</td>
			</tr>
			<tr>
				<td><label for="content">내용:</label></td>
				<td colspan="2">${vo1.content}</td>
			</tr>
			<tr>
				<td><label for="upFile">파일:</label></td>
				<td colspan="2"><img width="30" alt="ingName" src="upload/${vo1.fname}"></td>
			</tr>
		</tbody>
	</table>
	<h3>댓글입력</h3>
<!-- 	<form action="comment_insertOK.do" method="post" enctype="multipart/form-data"> -->
<!-- 		<table> -->
<!-- 			<tbody> -->
<!-- 				<tr> -->
<!-- 					<td><label for="content">title:</label> -->
<!-- 					<td><input type="text" placeholder="content" id="content" name="content" value="content"></td> -->
<!-- 					<td><input type="submit" value="작성글 게시"></td> -->
<!-- 					<td></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<%-- 					<td><input type="hidden" placeholder="club_id" id="club_id" name="club_id" value="${param.club_id}"></td> --%>
<%-- 					<td>${param.club_id}</td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<%-- 					<td><input type="hidden" placeholder="board_id" id="board_id" name="board_id" value="${param.board_id}"></td> --%>
<%-- 					<td>${param.board_id}</td> --%>
<!-- 				</tr> -->
<!-- 			</tbody> -->
<!-- 		</table> -->
<!-- 	</form> -->
	
	<form action="comment_insertOK.do" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td><input type="text" placeholder="comment" id="comment" name="comment" value="comment"></td>
					<td><input type="submit" value="작성글 게시"></td>
					<td></td>
				</tr>
				<tr>
					<td><input type="hidden" placeholder="club_id" id="club_id" name="club_id" value="${param.club_id}"></td>
					<td>${param.club_id}</td>
				</tr>
				<tr>
					<td><input type="hidden" placeholder="board_id" id="board_id" name="board_id" value="${param.board_id}"></td>
					<td>${param.board_id}</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	
	<h3>댓글 출력</h3>
	<table>
			<tr>
				<td colspan="3">댓글(${vos.size()})</td>
			</tr>
		<c:forEach var="vo" items="${vos}">
		   	<tr>
		           <td>${vo.commenter}(${vo.cdate})</td>
		           <td><a href="comment_deleteOK.do?club_id=${param.club_id}&&board_id=${vo.board_id}&&comment_id=${vo.comment_id}">댓글삭제</a></td>
		    </tr>
		    <tr>
		    	<td>${vo.comment}</td>
		    </tr>
	     </c:forEach>
	</table>
</body>
</html>