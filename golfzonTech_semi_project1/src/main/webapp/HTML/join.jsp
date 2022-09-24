<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <script src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <!-- join CSS -->
    <style>
        #container {
            position: relative;
            width: 1130px;
            height: 1000px;
            padding: 0px;
            display: block;
            background-color: rgba(176, 224, 230, 0.062);
        }
        #join_logo_img {
          width: 350px;
          height: 350px;
          background-repeat: no-repeat;
          position : relative;
          top: 30%;
          left: 8%;
        }
        #join_box {
          position : relative;
          background-color: rgba(135, 240, 125, 0.041);
          border: 1px solid rgb(32, 99, 26);
          margin: 0px;
          width : 500px;
          height: 700px;
          top : -20%;
          left : 50%;
        }
        #join_box_head {
          padding-left: 20px;
          font-size: 25px;
          position: relative;
          left: 29%;
          color : rgba(26, 138, 26, 0.753);
          letter-spacing : 7px;
        }
        .join_title {
          margin:0px;
          padding-bottom: 5px;
          font-size: 20px;
        }
        #date {
          width: 200px;
        }
        #content ul li {
          float: left;
        }
        #content ul {
          display: inline-block;
          width: 480px;
        }
        #content h3 {
          font-size: 15px;
        }
        .input_text {
          width: 200px;
          height: 20px;
          margin-right: 10px;
        }
        #idcheck {
          width: 75px;
          height: 25px;
          position: relative;
          left: 50%;
          transform: translateX(-50%);
          background: linear-gradient(125deg,#81eca5,#71e75c,#81ec95);
          background-position: left;
          background-size: 200%;
          color:white;
          font-weight: bold;
          border:none;
          cursor:pointer;
          transition: 0.4s;
          border-radius: 20px 20px 20px 20px;
        }
        #idcheck:hover {
          background-position: right;
        }
        #submit_btn {
          width: 300px;
          height:40px;
          position:relative;
          left:70%;
          transform: translateX(-50%);
          margin-bottom: 40px;
          background: linear-gradient(125deg,#81eca5,#71e75c,#81ec95);
          background-position: left;
          background-size: 200%;
          color:white;
          font-weight: bold;
          border:none;
          cursor:pointer;
          transition: 0.4s;
          display:inline;
          border-radius: 20px 20px 20px 20px;
        }
        #submit_btn:hover {
          background-position: right;
        }
    </style>
    <script>
    // 공백체크 코드
      function checkValue() {
        var form = document.userInfo;

        if(!form.id.value) {
          alert("아이디가 비어있습니다.");
          return false;
        }
        if(form.idUncheck.value != "idCheck") {
          alert("아이디 중복체크를 해주세요.");
          return false;
        }
        if(!form.password.value) {
          alert("비밀번호를 입력해주세요.");
          return false;
        }
        // 비밀번호와 비밀번호 확인이 동일한지 확인하는 코드
        if(form.password.value != form.passwordcheck.value) {
          alert("비밀번호를 다릅니다.");
          return false;
        }
        if(!form.name.value) {
          alert("이름을 입력하세요.");
          return false;
        }
      }
      // 아이디 중복체크 화면 open (새창으로 열기)
      function openIdChk(){
        window.name = "parentForm";
        window.open("idCheckpage.do","name(member_id)","resizable=no width=500 height=200 left=1000 top=500","scrollbars=no");
      }
      // 아이디 입력창에 값 입력시 hidden에 idUncheck를 세팅,
      // 중복체크 후 다시 아이디 창이 새로운 아이디를 입력했을 때 다시 중복체크3
      function inputIdChk(){
        document.userInfo.idDuplication.value ="idUncheck";
      }
    </script>
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
      <img id="join_logo_img" src="CSS/logo/logo_large_nobackground.png" alt="join_logo_img">
      <div id="join_box">
        <ul id="join_box_head">
          <h2>회원가입</h2>
        </ul>
        <div id="content">
          <form class="from_design" action="join.do" name="userInfo" onsubmit="return checkValue()">
            <ul>
              <h3 class="join_title">아이디</h3>
              <li><input class="input_text" type="text" maxlength="25" name="member_id" onkeydown="inputIdCheck()"></li>
              <!-- onkeydown : 중복체크 후 수정할 경우를 방지하기 위한 기능 -->
              <li><input id="idcheck" type="button" value="중복확인" onclick="openIdChk()"></li>
              <li><input type="hidden" name="idUncheck" value="idUncheck"></li>
            </ul>
            <ul>
              <h3 class="join_title">비밀번호</h3>
              <li><input class="input_text" type="password" name="password" value="password"></li>
            </ul>
            <ul>
              <h3 class="join_title">비밀번호 확인</h3>
              <li><input class="input_text" type="password" name="passwordcheck" value="password"></li>
            </ul>
            <ul>
              <h3 class="join_title">이름</h3>
              <li><input class="input_text" type="text" name="name" value="name"></li>
            </ul>
            <ul>
              <h3 class="join_title">생년월일</h3>
              <li><input type="date" name="date" id="date" class="date" title="생년월일"></li>
              <script>// date 오늘 날짜 세팅
                document.getElementById('date').value = new Date().toISOString().substring(0, 10);
              </script>
              <!-- <li><select name="year" id="year" title="년도" class="year" onchange="javascript:lastday();"></select>년</li>
              <li><select name="month" id="month" title="월" class="month" onchange="javascript:lastday();"></select>월</li>
              <li><select name="day" id="day" title="일" class="day"></select>일</li> -->
            </ul>
            <ul>
              <h3 class="join_title">성별</h3>
              <li><input type="radio" name="gender" value="남">남</li>
              <li><input type="radio" name="gender" value="여">여</li>
            </ul>
            <ul>
              <h3 class="join_title">지역</h3>
              <li><input type="text" name="country" value="country"></li>
            </ul>
            <!-- <ul>
              <h3 class="join_title">Hadi</h3>
              <li><input type="text" name="hadi" value="hadi"></li>
            </ul> -->
            <ul>
              <li><input id="submit_btn" type="submit" name="join" value="가입"></li>
            </ul>
          </form>
        </div>
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