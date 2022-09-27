<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>액티비티 등록</title>
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
              <a href="activity_selectAll.do">액티비티</a>
            </li>
            <li class="nav_item">
              <a href="event_selectAll.do">이벤트</a>
            </li>
          </ul>
        </div>
    </div>

    <div id="container">
        <!-- 액티비티(타이틀) -->
        <div id="insert_title_box">
            <div id="insert_title">
                <p style="font-size: 30px; padding-left: 30px; color: forestgreen;"><strong>액티비티개설</strong></p>
            </div>
        </div>
        <!-- 개발영역(insert) -->
        <div id="insert_box">
            <div id="insert_content">
                <div style="padding: 30px;">
                    <form action="insert.do">
                        <p>개설자_name</p>
                        <p>[ 액티비티 공개여부 ]</p>
                        <select name="club_id" id="club_id" name="club_id">
                            <option value="">전체공개</option>
                            <option value="">모임공개</option>
                        </select>
                        <p>[ 액티비티명 ]</p>
                        <input type="text" id="act_name" name="act_name">
                        <p>[ 성별 ]</p>
                        <select name="gender" id="gender" name="gender">
                            <option value="all">무관</option>
                            <option value="male">남</option>
                            <option value="female">여</option>
                        </select>
                        <p>[ 연령대 ]</p>
                        <select name="age" id="age" name="age">
                            <option value="all">무관</option>
                            <option value="age_10">10대</option>
                            <option value="age_20">20대</option>
                            <option value="age_30">30대</option>
                            <option value="age_40">40대</option>
                            <option value="age_50">50대</option>
                            <option value="age_60">60대 이상</option>
                        </select>
                        <P>[ 지역 ]</P>
                        <select name="location" id="location" name="location">
                            <option value="seoul">서울</option>
                            <option value="daejeon">대전</option>
                            <option value="daegu">대구</option>
                            <option value="bussan">부산</option>
                        </select>
                        <P>[ 비용 ]</P>
                        <input type="number" id="cost" name="cost">
                        <p>[ 라운딩날짜 ]</p>
                        <input type="datetime-local" id="rdate" name="rdate">
                        <p>[ 모집마감일자 ]</p>
                        <input type="datetime-local" id="adate" name="adate">
                        <input type="submit" value="액티비티 개설">
                    </form>
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