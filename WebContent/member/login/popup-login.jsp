<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
function emailFind(){
	
	
	//새창열기
	
	window.open("./emailFindForm.me","winname","width=500,height=200")
	//location.href("/member/emailFind.jsp","winname","width=500,height=300")
}

function passwordFind(){
	
	
	//새창열기
	
	window.open("./passwordFindForm.me","winname","width=500,height=200")
	//location.href("/member/emailFind.jsp","winname","width=500,height=300")
}
	
	
</script>
<!------------------------------------------  로그인 팝업  ------------------------------------------>
     <div id="loginpop" class="modal">
  		<span onclick="document.getElementById('loginpop').style.display='none'" class="close" title="창닫기">&times;</span>
 		<form class="modal-content animate" action="loginPro.me" method="post" id="login">
    		<div class="container">
     			<h1>로그인</h1>
      			<hr>
      			
      			<input type="text" placeholder="이메일을 입력해주세요." name="email" required class="join-input">
      			<input type="password" placeholder="비밀번호를 입력해주세요." name="password" required class="join-input">

 
      			
      			

     			<div class="clearfix">
       		 		<button type="submit" class="signupbtn join-button">로그인</button>
       				<button type="button" onclick="document.getElementById('loginpop').style.display='none'" class="cancelbtn join-button">취소</button>
     		 	</div>
     		 	<div>
     		 	<a href="#" class="w3-right" style="margin-left: 10px;" onclick="passwordFind()" >비밀번호 찾기</a>
      			<a href="#" class="w3-right" onclick="emailFind()">아이디 찾기</a>
     		 	</div>
     		 	<br>
     		 	
   		 	</div>
  		</form>
	 </div>
<!------------------------------------------  로그인 팝업  ------------------------------------------>