<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<title>아이티윌 도서관</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<!------------------------ append css ------------------------------>

<script>
function passChangeInputDisplay() {
	$(".passChangeInput").css('display', 'block');
	$(".passChangeBtn").css('display', 'none');
}
function passChangeBtnDisplay() {
	$(".passChangeInput").css('display', 'none');
	$(".passChangeBtn").css('display', 'block');
	$("#nowPass").val("");
	$("#newPass").val("");
	$("#repeatPass").val("");
}

function passChangeSubmit() {
	var nowPass = $('#nowPass').val();
	var newPass= $('#newPass').val();
	var repeatPass = $('#repeatPass').val();
	var memberNo = ${memberBean.no};
	
	if(nowPass == "") {
		$('#nowPassText').fadeIn(200).delay(2000).fadeOut(200);
		return null;
	} 
	else if(newPass == "" || newPass.length < 6 || newPass.length > 16) {
		$('#newPassText').fadeIn(200).delay(2000).fadeOut(200);
		return null;
	}
	else if(repeatPass != newPass) {
		$('#repeatPassText').fadeIn(200).delay(2000).fadeOut(200);
		return null;
	} 
	else {
		
	$.ajax({
			type : "POST",
			url : "myInfoPassChange.my",
			data : { "nowPass" : nowPass, "newPass" : newPass, "memberNo" : memberNo },
			success : function(data) {
				if(data == 1) {
					alert("비밀번호 변경 성공! ㅇ_<");
					passChangeBtnDisplay()
				} else {
					$("#alertPassText").fadeIn(200).delay(2000).fadeOut(200);
				}
			},
			error : function() {
				alert("잘못된 접근입니다.");
			}
		});
		
	}
}

function profilChange() {
	var profilImg = $("#profilImg").attr("src");
	
	window.open("myInfoProfilForm.my?profilImg=" + profilImg, "",
	"width=460,height=500");
}
</script>
</head>
<body class="w3-light-grey">

<!----------- Sidebar/menu ------------>
<jsp:include page="../inc/sidebar.jsp" />
<!----------- Sidebar/menu ------------>

     
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main main-start">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  
  
  
  	<!------------------------------ 메인 내용 ---------------------------------->
  	<div class="w3-container">
			<h1><b>회원정보</b></h1>
	</div>
		
  	<!-- The Grid -->
  	<div class="w3-row-padding">
  
		<div class="w3-col m3">
      		<div class="w3-card w3-round w3-white">
        		<div class="w3-container">
         			<h4 class="w3-center">프로필 사진</h4>
         			<c:set var ="imageName" value = "${memberBean.image }"/>
         			<c:choose>
         				<c:when test="${fn:contains(imageName, 'http')}">
         					<p class="w3-center"><img src="${memberBean.image}" class="w3-circle" style="width:150px;height:150px;" alt="Avatar" id="profilImg"></p>
         				</c:when>
         				<c:otherwise>
         					<p class="w3-center"><img src="${pageContext.request.contextPath}/images/member/${memberBean.image}" class="w3-circle" style="width:150px;height:150px;" alt="Avatar" id="profilImg"></p>
         				</c:otherwise>
         			</c:choose>
         			<hr>
         			<p class="w3-center"><button class="changeBtn" onclick="profilChange()">프로필 사진 변경하기</button></p>
        		</div>
      		</div>
      		<br>
      	</div>
    
    <!-- Right Column -->
    <div class="w3-twothird">
      <div class="w3-container w3-card w3-white w3-margin-bottom">
      	<h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>회원정보설정</h2>
        <div class="w3-container">
          <table class="w3-table w3-striped w3-bordered w3-border">
          	<tr>
          		<td class="info-td-left">이메일</td><td>${memberBean.email }</td>
          	</tr>
          	<tr>
          		<td class="info-td-left">비밀번호</td>
          		<td>
          			<div class="passChangeInput">
          				<div class="tooltip">
          					<input type="password" name="nowPass" id="nowPass" placeholder="현재 비밀번호" class="info-input"><br>
          					<span class="tooltiptext" id="nowPassText">현재 비밀번호를 입력해주세요.</span>
          					<span class="tooltiptext" id="alertPassText">비밀번호가 다릅니다. 다시 입력해주세요.</span>
          				</div>
          				<div  class="tooltip">
          					<input type="password" name="newPass" id="newPass" placeholder="신규 비밀번호" class="info-input"><br>
          					<span class="tooltiptext" id="newPassText">유효한 새로운 비밀번호를 입력해주세요</span>
          				</div>
          				<span style="opacity: 0.6">비밀번호를 형식에 맞게 6자이상 16자 이하로 적어주세요.</span><br>
          				<div  class="tooltip">
          					<input type="password" name="repeatPass" id="repeatPass" placeholder="비밀번호 확인" class="info-input"><br>
          					<span class="tooltiptext" id="repeatPassText">비밀번호가 일치하지 않습니다. 다시 입력해주세요.</span>
          				</div>
          				<span style="opacity: 0.6">비밀번호를 다시 한번 입력해주세요.</span><br><br>
          				<input type="button" value="변경" class="w3-button w3-dark-grey w3-round-medium" onclick="passChangeSubmit()">
          				<input type="button" value="취소" class="w3-button w3-dark-grey w3-round-medium" onclick="passChangeBtnDisplay()"><br>
          			</div>
          			<div class="passChangeBtn">
          				●●●●●●●● &nbsp; 
          				<input type="button" class="w3-button w3-dark-grey w3-round-medium" value="변경" onclick="passChangeInputDisplay()">
          			</div>
          		</td>
          	</tr>
          	<tr>
          		<td class="info-td-left">이름</td><td>${memberBean.name }</td>
          	</tr>
          	<tr>
          		<td class="info-td-left">성별</td><td>${memberBean.gender }</td>
          	</tr>
          	<tr>
          		<td class="info-td-left">생년월일</td><td>${memberBean.birth }</td>
          	</tr>
          	<tr>
          		<td class="info-td-left">가입일자</td><td>${memberBean.reg_date }</td>
          	</tr>
          </table>
          <br><br>
        </div>
      </div>


    <!-- End Right Column -->
    </div>
</div>
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
  
	
</body>
</html>
