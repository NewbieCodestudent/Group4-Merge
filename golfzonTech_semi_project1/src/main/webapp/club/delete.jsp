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

	<form action="club_deleteOK.do" method="post"
		enctype="multipart/form-data">
		<table>
			<tbody>
				 <tr>
            <td><label for="club_id">CLUB_ID:</label></td>
            <td colspan="2">${param.club_id}</td>
         </tr>
         <tr>
            <td colspan="2">정말 삭제하시겠습니까?</td>
            <td><a href="club_deleteOK.do?club_id=${param.club_id}">삭제완료</a></td>
         </tr>
			</tbody>
		</table>
	</form>
</body>
</html>