<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GolfMate_모임_액티비티</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/board/boardselectAll.css">
    <style>
    /* inset 내용 */
	#activity_content_box ul {
	    padding : 0px
	}
	#activity_content_box p {
	    margin: 0px;
	}
	#activity_content_box {
	    padding: 20px;
	    border: 1px solid gray;
	    border-radius: 50px;
	    margin: 20px;
	}
	#flex {
	    display: flex;
	    justify-content: space-around;
	}
	#access_power {
	    margin-top: 10px;
	}
	#club_id {
	    margin-left: 20px;
	    font-size: 25px;
	    width: 130px;
	}
	#act_name {
	    font-size: 25px;
	    width: 500px;
	}
	/* insert_left */
	#activity_main_left {
	    padding-left: 50px;
	}
	#activity_main_left ul {
	    margin: 0px;
	}
	#activity_main_left, #activity_main_right {
	    width: 545px;
	    font-size: 18px;
	    line-height: 35px;
	}
	#preview_image {
	    padding: 10px;
	    margin: 10px 0px 0px 20px;
	    width: 330px;
	    height: 230px;
	    border: 1px solid gray;
	    border-radius: 10px;
	}
	#act_content {
	    resize: none;
	    border-radius: 5px;
	    border: 1px solid gray;
	    margin-left: 20px;
	    font-size: 15px;
	    width: 400px;
	    height: 150px;
	}
	/* insert_right */
	#activity_main_right ul {
	    margin-top: 0px;
	}
	#activity_info ul {
	    padding-left: 20px;
	}
	#activity_info ul li {
	    font-size:15px;
	}
	.right_list_bottom, .right_list_top {
	    margin-left: 20px;
	}
	.right_list_bottom select,.right_list_bottom input {
	    font-size: 15px;
	    height: 30px;
	    border: 1px solid gray;
	    border-radius: 5px;
	}
	#fname {
	    margin: 0px 0px 30px 20px;
	}
	#rdate, #adate {
		width:220px;
	    font-size: 15px;
	    border-radius: 5px;
	}
	.right_list_bottom select {
	    font-size: 15px;
	    border: 1px solid gray;
	    border-radius: 5px;
	}
	#act_insert_submit {
	    position: absolute;
	    font-size: 20px;
	    font-weight: bold;
	    right: 80px;
	    top: 270px;
	    border: 1px solid gray;
	    border-radius: 5px;
	    background-color: #7ea8d853;
	}
	/* 액티비티 selectOne 내용 */
	#activity_content_box {
	    display: flex;
	    justify-content: space-around;
	    padding: 20px;
	    border: 1px solid gray;
	    border-radius: 50px;
	    margin: 20px;
	}
	#activity_main_left, #activity_main_right {
	    width: 545px;
	    font-size: 20px;
	}
	#leader_info {
	    margin-bottom: 50px;
	}
	#leader_profill {
	    padding: 20px;
	    width: 300px;
	    border-radius: 30px;
	}
	#activity_info {
	    padding-left: 0px;
	    line-height: 30px;
	}
	#act_content {
	    padding: 10px 20px;
	    border: 2px solid rgb(47, 47, 168);
	    border-radius: 5px;
	    width: 400px;
	    height: 150px;
	    overflow: scroll;
	}
	#member_box{
		margin-left:20px;
	    padding: 10px 20px;
	    border: 2px solid rgb(47, 47, 168);
	    border-radius: 5px;
	    width: 400px;
	    height: 410px;
	    overflow: scroll;
	}
	#member_box ul li {
	    display: inline-block;
	}
	#status a {
	    position: absolute;
	    font-size: 15px;
	    right: 77px;
	    top: 443px;
	    border: 2px solid gray;
	    border-radius: 5px;
	    padding: 0px;
	}
	#status a {
	    background-color: rgb(223, 163, 163);
	}
	#status form a {
	    background-color: rgb(187, 187, 228);
	}
	.icon11, .icon10, .icon00 {
	    width: 30px;
	    height: 30px;
	}
	.leader_power_position a {
	    position: relative;
	    right: -130px;
	    border: 1px solid gray;
	    border-radius: 5px;
	    font-size: 15px;
	    color: white;
	}
	/* 제목영역 */
	#activity_name_box {
	    position: relative;
	    background-color: #7ea8d853;
	    width: 1130px;
	    height: 140px;
	}
	#insert_title {
	    position: absolute;
	    background-color: white;
	    width: 1070px;
	    height:80px;
	    margin: 30px;
	}
	#activity_hidden_button {
	    position: absolute;
	    right: 90px;
	    top: 230px;
	}
	#activity_hidden_button img {
	    width: 30px;
	    height: 30px;
	}
    </style>
	<script>
        function action(x) {
            if(x==0) {
                location.href = "club_activity_update.do?club_id=${activity_info.club_id}&act_id=${leader_info.act_id}&club_name=${param.club_name}"
            } else if(x==1) {
                var msg = confirm("액티비티를 삭제하시겠습니까?");
                if(msg==true) {
                    location.href = "club_activity_deleteOK.do?club_id=${activity_info.club_id}&&act_id=${leader_info.act_id}&club_name=${param.club_name}";
                } else {
                    return false;
                }
            }
        }
    </script>
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
  
  <!-- 메인내용 -->
  <div id="container">
  	<!-- 게시판 제목영역 -->
    <div id="club_name_box">
      <div id="club_title">
        <p style="font-size: 30px; padding-left: 30px;">
            <strong>${param.club_name}</strong>
        </p>
      </div>
    </div>

	<!-- 버튼영역 -->
    <div id="club_content">
      <ul>
        <li><a href="board_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">게시글</a></li>
        <li><a href="club_activity.do?club_id=${param.club_id}&club_name=${param.club_name}" style="color:blue;">액티비티</a></li>
        <li><a href="album_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">앨범</a></li>
        <li><a href="vote_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">투표</a></li>
      </ul>
    </div>
	
	<!-- 개발영역(selectOne) -->
        <!-- 액티비티 내용영역 -->
        <div id="activity_content_box">
	        <!-- 접속자 정보 (리더인지 아닌지 -> 수정가능 여부): ${isLeader} -->
	        <div id="activity_hidden_button">
	            <input type="hidden" id="isLeader" value="1">
	            <a class="leader_power" title="수정"  onclick="action(0)">
	                <img src="CSS/icon/setting_icon.png" alt="update" class="leader_power">
	            </a>
	            <a class="leader_power" title="삭제" onclick="action(1)">
	                <img src="CSS/icon/delete.png" alt="delete" class="leader_power">
	            </a>
	        </div>
            <div id="activity_main_left">
                <div id="leader_info">
                    <ul>
                        <li style="font-weight: bold; font-size: 25px;">[ 주최자 ]</li>
                        <!-- <li>${leader_info.fname}</li>
                        <li>${leader_info.name}(${leader_info.age} / ${leader_info.gender})</li> -->
                        <li><img src="upload/${leader_info.fname}" alt="leader_profill" id="leader_profill"></li>
                        <li style="padding-left: 20px;">${leader_info.name}(${leader_info.age} / ${leader_info.gender})</li>
                    </ul>
                </div>
                <div id="activity_info">
                    <p style="font-weight: bold; font-size: 25px;">[ 액티비티 정보 ]</p>
                    <ul>
                        <p>[ 기간 ]</p>
                        <!-- <li>등록일 : ${activity_info.rdate}</li>
                        <li>마감일 : ${activity_info.adate}</li> -->
                        <li id="rdate">라운딩일자 : ${activity_info.rdate}</li>
                        <li id="adate">신청마감일 : ${activity_info.adate}</li>
                        <p style="margin-bottom: 0px;">[ 신청조건 ]</p>
                        <ul style="padding: 0px;">
                            <li>지역 : ${activity_info.location}</li>
                            <!-- <li>${activity_info.location}</li> -->
                            <li>성별 : ${activity_info.gender}</li>
                            <!-- <li>${activity_info.gender}</li> -->
                            <li id="activity_info_age">연령대 : ${activity_info.age}대</li>
                            <!-- <li>${activity_info.age}대</li> -->
                        </ul>
                    </ul>
                </div>
            </div>
            <div id="activity_main_right">
            	<p style="font-weight: bold; font-size: 25px;">[ 소개글 ]</p>
                <input id="act_content" type="text" value="${activity_info.act_content}">
                <p style="font-weight: bold; font-size: 25px;">[ 액티비티 인원 ]</p>
                <!-- 1. 모집마감이 1이면 참여신청 버튼이 뜨면 안됨(모집:0, 마감:1) : ${activity_info.status}-->
                <!-- 2. 액티비티 참여 신청/취소(가입상태(true)/기신청여부(flase)) : ${isApplied} -->
                <div id="status">
                    <form action="club_activity_member_insertOK.do" method="post">
                        <input type="hidden" id="act_id" name="act_id" value="${leader_info.act_id}">
                        <input type="hidden" id="club_id" name="club_id" value=${param.club_id}>
                        <input type="hidden" id="club_name" name="club_name" value=${param.club_name}>
                        <a id="isApplied_true"><label for="memberInsertSubmit">참여신청</label></a>
                        <input type="submit" id="memberInsertSubmit" hidden>
                    </form>
                    <a id="isApplied_false" href="club_activity_member.deleteOK.do?act_id=${leader_info.act_id}&club_id=${param.club_id}&am_id=${applied_member.am_id}&club_name=${param.club_name}&size=${act_joined_member.size()}">참여취소</a>
                </div>
                <div id="member_box">
                    <!-- <p>[ 승인된 인원 : ${act_joined_member.size()}명 ]</p> -->
                    <p>[ 승인된 인원 : ${act_joined_member.size()}명 ]</p>
                    <ul class="leader_power_position">
                        <c:forEach var="joined_member" items="${act_joined_member}">
                        <!-- <li>${joined_member.member_id}/ ${joined_member.approved}/ ${joined_member.amtype}</li> -->
                            <c:choose>
                                <c:when test="${joined_member.amtype==1}">
                                    <li><img src="" class="icon11" alt="grade"></li>
                                    <li>${joined_member.member_id}</li>
                                    <br>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${joined_member.amtype==0}">
                                    <li><img src="" class="icon10" alt="grade"></li>
                                    <li>${joined_member.member_id}</li>
                                    <li>
                                    <a class="leader_power" style="background-color: rgb(238, 78, 78);" href="club_activity_member.deleteOK.do?act_id=${joined_member.act_id}&club_id=${param.club_id}&am_id=${joined_member.am_id}&club_name=${param.club_name}&size=${act_joined_member.size()}">강퇴</a>
                                    </li>
                                    <br>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    <p>[ 신청한 인원 : ${act_not_joined_member.size()}명]</p>
                    <ul class="leader_power_position">
                        <c:forEach var="not_joined_member" items="${act_not_joined_member}">
                        <form action="club_activity_member.updateOK.do" method="post">
                            <!-- <li>${not_joined_member.member_id}/ ${not_joined_member.approved}/ ${not_joined_member.amtype}</li> -->
                            <li><img src="CSS/icon/nomember.png" alt="grade" class="icon00"></li>
                            <li>${not_joined_member.member_id}</li>
                            <input type="hidden" name="act_id" value="${not_joined_member.act_id}">
                            <input type="hidden" name="am_id" value="${not_joined_member.am_id}">
                            <input type="hidden" name="size" value="${act_joined_member.size()}">
                            <input type="hidden" name="club_id" value="${param.club_id}">
                            <input type="hidden" name="club_name" value="${param.club_name}">
                            <input type="submit" class="leader_power" style="background-color: rgb(78, 94, 238)" value="승인">
                        </form>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>	
    </div>
    <script>
    	// 연령대 필터
    	let age = ${activity_info.age}
    	if(age == 0) {
    		document.getElementById("activity_info_age").innerText = "연령대 : 무관";
    	} else if(age == 60) {
    		document.getElementById("activity_info_age").innerText = "연령대 : 60대 이상";
    	} 
        // 시간출력시 초 삭제
        let rdate = document.getElementById('rdate');
        let adate = document.getElementById('adate');
        rdate.innerText = rdate.outerText.slice(0,-5);
        adate.innerText = adate.outerText.slice(0,-5);
        // 모집가능여부 출력변수
        let isApplied = "${isApplied}";
        // 리더 권한 출력 변수
        var isLeader = "${isLeader}";
        var isLeader_view = document.getElementsByClassName("leader_power");
        var leader_icon = document.getElementsByClassName('icon11');
        var member_icon = document.getElementsByClassName('icon10');
        console.log("status : "+status);
        console.log("isApplied : "+isApplied);
        console.log("isLeader : "+isLeader);
        /* isApplied = false;
        isLeader = 0; */
        // 모집가능여부 출력함수
        if (status == "1") {
        	console.log("모집중지")
            if(isLeader == "false") {
            	console.log("리더가 아닙니다.")
                if(isApplied == "true") {
                	console.log("액티비티 참여중인 인원입니다.")
                    document.getElementById('isApplied_true').setAttribute('style', 'display:none;');    
                } else {
                	console.log("액티비티 미참여중인 인원입니다.")
                    document.getElementById('status').setAttribute('style','display:none;');    
                }
            } else {
            	console.log("환영합니다. 리더!")
                document.getElementById('isApplied_true').setAttribute('style', 'display:none;');
                document.getElementById('isApplied_false').setAttribute('style', 'display:none;');
            }
        } else {
        	console.log("모집중")
            if (isLeader == "true") {
            	console.log("환영합니다. 리더!")
                document.getElementById("status").setAttribute("style","display:none;");
            } else {
            	console.log("리더가 아닙니다.")
                if (isApplied == "true") {
                	console.log("액티비티 참여중인 인원입니다.")
                    document.getElementById('isApplied_true').setAttribute('style', 'display:none;');
                } else {
                	console.log("액티비티 미참여중인 인원입니다.")
                    document.getElementById('isApplied_false').setAttribute('style', 'display:none;');
                }
            }
        }
        // 리더 권한 출력 함수
        if(isLeader == "false") {
            console.log("리더의 권한이 없습니다.");
            console.log(isLeader_view.length);
            for (let i = 0; i < isLeader_view.length; i++) {
                isLeader_view[i].setAttribute('style','display:none;');
            }
            
        }
        // icon 출력
        for (let i = 0; i < leader_icon.length; i++) {
            leader_icon[i].setAttribute('src','CSS/icon/leader.png');
        }
        for (let i = 0; i < member_icon.length; i++) {
            member_icon[i].setAttribute('src','CSS/icon/member.png');
        }
        // 액티비티 버튼 액션
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