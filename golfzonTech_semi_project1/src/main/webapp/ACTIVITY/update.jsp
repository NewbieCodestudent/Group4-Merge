<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>액티비티 수정</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/activity/update.css">
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
        <div id="activity_name_box">
            <div id="update_title">
                <p style="font-size: 30px; padding-left: 30px;">
                    <strong style="color: rgb(24, 64, 173);">${activity_info.act_name}</strong>
                </p>
            </div>
        </div>
        <!-- 액티비티 버튼영역 -->
        <div id="activity_content">
            <ul>
                <li><a href="activity_selectAll.do">목록</a></li>
            </ul>
        </div>
        </ul>
        <!-- update 영역 -->
        <form action="activity_updateOK.do?club_id=${activity_info.club_name}" method="post" enctype="multipart/form-data" name="submit_info" onsubmit="return checkValue()">
            <input hidden name="club_name" value="${param.club_name}">
            <div id="activity_content_box">
                <div id="access_power">
                    <ul>
                        <li>
                            <select name="club_id" id="club_id" name="club_id">
                                <option value="${param.club_id}" selected>전체공개</option>
                                <c:forEach var="entry" items="${club}">
                                    <option value="${entry.key}">${entry.value}</option>
                                </c:forEach>
                            </select>
                            <input type="text" id="act_name" name="act_name" value="${activity_info.act_name}" maxlength="25">
                        </li>
                        <input type="submit" id="act_update_submit" value="수 정">
                        <input type="button" id="act_delete_submit" value="삭 제" onclick="delete_action()">
                    </ul>
                </div>
                <div id="flex">
                    <div id="activity_main_left">
                        <ul>
                            <li style="font-weight: bold; font-size: 20px;">[ 액티비티 대표사진 ]</li>
                            <li><img src="${activity_info.act_fname}" alt="" id="preview_image"></li>
                            <li>
                                <input type="file" id="fname" name="fname" value="${activity_info.act_fname}">
                            </li>
                            <li style="font-weight: bold; font-size: 20px;">[ 액티비티 소개 ]</li>
                            <li><textarea id="act_content" name="act_content" maxlength="500">${activity_info.act_content}</textarea></li>
                        </ul>
                    </div>
                    <div id="activity_main_right">
                        <div id="activity_info">
                            <p style="font-weight: bold; font-size: 20px; margin-bottom: 20px;">[ 액티비티 정보 ]</p>
                            <ul>
                                <li>[ 기간 ]</li>
                                <li class="right_list_top">
                                    라운딩날짜 : 
                                    <input type="datetime-local" id="rdate" name="rdate" value="${activity_info.rdate}">
                                </li>
                                <li class="right_list_top" style="margin-bottom: 50px;">
                                    모집마감일 : 
                                    <input type="datetime-local" id="adate" name="adate" value="${activity_info.adate}">
                                </li>
                                <li>
                                    [ 조건 ]
                                </li>
                                <li class="right_list_bottom">
                                    지 역 : 
                                    <select name="location" id="location" name="location" style="width: 80px;" onchange="location_cc(this)">
                                        <option value="${activity_info.location}">${activity_info.location}</option>
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
                                </li>
                                <li class="right_list_bottom">
                                    CC명 :
                                    <select name="cc_id" id="cc_id" name="cc_id" style="width: 200px;">
                                        <option value="${activity_info.cc_id}" hidden>${activity_info.cc_name}</option>
                                        <c:forEach var="entry" items="${cc}">
                                            <option value="${entry.key}">${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </li>
                                <li class="right_list_bottom">
                                    성 별 :
                                    <select name="gender" id="gender" name="gender" style="width: 80px;">
                                        <option value="${activity_info.gender}" hidden>${activity_info.gender}</option>
                                        <option value="무관">무관</option>
                                        <option value="남">남</option>
                                        <option value="여">여</option>
                                    </select>
                                </li>
                                <li class="right_list_bottom">
                                    연령대 :
                                    <select name="age" id="age" name="age", value=0 style="width: 100px;">
                                        <option value="${activity_info.age}" id="activity_info_age" hidden>${activity_info.age}</option>
                                        <option value=0>무관</option>
                                        <option value="10">10대</option>
                                        <option value="20">20대</option>
                                        <option value="30">30대</option>
                                        <option value="40">40대</option>
                                        <option value="50">50대</option>
                                        <option value="60">60대 이상</option>
                                    </select>
                                </li>
                                <li class="right_list_bottom">
                                    비용(만원) :
                                    <input type="number" id="cost" name="cost" value="${activity_info.cost}" style="width: 80px;">
                                </li>
                            </ul>
                        </div>
                        <input type="hidden" id="act_id" name="act_id" value = "${param.act_id}"><br>
                        <input type="hidden" id="club_id" name="club_id" value = "${param.club_id}"><br>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <script>
		// 연령대 필터
		let age = ${activity_info.age}
		if(age == 0) {
			document.getElementById("activity_info_age").innerText = "무관";
		} else if(age == 60) {
			document.getElementById("activity_info_age").innerText = "60대 이상";
		} 

        // 개설 전 조건 확인
        function checkValue() {
            var form = document.submit_info;

            if(!form.act_name.value) {
                alert("액티비티 제목를 작성해주세요.");
                return false;
            }
            if(!form.act_content.value) {
                alert("액티비티 소개를 작성해주세요.");
                return false;
            }
            if(form.rdate.value <= form.adate.value) {
                alert("모집마감일을 라운딩날짜보다 이전으로 설정해주세요.");
                return false;
            }
        }

        // 삭제 alert
        function delete_action() {
            var msg = confirm("정말 삭제하시겠습니까?");
            if(msg == true) {
                location.href = "activity_deleteOK.do?act_id=${param.act_id}&club_name=${param.club_name}&club_id=${param.club_id}";
            } else {
                return false;
            }
        }
        // reviewImg
        function readImage(input) {
            if(input.files && input.files[0]) {
                const reader = new FileReader()
                reader.onload = e => {
                    const previewImage = document.getElementById("preview_image")
                    previewImage.src = e.target.result
                }
                reader.readAsDataURL(input.files[0])
            }
        }
        const inputImage = document.getElementById("fname")
        inputImage.addEventListener("change", e => {
            readImage(e.target)
        })
        // ${activity_info.location} 지역이름 1,2,3,4
        // ${activity_info.cc_name} cc이름 1,2,3,4
        let location_select = document.getElementById('location').options;
        let cc_select = document.getElementById('cc_id').options;
        for (let i = 0; i < location_select.length; i++) {
            if (location_select[i].value == ${activity_info.location}) {
                location_select[i].selected = true;
            }
        }
        for (let i = 0; i < cc_select.length; i++) {
            if (cc_select[i].value == ${activity_info.cc_id}) {
                cc_select[i].selected = true;
            }
        }
    </script>

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