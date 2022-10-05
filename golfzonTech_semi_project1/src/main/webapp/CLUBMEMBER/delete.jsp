<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>모임탈퇴</h3>

<form action="cm_leaveOK.do" method="get">
	<table>
		      <tbody>
       <tr>
               <td><label for="club_leader">내ID:</label></td>
               <td><input type="hidden" placeholder="member_id" id="member_id"
                  name="member_id" value="${vo.member_id}"></td>
            </tr> 
          <tr>
               <td><label for="club_leader">모임ID:</label></td>
               <td> <input type="hidden" placeholder="club_id" id="club_id"
                  name="club_id" value="${param.club_id}"></td>
           <tr>
               <td></td>
               <td><input type="submit" value="탈퇴하기"></td>
            </tr>

<!--                 <tr> -->
<!--             <td colspan="2">정말 탈퇴하실건가요?</td> -->
<%--             <td><a href="cm_leaveOK.do?member_id=${param.member_id}">네</a></td> --%>
<%--              <td><a href="club_selectOne.do?club_id=${param.club_id}">아니요</a></td> --%>
<!--          </tr> -->
      </tbody>

	</table>
	</form>

     

</body>
</html>