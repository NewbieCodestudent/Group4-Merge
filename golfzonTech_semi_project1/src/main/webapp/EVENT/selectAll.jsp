<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이벤트</title>
    <script src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/event/eventAll.css">
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

    <!-- container 영역 -->
    <div id="container">
        <div id="event_intro_background">
          <div id="event_info_box">
            <div id="event_title">
                <P>[ 이벤트 ]</P>
                <p id="event_count"></p>
            </div>
            <div id="event_sort">
                <ul>
                    <li>정렬</li>
                    <li><a onclick="action(0)">전체</button></li>
                    <li><a onclick="action(1)">진행중</button></li>
                    <li><a onclick="action(2)">종료</button></li>
                </ul>
            </div>
            <div id="event_main">
              <!-- event module -->
              <div class="event_content">
                  <ul>
                    <a href="Golf Party" class="event_link">
                          <li><img src="CSS/event/event_0.jpg" alt="event_0.jpg" title="이벤트0" class="event_img"></li>
                      </a>
                      <li class="event_date">2022-08-02 ~ 2022-09-27</li>
                      <li class="event_ing_ed"></li>
                      <li class="event_name">Golf Party</li>
                  </ul>
              </div>
              <div class="event_content">
                <ul>
                  <a href="DRIVE FOR DIAGNOSIS" class="event_link">
                        <li><img src="CSS/event/event_1.jpg" alt="event_1.jpg" title="이벤트1" class="event_img"></li>
                    </a>
                    <li class="event_date">2022-08-02 ~ 2022-12-31</li>
                    <li class="event_ing_ed"></li>
                    <li class="event_name">DRIVE FOR DIAGNOSIS</li>
                </ul>
              </div>
              <div class="event_content">
                <ul>
                  <a href="Golf_Event" class="event_link">
                        <li><img src="CSS/event/event_2.jpg" alt="event_2.jpg" title="이벤트2" class="event_img"></li>
                    </a>
                    <li class="event_date">2022-10-03 ~ 2022-12-31</li>
                    <li class="event_ing_ed"></li>
                    <li class="event_name">Golf_Event</li>
                </ul>
              </div>
              <div class="event_content">
                <ul>
                  <a href="Golf_Event" class="event_link">
                        <li><img src="CSS/event/event_3.jpg" alt="event_3.jpg" title="이벤트3" class="event_img"></li>
                    </a>
                    <li class="event_date">2022-06-28 ~ 2022-10-27</li>
                    <li class="event_ing_ed"></li>
                    <li class="event_name">Visit Thailand Year 2022 Golf Tournament</li>
                </ul>
              </div>
              <div class="event_content">
                <ul>
                  <a href="Golf_Event" class="event_link">
                        <li><img src="CSS/event/event_4.jpg" alt="event_4.jpg" title="이벤트4" class="event_img"></li>
                    </a>
                    <li class="event_date">2022-07-03 ~ 2023-01-15</li>
                    <li class="event_ing_ed"></li>
                    <li class="event_name">스마트스코어 아마추어 골프리그</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
    </div>
    <!-- event script -->
    <script>
      // 이벤트 갯수 count
      var event_content = document.getElementsByClassName("event_content");
      document.getElementById("event_count").innerHTML = "(총 "+event_content.length+"개)";

      // 이벤트 진행여부 변수 생성
      var event_date = document.getElementsByClassName("event_date");
      var event_ing_ed = document.getElementsByClassName("event_ing_ed");
      let today = new Date();
      console.log(today);
      let today_year =  today.getFullYear();
      let today_month =  today.getMonth()+1;
      let today_date =  today.getDate();
      console.log("today_year : "+today_year);
      console.log("today_month : "+today_month);
      console.log("today_date : "+today_date);

      // 이벤트 기간비교 (진행중, 종료 판단)
      for (let i = 0; i < event_date.length; i++) {
        let str = event_date[i].outerText;
        let date = str.charAt(21) + str.charAt(22);
        let month = str.charAt(18) + str.charAt(19);
        let year = str.charAt(13) + str.charAt(14) + str.charAt(15) + str.charAt(16);
        console.log("year : "+year);
        console.log("month : "+month);
        console.log("date:"+date);
        if (year >= today_year) {
          if (month >= today_month) {
            if (date >= today_date) {
              event_ing_ed[i].innerText = "진행중";
            } else {
              event_ing_ed[i].innerText = "종료";
            }
          } else {
            event_ing_ed[i].innerText = "종료";
          }
        } else {
          event_ing_ed[i].innerText = "종료";
        }
      }

      // 이벤트 진행여부 색 지정
      for (let i = 0; i < event_ing_ed.length; i++) {
        if(event_ing_ed[i].outerText == "진행중") {
        event_ing_ed[i].setAttribute('style', 'color:blue;')
        } else if(event_ing_ed[i].outerText == "종료") {
          event_ing_ed[i].setAttribute('style', 'color:gray;')
        }
      }

   // 이벤트 링크 걸어주시
      var link = document.getElementsByClassName("event_link");
      var img = document.getElementsByClassName("event_img");

      var img_link;
      var event_name;
      for (let i=0; i < img.length; i++) {
        img_link = img[i].getAttribute("alt", "alt");
        event_name = link[i].getAttribute("href", "href");
        link[i].setAttribute("href","event_selectOne.do?img_link="+img_link+"&event_name="+event_name);
      }

      // 정렬함수
      var view = document.getElementsByClassName("event_content");

      function view_all() {
        for (let i = 0; i < view.length; i++) {
          view[i].setAttribute("style", "display:inline-block;");
        }
      }
      function view_ing() {
        for (let i = 0; i < view.length; i++) {
          if(event_ing_ed[i].outerText == "진행중") {
            console.log("hear");
            view[i].setAttribute("style", "display:inline-block;");
          } else if(event_ing_ed[i].outerText == "종료") {
            view[i].setAttribute("style", "display:none;");
          }
        }
      }
      function view_ed() {
        for (let i = 0; i < view.length; i++) {
          if(event_ing_ed[i].outerText == "진행중") {
            console.log("hear");
            view[i].setAttribute("style", "display:none;");
          } else if(event_ing_ed[i].outerText == "종료") {
            view[i].setAttribute("style", "display:inline-block;");
          }
        }
      }
      function action(x) {
        if(x == "0") {
          view_all();
        } else if(x == "1") {
          view_ing();
        } else if(x == "2") {
          view_ed();
        }
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
  </body>
</html>