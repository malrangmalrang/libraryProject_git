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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsiveslides.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/themes.css">
<!------------------------ append css ------------------------------>

<!------------------------ append js ------------------------------>
<script src="${pageContext.request.contextPath }/scripts/responsiveslides.min.js"></script>
<!------------------------ append js ------------------------------>

<script>
    // You can also use "$(window).load(function() "
    $(function () {

      // Slideshow 1
      $("#slider1").responsiveSlides({
        auto: true,
        pager: true,
        nav: true,
        speed: 500,
        maxwidth: 800,
        namespace: "centered-btns"
      });
      
   // Slideshow 2
      $("#slider2").responsiveSlides({
        auto: true,
        pager: true,
        nav: true,
        speed: 500,
        maxwidth: 800,
        namespace: "centered-btns"
      });
    });   
    
    function mainSearch() {
    	var searchWord = $("#searchWord").val();
    	
		if(searchWord == "") {
			alert("검색어를 입력해주세요.");
		} else {
			location.href="bookList.do?searchType=title&searchWord=" + searchWord;
		}
	}    
    
</script>
<%
//자체테스트용 아이디설정
// 	String email = "like2hyun@naver.com";
// 	int no = 11;
// 	session.setAttribute("email", email);
// 	session.setAttribute("memberNo",no);

%>
</head>
<body class="w3-light-grey">

<!----------- Sidebar/menu ------------>
<jsp:include page="../inc/sidebar.jsp" />
<!----------- Sidebar/menu ------------>

     
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main main-start">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-aqua w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  
  	<!------------------------------ 메인 내용 ---------------------------------->
  	
  	<!-- Main-img -->
  	<div class="w3-container main-space">
    	<img src="${pageContext.request.contextPath}/images/main/library.jpg" alt="mainimg" class="main-img w3-left w3-card">
    	<div class="top-left-text"><span style="font-size: 20px;">짱구의 원픽...</span><br>부산 아이티윌 도서관<br>빠른 검색은 요기요 <i class="fa fa-hand-o-down"></i></div>
 		<div class="bottom-left-search">
 			<input type="text" id="searchWord" placeholder="검색어를 입력해주세요."><button onclick="mainSearch()"><i class="fa fa-search"></i></button>
 		</div>
  		<div class="bottom-right-text"><b>이것이 부산<br>아이티윌 도서관!</b></div>
    		<img src="${pageContext.request.contextPath}/images/main/poongseong.png" class="bottom-right-img">
    		<img src="${pageContext.request.contextPath}/images/main/jjangoo.gif" class="bottom-right-img2">
  		</div>
  	<!-- Main-img -->
  	
  	<br>
  	
  	 <!-- center -->  
  	 
  	<!-- 베스트 대여 -->
  	<div class="w3-card w3-pale-red center-book-space w3-left w3-padding" >
  		<h3 class="w3-center"  style="margin: 10px 0 0 0"><b>베스트대출</b></h3>
    	<div class="rslides_container">
      		<ul class="rslides" id="slider1">
      			<c:forEach var="book" items="${bestBookList}" varStatus="status">
        			<%-- <li><a href="bookContent.do?isbn=${book.isbn}&page=1"><img src="${book.image}" alt="베스트 대여" class="w3-card"></a> --%>
        			<li><a href="bookContent.do?isbn=${book.isbn}&type=best"><img src="${book.image}" alt="베스트 대여" class="w3-card"></a>
        				  <h4 class="w3-center" style="margin: 0">${status.count }위(${book.rentCount}회)</h4>
        			</li>
        		</c:forEach>
      		</ul>
    	</div>
  	</div>
  	
  	<!-- 최신 리뷰 -->
  	<div class="w3-card w3-right w3-sand center-book-space-right w3-center w3-padding">
		<h3 class="w3-center"  style="margin: 10px 0 0 0"><b>최신 리뷰</b></h3>
  		
  		<div class="rslides_container">
      		<ul class="rslides" id="slider2">
      			<c:forEach var="review" items="${recentReviewList}">
        		<li>
        			<div class="w3-left center-book-review-img" style="margin-left: 35px">
        				<img src="${review.image}" alt="최신 리뷰" class="w3-card" >
        			</div>
        			<div class="center-book-review">
        				<h4>${review.title }</h4>
        				${review.content}
        			</div>
        			<h4 class="w3-center w3-clear" style="margin: 0"><a href="reviewView.rv?no=${review.no}&page=1">글보기</a></h4>
        		</li>
        		</c:forEach>
      		</ul>
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
