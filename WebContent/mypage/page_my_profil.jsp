<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>프로필 사진 변경</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<!------------------------ append css ------------------------------>

<script type="text/javascript">

var sel_file;

$(document).ready(function(){
	$("#input_img").on("change", handleImgFileSelect);	
});

function handleImgFileSelect(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f) {
		if(!f.type.match("image.*")) {
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}
		
		sel_file = f;
		
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#profilImg").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});
} 

function imageCheck() {
	var file = document.imageForm.image.value;
	
	if(file == "") {
		alert("이미지 파일을 선택해주세요");
		return;
	}
	
	else if(file != "") {
		var fileExt = file.substring(file.lastIndexOf(".") + 1);
		var reg = /gif|jpg|jpeg|png/i;
		
		if(reg.test(fileExt) == false) {
			alert("첨부파일은 gif, jpg, jpeg, png로 된 이미지만 가능합니다.");
			return;
		} else if(document.imageForm.image.files[0].size > 5 * 1024 * 1024) {
			alert("파일 크기는 5MB 이하로 업로드 가능합니다");
			return;
		} else {
			document.imageForm.submit();			
		}
	}
	
}

function closeWindow() {
	self.close();
}

</script>
</head>
<body style="background-color: #f1f1f1;"> 
	<div class="w3-container w3-center" style="margin-top: 40px">
      	<div class="w3-card w3-round w3-white">
        	<div class="w3-container">
         		<h4 class="w3-center">프로필 사진</h4>
         		<p class="w3-center"><img src="${param.profilImg }" class="w3-circle" style="width:150px;height:150px" alt="Avatar" id="profilImg"></p>
         		<hr>
         		<form action="myInfoProfilPro.my" method="POST" enctype="multipart/form-data" name="imageForm">
         			<p class="w3-center"><input type="file" name="image" id="input_img" class="input-file"/></p>
         			<p class="w3-center" style="color: red">5M이하의 이미지 파일만 업로드 해주세용~</p>
         			<input type="button" class="changeBtn chabgeBtn-profil" onclick="imageCheck()" value="확인"/>
         			<input type="button" class="changeBtn chabgeBtn-profil" onclick="closeWindow()" value="취소"/>
         		</form>
        	</div>
      	</div>
      	<br>
	</div>
</body>
</html>