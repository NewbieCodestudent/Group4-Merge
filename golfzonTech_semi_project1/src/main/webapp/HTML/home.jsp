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
    <link rel="stylesheet" href="CSS/main_container.css">
  </head>
  <body>
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
				</c:when>
				<c:otherwise>
					<a href="logout.do">로그아웃</a>
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
            <a href="액티비티메인페이지">액티비티</a>
          </li>
          <li class="nav_item">
            <a href="이벤트메인페이지">이벤트</a>
          </li>
        </ul>
      </div>
    </div>
    <div id="container">
      <div id="event_ad">
        <iframe style="width: 1070px; height:200px; margin:0px; border:1px solid gray" src="AD_scr.do" scrolling="no">
          <p>배너광고위치 : 브라우저가 지원하지 않습니다.</p>
        </iframe>
      </div>
      <div id="recommend_box">
        <div id="recommend_club">
          <div style="margin:10px">
            <h2>추천 모임</h2>
            <a class="search_all" href="모임메인페이지">more</a>
          </div>
          <a class="recommend_club_view" href="모임추천1">
            <img class="recommend_img" src="../Users/JS/Desktop/test/profill_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천2">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천3">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천4">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천5">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
        </div>
        <div id="recommend_activity">
          <div style="margin:10px">
            <h2>추천 액티비티</h2>
            <a class="search_all" href="액티비티메인페이지">more</a>
          </div>
          <a class="recommend_club_view" href="모임추천1">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천2">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천3">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천4">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천5">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
        </div>
        <div id="recommend_deadline">
          <div style="margin:10px">
            <h2>마감임박 액티비티</h2>
            <a class="search_all" href="액티비티메인페이지">more</a>
          </div>
          <a class="recommend_club_view" href="모임추천1">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천2">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천3">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천4">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
          <a class="recommend_club_view" href="모임추천5">
            <img class="recommend_img" src="CSS/reconmend_club_dumy/test_1.jpg">
            <strong style="font-size:20px;">모임_name</strong>
            <div class="club_" style="height:30px; margin:0px;">
              <img src="CSS/member_profill_dumy/profill_1.jpg" class="member_profill" title="member_profill" alt="member_profill">
              <div style="display:inline;">
                <strong style="font-size:17px;">User_1<br>개설날짜 : 2022.09.22</strong>
              </div>
            </div>
          </a>
        </div>
      </div>
      <div>
        빈공간
      </div>
    </div>
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