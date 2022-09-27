<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>액티비티</title>
    <link rel="stylesheet" href="/CSS/header.css">
    <link rel="stylesheet" href="/CSS/footer.css">
    <link rel="stylesheet" href="/CSS/activity.css">
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
    
	<!-- main -->
    <div id="container">
        <div id="container">
            <!-- 상세검색 selectList -->
            <div id="search_club">
                <form action="searchList">
                    <select name="country" id="country" >
                        <option value="">지역</option>
                        <option value="seoul">서울</option>
                        <option value="daejeon">대전</option>
                        <option value="daegu">대구</option>
                        <option value="bussan">부산</option>
                    </select>
                    <select name="age" id="age">
                        <option value="">나이</option>
                        <option value="teenage">10대</option>
                        <option value="twenty">20대</option>
                        <option value="thirty">30대</option>
                        <option value="forty">40대</option>
                        <option value="fifty">50대</option>
                    </select>
                    <select name="male_female" id="male_female">
                        <option value="">성별</option>
                        <option value="male">남자</option>
                        <option value="female">여자</option>
                    </select>
                    <select name="search_select" id="search_select">
                        <option value="">검색영역</option>
                        <option value="m_c_10">모임이름</option>
                        <option value="m_c_20">개설자</option>
                    </select>
                    <input type="text" name="search_text" id="search_text">
                    <input type="submit">
                </form>
            </div>
            
            <!-- 액티비티 버튼 -->
            <div id="clubSort">
              <ul id="clubSort_left">
                <a hred=""><li>최신순</li></a>
                <a hred=""><li>마감임박순</li></a>
              </ul>
              <ul id="clubSort_right">
                <a href="insert.do"><li>액티비티 개설</li></a>
              </ul>
            </div>
            
            <!-- 모임출력 selectAll -->
            <div id="club_All">
              <a class="club_box" href="selectOne.html">
                <img class="club_img" src="${club_img}" alt="club">
                <strong style="font-size:20px;">${club_name}</strong>
                <div style="height:30px; margin:0px;">
                  <img class="profill_img" src="${img_name}" class="member_profill" title="member_profill" alt="member_profill">
                  <div style="display:inline;">
                    <strong style="font-size:17px;">${act_leader_name}<br>라운딩 날짜 : ${rdate}</strong>
                  </div>
                </div>
              </a>
            </div>
            
            <!-- 하단 번호 -->
            <div id="club_All_Number">
              <c:if test="${startPage != 1}">
                <a href=`clubListAction.do?page=${startPage-1}`>[ 이전 ]</a>
              </c:if>
              <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
                <!-- &nbsp : space의 특수문자코드 -->
                <c:if test="${pageNum == spage}">${pageNum}&nbsp;</c:if>
                <c:if test="${pageNum != spage}">
                  <a href = `culbListAtion.do?page=${pageNum}`>${pageNum}&nbsp;</a>
                </c:if>
              </c:forEach>
              <c:if test="${endPage != maxPage}">
                <a href=`clubListAction.do?page=${endPage+1}`>[ 다음 ]</a>
              </c:if>
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