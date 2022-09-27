<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원탈퇴</h3>


<form action="mypage_deleteOK.do" method="get">
		<table>
			<tbody>
				 <tr>
            <td><label for="member_id">MEMBER_ID:${member_id}</label></td>
            <td colspan="2"></td>
         </tr>
         <tr>
            <td colspan="2">정말 탈퇴하실건가요?</td>
            <td><a href="mypage_deleteOK.do?member_id=${member_id}">네</a></td>
             <td><a href="mypage.do?member_id=${member_id}">아니요</a></td>
         </tr>
			</tbody>
		</table>
	</form>
</body>
</html>