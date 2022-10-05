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
    
  
    <h3>selectOne.jsp</h3>
	<jsp:include page="top_menu.jsp"></jsp:include>
	
	<h3>게시글 출력</h3>
	<table>
		<tbody>
			<tr>
				<td colspan="3">
				<p>${isWriter}</p>
				<!-- isWriter: true일 경우 수정, 삭제를 출력 -->
				<a href="board_update.do?club_id=${param.club_id}&&board_id=${param.board_id}">게시글 수정</a>
				<a href="board_delete.do?club_id=${param.club_id}&&board_id=${param.board_id}">게시글 삭제</a>
				</td>
			</tr>
			<tr>
				<td><label for="notice">카테고리:</label></td>
				<td colspan="2">${vo1.notice}</td>
			</tr>
			<tr>
			<tr>
				<td><label for="board_id">게시글 번호:</label></td>
				<td colspan="2">${param.board_id}</td>
			</tr>
			<tr>
				<td><label for="title">제목:</label></td>
				<td colspan="2">${vo1.title}</td>
			</tr>
			<tr>
				<td><label for="writer">작성자:</label></td>
				<td colspan="2">${vo1.writer}</td>
			</tr>
			<tr>
				<td><label for="wdate">작성일자:</label></td>
				<td colspan="2">${vo1.wdate}</td>
			</tr>
			<tr>
				<td><label for="content">내용:</label></td>
				<td colspan="2">${vo1.content}</td>
			</tr>
			<tr>
				<td><label for="upFile">파일:</label></td>
				<td colspan="2"><img width="30" alt="imgName" src="upload/board/${vo1.fname}"></td>
			</tr>
		</tbody>
	</table>
	<h3>댓글입력</h3>
	<form action="comment_insertOK.do" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td><input type="text" placeholder="comment" id="comment" name="comment" value="comment"></td>
					<td><input type="submit" value="작성글 게시"></td>
					<td></td>
				</tr>
				<tr>
					<td><input type="hidden" placeholder="club_id" id="club_id" name="club_id" value="${param.club_id}"></td>
					<td>${param.club_id}</td>
				</tr>
				<tr>
					<td><input type="hidden" placeholder="board_id" id="board_id" name="board_id" value="${param.board_id}"></td>
					<td>${param.board_id}</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	
	<h3>댓글 출력</h3>
	<table>
			<tr>
				<td colspan="3">댓글(${vos.size()})</td>
			</tr>
		<c:forEach var="vo" items="${vos}">
		   	<tr>
		           <td>${vo.commenter}(${vo.cdate})</td>
		           <td><a href="comment_deleteOK.do?club_id=${param.club_id}&&board_id=${vo.board_id}&&comment_id=${vo.comment_id}">댓글삭제</a></td>
		    </tr>
		    <tr>
		    	<td>${vo.comment}</td>
		    </tr>
	     </c:forEach>
	</table>
    
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