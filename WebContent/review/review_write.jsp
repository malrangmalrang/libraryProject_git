<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/write.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/check.css">
<!------------------------ append css ------------------------------>

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
  
  
  	<div class="w3-clear"></div>
  	<!------------------------------ 메인 내용 ---------------------------------->
  	<div class="review-write-container" style="margin-top: 40px">
 		<form class="review-write-animate" action="reviewWritePro.rv" method="post" id="reviewSubmit">
     		<div class="review-write-slideshow-container">

     			<%
     				ArrayList<HashMap<String, String>> bookList = (ArrayList<HashMap<String, String>>)request.getAttribute("bookList");
     				HashMap<String, String> book = null;
     			%>
     				<div class="review-write-mySlides review-write-fade">
     					<% 
     						for(int i=0; i<bookList.size(); i++) {
     							book = bookList.get(i);
     							if(i%3 != 0 || i==0) {
     							%>
     								<img src="<%=book.get("image") %>" width="170px" height="240px" class="review-write-img-shake" 
     									onclick='rentalNoInput(<%=book.get("rental_no") %>)'>
     							<%} else {%>
     							</div>
     							<div class="review-write-mySlides review-write-fade">
     								<img src="<%=book.get("image") %>" width="170px" height="240px" class="review-write-img-shake"
     									onclick='rentalNoInput(<%=book.get("rental_no") %>)'>
     							<%
     							}
     						}
     					%>
					</div>
		
				<a class="review-write-prev" onclick="plusSlides(-1)">&#10094;</a>
				<a class="review-write-next" onclick="plusSlides(1)">&#10095;</a>

			</div>
			<br>

			<div style="text-align:center">
				<%for(int i=0; i<bookList.size(); i+=3) { %>
  					<span class="review-write-dot" onclick="currentSlide(<%=i/3 + 1%>)"></span>
  				<%} %> 
			</div>
			
			<div style="text-align: center">리뷰를 쓰실 책을 선택해주세요</div>
     
     		<!-- 글쓰기 시작 -->
     		<input type="hidden" id="rentalNo" name="rentalNo">
     		<input type="text" name="name" class="review-write-text" value = "${memberBean.name }" readonly="readonly">
    		<input type="text" name="title" class="review-write-text" placeholder="제목을 입력해주세요." required="required">
			<input type="radio"name="isPublic" value="공개" checked="checked">공개 
			<input type="radio"name="isPublic" value="비공개">비공개 
    		<textarea name="content" class="review-write-text" placeholder="내용을 입력해주세요." style="height: 500px"></textarea>
			
			<div style="text-align: center">
    			<input type="submit" value="글쓰기" class="review-write-btn">
    			<input type="reset" value="다시쓰기" class="review-write-btn">
    			<input type="button" value="취소" class="review-write-btn" onclick="location.href='reviewList.rv'">
			</div>
  			
      		<div class="clearfix"></div>
<%--       		<input type="hidden"name="isbn"value="<%=book.getIsbn() %>"> --%>
  		</form>
	</div>

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

function rentalNoInput(no) {
	$('#rentalNo').val(no);
}

$("img").click(function(){
	$(".review-write-mySlides > img").css("border", "none");
	$(this).css("border", "1px solid #2196F3");
});

$("#reviewSubmit").submit(function(){
	if($("#rentalNo").val() == "") {
		alert("리뷰를 쓰실 책을 선택해주세욧!");
		return false;
	} 
});

</script>
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
