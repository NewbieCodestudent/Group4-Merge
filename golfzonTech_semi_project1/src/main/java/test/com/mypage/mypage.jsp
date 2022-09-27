<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/mypage.css">
  </head>
  <body>
    <!-- header 영역 -->
    <div id="header">
      <div id="header_up">
        <div id="logo">
          <a href="home.do">
            <img id="logo_img" src="CSS/logo/logo_small.png" alt="logo_img">
          </a>
        </div>
        <div id="account">
        ${member_id}
        <c:choose>
          <c:when test="${member_id == null}">
            <a href="login.do">로그인</a>
            <a href="member_join.do">회원가입</a>
            <style>#account {left : 90%;}</style>
          </c:when>
          <c:otherwise>
            <a href="mypage.do?member_id=${member_id}">마이페이지</a>
            <a href="logout.do">로그아웃</a>
            <style>#account {left : 83%;}</style>
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
    
    <!-- 개발영역 -->
    <div id="container">
        <div id="mypage_intro_background">
          <div id="my_info_box">
            <div>
              <div>
                <a href="mypage_update.do?member_id=${member_id}" >
                  <img src="CSS/icon/setting_icon.png" alt="setting" style="width:30px; padding:10px; position: absolute; left:95%;">
                </a>
              </div>
              <ul id="my_info">
                <li><img src="upload/${vo2.img_name}" id="profill"></li>
                <li>
                  <ul style="height: 350px;">
                    <li style="font-size: 40px; padding: 30px 0px 40px 0px;">${member_id} 님 환영합니다.</li>
                    <li style="font-size: 25px; padding-bottom: 10px;">[ 나의 정보 ]</li>
                    <li>이름 : ${vo2.name}</li>
                    <li>생년월일(나이) : ${vo2.birthday}(만 ${vo2.m_age}세)</li>
                    <li>성별 : ${vo2.gender}</li>
                    <li>지역 : ${vo2.location}</li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="club_content_box">
          <div>
            <a href="mypage_delete.do">탈퇴</a>
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