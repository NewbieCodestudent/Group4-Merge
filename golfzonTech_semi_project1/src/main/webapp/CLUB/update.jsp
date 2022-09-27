<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>클럽정보 수정</title>
<link rel="stylesheet" href="CSS/header.css">
<link rel="stylesheet" href="CSS/footer.css">
<link rel="stylesheet" href="CSS/clubpage_update.css">
<script>
function doAction(value) {
	  if(value == 0) {
		  location.href = "club_updateOK.do?club_id=${param.club_id}";
	  } else if(value == 1) {
		  console.log(${param.club_id});
		  var msg = confirm("정말 삭제하시겠습니까?");
		  if(msg==true){
			  location.href = "club_deleteOK.do?club_id=${param.club_id}";  
		  } else {
			  return false;
		  }
	  }
	}
</script>

</head>
<body>
   <!-- header 영역 -->
   <div id="header">
      <div id="header_up">
         <div id="logo">
            <a href="home.do"> <img id="logo_img"
               src="CSS/logo/logo_small.png" alt="logo_img">
            </a>
         </div>
         <div id="account">
            ${member_id}
            <c:choose>
               <c:when test="${member_id == null}">
                  <a href="login.do">로그인</a>
                  <a href="member_join.do">회원가입</a>
                  <style>#account {
   left: 90%;
}
</style>
               </c:when>
               <c:otherwise>
                  <a href="mypage.do?member_id=${member_id}">마이페이지</a>
                  <a href="logout.do">로그아웃</a>
                  <style>
#account {
   left: 83%;
}
</style>
               </c:otherwise>
            </c:choose>
         </div>
      </div>
      <div id="header_nav">
         <ul id="nav_box">
            <li class="nav_item"><a href="club_selectAll.do">모임</a></li>
            <li class="nav_item"><a href="activity_selectAll.do">액티비티</a></li>
            <li class="nav_item"><a href="event_selectAll.do">이벤트</a></li>
         </ul>
      </div>
   </div>

   <!-- 개발영역 -->
   <div id="container">
      <div id="club_intro_background">
         <div id="club_info_box">
         	<ul style="margin: 0%;">
          		<li><input type="button" value="수정" onclick="doAction(0)"></li>
          		<li><input type="button" value="삭제" onclick="doAction(1)"></li>
        	</ul>
            <form id="form_box" action="club_updateOK.do" name="mypage_updateOK"
               method="post" enctype="multipart/form-data"
               onsubmit="return checkValue()">

               <label for="submit_button" id="submit_button_design">정보 수정</label>
               <input type="submit" value="수정" id="submit_button"
                  name="submit_button" style="display: none;">
               <ul id="club_info">
                  <li>
                     <ul>
                        <li><img src="upload/${vo2.club_img}" id="profill"></li>
                        <li id="filebox"><label for="upFile">프로필사진</label> <input
                           type="File" id="upFile" name="upFile" onchange="fileUpload()"
                           multiple></li>
                     </ul>
                  </li>
                  <li>
                     <ul style="height: 350px;">
                        <%--                     <li style="font-size: 40px; padding: 30px 0px 40px 0px;">${club_id} 님의 정보 수정</li> --%>
                        <li style="font-size: 25px; padding-bottom: 10px;">[ 클럽 정보
                           ]</li>
                        <!-- 수정불가 정보 -->
                        <li>
                        <input type="hidden" value= "${vo2.club_id}"></li>
                        <li>클럽리더 : ${vo2.club_leader}</li>
                        <!-- 수정가능 정보 -->
                        <li>수정가능정보</li>
                        <li>클럽명 : <input type="text" name="club_name"
                           value="${vo2.club_name}" placeholder="클럽명"></li>
                        <li>클럽설명 : <input type="text" name="club_desc"
                           value="${vo2.club_desc}" placeholder="클럽설명"></li>
                           <li>비공개여부 : <input type="text" name="close"
                           value="${vo2.close}" placeholder="클럽설명"></li>
                        <li>[성별조건]</li>
<!--                         <li><select name="gender" id="gender"> -->
<!--                               <option value="">무관</option> -->
<!--                               <option value="male">남자</option> -->
<!--                               <option value="female">여자</option> -->
<!--                         </select></li> -->
<!--                         <li>[나이조건]</li> -->
<!--                         <li><select name="age" id="age"> -->
<%--                               <option value="${vo2.age}">${vo2.age}대</option> --%>
<!--                               <option value="all">무관</option> -->
<!--                               <option value="10">10대</option> -->
<!--                               <option value="20">20대</option> -->
<!--                               <option value="30">30대</option> -->
<!--                               <option value="40">40대</option> -->
<!--                               <option value="50">50대</option> -->
<!--                         </select></li> -->
<!--                         <li>지역조건</li> -->
<!--                         <li> -->
<!--                         <select name="location" id="location"> -->
<%--                            <option value="${vo2.location}" selected disabled hidden>${vo2.location}</option> --%>
<!--                            <option value="무관">무관</option> -->
<!--                            <option value="서울">서울</option> -->
<!--                            <option value="경기">경기</option> -->
<!--                            <option value="강원">강원</option> -->
<!--                            <option value="전북">전북</option> -->
<!--                            <option value="전남">전남</option> -->
<!--                            <option value="경북">경북</option> -->
<!--                            <option value="경남">경남</option> -->
<!--                            <option value="충북">충북</option> -->
<!--                            <option value="충남">충남</option> -->
<!--                            <option value="제주">제주</option> -->
<!--                            <option value="대전">대전</option> -->
<!--                            <option value="대전">인천</option> -->
<!--                            <option value="광주">광주</option> -->
<!--                            <option value="대구">대구</option> -->
<!--                            <option value="부신">부산</option> -->
<!--                         </select> </li> -->
  <li>지역 : <input type="text" name="age" value="${vo2.age}" placeholder="age"></li>
  <li>지역 : <input type="text" name="gender" value="${vo2.gender}" placeholder="gender"></li>
  <li>지역 : <input type="text" name="location" value="${vo2.location}" placeholder="지역"></li>
                        <li>[비공개여부]</li>
<!--                         <li> -->
<!--                            <select name="close" id="close"> -->
<%--                               <c:choose> --%>
<%--                                  <c:when test="${vo2.close} == 0"> --%>
<%--                                     <option value="${vo2.close}" selected disabled hidden>공개</option> --%>
<%--                                  </c:when> --%>
<%--                                  <c:otherwise> --%>
<%--                                     <option value="${vo2.close}" selected disabled hidden>비공개</option> --%>
<%--                                  </c:otherwise> --%>
<%--                               </c:choose>    --%>
<!--                               <option value="0">공개</option> -->
<!--                               <option value="1">비공개</option> -->
<!--                            </select> -->
<!--                         </li> -->
                        <ul>

                        </ul>
                     </ul>
                  </li>
               </ul>
            </form>
         </div>
      </div>
      <div class="club_content_box">
         <div>
            <a href="club_delete.do?club_id=${param.club_id}">탈퇴</a>
         </div>
      </div>
   </div>

   <!-- footer 영역 -->
   <div id="footer">
      <div id="title_introduce">
         <h3 class="title">[ Project 소개 ]</h3>
      </div>
      <div id="introduce_box">
         <div class="introduce">
            <h3 class="title">Project Name</h3>
            <a id="project_story">Golf Mate</a>
         </div>
         <div class="introduce">
            <h3 class="title">Team Member</h3>
            <a id="project_member"> 이재석 </a> <a id="project_member"> 이주희 </a> <a
               id="project_member"> 최수연 </a>
         </div>
      </div>
   </div>
</body>
</html>