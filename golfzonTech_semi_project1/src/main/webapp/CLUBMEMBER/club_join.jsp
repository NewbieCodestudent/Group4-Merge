<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모임가입하기</title>

</head>
<body>
   <h3>모임가입하기</h3>
<form action="club_join_applyOK.do" method="post">
	<table>
		      <tbody>
       <tr>
               <td><label for="club_leader">모임장명:</label></td>
               <td><input type="hidden" placeholder="member_id" id="member_id"
                  name="member_id" value="${vo.member_id}"></td>
            </tr> 
          <tr>
               <td><label for="club_leader">모임장명:</label></td>
               <td> <input type="hidden" placeholder="club_id" id="club_id"
                  name="club_id" value="${param.club_id}"></td>
           <tr>
               <td></td>
               <td><input type="submit" value="가입하기"></td>
            </tr>
      </tbody>

	</table>
	</form>

</body>
</html>