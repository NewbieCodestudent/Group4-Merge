<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>GolfMate_모임_게시글</title>
  <link rel="stylesheet" href="CSS/header.css">
  <link rel="stylesheet" href="CSS/footer.css">
  <link rel="stylesheet" href="CSS/board/selectOne.css">
  <script>
  /* 삭제를 alert 여는 script문 */
    function doAction(value) {
	  if(value == 0) {
		  location.href = "board_update.do?club_id=${param.club_id}&club_name=${param.club_name}&board_id=${param.board_id}";
	  } else if(value == 1) {
		  var msg = confirm("정말 삭제하시겠습니까?");
		  if(msg==true){
			  location.href = "board_deleteOK.do?club_id=${param.club_id}&club_name=${param.club_name}&board_id=${param.board_id}";  
		  } else {
			  return false;
		  }
	  } else if(value == 2) {
		  location.href = "board_insert.do?club_id=${param.club_id}&club_name=${param.club_name}";
	  }
  	}
  	function commentAction(value) {
  		var msg = confirm("정말 삭제하시겠습니까?");
		  if(msg==true){
			  location.href = "comment_deleteOK.do?club_id=${param.club_id}&club_name=${param.club_name}&board_id=${param.board_id}&&comment_id="+value;  
		  } else {
			  return false;
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
    <!-- 모임이름표시 -->
    <div id="club_name_box">
      <div id="club_title">
        <p style="font-size: 30px; padding-left: 30px;">
            <strong>${param.club_name}</strong>
        </p>
      </div>
    </div>
    
    <!-- 모임 카테고리 -->
    <div id="club_content">
      <ul>
        <li><a href="board_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}" style="color:blue;">게시글</a></li>
        <li><a href="club_activity.do?club_id=${param.club_id}&club_name=${param.club_name}">액티비티</a></li>
        <li><a href="album_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">앨범</a></li>
        <li><a href="vote_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">투표</a></li>
      </ul>
    </div>
    
    <!-- selectOne 구역 -->
    <div id="selectOne_box">
      <div id="update_delete_insert">
        <ul style="margin: 0%;">
          <li class="leader_power"><input type="button" value="수정" onclick="doAction(0)"></li>
          <li class="leader_power"><input type="button" value="삭제" onclick="doAction(1)"></li>
		  <li><input type="button" value="작성" onclick="doAction(2)"></li>          
        </ul>
      </div>
      <div id="selectOne_top">
        <ul style="padding-left: 20px;">
          <li name="type" style="width: 80px; border-right: 3px solid gray;" id="notice">${vo1.notice}</li>
          <li style="width: 490px; text-align: left; padding-left: 20px; border-right: 3px solid gray;"><label for="title">제목 : </label>${vo1.title}</li>
          <li style="width: 150px; border-right: 3px solid gray;"><label for="writer">작성자 : </label>${vo1.writer}</li>
          <li style="width: 280px;" id="wdate">작성일자 : ${vo1.wdate}</li>
        </ul>
      </div>
      <div id="selectContent">
        <label for="content">${vo1.content}</label><br>
        <img style="padding:10px; width:300px;" alt="imgName" src="upload/${vo1.fname}">
      </div>
      <div id="selectFile">
        <a href="${vo1.fname}" download=""><button>다운로드</button> : ${vo1.fname}</a>
      </div>
    </div>

    <!-- 댓글공간 -->
    <div id="comment_insert">
      <form action="comment_insertOK.do" method="post" enctype="multipart/form-data" id="comment_box">
        <textarea name="comment" id="comment" placeholder="댓글을 작성해주세요." maxlength="500"></textarea>
        <input type="submit" value="등 록" id="submit">
        <input type="hidden" placeholder="club_id" id="club_id" name="club_id" value="${param.club_id}">
        <input type="hidden" placeholder="board_id" id="board_id" name="board_id" value="${param.board_id}">
        <input type="hidden" placeholder="club_name" id="club_name" name="club_name" value="${param.club_name}">
      </form>
    </div>
    <div id="comment_selectAll">
      <ul class="comment_selectAll_1">
        <li><img src="CSS/icon/comment.png" alt="comment" style="width: 30px;"></li>
        <li style="height: 24px;">댓글(${vos.size()})</li>
      </ul>
      <div style="overflow:auto; height: 420px;">
        <c:forEach var="vo" items="${vos}">
          <ul class="comment_selectAll_1" style="overflow:auto;">
            <li style="line-height: 28px;" class="cdate">${vo.commenter}(${vo.cdate})</li><br>
            <li><input type="button" value="삭제" onclick="commentAction(${vo.comment_id})" class="comment_delete ${vo.commenter}">
              ${vo.comment}
            </li>
          </ul>
        </c:forEach>
      </div>
    </div>
  </div>
  <script>
  	// 공지인가 게시글인가
  	let notice = document.getElementById('notice').outerText;
  	if(notice == "1") {
  		document.getElementById('notice').innerText = "공지";
  	} else {
  		document.getElementById('notice').innerText = "게시글";
  	}
  	
  	// 날짜 필터
  	let wdate = document.getElementById('wdate');
  	wdate.innerText = wdate.outerText.slice(0,-5);
  	let cdate = document.getElementsByClassName('cdate');
	  console.log(wdate.length);
	  for (let i = 0; i < cdate.length; i++) {
	    console.log(cdate[i].outerText.slice(0,-5));
	    cdate[i].innerText = cdate[i].outerText.slice(0,-6)+")";
	  }
	  
	// session 값 가져와서 작성자와 맞는지 확인
	<%String session_id = (String)session.getAttribute("member_id");%>
	let session = "<%=session_id%>";
	console.log(session);
	let comment_writer = document.getElementsByClassName('<%=session_id%>');
	console.log(comment_writer.length)
	for (let i = 0; i < comment_writer.length; i++) {
	 comment_writer[i].setAttribute('style','display:block');
	}
	  
	 // 리더, 작성자 확인
	 let leader_power = document.getElementsByClassName('leader_power');
	 let isWriter = ${isWriter}
	 console.log(isWriter)
	 let isLeader = ${isLeader}
	 console.log(isLeader)
	 if(isWriter == false) {
	console.log("작성자가 아닙니다.")
	   for (let i = 0; i < leader_power.length; i++) {
	     leader_power[i].setAttribute('style','display:none');
	   }
	 }
	 
	 // 리더이면 리더의 권한을 출력
	 if(isLeader == false) {
	  console.log("리더가 아닙니다.")
	   for (let i = 0; i < leader_power.length; i++) {
	     leader_power[i].setAttribute('style','display:none');
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