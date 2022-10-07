<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모임_앨범</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <link rel="stylesheet" href="CSS/album/selectAll.css">
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
    
    <!-- main -->
  <div id="container">
    <div id="club_name_box">
      <div id="club_title">
        <p style="font-size: 30px; padding-left: 30px;">
            <strong>${param.club_name}</strong>
        </p>
      </div>
    </div>
    <!-- 게시판 버튼영역 -->
    <div id="club_content">
      <ul>
        <li><a href="board_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">게시글</a></li>
        <li><a href="club_activity.do?club_id=${param.club_id}&club_name=${param.club_name}">액티비티</a></li>
        <li><a href="album_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}" style="color:blue;">앨범</a></li>
        <li><a href="vote_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}">투표</a></li>
      </ul>
    </div>

    <div id="board_content_box">
      <div id="board_sort_box">
        <ul style="padding-left: 20px;">
          <li>
            <form action="album_searchList.do?club_id=${param.club_id}" method="get">
              <select name="searchKey" id="searchKey" style="height: 30px; font-size: 15px; border-radius: 5px; border: 2px solid gray;">
                <option value="title">title</option>
                <option value= "writer">writer</option>
              </select> 
              <input type="text" name="searchWord" id="searchWord" value="title" style="width: 300px; height: 30px; font-size: 15px; border-radius: 5px; border: 2px solid gray;">
              <input type="hidden" name="club_id" id="club_id" value="${param.club_id}">
              <input type="submit" style="height: 30px; font-size: 15px; border-radius: 5px; border: 2px solid gray;">
            </form>
          </li>
        </ul>
        <ul style="position: absolute; top: 7%; left: 86%;">
          <li><a style="font-size: 18px;" onclick="action('insert')">앨범등록</a></li>
        </ul>
        <ul id="sort_list" style="padding-left: 20px; font-size:15px; margin: 30px 0px 0px 0px;">
          <li>작성일기준 |</li>
          <li><a href="album_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}&order=desc">내림차순</a></li>
          <li><a href="album_selectAll.do?club_id=${param.club_id}&club_name=${param.club_name}&order=asc">오름차순</a></li>
        </ul>
      </div>
      <div style="width: 1130px;" id="board_content_ul">
        <!-- selectAll -->
        <!-- 앨범 정렬 -->
        <div id="album_box">
          <!-- c문 출력 -->
           <c:forEach var="album" items="${album_list}">
	          <ul class="album_item">
	            <a id="${album.album_id}" onclick="selectOne(${album.album_id})">
	            <li><img src="upload/${album.fname}" alt="album_img" class="album_img" id="${album.album_id}_fname"></li>
	            <li id="${album.album_id}_text">${album.title}</li>
	            <li id="${album.album_id}_writer">${album.writer}</li>
	            <li id="${album.album_id}_date">${album.wdate}</li>
	            <li id="${album.album_id}_id" style="display:none;">${album.album_id}</li>
	            </a>
	          </ul>
          </c:forEach>
          <!-- c문 출력 여기까지-->
        </div>
      </div>
      </div>
      <!-- insert 앨범등록 버튼을 통해서 반응형으로 생성 -->
      <div id="album_insert" style="display: none;">
        <form action="album_insertOK.do?" method="post" enctype="multipart/form-data">
          <!-- 제목 -->
          <input type="text" placeholder="앨범의 제목을 작성해 주세요." id="album_title" name="album_title" maxlength="25">
          <!-- user의 id 가져오기 -->
          <input type="text" value="${member_id}" id="insert_writer" name="insert_writer">
          <br>
          <ul style="padding: 0px; margin: 0px;">
            <!-- 파일 업로드 공간 -->
            <li><img id="preview_image" src=""></li>
            <li><input type="file" id="upFile" name="upFile" multiple></li>
          </ul>
          <!-- 제출, 취소 버튼 -->
          <label class="submit" for="insert_submit" style="right:90px"><a>등 록</a></label>
          <input type="submit" id="insert_submit" hidden>
          <label class="submit" for="insert_cancle" style="right:35px" onclick="action('cancle_insert')"><a>취 소</a></label>
          <input type="button" id="insert_cancle" hidden>
          <input type="text" name="club_id" value="${param.club_id}" hidden>
          <input type="text" name="club_name" value="${param.club_name}" hidden>
        </form>
      </div>
      <!-- selectOne 앨범선택으로 자동으로 값을 가져와서 출력 -->
      <div id="selectOne" style="display: none;">
        <form action="album_updateOK.do" method="post" enctype="multipart/form-data">
          <!-- 제목 자동으로 가져온다. -->
          <input type="text" id="selectOne_title" name="selectOne_title" value="">
          <!-- user의 id 가져오기 -->
          <input type="text" id="selectOne_writer" name="selectOne_writer" value="" readonly>
          <!-- 작성일 -->
          <input type="text" value="" id="update_date" name="update_date" readonly>
          <!-- 앨범ID -->
          <input type="hidden" value="" id="update_id" name="update_id" readonly>
          <br>
          <ul style="padding: 0px; margin: 0px;">
            <!-- 수정 파일 업로드 공간 -->
            <li><img id="update_image" src=""></li>
            <li><input type="file" id="updateFile" name="updateFile" multiple></li>
          </ul>
          <!-- 수정, 삭제버튼 -->
          <label class="submit writer_power" for="update_submit" style="right: 145px;"><a>수 정</a></label>
          <input type="submit" id="update_submit" hidden>
          
          <label class="submit writer_power" for="album_delete_button" style="right: 90px;"><a id="delete_button" href="">삭 제</a></label>
          <input type="submit" id="album_delete_button" hidden>
          
          <label class="submit" for="update_cancle" style="right:35px" onclick="action('cancle_insert')"><a>닫 기</a></label>
          <input type="button" id="update_cancle" hidden>
          
          <input type="text" value="${param.club_id}" name="club_id" hidden>
          <input type="text" value="${param.club_name}" name="club_name" hidden>
        </form>
      </div>
    </div>
  </div>
  <script>
    // selectOne 자료 가져오기
    // insert 앨범 사진 미리보기 및 이름 출력
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
    const inputImage = document.getElementById("upFile")
    inputImage.addEventListener("change", e => {
        readImage(e.target)
    })
    // insert, selectOne, updateOk, delete 출력
    function action(x,y,z){
      document.getElementById('album_insert').setAttribute('style','display:none');
      document.getElementById('selectOne').setAttribute('style','display:none');
      if(x == "insert") {
        document.getElementById('album_insert').setAttribute('style','display:block');
      } else if(x == "update") {
          location.href = "album_updateOK.do";
       } else if(x == "delete") {
          var msg = confirm("정말 삭제하시겠습니까?");
          let album_id = y;
   		  let club_id = z;
          if(msg==true){
             alert("게시글을 삭제하였습니다.");
          } else {
             return false;
          }
      }
    }
    
	// session 값 가져와서 작성자와 맞는지 확인
	<%String session_id = (String)session.getAttribute("member_id");%>
	let session = "<%=session_id%>";
	console.log("session : "+session);
    
    function selectOne(x) {
    	if(x == x) {
            document.getElementById('selectOne').setAttribute('style','display:block');
            let update_id = document.getElementById(x+'_id').outerText;
            let club_id = document.getElementById('club_id').value;
            console.log("club_id : "+club_id);
            // selectOne에 선택한 객체의 값 넣기
            console.log(document.getElementById(x+'_text').outerText)
            console.log(document.getElementById(x+'_writer').outerText)
            console.log(document.getElementById(x+'_date').outerText)
            console.log(document.getElementById(x+'_id').outerText)
            console.log(document.getElementById(x+'_fname').getAttribute('src'))
            document.getElementById('selectOne_title').setAttribute('value',document.getElementById(x+'_text').outerText);
            document.getElementById('selectOne_writer').setAttribute('value',document.getElementById(x+'_writer').outerText);
            document.getElementById('update_date').setAttribute('value',document.getElementById(x+'_date').outerText);
            document.getElementById('update_id').setAttribute('value',document.getElementById(x+'_id').outerText);
            document.getElementById('delete_button').setAttribute('href',"album_deleteOK.do?album_id="+x+"&club_id=${param.club_id}&club_name=${param.club_name}");
            document.getElementById('update_image').setAttribute('src',document.getElementById(x+'_fname').getAttribute('src'));
            
            let comment_writer = document.getElementById('selectOne_writer').value;
      	  	console.log("comment_writer : "+comment_writer)
      	  	console.log(session == comment_writer)
			let writer_power = document.getElementsByClassName('writer_power');      	  	
      	  	if(session == comment_writer) {
      	  		console.log("작성자")
      		 	for (var i = 0; i < writer_power.length; i++) {
      		 		writer_power[i].addAttribute('style','display:block');
	      		}
      	  	} else {
	      	  	console.log("작성자가 아님")
	  		 	for (var i = 0; i < writer_power.length; i++) {
	  		 		writer_power[i].setAttribute('style','display:none');
	      		}
      	  	}
    	} 
    }
    
    // 작성일 출력 함수
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    wdate = year+"-"+month+"-"+day+" "+hour+":"+minute;
    console.log(wdate)
    document.getElementById("update_date").setAttribute('value',wdate);
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