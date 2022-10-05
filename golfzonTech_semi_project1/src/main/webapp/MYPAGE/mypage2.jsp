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
    <link rel="stylesheet" href="CSS/mypage/mypage.css">
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
            <a href="mypage.do">마이페이지</a>
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
        <!-- 마이페이지 하단부분 -->
        <div id="mypage_bottom">
          <div id="mypage_category">
            <ul class="tabs">
              <li data-tab-target="#view_club" class="active tab">모임</li>
              <li data-tab-target="#view_activity" class="tab">액티비티
              </li>
            </ul>
          </div>
          <!-- 하단 클럽 정보 -->
          <div id="view_club" data-tab-content>
            <div id="club_searchList">
              <form action="club_searchList">
                <select name="searchKey" id="searchKey">
                  <option selected hidden>검색기준</option>
                  <option value="club_name">모임명</option>
                  <option value="club_leader">모임장</option>
                </select>
                <input type="text" id="searchWord">
                <input type="submit" id="submit">
              </form>
              <ul>
                <li>최신순</li>
                <li>제목순</li>
              </ul>
            </div>
            <c:forEach var="myclub" items="${myclublist}">
            <div style="margin: 0px 20px;">
              <div class="club_box">
                <a href="club_selectOne.do?club_id=${myclub.club_id}">
              
                  <ul>
                    <li><img src="upload/${myclub.club_img}" alt="${myclub.club_img}" id="club_img"></li>
                    <li id="club_name">모임명 : ${myclub.club_name}</li>
                    <li id="cdate">개설일 : ${myclub.cdate}</li>
                  </ul>
                </a>
              </div>
            </div>
             </c:forEach>
          </div>
          <!-- 하단 액티비티 정보 -->
          <div id="view_activity" data-tab-content class="active">
            <div id="activity_searchList">
              <form action="activity_searchList">
                <select name="searchKey" id="searchKey">
                  <option selected hidden>검색기준</option>
                  <option value="activity_name">액티비티명</option>
                  <option value="activity_leader">액티비티장</option>
                </select>
                <input type="text" id="searchWord">
                <input type="submit" id="submit">
              </form>
              <ul>
                <li>최신순</li>
                <li>제목순</li>
              </ul>
            </div>
            <!-- c문 -->
            <div style="margin: 0px 20px;">
              <div class="activity_box">
                <a href="">
                  <ul>
                    <li><img src="activity_img" alt="activity_img" id="activity_img"></li>
                    <li>activity_name</li>
                    <li>activity_wdate</li>
                  </ul>
                </a>
              </div>
            </div>
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
    <script>
       const tabs = document.querySelectorAll('[data-tab-target]');
       const tabContents = document.querySelectorAll('[data-tab-content]');
   
       tabs.forEach(tab => {
         tab.addEventListener('click', () => {
           const target = document.querySelector(tab.dataset.tabTarget)
           tabContents.forEach(tabcontent => {
             tabcontent.classList.remove('active');
           });
           tabs.forEach(tab => {
             tab.classList.remove('active');
           });
           tab.classList.add('active')
           target.classList.add('active')
         })
       });
    </script>
  </body>
</html>