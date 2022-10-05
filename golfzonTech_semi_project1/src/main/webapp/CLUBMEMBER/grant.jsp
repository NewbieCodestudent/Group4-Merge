<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>가입승인</h3>


<form action="cm_grantOK.do" method="post">
		<table>
			<tbody>
				 <tr>
            <td><label for="cm_id">cm_id:${param.cm_id}</label></td>
            <td><label for="club_id">club_id:${param.club_id}</label></td>
            <td colspan="2"></td>
         </tr>
         <tr>
            <td colspan="2">운영자로 임명하시겠습니까?</td>
            <input type="hidden" name="cm_id" value="${param.cm_id}">
            <input type="hidden" name="club_id" value="${param.club_id}">
            <td><input type="submit" value="네"></td>
             <td><a href="club_selectOne.do?club_id=${param.club_id}">아니요</a></td>
         </tr>
			</tbody>
		</table>
	</form>
</body>
</html>