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
    <link rel="stylesheet" href="CSS/clubpage.css">
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
					<style>#account {left : 90%;}</style>
				</c:when>
				<c:otherwise>
					<a href="mypage.do">마이페이지</a>
					<a href="logout.do">로그아웃</a>
					<style>#account {left : 83%;}</style>
				</c:otherwise>
			</c:choose>
          </div>
        </div>


	<!-- main -->
    <div id="container">
        <div id="club_intro_background">
          <div id="club_info">
            <a href="settin page" title="모임설정" sec:authorize="isleader()"><img src="CSS/icon/setting_icon.png" alt="setting" style="width:30px; margin: 0px; position: absolute; left:97%; top:1%;"></a>
            <div class="club_box">
<!--             모임수정 삭제 버튼 -->
            <div>
            <td colspan="3">
					<a href="club_update.do?club_id=${param.club_id}">모임정보수정</a>
					<a href="club_delete.do?club_id=${param.club_id}">모임삭제</a>
				</td>
            </div>
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
                </ul>
              </div>
            </div>
            <div class="club_box" style="left:50px;">
              <div id="club_name">
                <p>${vo2.club_name}</p>
              </div>
              <div id="club_intro">
                <p style="padding: 15px; margin:0px; font-size: 15px;">${vo2.club_desc}</p>
              </div>
              <div id="club_vote">
                <ul style="margin:0px; padding:15px;">
                  <li>투표1</li>
                  <li>투표2</li>
                  <li>투표3</li>
                  <li>투표4</li>
                </ul>
              </div>
            </div>
            <div class="club_box" style="left:70px;">
              <p style="font-size: 15px; margin: 5px 0px;"><strong>모임원</strong></p>
              <div id="club_member">
                <p>모임원 목록 C:foreach 활용</p>
              </div>
            </div>
          </div>
        </div>
        <div class="club_content_box">
          <div class="box_title">
            <p>게시글 목록</p>
            <a href="/board/selectAll.html">more</a>
          </div>
          <div class="board">
            <div class="row header green">
              <div class="cell">게시글 번호</div>
              <div class="cell">제목</div>
              <div class="cell">작성자</div>
              <div class="cell">등록일</div>
            </div>
            <div class="row">
              <div class="cell" data-title="number">1</div>
              <div class="cell" data-title="title">게시글1</div>
              <div class="cell" data-title="writer">작성자1</div>
              <div class="cell" data-title="wdate">등록일</div>
            </div>
          </div>
        </div>
        <div class="club_content_box">
          <div class="box_title">
            <p>액티비티</p>
            <a href="club_activity">more</a>
          </div>
          <div class="box_content">
            
          </div>
        </div>
        <div class="club_content_box">
          <div class="box_title">
            <p>앨범</p>
            <a href="club_album">more</a>
          </div>
          <div class="box_content">

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