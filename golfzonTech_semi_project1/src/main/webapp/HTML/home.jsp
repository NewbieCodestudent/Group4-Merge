<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GolfMate</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/home.css">
  </head>
  <body>
	<!-- header -->
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
	           <a href="club_selectAll.do?order=desc">모임</a>
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

	<!-- main -->
    <div id="container">
	  <!-- 배너공간 -->
      <div id="event_ad">
        <iframe style="width: 1070px; height:200px; margin:0px; border:1px solid gray" src="AD_scr.do" scrolling="no">
          <p>배너광고위치 : 브라우저가 지원하지 않습니다.</p>
        </iframe>
      </div>
      <!-- 추천모임, 액티비티 공간 -->
      <div id="recommend_box">
        <div id="recommend_club">
          <div style="margin:10px">
            <h2 class="event_title">[ 추천 모임: 따끈따끈 신규 클럽! 같이 시작해봐요. ]</h2>
            <!-- <a class="search_all" href="club_selectAll.do">more</a> -->
          </div>
          <c:forEach var="club" items="${club_list}" end = "4">
	          <a class="recommend_club_view" href="club_selectOne.do?club_id=${club.club_id}&club_name=${club.club_name}">
	            <img class="recommend_img" src="upload/${club.club_img}">
	            <strong style="font-size:20px;">${club.club_name}</strong>
	            <div class="club_" style="height:30px; margin:0px;">
	              <img src="upload/${club.img_name}" class="member_profill" title="member_profill">
	              <div style="display:inline;">
	                <strong style="font-size:17px;">${club.club_leader}</strong><br>
	                <strong class="club_cdate">개설일 : ${club.cdate}</strong>
	              </div>
	            </div>
	          </a>
          </c:forEach>
        </div>
        <div id="recommend_activity">
          <div style="margin:10px">
            <h2 class="event_title">[ 추천 액티비티: 10월은 단풍구경과 함께! 강원도로 가봐요!! ]</h2>
            <!-- <a class="search_all" href="액티비티메인페이지">more</a> -->
          </div>
          <c:forEach var="act_loc" items="${act_list_loc}" end = "4">
	          <a class="recommend_club_view" href="activity_selectOne.do?act_id=${act_loc.act_id}">
	            <img class="recommend_img" src="upload/${act_loc.act_fname}">
	            <strong style="font-size:20px;">${act_loc.act_name}</strong>
	            <div class="club_" style="height:30px; margin:0px;">
	              <img src="upload/${act_loc.leader_fname}" class="member_profill" title="member_profill">
	              <div style="display:inline;">
	                <strong style="font-size:17px;">${act_loc.act_leader}</strong><br>
	                <strong class="act_date_rdate">라운딩일 : ${act_loc.rdate}</strong><br>
	                <strong class="act_date_adate">마감일 : ${act_loc.adate}</strong>
	              </div>
	            </div>
	          </a>
          </c:forEach>
        </div>
        <div id="recommend_deadline">
          <div style="margin:10px">
            <h2 class="event_title">[ 마감임박 액티비티: 기다리기 싫은 당신을 위해! ]</h2>
            <!-- <a class="search_all" href="액티비티메인페이지">more</a> -->
          </div>
          <c:forEach var="act_date" items="${act_list_date}" end = "4">
	          <a class="recommend_club_view" href="activity_selectOne.do?act_id=${act_date.act_id}">
	            <img class="recommend_img" src="upload/${act_date.act_fname}">
	            <strong style="font-size:18px;">${act_date.act_name}</strong>
	            <div class="club_" style="height:30px; margin:0px;">
	              <img src="upload/${act_date.leader_fname}" class="member_profill" title="member_profill">
	              <div style="display:inline;">
	                <strong style="font-size:17px;">${act_date.act_leader}</strong><br>
	                <strong class="act_date_rdate">라운딩일 : ${act_date.rdate}</strong><br>
	                <strong class="act_date_adate">마감일 : ${act_date.adate}</strong>
	              </div>
	            </div>
	          </a>
          </c:forEach>
        </div>
      </div>
    </div>
    <script>
    	// date fillter
        let act_date_rdate = document.getElementsByClassName('act_date_rdate');
    	let act_date_adate = document.getElementsByClassName('act_date_adate');
        for (let i = 0; i < act_date_rdate.length; i++) {
          console.log(act_date_rdate[i].outerText.slice(0,-5));
          act_date_rdate[i].innerText = act_date_rdate[i].outerText.slice(0,-5);
          act_date_adate[i].innerText = act_date_adate[i].outerText.slice(0,-5);
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