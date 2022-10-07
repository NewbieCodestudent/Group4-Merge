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
    /* id 중복체크 */
    window.onload = function() {
        console.log("onload...");

        //2
        let btn_idCheck = document.querySelector("#btn_idCheck");
        let result = document.querySelector("#result");
        //3
        btn_idCheck.onclick = function(event) {
           console.log("onclick...");
           let member_id = document.querySelector("#member_id");
           // console.log(id);
           console.log(member_id.value);

           let req = new XMLHttpRequest();

           req.addEventListener("load", function() {
              console.log(this.status);
              console.log(this.responseText);
              // {"result":"Not OK"}
              if (this.status == 200) {
                 try {
                    let txt_json = this.responseText;
                    let obj_json = JSON.parse(txt_json);
                    console.log(obj_json);
                    console.log(obj_json.result);

                    let txt = "";

                    if (obj_json.result == 'Not OK') {
                       txt = "사용중인 아이디";
                    } else {
                       txt = "사용가능 아이디";
                    }
                    result.innerHTML = txt;
                 } catch (e) {
                    console.log("json 형식이 아님.");
                 }
              }//end if
           });
           req.open("GET", "http://localhost:8090/golfzonTech_semi_project1/idCheck.do?member_id=" + member_id.value);
           req.send();
           event.preventDefault();
           event.stopPropagation();
        };
     };
    /* 공백체크 코드 */
      function checkValue() {
        var form = document.userInfo;

        if(!form.member_id.value) {
          alert("아이디가 비어있습니다.");
          return false;
        }
        /* if(form.idUncheck.value != "idCheck") {
          alert("아이디 중복체크를 해주세요.");
          return false;
        } */
        if(!form.pw.value) {
          alert("비밀번호를 입력해주세요.");
          return false;
        }
        if(!form.pwcheck.value) {
            alert("비밀번호를 확인해 주세요.");
            return false;
          }
        // 비밀번호와 비밀번호 확인이 동일한지 확인하는 코드
        if(form.pw.value != form.pwcheck.value) {
          alert("비밀번호가 다릅니다.");
          return false;
        }
        if(!form.name.value) {
          alert("이름을 입력해주세요.");
          return false;
        }
        if(!form.gender.value) {
            alert("성별를 선택해주세요.");
            return false;
        }
        if(form.location.value == "지역") {
        	console.log(form.location.value);
            alert("지역을 선택해주세요.");
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

    <!-- main -->
    <div id="container">
      <img id="join_logo_img" src="CSS/logo/logo_large_nobackground.png" alt="join_logo_img">
      <div id="join_box">
        <ul id="join_box_head">
          <h2>회원가입</h2>
        </ul>
        <div id="content">
          <form method="post" enctype="multipart/form-data" class="from_design" action="member_joinOK.do" name="userInfo" onsubmit="return checkValue()">
            <ul>
              <h3 class="join_title">아이디</h3>
              <li><input class="input_text" type="text" maxlength="25" id="member_id" name="member_id" placeholder="아이디를 입력해 주세요."></li>
              <li><input type="button" id="btn_idCheck" name="btn_idCheck" value="중복확인"><span id="result"></span></li>
              <li><input type="hidden" name="idUncheck" value="idUncheck"></li>
            </ul>
            <ul>
              <h3 class="join_title">비밀번호</h3>
              <li><input class="input_text" type="password" name="pw" placeholder="비밀번호를 입력해 주세요."></li>
            </ul>
            <ul>
              <h3 class="join_title">비밀번호 확인</h3>
              <li><input class="input_text" type="password" name="pwcheck" placeholder="비밀번호를 확인해 주세요."></li>
            </ul>
            <ul>
              <h3 class="join_title">프로필</h3>
              <li><input type="file" name="upfile" value="upfile"></li>
            </ul>
            <ul>
              <h3 class="join_title">이름</h3>
              <li><input class="input_text" type="text" name="name" placeholder="이름을 입력해 주세요."></li>
            </ul>
            <ul>
              <h3 class="join_title">생년월일</h3>
              <li><input type="date" name="birthday" id="birthday" class="birthday" title="생년월일" value="1970-01-01"></li>
            </ul>
            <ul>
              <h3 class="join_title">성별</h3>
              <li><input type="radio" name="gender" value="남">남</li>
              <li><input type="radio" name="gender" value="여">여</li>
            </ul>
            <ul>
              <h3 class="join_title">지역</h3>
              <li>
				<select name="location" id="location">
					<option selected hidden>지역</option>
                    <option value="서울">서울</option>
                    <option value="경기">경기</option>
                    <option value="강원">강원</option>
                    <option value="전북">전북</option>
                    <option value="전남">전남</option>
                    <option value="경북">경북</option>
                    <option value="경남">경남</option>
                    <option value="충북">충북</option>
                    <option value="충남">충남</option>
                    <option value="제주">제주</option>
                    <option value="대전">대전</option>
                    <option value="인천">인천</option>
                    <option value="광주">광주</option>
                    <option value="대구">대구</option>
                    <option value="부신">부산</option>
                </select>
			  </li>
            </ul>
            <ul>
              <li><input id="submit_btn" type="submit" name="join" value="가입"></li>
            </ul>
          </form>
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