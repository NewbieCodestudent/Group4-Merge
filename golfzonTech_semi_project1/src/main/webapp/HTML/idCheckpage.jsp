<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 중복 체크</title>
    <style type="text/css">
    #wrap {
      width: 490px;
      text-align :center;
      margin: 0 auto 0 auto;
      }
      #chk{
        text-align:center;
      }
      #cancelBtn{
        visibility:visible;
      }
      #useBtn{
        visibility:hidden;
      }
    </style>
    <script>
	  	// 회원가입창의 아이디 입력란의 값을 가져온다.
	    function pValue(){
	      document.getElementById("member_id").value = opener.document.userInfo.member_id.value;
	    }
	  	
	  	window.onload = funciont() {
		  	// 중복체크 문구
			let btn_idCheck = document.querySelector("#btn_idCheck");
			let result = document.querySelector("#result");
			//3
			btn_idCheck.onclick = function(event) {
				console.log("onclick...");
				let member_id = document.querySelector("#member_id");
				// console.log(id);
				console.log(member_id.value);
	
				let req = new XMLHttpRequest();
	
				req.addEventListener("load", function() {
					console.log(this.status);
					console.log(this.responseText);
					// {"result":"Not OK"}
					if (this.status == 200) {
						try {
							let txt_json = this.responseText;
							let obj_json = JSON.parse(txt_json);
							console.log(obj_json);
							console.log(obj_json.result);
	
							let txt = "";
	
							if (obj_json.result == 'Not OK') {
								txt = "사용중인 아이디 입니다.";
							} else {
								txt = "사용가능한 아이디 입니다.";
							}
							result.innerHTML = txt;
						} catch (e) {
							console.log("json 형식이 아님.");
						}
	
					}//end if
	
				});
	
				req.open("GET",
						"http://localhost:8090/golfzonTech_semi_project1/idCheckpage.do?member_id="
								+ member_id.value);
	
				req.send();
	
				event.preventDefault();
				event.stopPropagation();
	
			};
	  	};
	    
	  	// 사용하기 클릭 시 부모창으로 값 전달
	    function sendCheckValue(){
	      // 중복체크 결과인 idCheck 값을 전달한다.
	      opener.document.userInfo.idDuplication.value ="idCheck";
	      // 회원가입 화면의 ID입력란에 값을 전달
	      opener.document.userInfo.id.value = document.getElementById("userId").value;
	      if (opener != null) {
	        opener.chkForm = null;
	        self.close();
	      }
	    }
    </script>
  </head>
  <body onload="pValue()">
    <div id="wrap"><br>
      <b style="font-size:20px; color:gray;">아이디 중복체크</b>
      <hr size="1" width="460">
      <br>
      <div id="chk">
        <form id="checkForm">
          <input type="text" id="member_id" name="member_id">
          <input type="button" id="btn_idCheck" name="btn_idCheck" value="중복확인"><span id="result"></span>
        </form>
        <div id="msg"></div>
        <br>
        <input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
        <input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">
      </div>
    </div>
  </body>
</html>