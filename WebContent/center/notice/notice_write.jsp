<%@page import="vo.BoardBean"%>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>아이티윌 도서관</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="./css/write.css">
<!------------------------ append css ------------------------------>
<script type="text/javascript">
function enterToBr(){
	var str = document.getElementById("textarea").value;
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br>');
	document.getElementById("result").value = str;
// 	str = str.replace('\r\n', '<br>');
}
</script>
</head>
<body class="w3-light-grey">

<%
 MemberBean memberBean = (MemberBean)session.getAttribute("memberBean");
// BoardBean article = (BoardBean)request.getAttribute("article");
%>

<!----------- Sidebar/menu ------------>
<jsp:include page="../../inc/sidebar.jsp" />
<!----------- Sidebar/menu ------------>

     
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main main-start">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  
  
  	<div class="w3-clear"></div>
  	
  <!------------------------------ 메인 내용 ---------------------------------->	
  	
	<!-- 공지사항 등록 -->
	<section id="writeForm">
		<h2>공지사항 글 등록</h2>
		<form action="noticeWritePro.bo" method="post" enctype="multipart/form-data" name="noticeForm" class="review-write-animate">
		<div class="review-write-container" style="margin-top: 40px">
 		
			<table>
				<tr>
					<!-- <td class="td_left"><label for="board_name">글쓴이</label></td> -->
					<input type="text" name="member_no" class="review-write-text" value="<%=memberBean.getNo() %>" readonly="readonly" hidden="hidden"/>
					<td class="td_right"><input type="text" class="review-write-text" value="관리자" readonly="readonly" /></td>
				</tr>
				<tr>
					<!-- <td class="td_left"><label for="board_subject">제목</label></td>  -->
					<td class="td_right"><input type="text" name="title" class="review-write-text" cols="150" rows="15" placeholder="제목을 입력하세요" required="required""/></td>
				</tr>
				<tr>
				<!-- 	<td class="td_left"><label for="board_content">내용</label></td>  -->
					<td class="td_right"><textarea name="content" id = "textarea" class="review-write-text" cols="150" rows="15" placeholder="내용을 입력하세요" required="required""></textarea><textarea id="result" name="content" hidden="hidden"></textarea>
					</td>
				</tr>
				<tr>
				
				
					<!-- <td class="td_left"><label for="board_file">파일 첨부</label></td> -->
					<td class="td_right"><input type="file" name="file" class="review-write-text"/></td>
				</tr>
			</table>
			<div style="text-align: center">
    			<input type="submit" value="글쓰기" class="review-write-btn" onclick="enterToBr()">
    			<input type="reset" value="취소" class="review-write-btn" >
			</div>
			<div class="clearfix"></div>
			
			</div>
			</form>
	</section>
	
<script>

var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("review-write-mySlides");
  var dots = document.getElementsByClassName("review-write-dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace("review-write-active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " review-write-active";
}

</script>
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>