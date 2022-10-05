<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GolfMate_모임개설</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/club/insert.css">
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
    
	<div id="container">
    <!-- insert -->
    <div id="insert_box">
      <div id="insert_form">
        <p>[ ${member_id}님의 모임개설 ]</p>
        <form action="club_insertOK.do" method="post" enctype="multipart/form-data" id="club_insert">
          <div id="close_name">
            <select id="close" name="close">
              <option value="0">공개</option>
              <option value="1">비공개</option>
            </select>
            <input type="text" placeholder="모임이름을 작성해주세요." id="club_name" name="club_name" maxlength="25">
          </div>
          <div id="imgViewArea">
            <ul>
              <li>
                <p>[ 대표 이미지 ]</p>
                <img id="preview_image" src="CSS/icon/golfclub.png"><br>
                <input type="file" id="upFile" name="upFile">
              </li>
              <li>
                <p>[ 모임 소개글 ]</p>
                <textarea  id="club_desc" name="club_desc" placeholder="우리 모임은..." maxlength="500"></textarea>
              </li>
            </ul>
          </div>
          <script>
            function readImage(input) {
              // 인풋 태그에 파일이 있는 경우
              if(input.files && input.files[0]) {
                  // 이미지 파일인지 검사 (생략)
                  // FileReader 인스턴스 생성
                  const reader = new FileReader()
                  // 이미지가 로드가 된 경우
                  reader.onload = e => {
                      const previewImage = document.getElementById("preview_image")
                      previewImage.src = e.target.result
                  }
                  // reader가 이미지 읽도록 하기
                  reader.readAsDataURL(input.files[0])
              }
          }
          // input file에 change 이벤트 부여
          const inputImage = document.getElementById("upFile")
          inputImage.addEventListener("change", e => {
              readImage(e.target)
          })
          </script>
          <input type="hidden" placeholder="모임장명" id="club_leader" name="club_leader" value="${member_id}">
          <p style="font-size: 25px;">[ 가입조건설정 ]</p>
          <div id="club_condition">
            <ul>
              <li>
                <p>성별 : </p>
                <select name="gender" id="gender">
                  <option value="무관">무관</option>
                  <option value="남">남</option>
                  <option value="여">여</option>
                </select>
              </li>
              <li>
                <p>나이 : </p>
                <select name="age" id="age">
                  <option value="0">무관</option>
                  <option value="10">10대</option>
                  <option value="20">20대</option>
                  <option value="30">30대</option>
                  <option value="40">40대</option>
                  <option value="50">50대</option>
                  <option value="60">60대이상</option>
                </select>
              </li>
              <li>
                <p>지역 : </p>
                <select name="location" id="location">
                  <option value="무관">무관</option>
                  <option value="서울">서울</option>
                  <option value="경기">경기</option>
                  <option value="강원">강원</option>
                  <option value="전북">전북</option>
                  <option value="전남">전남</option>
                  <option value="경북">경북</option>
                  <option value="경남">경남</option>
                  <option value="충북">충북</option>
                  <option value="충남">충남</option>
                  <option value="제주">제주</option>
                  <option value="대전">대전</option>
                  <option value="대전">인천</option>
                  <option value="광주">광주</option>
                  <option value="대구">대구</option>
                  <option value="부신">부산</option>
                </select>
              </li>
            </ul>
          </div>
          <input type="submit" value="모임개설" id="submit">
        </form>
      </div>
    </div>
  </div>
	
  <!-- footer -->
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