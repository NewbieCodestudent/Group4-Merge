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
    
	<div id="container">
      <div id="search_club">
        searchList
        <form action="club_searchList.do">
          <select name="searchKey" id="searchKey" >
            <option value="location">지역</option>
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
          <select name="member_count" id="member_count">
            <option value="">가입자수</option>
            <option value="m_c_10">10명 이상</option>
            <option value="m_c_20">20명 이상</option>
            <option value="m_c_30">30명 이상</option>
            <option value="m_c_40">40명 이상</option>
            <option value="m_c_50">50명 이상</option>
          </select>
          <select name="search_select" id="search_select">
            <option value="">검색영역</option>
            <option value="m_c_10">모임이름</option>
            <option value="m_c_20">운영자</option>
          </select>
          <input type="text" name="searchWord" id="searchWord">
          <input type="submit">
        </form>
      </div>
      <a href="club_insert.do">모임등록</a>
      <table border="1">
		<tr>
			<th>club_id</th>
			<th>club_name</th>
			<th>club_leader</th>
			<th>개설일</th>
			<th>모임이미지</th>
		</tr>
		<c:forEach var="vo" items="${vos}">
			<tr>
				<td><a href="club_selectOne.do?club_id=${vo.club_id}">${vo.club_id}</a></td>
				<td>${vo.club_name}</td>
				<td>${vo.club_leader}</td>
				<td>${vo.cdate}</td>
				<td><img width="50px" alt="${vo.club_name}" src="../upload/club/${vo.club_img}"></td>
			</tr>
		</c:forEach>
	</table>
      
<!--       정렬 버튼 -->
<!--       <div id="clubSort"> -->
<!--         <ul id="clubSort_left"> -->
<!--           <li>최신순</li> -->
<!--           <li>제목순</li> -->
<!--           <li>가입자수</li> -->
<!--         </ul> -->
<!--         <ul id="clubSort_right"> -->
<!--           <li><a>목록</a></li> -->
<!--           <li><a>모임개설</a></li> -->
<!--         </ul> -->
<!--       </div> -->
      
      <!-- selectAll -->
	
	
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