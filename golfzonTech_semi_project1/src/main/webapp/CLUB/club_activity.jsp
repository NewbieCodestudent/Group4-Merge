<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GolfMate_모임_액티비티</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/board/selectAll.css">
    <link rel="stylesheet" href="CSS/activity/selectAll.css">
  </head>
<body>
	<!-- headarea -->
    <div id="header">
	    <div id="header_up">
	      <div id="logo">
	        <a href="home.do">
	          <img id="logo_img" src="CSS/logo/logo_small.png" alt="logo_img">
	        </a>
	      </div>
	      <div id="account">
	        <c:choose>
	          <c:when test="${member_id == null}">
	            <a href="login.do">로그인</a>
	            <a href="member_join.do">
	              회원가입
	            </a>
	            <style>#account {right : 0px;} #account a {padding: 0px 10px;}</style>
	          </c:when>
	          <c:otherwise>
	            <a href="mypage.do?member_id=${member_id}">
	              <img src="CSS/icon/login.png" alt="mypage" name="mypage" id="mypage" title="마이페이지">
	            </a>
	            <a href="logout.do">
	              <img src="CSS/icon/logout.jpg" alt="logout" name="logout" id="logout" title="로그아웃">
	            </a>
	            <br>
	            ${member_id}님 환영합니다.
	            <style>#account {position:absolute; text-align:right; right:0px; line-height: 20px;} #account a img {width: 25px; height: 30px; padding: 0px 13px;}</style>
	          </c:otherwise>
	        </c:choose>
	      </div>
	    </div>
	    <div id="header_nav">
	      <ul id="nav_box">
	        <li class="nav_item">
	          <a href="club_selectAll.do">모임</a>
	        </li>
	        <li class="nav_item">
	          <a href="activity_selectAll.do">액티비티</a>
	        </li>
	        <li class="nav_item">
	          <a href="event_selectAll.do">이벤트</a>
	        </li>
	      </ul>
	    </div>
  	</div>
  
  <!-- 메인내용 -->
  <div id="container">
  	<!-- 게시판 제목영역 -->
    <div id="club_name_box">
      <div id="club_title">
        <p style="font-size: 30px; padding-left: 30px;">
            <strong>${param.club_name}</strong>
        </p>
      </div>
    </div>

	<!-- 버튼영역 -->
    <div id="club_content">
      <ul>
        <li><a href="board_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">게시글</a></li>
        <li><a href="club_activity.do?club_id=${param.club_id}&club_name=${param.club_name}" style="color:blue;">액티비티</a></li>
        <li><a href="album_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">앨범</a></li>
        <li><a href="vote_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">투표</a></li>
      </ul>
    </div>
	
	<!-- 액티비티 정렬버튼 -->
	<div style="position: relative; font-size:15px;" id="sort_list">
		<ul style="padding-left: 20px; margin: 30px 0px 0px 0px;">
			<li>정렬기준 |</li>
			<li><a href="activity_selectAll.do?club_id=${club_id}&&order=id">최신순</a></li>
			<li><a href="activity_selectAll.do?club_id=${club_id}&&order=adate">마감임박순</a></li>
		</ul>
		<ul style="position:absolute; bottom:-15px; right: 15px;">
			<li><a href="club_activity_insert.do?club_id=${param.club_id}&club_name=${param.club_name}">액티비티 개설</a></li>
	    </ul>
	</div>
	      
	<!-- 모임출력 selectAll -->
	<div class="selectAll_container">
		<c:forEach var="vo" items="${vos}">
		<div class="selectAll_box">
			<ul>
	 		   <a href="club_activity_selectOne.do?club_id=${vo.club_id}&act_id=${vo.act_id}&club_name=${param.club_name}">
				<li><img class="selectAll_img" src="upload/${vo.act_fname}" alt="club"></li>
				<li class="selectAll_name">${vo.act_name}</li>
				<li class="selectAll_leader"><img class="selectAll_profill" src="upload/${vo.leader_fname}" title="member_profill" alt="member_profill">${vo.act_leader}</li>
				<li class="rdate">라운딩일 : ${vo.rdate}</li>
                <li class="adate">마감일 : ${vo.adate}</li>
				<input type="hidden" name="${vo.status}">
			    </a>
			</ul>
		</div>
		</c:forEach>
	</div>
  </div>
  <script>
	//wdate fillter
	let rdate = document.getElementsByClassName('rdate');
	let adate = document.getElementsByClassName('adate');
	for (let i = 0; i < rdate.length; i++) {
	 	rdate[i].innerText = rdate[i].outerText.slice(0,-5);
	 	adate[i].innerText = adate[i].outerText.slice(0,-5);
	}	
  </script>
  
  <!-- footerarea -->
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
        <a id="project_member">
          이재석
        </a>
        <a id="project_member">
          이주희
        </a>
        <a id="project_member">
          최수연
        </a>
      </div>
    </div>
  </div>
</body>
</html>