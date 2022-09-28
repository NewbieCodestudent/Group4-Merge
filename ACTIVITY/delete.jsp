<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>액티비티</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <style>
        #container {
            position: relative;
            width: 1130px;
            height: 940px;
            padding: 0px;
            display: block;
        }
        #insert_title_box {
            position: relative;
            background-color: rgba(157, 221, 171, 0.648);
            width: 1130px;
            height: 140px;
        }
        #insert_title {
            position: absolute;
            background-color: white;
            width: 1070px;
            height:80px;
            margin: 30px;
        }
        #insert_box {
            position: relative;
            width: 1130px;
            height: 770px;
            margin-top: 30px;
            border-top: 5px solid rgb(9, 9, 143);
        }
        #insert_content {
            position: absolute;
            margin: 30px;
            width: 1070px;
            height: 710px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <!-- headarea -->
    <div id="header">
        <div id="header_up">
          <div id="logo">
            <a href="home.do">
              <img id="logo_img" src="/CSS/logo/logo_small.png" alt="logo_img">
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
              <a href="activity_selectAll.do">액티비티</a>
            </li>
            <li class="nav_item">
              <a href="event_selectALl.do">이벤트</a>
            </li>
          </ul>
        </div>
    </div>
    
    <div id="container">
        <!-- 개발영역(selectOne) -->
        <div id="insert_title_box">
            <div id="insert_title">
                <p style="font-size: 30px; padding-left: 30px;">
                    <!-- 모집중, 모집마감 변수 설정 구역 -->
                    <strong id="var" style="">
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
                    <strong>act_name</strong>
                </p>
                <p style="font-size: 30px; padding-left: 30px; color: forestgreen;">
                    
                </p>
            </div>
        </div>
        <a href="insert.do"><li>액티비티 개설</li></a>
       	<div>
        	<p>접속자 정보 (리더인지 아닌지 -> 수정가능 여부): ${flag}</p>
       	</div>
        <ul>
        	<strong>삭제하시겠습니까?</strong>
            <a href="activity_deleteOK.do?act_id=${param.act_id}"><li>예</li></a>
            <a href="activity_selectOne.do?act_id=${param.act_id}"><li>아니요</li></a>
        </ul>
        
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