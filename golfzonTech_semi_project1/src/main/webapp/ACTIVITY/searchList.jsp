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
    <link rel="stylesheet" href="CSS/activity.css">
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
                <form action="activity_searchList.do?club_id=${param.club_id}">
                    <select name="location" id="location" >
                        <option value="무관">지역</option>
                        <option value="서울">서울</option>
                        <option value="대전">대전</option>
                        <option value="대구">대구</option>
                        <option value="부산">부산</option>
                    </select>
                    <select name="age" id="age">
                        <option value="0">나이</option>
                        <option value="10">10대</option>
                        <option value="20">20대</option>
                        <option value="30">30대</option>
                        <option value="40">40대</option>
                        <option value="50">50대</option>
                        <option value="60">60대 이상</option>
                    </select>
                    <select name="gender" id="gender">
                        <option value="무관">성별</option>
                        <option value="남">남자</option>
                        <option value="여">여자</option>
                    </select>
                    <select name="searchKey" id="searchKey">
                        <option value="">검색영역</option>
                        <option value="act_name">모임이름</option>
                        <option value="ac_leader">개설자</option>
                    </select>
                    <input type="text" name="searchWord" id="searchWord" value="">
                    <input type="submit">
                </form>
            </div>
            
            <!-- 액티비티 버튼 -->
            <div id="clubSort">
              <ul id="clubSort_left">
                <a href="activity_selectAll.do?club_id=${club_id}&&order=id"><li>최신순</li></a>
                <a href="activity_selectAll.do?club_id=${club_id}&&order=adate"><li>마감임박순</li></a>
              </ul>
              <ul id="clubSort_right">
                <a href="activity_insert.do"><li>액티비티 개설</li></a>
              </ul>
            </div>
            
            <!-- 모임출력 selectAll -->
            <div id="club_All">
			<c:forEach var="vo" items="${vos}">
              <a class="club_box" href="activity_selectOne.do?club_id=${vo.club_id}&&act_id=${vo.act_id}">
                <p>status: ${vo.status}</p>
                <img class="activity_img" src="upload/ACTIVITY/${vo.act_fname} " alt="club">
                <strong style="font-size:20px;">${vo.act_name}</strong>
                <div style="height:30px; margin:0px;">
                  <img class="profill_img" src="upload/ACTIVITY/${vo.leader_fname}" class="member_profill" title="member_profill" alt="member_profill">
                  <div style="display:inline;">
                    <strong style="font-size:17px;">${vo.act_leader}<br>라운딩 날짜 : ${vo.rdate}<br>마감 날짜 : ${vo.adate}</strong>
                  </div>
                </div>
              </a>
             </c:forEach>
            </div>
            
            <!-- 추후 개발 -->
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