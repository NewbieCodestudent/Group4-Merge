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
    <!-- 로그인 CSS -->
    <style>
      #container {
        position: relative;
        width: 1130px;
        height: 1000px;
        padding: 0px;
        display: block;
        background-color: rgba(176, 224, 230, 0.062);
      }
      #login_logo_img {
        width: 350px;
        height: 350px;
        background-repeat: no-repeat;
        position : relative;
        top: 30%;
        left: 8%;
      }
      #login_box {
          position : relative;
          background-color: rgba(135, 240, 125, 0.041);
          border: 1px solid rgb(32, 99, 26);
          margin: 0px;
          width : 500px;
          height: 400px;
          top : -8%;
          left : 50%;
        }
        #login_btn {
          width: 300px;
          height:40px;
          position:relative;
          left:70%;
          transform: translateX(-50%);
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
        #login_btn:hover {
          background-position: right;
        }
        #login_head {
          font-size: 25px;
          position: relative;
          left: 37%;
          color : rgba(26, 138, 26, 0.753);
          letter-spacing : 7px;
        }
        .login_title img {
          width: 52px;
          height: 52px;
          padding-right: 0px;
        }
        #login_box #member_id,#pw {
          width: 370px;
          height: 50px;
          padding: 0px;
          border: 0px;
          background-color: rgba(236, 236, 236, 0.479);
          font-size : 15px;
        }
        #login_box ul{
          width: 450px;
          display : inline-block;
        }
        #login_box ul li {
          float : left;
        }
        ::placeholder {
          color : forestgreen
        }
  </style>
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
        <img id="login_logo_img" src="CSS/logo/logo_large_nobackground.png" alt="join_logo_img">
        <div id="login_box">
          <div id="login_head">
            <h2>로그인</h2>
          </div>
          <form action="loginOK.do" method="post">
            <ul style="margin-top: 27px;">
              <li class="login_title"><img src="CSS/icon/login_id.PNG" alt="login_icon"></li>
              <li><input type="text" id="member_id" name="member_id" placeholder="아이디를 입력해주세요."></li>
            </ul>
            <ul>
              <li class="login_title"><img src="CSS/icon/login_password.png" alt="login_password"></li>
              <li><input type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요."></li>
            </ul>
            <ul>
              <li><input id="login_btn" type="submit" value="로그인"></li>
            </ul>
            <ul style="font-size: 15px; letter-spacing: 1px; position: relative; left: 10%;">
              <li style="padding-right: 10px;">아직 GolfMate 회원이 아닌가요?</li>
              <li><a href="member_join.do"><strong><ins>회원가입</ins></strong></a></li>
            </ul>
          </form>
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