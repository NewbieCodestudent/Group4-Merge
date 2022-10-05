<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>모임_게시판</title>
  <link rel="stylesheet" href="/CSS/header.css">
  <link rel="stylesheet" href="/CSS/footer.css">
  <style>
    #container {
      height: 1000px;
    }
    #club_name_box {
      background-color: rgba(164, 219, 201, 0.307);
      height: 150px;
    }


  </style>
</head>
<body>
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
          <a href="액티비티메인페이지">액티비티</a>
        </li>
        <li class="nav_item">
          <a href="이벤트메인페이지">이벤트</a>
        </li>
      </ul>
    </div>
  </div>
  <div id="container">
    <div>
      <div id="club_name_box">
        <p>club_name</p>
      </div>
      
    </div>
    <div>
      게시글 액티비티 앨범 투표
    </div>
    
    <jsp:include page="top_menu.jsp"></jsp:include>
    
    <form action="board_searchListOK.do?club_id=20" method="get">
      <table>
        <tr>
          <td><select name="searchKey" id="searchKey">
              <option value="title">title</option>
              <option value="content">content</option>
              <option value= "writer">writer</option></td>
          <td></select> 
          <input type="text" name="searchWord" id="searchWord" value="title"></td>
          <input type="hidden" name="club_id" id="club_id" value="20"></td>
          <td><input type="submit"></td>
        </tr>
      </table>
    </form>
    
    <a href="board_selectAll.do?club_id=20&&order=desc">작성일 내림차순</a>
    <a href="board_selectAll.do?club_id=20&&order=asc"">작성일 오름</a>
    <h1>selectAll_notice</h1>
    <c:forEach var="notice" items="${notices}">
      <a href="board_selectOne.do?club_id=${param.club_id}&&board_id=${notice.board_id}">${notice.board_id}</a>
      ${notice}
      <br>
    </c:forEach>
    
    <h1>selectAll_common</h1>
    <c:forEach var="common" items="${commons}">
      <a href="board_selectOne.do?club_id=${param.club_id}&&board_id=${common.board_id}">${common.board_id}</a>
      ${common}
      <br>
    </c:forEach>
    
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