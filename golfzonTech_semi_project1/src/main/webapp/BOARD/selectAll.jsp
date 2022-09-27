<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GolfMate_모임_게시판</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/board/boardselectAll.css">
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
	            ${member_id}님 환영합니다.
	            <a href="mypage.do?member_id=${member_id}">
	              <img src="CSS/icon/login.png" alt="mypage" name="mypage" id="mypage" title="마이페이지">
	            </a>
	            <a href="logout.do">
	              <img src="CSS/icon/logout.jpg" alt="logout" name="logout" id="logout" title="로그아웃">
	            </a>
	            <style>#account {position:absolute; top:20px; left:900px; width:230px} #account a img {width: 25px; height: 30px; padding: 0px 10px;}</style>
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
    <div id="club_name_box">
      <div id="club_title">
        <p style="font-size: 30px; padding-left: 30px;">
            <!-- 모집중, 모집마감 변수 설정 구역 -->
            <strong id="var">
                <script>
                    // 변수 var = 모집관련 boolean
                    var club_id = 0;
                    if(club_id == 0) {
                        document.write("모집중");
                        document.getElementById('var').style.color = "blue";
                    } else if(club_id == 1) {
                        document.write("모집마감");
                        document.getElementById('var').style.color = "red";
                    }
                </script>
            </strong>
            <strong>club_name</strong>
        </p>
      </div>
    </div>

    <div id="club_content">
      <ul>
        <li><a href="board_selectAll.do?club_id=20" style="color:blue;">게시글</a></li>
        <li><a href="activity_selectAll.do">액티비티</a></li>
        <li><a href="album_selectAll.do">앨범</a></li>
        <li><a href="vote_selectAll.do">투표</a></li>
      </ul>
    </div>

    <div id="board_content_box">
      <div id="board_sort_box">
        <ul style="padding-left: 20px;">
          <li>
            <form action="board_searchListOK.do?club_id=20" method="get">
              <select name="searchKey" id="searchKey" style="height: 30px; font-size: 15px; border-radius: 5px; border: 2px solid gray;">
                <option value="title">title</option>
                <option value="content">content</option>
                <option value= "writer">writer</option>
              </select> 
              <input type="text" name="searchWord" id="searchWord" value="title" style="width: 300px; height: 30px; font-size: 15px; border-radius: 5px; border: 2px solid gray;">
              <input type="hidden" name="club_id" id="club_id" value="20">
              <input type="submit" style="height: 30px; font-size: 15px; border-radius: 5px; border: 2px solid gray;">
            </form>
          </li>
        </ul>
        <ul style="position: absolute; top: 7%; left: 86%;">
          <li><a href="board_insert.do?club_id=${param.club_id}" style="font-size: 18px;">게시글 작성</a></li>
        </ul>
        <ul id="sort_list" style="padding-left: 20px; font-size:15px; margin: 30px 0px 0px 0px;">
          <li>작성일기준 |</li>
          <li><a href="board_selectAll.do?club_id=20&&order=desc">내림차순</a></li>
          <li><a href="board_selectAll.do?club_id=20&&order=asc">오름차순</a></li>
        </ul>
      </div>
      <div style="width: 1130px;" id="board_content_ul">
        <!-- 게시글 정렬 -->
        <div id="board_sort_title">
          <ul style="border-bottom: 3px solid gray;">
            <li style="width: 100px; height: 30px; border-right: 5px solid gray;">번호</li>
            <li style="width: 575px; height: 30px; border-right: 5px solid gray;">제목</li>
            <li style="width: 200px; height: 30px; border-right: 5px solid gray;">작성자</li>
            <li style="width: 150px; height: 30px;">등록일</li>
          </ul>
        </div>
        <!-- 게시글 출력(selectAll) -->
        <div id="board_content">
          <ul class="board_content">
            <c:forEach var="notice" items="${notices}">
                <li style="font-weight: bold; width: 100px; height: 30px;">공지</li>
                <li style="width: 575px; height: 30px;">
                	<a href="board_selectOne.do?club_id=${param.club_id}&&board_id=${notice.board_id}">${notice.title}</a>
                </li>
                <li style="width: 200px; height: 30px;">${notice.writer}</li>
                <li style="width: 150px; height: 30px;">${notice.wdate}</li>
            </c:forEach>
          </ul>
          <ul class="board_content">
            <c:forEach var="common" items="${commons}">
              <li style="font-weight: bold; width: 100px; height: 30px;">
              	<p class="board_counter"></p>
              </li>
              <li style="width: 575px; height: 30px;">
              	<a href="board_selectOne.do?club_id=${param.club_id}&&board_id=${common.board_id}">
              		${common.title}
              	</a>
              </li>
              <li style="width: 200px; height: 30px;">${common.writer}</li>
              <li style="width: 150px; height: 30px;">${common.wdate}</li>
            </c:forEach>
          </ul>
          <script>
            var number = document.getElementsByClassName("board_counter").length;
            var test = document.getElementsByClassName("board_counter");
            for (let i = 0; i < number; i++) {
              test[i].innerHTML = i+1;
            }
          </script>
        </div>
      </div>
    </div>
  </div>
  
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