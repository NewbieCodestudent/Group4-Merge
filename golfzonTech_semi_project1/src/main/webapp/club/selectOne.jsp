<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GolfMate_모임</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/club/clubpage.css">
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
  	
   <!-- main -->
    <div id="container">
      <div id="club_intro_background">
        <div id="club_info">
       	  <a href="club_update.do?club_id=${param.club_id}" title="모임설정"><img src="CSS/icon/setting_icon.png" id="club_setting" alt="setting" title="모임정보수정"></a>
          <div class="club_box">
            <div id="club_img">
              <img src="upload/${vo2.club_img}" alt="${vo2.club_img}">
            </div>
            
            <div id="club_leader">
              <p><strong style="font-size: 18px;">모임장 정보</strong></p>
              <img src="upload/${vo3.img_name}" alt="${vo3.img_name}" style="width: 100px; height:100px;"></li>
              <ul id="club_leader_info">
                <li>이름 : ${vo3.name}</li>
                <li>성별 : ${vo3.m_gender}</li>
                <li>나이 : 만 ${vo3.m_age}세</li>
                <li>지역 : ${vo3.m_location}</li>
                <li style="display:none">히든 ${vo3.member_id}</li>
              </ul>
            </div>
          </div>
          <div class="club_box" style="left:50px;">
            <div id="club_name">
              <p>
              	<strong id="club_close">
	              <script>
	                    // 공개, 비공개 명시
	                    var club_close = ${vo2.close};
	                    if(club_close == 0) {
	                        document.write("공개모임");
	                        document.getElementById('club_close').style.color = "blue";
	                    } else if(club_close == 1) {
	                        document.write("비공개모임");
	                        document.getElementById('club_close').style.color = "red";
	                    }
                  </script>
                </strong>
                ${vo2.club_name}
              </p>
            </div>
            <div id="club_intro">
              <p style="padding: 15px; margin:0px; font-size: 15px;">${vo2.club_desc}</p>
            </div>
            <div id="club_vote">
              <ul style="margin:0px; padding:15px;">
                <p>[ 가입조건 ]</p>
	            <li>성별 : ${vo2.gender}</li>
	            <li id="club_age">나이 : ${vo2.age}</li>
	            <li>지역 : ${vo2.location}</li>
              </ul>
            </div>
          </div>
          <div class="club_box" style="left:70px;">
            <p style="font-size: 15px; margin: 5px 0px;"><strong>모임원</strong></p>
            <!-- 가입신청 -->
            <form action="club_join_applyOK.do" method="post">
              <input type="submit" id="club_join_apply" value="가입신청" onclick="join_apply('${vo.member_id}', '${param.club_id}')">
              <input type="button" id="club_join_no" value="가입할 수 없는 모임입니다.">
              <a href="cm_leaveOK.do?club_id=${param.club_id}" id="leave">모임탈퇴</a>
              <input type="hidden" name="member_id" value="${vo.member_id}">
              <input type="hidden" name="club_id" value="${param.club_id}">
            </form>
            <div id="club_member">
              <ul id="club_member_info">
                <li style="font-size: 15px; font-weight: bold;">[ 모임원 ]</li>
                <br>
                <c:forEach var="vo" items="${vos}">
                  <c:choose>
                    <c:when test="${vo.cmtype==2}">
                      <li><img src="CSS/icon/leader.png" class="club_grade" alt="leader"></li>
                      <li>${vo.name}(${vo.m_age}세 / ${vo.m_gender})</li>
                      <br>
                    </c:when>
                    <c:when test="${vo.cmtype==1}">
                      <form action="cm_revokeOK.do" method="post">
                        <li><img src="CSS/icon/leader2.png" class="club_grade" alt="leader"></li>
                        <li>${vo.name}(${vo.m_age}세 / ${vo.m_gender})</li>
                        <li><input type="hidden" value="${vo.cm_id}" name="cm_id"></li>
                        <li><input type="hidden" value="${param.club_id}" name="club_id"></li>
                        <li><input type="submit" class="decrease" value="강등" onclick="decrease('${vo.name}')"></li>
                      </form>
                      <form action="cm_kickOK.do" method="post">
                      	<li><input type="submit" class="kick" value="강퇴" onclick="kick('${vo.name}')"></li>
                      	<input type="hidden" name="cm_id" value="${vo.cm_id}">
          				<input type="hidden" name="club_id" value="${param.club_id}">
                      </form>
                      <br>
                    </c:when>
                    <c:when test="${vo.cmtype==0}">
                      <form action="cm_grantOK.do" method="post">
                        <li><img src="CSS/icon/member.png" class="club_grade" alt="leader"></li>
                        <li>${vo.name}(${vo.m_age}세 / ${vo.m_gender})</li>
                        <li><input type="hidden" value="${vo.cm_id}" name="cm_id"></li>
                        <li><input type="hidden" value="${param.club_id}" name="club_id"></li>
                        <li><input type="submit" class="increase" value="승급" onclick="increase('${vo.name}')"></li>
                      </form>
                      <form action="cm_kickOK.do" method="post">
                      	<li><input type="submit" class="kick" value="강퇴" onclick="kick('${vo.name}')"></li>
                      	<input type="hidden" name="cm_id" value="${vo.cm_id}">
          				<input type="hidden" name="club_id" value="${param.club_id}">
                      </form>
                      <br>
                    </c:when>
                  </c:choose>
                </c:forEach>
                <li style="font-size: 15px; font-weight: bold;">[ 신청인원 ]</li>
                <br>
                <c:forEach var="vo" items="${vos2}">
                  <form action="club_join_acceptOK.do" method="post">
                    <li><img src="CSS/icon/nomember.png" class="club_grade" alt="leader"></li>
       	            <li>${vo.name}(${vo.m_age}세 / ${vo.m_gender})</li>
	                <li><input type="submit" class="member_accept" value="승인" onclick="member_accept('${vo.name}')"></li>
                    <input type="hidden" value="${vo.cm_id}" name="cm_id">
                    <input type="hidden" value="${param.club_id}" name="club_id">
                    <br>
                  </form>
	            </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <!-- 하단 영역 -->
      <!-- 게시판 -->
      <div class="club_content_box">
        <!-- 게시글 -->
        <div class="box_title">
          <p>게시글 목록</p>
          <a href="board_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">more</a>
        </div>
        <div id="board">
          <!-- 게시글 정렬 -->
          <div>
            <ul style="border-bottom: 3px solid gray;">
              <li class="board_cell" style="width: 100px;">번호</li>
              <li class="board_cell" style="width: 400px;">제목</li>
              <li class="board_cell" style="width: 250px;">작성자</li>
              <li class="board_cell" style="width: 250px;">등록일</li>
            </ul>
          </div>
          <!-- 게시글 출력(selectAll) -->
          <div>
            <!-- 공지 2개 -->
            <c:forEach var="notice" items="${notices}" end="1">
                <ul class="board_content">
                    <li class="board_cell" style="width: 100px;">공지</li>
                    <li class="board_cell" style="width: 400px;">
                      <a href="board_selectOne.do?club_id=${notice.club_id}&club_name=${param.club_name}&board_id=${notice.board_id}">${notice.title}</a>
                    </li>
                    <li class="board_cell" style="width: 250px;">${notice.writer}</li>
                    <li class="board_cell wdate" style="width: 250px;">${notice.wdate}</li>
                </ul>
              </c:forEach>
            <!-- 게시글 3개 -->
              <c:forEach var="common" items="${commons}" end="2">
                <ul class="board_content">
                    <li class="board_cell" style="width: 100px;">
                      <p class="board_counter" style="margin: 0px;"></p>
                    </li>
                    <li class="board_cell" style="width: 400px;">
                      <a href="board_selectOne.do?club_id=${common.club_id}&club_name=${param.club_name}&board_id=${common.board_id}">
                        ${common.title}
                      </a>
                    </li>
                    <li class="board_cell" style="width: 250px;">${common.writer}</li>
                    <li class="board_cell wdate" style="width: 250px;">${common.wdate}</li>
                </ul>
              </c:forEach>
          </div>
        </div>
      </div>
      <!-- 액티비티 -->
      <div class="club_content_box">
        <div class="box_title">
          <p>액티비티</p>
          <a href="club_activity.do?club_id=${param.club_id}&club_name=${param.club_name}">more</a>
        </div>
        <!-- 액티비티틀 -->
        <div id="activity_box">
          <!-- 더미 생성 3개만 출력 -->
          <c:forEach var="activity" items="${activity_list}" end="2">
	            <a href="club_activity_selectOne.do?club_id=${activity.club_id}&club_name=${param.club_name}&act_id=${activity.act_id}">
	              <div class="activity_box">
	                <ul style="padding: 0px;">
	                  <li><img src="upload/${activity.act_fname}" alt="activity" class="activity_img"></li>
	                  <li>
	                    <ul style="padding:0px 10px;" class="activity_text">
	                      <li>${activity.act_name}</li>
	                      <li>주최자: ${activity.act_leader}</li>
	                      <li class="rdate">라운딩일: ${activity.rdate}</li>
	                      <li class="adate">마감일자: ${activity.adate}</li>
	                    </ul>
	                  </li>
	                </ul>
	              </div>
	            </a>
            </c:forEach>
        </div>
      </div>
      <!-- 앨범 -->
      <div class="club_content_box">
        <div class="box_title">
          <p>앨범</p>
          <a href="album_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">more</a>
        </div>
        <div class="album">
          <c:forEach var="album" items="${album_list}" end="4">
          		<img src="upload/${album.fname}" alt="album" class="album_img">
          </c:forEach>
        </div>
      </div>
    </div>
    <!-- 상단 부분 JS -->
    <script>
    // wdate fillter
    let wdate = document.getElementsByClassName('wdate');
    console.log(wdate.length);
    for (let i = 0; i < wdate.length; i++) {
      console.log(wdate[i].outerText.slice(0,-5));
      wdate[i].innerText = wdate[i].outerText.slice(0,-5);
    }
    let rdate = document.getElementsByClassName('rdate');
    let adate = document.getElementsByClassName('adate');
    console.log(wdate.length);
    for (let i = 0; i < rdate.length; i++) {
   		rdate[i].innerText = rdate[i].outerText.slice(0,-5);
   		adate[i].innerText = adate[i].outerText.slice(0,-5);
    }
    // 나이 필터
    let age = ${vo2.age}
    if(age == 0) {
		document.getElementById("club_age").innerText = "나이 : 무관";
   	} else if(age == 60) {
   		document.getElementById("club_age").innerText = "연령대 : 60대 이상";
   	} 
    //각 버튼
      // 승급
      function increase(x, y, z) {
        var msg = confirm(x+"님을 운영자로 승급하시겠습니까?");
        if(msg==true) {
        	alert("승급완료");
        } else {
            return false;
        }
      }
      // 강등
      function decrease(x, y, z) {
        var msg = confirm(x+"님을 일반회원으로 강등하시겠습니까?");
        if(msg==true) {
        	alert("강등완료");
        } else {
            return false;
        }
      }
      // 강퇴
      function kick(x,y,z) {
        var msg = confirm(x+"님을 강퇴하시겠습니까?");
          if(msg==true) {
        	  alert("강퇴완료");
          } else {
              return false;
          }
      }
      // 승인
      function member_accept(x,y,z) {
        var msg = confirm(x+"님을 회원으로 승인하시겠습니까?");
        console.log(x)
        console.log(y)
        console.log(y)
          if(msg==true) {
              alert("승인완료");
          } else {
              return false;
          }
      }
	  // 모임신청
      function join_apply(x,y) {
		var msg = confirm("모임에 가입을 신청하시겠습니까?");
          if(msg==true) {
        	  alert("신청완료");
          } else {
              return false;
         }			
      }

      var number = document.getElementsByClassName("board_counter").length;
            var test = document.getElementsByClassName("board_counter");
            for (let i = 0; i < number; i++) {
              test[i].innerHTML = i+1;
            }

      // 버튼 보이는 권한 부여
      // 클럽에서의 권한 : isLeader
      // 가입조건의 충족 : isQualified
      let cmtype = "${isLeader}";
      console.log("isLeader : "+cmtype);
      let class_increase = document.getElementsByClassName('increase');
      let class_decrease = document.getElementsByClassName('decrease');
      let class_kick = document.getElementsByClassName('kick');
      let class_member_accept = document.getElementsByClassName('member_accept');
      if(cmtype == "true") {
        document.getElementById('club_setting').setAttribute('style', 'display:block');
        document.getElementById('club_join_apply').setAttribute('style', 'display:none');
        for (let i = 0; i < class_increase.length; i++) {
          class_increase[i].setAttribute('style', 'display:block');
        }
        for (let i = 0; i < class_decrease.length; i++) {
          class_decrease[i].setAttribute('style', 'display:block');
        }
        for (let i = 0; i < class_kick.length; i++) {
          class_kick[i].setAttribute('style', 'display:block');
        }
        for (let i = 0; i < class_member_accept.length; i++) {
          class_member_accept[i].setAttribute('style', 'display:block');
        }
      }
      var isQualified = "${isQualified}";
      if(isQualified == "true") {
      	document.getElementById('club_join_no').setAttribute('style','display:none');
      } else {
      	if(cmtype == "true"){
	      	document.getElementById('club_join_apply').setAttribute('style','display:none');
	      	document.getElementById('club_join_no').setAttribute('style','display:none');
      	} else {
	      	document.getElementById('club_join_apply').setAttribute('style','display:none');
      	}
      }
      console.log("isQualified : "+${isQualified});
      // 비원일 경우 신청보이기...
      let nomember_cmtype = "${isMember}";
      console.log("isMember : "+nomember_cmtype);
      if(nomember_cmtype == "true") {
        document.getElementById('club_join_apply').setAttribute('style', 'display:none');
      }
      console.log("status : "+${vo5.status})
      if("${vo5.status}"=="0") {
        let club_content_box = document.getElementsByClassName('club_content_box');
        for (let i = 0; i < club_content_box.length; i++) {
          club_content_box[i].setAttribute('style','display:none');
        }
        document.getElementById('container').setAttribute('style','height:480px');
      } else {
    	  if(cmtype == "true"){
    		  document.getElementById('leave').setAttribute('style', 'display:none');
    	  } else {
    		  document.getElementById('leave').setAttribute('style', 'display:block');
    	  }
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