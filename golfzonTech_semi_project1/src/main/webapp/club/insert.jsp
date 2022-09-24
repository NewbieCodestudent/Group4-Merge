<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모임개설</title>
</head>
<body>
   <h3>글쓰기</h3>
   <jsp:include page="../top_menu.jsp"></jsp:include>
   <form action="club_insertOK.do" method="post" enctype="multipart/form-data">
 
      <table>
         <tbody>
            <tr>
               <td><label for="club_name">모임명:</label></td>
               <td><input type="text" placeholder="제목" id="club_name" name="club_name"
                  value="jsp servlet"></td>
            </tr>
            <tr>
               <td><label for="club_desc">모임설명:</label></td>
               <td>
                  <textarea  id="club_desc" name="club_desc" 
                  rows="5" cols="30">Hello java jsp servlet HTML5 javascript css</textarea>
               </td>
            </tr>
            <tr>
               <td><label for="club_leader">모임장명:</label></td>
               <td>${member_id} <input type="hidden" placeholder="모임장명" id="club_leader"
                  name="club_leader" value="${member_id}"></td>
            </tr> 
             <tr>
               <td><label for="gender">성별:</label></td>
               <td><input type="text" placeholder="성별" id="gender"
                  name="gender" value="여"></td>
            </tr>
             <tr>
               <td><label for="age">연령대:</label></td>
               <td><input type="text" placeholder="연령대" id="age"
                  name="age" value="20"></td>
            </tr>
             <tr>
               <td><label for="location">지역:</label></td>
               <td><input type="text" placeholder="지역" id="location"
                  name="location" value="서울"></td>
            </tr>
             <tr>
               <td><label for="close">비공개여부:</label></td>
               <td><input type="text" placeholder="비공개여부" id="close"
                  name="close" value="1"></td>
            </tr>
         	<tr>
					<td><label for="upFile">IMG_NAME:</label></td>
					<td><input type="file" id="upFile" name="upFile"></td>
					<td></td>
				</tr>
             <tr>
					<td></td>
					<td><input type="submit" value="모임개설"></td>
					<td></td>
				</tr>
         </tbody>
      </table>
   </form>
</body>
</html>