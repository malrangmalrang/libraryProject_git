<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinForm</title>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/scripts/jquery.min.js"></script>
<script type="text/javascript">
	function popupClose(){
		var f = document.getElementById('join');
		f.action = "${pageContext.request.contextPath}/joinPro.me";
		f.submit();
		window.open("about:blank","_self").close();
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
	
	function isSame(){
		var pw=document.fr.password.value;
		var confirmPW =document.fr.password2.value;
		if(pw.length < 6 || pw.length > 16){
			window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용가능합니다');
			document.getElmentById('pw').value=document.getElementById('pwCheck').value='';
			document.getElementById('same').innerHTML='';
		}
		if(document.getElementById('pw').value!='' && document.getElementById('pwCheck').value!=''){
			if(document.getElementById('pw').value==document.getElementById('pwCheck').value){
				document.getElementById('same').innerHTML='비밀번호가 일치합니다';
				document.getElementById('same').style.color='blue';
			}else{
				document.getElementById('same').innerHTML='비밀번호가 일치하지 않습니다';
				document.getElementById('same').style.color='red';
			}
		}
		
		
		
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

				document.fr.address.value = addr;
				document.fr.address1.value = extraAddr;
				document.fr.postcode.value = data.zonecode;
				// 커서를 상세주소 필드로 이동한다.

			}
		}).open();
	}
</script>
<link href="${pageContext.request.contextPath}/css/layout.css"
	rel="stylesheet" type="text/css" media="all">
</head>
<body>
	<form action="joinPro.me" id="join" method="post" name="fr" enctype="multipart/form-data" onsubmit="popupClose()">
		<div id="joincss">
		<h2>회원가입</h2>
			<table>
				<tr>
					<th><label>E-mail</label></th>
					<td><input type="email" name="email" class="input_box">
						<input type="button" value="CHECK" class="btn"
						onclick="emailCheck()"></td>
				</tr>
				<tr>
					<th><label>Password</label></th>
					<td><input type="password" name="password" class="input_box" id="pw"></td>
				</tr>
				<tr>
					<th><label>Password Check</label></th>
					<td><input type="password" name="password2" class="input_box" id="pwCheck"></td>
				</tr>
				<tr>
					<th><label>name</label></th>
					<td><input type="text" name="name" class="input_box"></td>
				</tr>
				<tr>
					<th><label>Gender</label></th>
					<td><label>Male</label>&nbsp;<input type="radio" name="Gender" value="남"
						checked="checked">&nbsp;<label>Female</label>&nbsp;<input type="radio" name="Gender"
						value="여"></td>
				</tr>
				<tr>
					<th><label>Birth</label></th>
					<td><input type="text" name="birth" class="input_box"></td>
				</tr>
				<tr>
					<th><label>Phone</label></th>
					<td><input type="text" name="phone" class="input_box"></td>
				</tr>
				<tr>
					<th><label id="postcode">Post-code</label></th>
					<td><input type="text" name="postcode" class="input_box">
						<input type="button" value="주소검색" class="btn"
						onclick="searchJuso()"></td>
				</tr>
				<tr>
					<th><label>Address</label></th>
					<td><input type="text" name="address1" class="input_box"></td>
				</tr>
				<tr>
					<th><label>Address-detail</label></th>
					<td><input type="text" name="address2" class="input_box"></td>
				</tr>
				<tr>
					<th><label>Picture</label></th>
					<td><input type="file" name="photo" id="photo"></td>
				</tr>
			</table>
			<div id="joinBtn">
				<input type="submit" value="회원가입" class="btn"><input
					type="button" value="취소" class="btn">
			</div>
		</div>
	</form>


</body>
</html>