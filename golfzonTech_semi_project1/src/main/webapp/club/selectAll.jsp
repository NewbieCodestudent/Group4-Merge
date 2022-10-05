<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GolfMate_전체모임</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/club/club_all.css">
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
	          <a href="club_selectAll.do?order=desc">모임</a>
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
    <div id="search_box">
      <div id="seachList_box">
        <p id="club_count">모 임(000)</p>
        <form action="club_searchList.do?club_id=${param.club_id}" id="club_searchList" name="club_searchList" method="get">
          <p style="font-size: 20px;">상세검색</p>
          <select name="location" id="location" >
            <option value="무관">지역</option>
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
          <select name="age" id="age">
            <option value="0">나이</option>
            <option value="10">10대</option>
            <option value="20">20대</option>
            <option value="30">30대</option>
            <option value="40">40대</option>
            <option value="50">50대</option>
            <option value="60">60대이상</option>
          </select>
          <select name="gender" id="gender">
            <option value="무관">성별</option>
            <option value="남">남자</option>
            <option value="여">여자</option>
          </select>
         
          <br>
          <select name="searchKey" id="searchKey">
            <option value="">검색영역</option>
            <option value="club_name">모임이름</option>
            <option value="club_leader">운영자</option>
          </select>
          <input type="text" name="searchWord" id="searchWord">
          <input type="reset" id="reset" value="초기화">
          <input type="submit" id="submit" value="검 색">
        </form>
      </div>
    </div>
    <!-- 정렬버튼 -->
    <div style="position: relative; font-size:15px;">
      <ul id="sort_list" style="padding-left: 20px; margin: 30px 0px 0px 0px;">
        <li>작성일기준 |</li>
        <li><a href="club_selectAll.do?order=desc">내림차순</a></li>
        <li><a href="club_selectAll.do?order=asc">오름차순</a></li>
      </ul>
      <ul style="position:absolute; bottom:-15px; right: 15px;">
        <li><a href="club_insert.do" style="font-size: 18px;">모임개설</a></li>
      </ul>
    </div>
    <!-- selectAll -->
	<div class="selectAll_container">
      <c:forEach var="vo" items="${vos}">
        <div class="selectAll_box">
          <ul>
            <a href="club_selectOne.do?club_id=${vo.club_id}&club_name=${vo.club_name}">
              <li><img class="selectAll_img" alt="${vo.club_name}" src="upload/${vo.club_img}"></li>
              <li class="selectAll_name">모임명 : ${vo.club_name}</li>
              <li class="selectAll_leader">
              	<img class="selectAll_profill" src="upload/${vo.img_name}" title="member_profill" alt="member_profill">
              	${vo.club_leader}
              </li>
              <li class="selectAll_cdate">개설일 : ${vo.cdate}</li>
              <li class="selectAll_id" style="display:none;">${vo.club_id}</li>
            </a>
          </ul>
        </div>
      </c:forEach>
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
    <script>
    	let club_count_src = document.getElementsByClassName('selectAll_id');
    	let club_count = document.getElementById('club_count');
    	club_count.innerText = "모 임(총 "+club_count_src.length+"개)"; 
    	
    </script>
	
  <!-- footer -->
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