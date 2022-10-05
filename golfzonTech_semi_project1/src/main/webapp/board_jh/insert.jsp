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
  
    <h3>insert.jsp</h3>
	<jsp:include page="top_menu.jsp"></jsp:include>
	
	<form action="board_insertOK.do?club_id=20" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<p>${isLeader}</p>
					<select name="notice">
					    <option value="0">게시글</option>
					    <option value="1">공지</option>
					</select>
				</tr>
				<tr>
					<td><label for="title">title:</label>
					<td><input type="text" placeholder="title" id="title" name="title" value="title1"></td>
					<td></td>
				</tr>
				<tr>
					<td><label for="content">content:</label></td>
					<td><input type="text" placeholder="content" id="content" name="content" value="content"></td>
					<td></td>
				</tr>
				<tr>
					<td><label for="upFile">upFile:</label></td>
					<td><input type="file" name="upFile" id="upFile" name="upFile"></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="작성글 게시"></td>
				</tr>
			</tbody>
		</table>
	</form>
    
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