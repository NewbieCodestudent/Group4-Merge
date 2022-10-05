<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>GolfMate_모임_게시글 작성</title>
  <link rel="stylesheet" href="CSS/header.css">
  <link rel="stylesheet" href="CSS/footer.css">
  <link rel="stylesheet" href="CSS/board/insert.css">
  <!-- 파일 이름 출력해주는 구문 -->
  <script type="text/javascript">
    function fileUpload(){
      var fileInput = document.getElementsByName("upFile");
  
      for( var i=0; i<fileInput.length; i++ ){
        if( fileInput[i].files.length > 0 ){
          for( var j = 0; j < fileInput[i].files.length; j++ ){
            console.log(fileInput[i].files[j].name); // 파일명 출력
            document.getElementById("upFileName").innerHTML = fileInput[i].files[j].name;
          }
        }
      }
  
    }
  </script>
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
    <div id="club_name_box">
      <div id="club_title">
        <p style="font-size: 30px; padding-left: 30px;">
            <strong>${param.club_name}</strong>
        </p>
      </div>
    </div>
    <div id="club_content">
      <ul>
        <li><a href="board_selectAll.do?club_id=${param.club_id}" style="color:blue;">게시글</a></li>
        <li><a href="activity_selectAll.do">액티비티</a></li>
        <li><a href="album_selectAll.do">앨범</a></li>
        <li><a href="vote_selectAll.do">투표</a></li>
      </ul>
    </div>
    <div id="board_content_box">
      <div style="font-size: 20px; font-weight: bold; position:relative; top: 2%; left: 95%;">
        <a href="board_selectAll.do?club_id=${param.club_id}">목록</a>
      </div>
      <div id="board_insert_box">
        <!-- 작성일 출력 -->
        <div id="current_date" style="font-size: 20px; position: absolute; top: 4%; left: 82%; font-weight: bold;"></p>
          <script>
          date = new Date();
          year = date.getFullYear();
          month = date.getMonth() + 1;
          day = date.getDate();
          document.getElementById("current_date").innerHTML = "작성일 : " + year + "." + month + "." + day;
          </script>
        </div>
        <!-- 게시글 insert -->
        <form action="board_insertOK.do" method="post" enctype="multipart/form-data">
          <select name="notice" id="notice">
            <option value="0">게시글</option>
            <option id="notice_leader" value="1">공지</option>
            <script>
            	let isLeader = "${isLeader}";
            	console.log(isLeader);
            	if(isLeader == "false") {
            		document.getElementById('notice_leader').setAttribute('hidden','hidden');
            	}
            </script>
          </select>
          <input type="text" placeholder="title" id="title" name="title" value="title1" maxlength="25">
          <br>
          <textarea type="text" placeholder="content" id="content" name="content" value="content" maxlength="500"></textarea>
          <!-- <input type="text" placeholder="content" id="content" name="content" value="content"> -->
          <div id="filebox">
            <label for="upFile">업로드</label> 
            <input type="File" id="upFile" name="upFile" onchange="fileUpload()" multiple>
            <p id="upFileName">파일이름</p>
          </div>
          <input type="hidden" value="${param.club_id}" name="club_id">
          <input type="hidden" value="${param.club_name}" name="club_name">
          <input id="submit" type="submit" value="등 록">
        </form>
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