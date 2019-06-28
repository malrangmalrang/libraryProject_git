<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">

	function popupClose(){
		var f = document.getElementById('join');
		f.action = "${pageContext.request.contextPath}/joinPro.me";
		f.submit();
		window.open("about:blank","_self").close();
	}
	function isSame(){
		//alert('테스트');
		var pw=document.fr.password.value;
		var confirmPW = document.fr.password2.value;
		if( pw.length < 6 || pw.length > 16){
			window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용가능합니다');
			document.getElementById('pw').value=document.getElementById('pwCheck').value='';
			document.getElementById('same').innerHTML='';
		}
		if(document.getElementById('pw').value!='' && document.getElementById('pwCheck').value!=''){
			//alert('test2');
			if(document.getElementById('pw').value==document.getElementById('pwCheck').value){
				document.getElementById('same').innerHTML='비밀번호가 일치합니다';
				document.getElementById('same').style.color='blue';
			}else{
				document.getElementById('same').innerHTML='비밀번호가 일치하지 않습니다';
				document.getElementById('same').style.color='red';
			}
		}
		
		
		
	}
	
	
	function emailCheck() {
		//id상자 비어있으면 "입력하세요"포커스
		//새창 열기
		if (document.fr.email.value == "") {
			alert("이메일을 입력하세요");
			document.fr.email.focus();
			return;
		}
		//새창열기
		var uemail = document.fr.email.value;
		window.open("emailCheck.me?userEmail=" + uemail, "",
				"width=460,height=140");

	}

	//적용예 (api 호출 전에 검색어 체크) 
	function searchJuso() {

		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.

				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.

				document.fr.address1.value = addr;
				document.fr.address2.value = extraAddr;
				document.fr.postcode.value = data.zonecode;
				// 커서를 상세주소 필드로 이동한다.

			}
		}).open();
	}
	
	function imageURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function(e) {
	            $('#replaceMe').attr('src', e.target.result)
	             .width(100)
	             .height(100);
	        }

	        reader.readAsDataURL(input.files[0]);
	    }
	}
	

	
</script>
     <!------------------------------------------ 회원가입 팝업 ------------------------------------------>
	 <div id="joinpop" class="modal">
  		<span onclick="document.getElementById('joinpop').style.display='none'" class="close" title="창닫기">&times;</span>
 		<form class="modal-content animate" action="joinPro.me" id="join" method="post" name="fr" enctype="multipart/form-data" >
    		<div class="container">
     			<h1>회원가입</h1>
      			<hr>
      			<span>프로필</span><br>
      			<img id="imgadsrc" style="width:60px; height: 60px; margin-top: 8px;">
      			<input type="hidden" id="imgad" name="imgad" >
      			
      			<input type="email" placeholder="이메일" name="email" id="email" required class="join-input"  style="width:70%">
      			<input type="button" value="중복검사" onclick="emailCheck()" class="join-button" style="width:87px; height: 48px;"><br>
      			<span>이메일 형식으로 입력해주세요.</span>

      			<input type="password" placeholder="비밀번호를 입력해주세요." name="password"  id="pw" required class="join-input"  >
				<span>비밀번호를 형식에 맞게 입력해주세요.</span>

     			<input type="password" placeholder="비밀번호 확인" name="password2" id="pwCheck" required class="join-input" onchange="isSame()" >
      			<span>비밀번호를 다시 한번 입력해주세요.</span><br>
      			<span id="same"></span>
      			
      			<input type="text" placeholder="이름" name="name" id="name" required class="join-input">
      			<span>이름을 입력해 주세요</span><br>
      			<span>카카오 연동시 닉네임이 이름이 아닌경우 실명으로 재입력해주세요</span><br>
      			
      			
      			<input type="number" placeholder="주민번호 앞자리 " name="birth"  required class="join-input" style="width: 40%" size="6" maxlength="6">
				<span class="w3-xlarge"><b>-</b></span>
     			<input type="text" name="Gender" required id="gender" class="join-input" maxlength="1" style="width: 10%; text-align: center;" >
				<span class="w3-xlarge"><b>●●●●●●</b></span>
     			<br>
				<span>주민번호 앞자리 6자리와 뒷자리 맨앞의 한자리를 입력해주세요.</span>     	
						
      			<input type="number" placeholder="전화번호" name="phone" required class="join-input" size="10" maxlength="12">
      			<span>전화번호를 '-'없이 입력해주세요.</span><br>
      			
      			<input type="number" name="postcode" placeholder="우편번호" required class="join-input" style="width: 50%" readonly="readonly">
      			<button class="join-button" style="width:65px; height: 48px;" onclick="searchJuso()">검색</button><br>
      			<span>우편번호를 검색해주세요.</span>
      			
      			<input type="text" placeholder="주소" name="address1" required class="join-input">
      			<span>주소를 검색해주세요.</span>
      			
      			<input type="text" placeholder="상세주소" name="address2" class="join-input">
      			<span>상세주소를 입력해주세요.</span><br>
      			<br>
      			
      			
      			
      			
      			
      			<section>
		<a id="kakao-login-btn"></a>
		<script type='text/javascript'>
			//<![CDATA[
			// 사용할 앱의 JavaScript 키를 설정해 주세요.
			Kakao.init('보안을 위해 비공개');
			// 카카오 로그인 버튼을 생성합니다.
			Kakao.Auth.createLoginButton({
				container : '#kakao-login-btn',
				success : function(authObj) {
					// 로그인 성공시, API를 호출합니다.
					Kakao.API.request({
						url : '/v2/user/me',
						success : function(res) {
							//alert(JSON.stringify(res));
							console.log(JSON.stringify(res));
							console.log(JSON.stringify(res.properties.nickname));
							console.log(JSON.stringify(res.properties.profile_image));
							console.log(JSON.stringify(res.properties.thumbnail_image));
							console.log(JSON.stringify(res.kakao_account.email));
							console.log(JSON.stringify(res.kakao_account.age_range));
							console.log(JSON.stringify(res.kakao_account.birthday));
							console.log(JSON.stringify(res.kakao_account.gender));
							
							
							/* alert(res.properties.thumbnail_image); */
							document.getElementById('email').value = res.kakao_account.email;
							document.getElementById('name').value= res.properties.nickname;
							document.getElementById('imgad').value= res.properties.profile_image;
							document.getElementById('imgadsrc').src=res.properties.profile_image;
							
							//document.getElementById('image').value = res.properties.thumbnail_image;
							if (res.kakao_account.gender=='male') {
								document.getElementById('gender').value ="1";
						        player[i].checked = false; 
							} else {
								document.getElementById('gender').value ="2";
							}
							
						},
						fail : function(error) {
							alert(JSON.stringify(error));
						}
					});
				},
				fail : function(err) {
					alert(JSON.stringify(err));
				}
			});
			//]]>
		</script>
	</section>
	
     			<div class="clearfix">
       		 		<button type="submit" class="signupbtn join-button">가입하기</button>
       				<button type="button" onclick="document.getElementById('joinpop').style.display='none'" class="cancelbtn join-button">취소</button>
     		 	</div>
   		 	</div>
  		</form>
	 </div>
    <!------------------------------------------ 회원가입 팝업 ------------------------------------------>
