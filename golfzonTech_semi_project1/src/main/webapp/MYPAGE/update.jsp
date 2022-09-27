<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GolfMate_개인정보수정</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/mypage_update.css">
    <script>
      function checkValue() {
        var form = document.mypage_updateOK;

        // 비밀번호와 비밀번호 확인이 동일한지 확인하는 코드
        if(form.password.value != form.passwordcheck.value) {
          alert("변경하는 비밀번호가 다릅니다.");
          return false;
        }
      }
      function doAction() {
	   	  var msg = confirm("정말 탈퇴하시겠습니까?");
	   	  if(msg==true){
	   		  location.href = "mypage_deleteOK.do?member_id=${param.member_id}";
	   	  } else {
			return false;
	   	  }
      }
      
    </script>
  </head>
  <body>
    <!-- header 영역 -->
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
    
    <!-- 개발영역 -->
    <div id="container">
        <div id="mypage_intro_background">
          <div id="my_info_box">
            <form id="form_box" action="mypage_updateOK.do" name="mypage_updateOK"  method="post"
		enctype="multipart/form-data" onsubmit="return checkValue()">
		
              <label for="submit_button" id="submit_button_design">정보 수정</label>
              <input type="submit" value="수정" id="submit_button" name="submit_button" style="display: none;">
              <ul id="my_info">
                <li>
                  <ul>
                    <li><img src="upload/${vo2.img_name}" id="profill"></li>
                    <li id="filebox">
                      <label for="upFile">프로필사진</label>
                      <input type="File" id="upFile" name="upFile" onchange="fileUpload()" value="upload/${vo2.img_name}" multiple>
                    </li>
                  </ul>
                </li>
                <li>
                  <ul style="height: 350px;">
                    <li style="font-size: 40px; padding: 30px 0px 40px 0px;">${member_id} 님의 정보 수정</li>
                    <li style="font-size: 25px; padding-bottom: 10px;">[ 나의 정보 ]</li>
                    <!-- 수정불가 정보 -->
                    <li>이름 : ${vo2.name}</li>
                    <li>생년월일(나이) : ${vo2.birthday}(만 ${vo2.m_age}세)</li>
                    <li>성별 : ${vo2.gender}</li>
                    <!-- 수정가능 정보 -->
                    <li>지역 :
                    	<select name="location" id="location">
                    		<option value="${vo2.location}" selected hidden >${vo2.location}</option>
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
		                    <option value="대전">인천</option>
		                    <option value="광주">광주</option>
		                    <option value="대구">대구</option>
		                    <option value="부신">부산</option>
              			 </select>
                    </li>
                    <li>비번 : <input type="password" name="pw" value="${vo2.pw}"></li>
                    <li>비번확인 : <input type="password" name="pwcheck" value="${vo2.pw}"></li>
                  </ul>
                </li>
              </ul>
            </form>
          </div>
        </div>
        <div class="club_content_box">
          <div>
            <input type="button" value="탈퇴" onclick="doAction()">
          </div>
        </div>
    </div>

    <!-- footer 영역 -->
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