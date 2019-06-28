<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="vo.book.DbCode"%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/book_list.css">
<!------------------------ append css ------------------------------>

</head>
<body class="w3-light-grey">

<!----------- Sidebar/menu ------------>
<jsp:include page="../../inc/sidebar.jsp" />
<!----------- Sidebar/menu ------------>

     
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main main-start">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  
  
  
  	<!------------------------------ 메인 내용 ---------------------------------->
  	<!-- Header -->
  	<header id="portfolio">
   	 	<div class="w3-container">
  	 		<h1><b>베스트대출</b></h1>
    		<!-- <p class="w3-center w3-xxlarge bestbook-list-year">2019년</p> -->
  			<!----- 날짜 페이지 ----->
<!--   			<div class="w3-center w3-large"> -->
  
<!--     			<div class="w3-bar"> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">«</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">1월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">2월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">3월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">4월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">5월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">6월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">7월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">8월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">9월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">10월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">11월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">12월</a> -->
<!--       				<a href="#" class="w3-bar-item w3-button w3-hover-black">»</a> -->
<!--     			</div> -->
<!--   			</div> -->
    		<!----- 날짜 페이지 ----->
    
    		<div class="w3-section w3-bottombar w3-padding-16">
      			<!-- <span class="w3-margin-right w3-xlarge">카테고리: </span> --> 
      			<c:set var="category" value="${category}"/>
      			<button <c:choose><c:when test="${category eq '' }">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do'">전체</button>
      			<button <c:choose><c:when test="${category eq 'A'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do?category=A'">${DbCode.BOOK_CATEGORY_A}</button>
      			<button <c:choose><c:when test="${category eq 'B'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do?category=B'">${DbCode.BOOK_CATEGORY_B}</button>
      			<button <c:choose><c:when test="${category eq 'C'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do?category=C'">${DbCode.BOOK_CATEGORY_C}</button>
      			<button <c:choose><c:when test="${category eq 'D'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do?category=D'">${DbCode.BOOK_CATEGORY_D}</button>
      			<button <c:choose><c:when test="${category eq 'E'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do?category=E'">${DbCode.BOOK_CATEGORY_E}</button>
      			<button <c:choose><c:when test="${category eq 'F'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do?category=F'">${DbCode.BOOK_CATEGORY_F}</button>
      			<button <c:choose><c:when test="${category eq 'G'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do?category=G'">${DbCode.BOOK_CATEGORY_G}</button>
      			<button <c:choose><c:when test="${category eq 'H'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> onclick="location.href='bookBestRent.do?category=H'">${DbCode.BOOK_CATEGORY_H}</button>
    		</div>
    	</div>
  </header>
  
  	<!-- 첫번째 줄-->
  	<div class="w3-row-padding w3-bottombar">
  		<c:forEach var="book" items="${bookList}" varStatus="status">
  		
    		<c:if test="${status.index == 5}">
				<div class="w3-row-padding w3-bottombar"></div><br>
    		</c:if>
  		
  		<div class="w3-container w3-margin-bottom w3-left" style="width: 20%">
  		
	      		<%-- <a href="bookContent.do?isbn=${book.isbn}&page=${pageInfo.nowPage}"><img src="${book.image}" style="width: 100%" ></a> --%>
	      		
	      		<a href="bookContent.do?isbn=${book.isbn}&type=best">
	      		<c:choose>
					<c:when test="${book.image != null}">
						<img src="${book.image}" width="100%" height="280px">
						<%-- <img src="${book.image}" width="100%"> --%>		
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/images/book/no-book-image.jpg" width="100%" height="280px">
					</c:otherwise>
				</c:choose>
	      		</a>
	      		
	      		<div class="w3-container w3-blue w3-center w3-xlarge " style="width: 100%;">
					<span>${status.count}위 (${book.bestRentCount}회 대출)</span>
	      		</div>
	      		<!-- 평점 -->
	      		<div class="w3-container w3-light-blue w3-center w3-medium" style="width: 100%;">
					<%-- <span>${book.averageGrade}점</span> --%>
					<c:set var="grade" value="${book.averageGrade}"/>
      			<c:choose>
					<c:when test="${grade >= 10}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 9}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fas fa-star-half-alt w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 8}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 7}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fas fa-star-half-alt w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 6}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 5}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fas fa-star-half-alt w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 4}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 3}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fas fa-star-half-alt w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 2}">
						<i class="fa fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>
					<c:when test="${grade >= 1}">
						<i class="fas fa-star-half-alt w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${grade})"/>
					</c:when>				
					<c:otherwise>
						<c:out value="등록된 평점이 없습니다."/>
					</c:otherwise>
				</c:choose>
	      		</div>
	      		<!-- 평점 -->
	    	</div>
	    </c:forEach>
  	</div>
   <!------------------------------ 메인 내용 ---------------------------------->
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
