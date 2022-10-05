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
    
    <!-- 개발영역 -->
    <div id="container">
        <div id="mypage_intro_background">
          <div id="my_info_box">
            <div>
              <div>
                <a href="mypage_update.do?member_id=${param.member_id}" title="모임설정">
                  <img src="CSS/icon/setting_icon.png" alt="setting" style="width:30px; padding:10px; position: absolute; left:95%;">
                </a>
              </div>
              <ul id="my_info">
                <li><img src="upload/${vo2.img_name}" id="profill" onerror="this.src='CSS/icon/profill.png'"></li>
                <li>
                  <ul style="height: 350px;">
                    <li style="font-size: 40px; padding: 30px 0px 40px 0px;">${vo2.member_id} 님 환영합니다.</li>
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
          <div id="view_club" data-tab-content class="active">
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
            <!-- 가입한 모임, 내모임 목록 -->
            <div id="clubs">
	            <c:forEach var="myclub" items="${myclublist}">
	              <div class="club_box">
	                <a href="club_selectOne.do?club_id=${myclub.club_id}&club_name=${myclub.club_name}">
	                  <ul>
	                    <li><img src="upload/${myclub.club_img}" alt="${myclub.club_img}" class="club_img"></li>
	                    <li id="club_name">모임명 : ${myclub.club_name}</li>
	                    <li id="cdate">개설일 : ${myclub.cdate}</li>
	                  </ul>
	                </a>
	              </div>
	             </c:forEach>
             </div>
          </div>
          <!-- 하단 액티비티 정보 -->
          <div id="view_activity" data-tab-content>
            <div id="activity_searchList">
              <form action="activity_searchList" method="get">
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
            <div id="activitys">
	            <c:forEach var="activity" items="${activity_list}">
	              <div class="activity_box">
	                 <c:choose>
	                    <c:when test="${activity.club_id != 0}">
	                       <a href="club_activity_selectOne.do?club_id=${activity.club_id}&club_name=${activity.club_name}&act_id=${activity.act_id}">
	                          <ul>
	                             <li><img src="upload/${activity.act_fname}" alt="activity_img" class="activity_img"></li>
	                             <li><strong>${activity.act_name}</strong></li>
	                             <li class="activity_rdate">라운딩일: ${activity.rdate}</li>
	                             <li class="activity_adate">마감일자: ${activity.adate}</li>
	                          </ul>
	                        </a>
	                    </c:when>
	                    <c:otherwise>
	                       <a href="activity_selectOne.do?club_id=${activity.club_id}&club_name=${activity.club_name}&act_id=${activity.act_id}">
	                          <ul>
	                             <li><img src="upload/${activity.act_fname}" alt="activity_img" class="activity_img"></li>
	                             <li><strong>${activity.act_name}</strong></li>
	                             <li class="activity_rdate">라운딩일: ${activity.rdate}</li>
	                             <li class="activity_adate">마감일자: ${activity.adate}</li>
	                          </ul>
	                        </a>
	                    </c:otherwise>
	                 </c:choose>
	              </div>
	            </c:forEach>
            </div>
          </div>
        </div>
    </div>
    <script>
      let activity_rdate = document.getElementsByClassName('activity_rdate');
      let activity_adate = document.getElementsByClassName('activity_adate');
      for (let i = 0; i < activity_rdate.length; i++) {
        activity_rdate[i].innerText = activity_rdate[i].outerText.slice(0,-5);
        activity_adate[i].innerText = activity_adate[i].outerText.slice(0,-5);
      }
    </script>
    

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