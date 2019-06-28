<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script type="text/javascript">
	function bookRent(isbn) {
		var isRent = confirm("대출 신청하시겠습니까?");
		if (isRent) {
			location.href="bookRentPro.do?isbn=" + isbn;
		} else {
			window.close();			
		}
	}
</script>

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
    		<h1><b>도서목록 (${pageInfo.listCount})</b></h1>
	
			<!----- 도서 검색 input ----->
			<form action="bookList.do" method="get">
				<select class="category-select" name="searchType">
  					<option value="title" <c:if test="${searchType eq 'title'}">selected</c:if>>제목</option>
  					<option value="author" <c:if test="${searchType eq 'author'}">selected</c:if>>저자</option>
  					<option value="isbn" <c:if test="${searchType eq 'isbn'}">selected</c:if>>ISBN</option>
  					<option value="keyword" <c:if test="${searchType eq 'keyword'}">selected</c:if>>키워드</option>
				</select>    
    			<c:set var="searchWord" value="${searchWord}"></c:set>
    			<input type="text" name="searchWord" <c:if test="${searchWord != ''}">value="${searchWord}"</c:if> placeholder="검색어를 입력해주세요." class="common-search" style="width:70%">
    			<button type="submit" class="common-search-button" style="width:10%"><i class="fa fa-search"></i></button>
    		</form>
   			<!----- 도서 검색 input ----->
    
    		<br>
    		<br>
    		<div class="w3-section w3-bottombar w3-padding-16">
      			<!-- <span class="w3-margin-right w3-xlarge">카테고리: </span> --> 
      			<c:set var="category" value="${category}"/>
      			<button <c:choose><c:when test="${category eq '' }">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?searchType=${searchType}&searchWord=${searchWord}'">전체</button>
      			<button <c:choose><c:when test="${category eq 'A'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?category=A&searchType=${searchType}&searchWord=${searchWord}'">${DbCode.BOOK_CATEGORY_A}</button>
      			<button <c:choose><c:when test="${category eq 'B'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?category=B&searchType=${searchType}&searchWord=${searchWord}'">${DbCode.BOOK_CATEGORY_B}</button>
      			<button <c:choose><c:when test="${category eq 'C'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?category=C&searchType=${searchType}&searchWord=${searchWord}'">${DbCode.BOOK_CATEGORY_C}</button>
      			<button <c:choose><c:when test="${category eq 'D'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?category=D&searchType=${searchType}&searchWord=${searchWord}'">${DbCode.BOOK_CATEGORY_D}</button>
      			<button <c:choose><c:when test="${category eq 'E'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?category=E&searchType=${searchType}&searchWord=${searchWord}'">${DbCode.BOOK_CATEGORY_E}</button>
      			<button <c:choose><c:when test="${category eq 'F'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?category=F&searchType=${searchType}&searchWord=${searchWord}'">${DbCode.BOOK_CATEGORY_F}</button>
      			<button <c:choose><c:when test="${category eq 'G'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?category=G&searchType=${searchType}&searchWord=${searchWord}'">${DbCode.BOOK_CATEGORY_G}</button>
      			<button <c:choose><c:when test="${category eq 'H'}">class="w3-button w3-black"</c:when><c:otherwise>class="w3-button w3-white"</c:otherwise></c:choose> 
      			onclick="location.href='bookList.do?category=H&searchType=${searchType}&searchWord=${searchWord}'">${DbCode.BOOK_CATEGORY_H}</button>
    		</div>
    	</div>
  </header>
  
   <div class="w3-container">
    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white w3-card book-list-table">
		<c:forEach var="book" items="${bookList}">	  
	      <tr>
	      	<td>
	      		<c:choose>
					<c:when test="${book.image != null}">
						<img src="${book.image}" class="w3-left">		
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/images/book/no-book-image.jpg" class="w3-left" width="150" height="210">
					</c:otherwise>
				</c:choose>
	        	<div>
	        		<p>
	        			<a class="w3-large" href="bookContent.do?isbn=${book.isbn}&type=list&page=${pageInfo.nowPage}">
	        				<b>제목</b>: ${book.title}<br> 
			        		<b>저자</b>: ${book.author}<br>
			      			<b>출판사</b>: ${book.publisher}<br> 
			      			<b>출판일</b>: ${book.publish_date}<br>
			      			<b>카테고리</b>: 
			      				<c:set var="book_category" value="${book.category}"/>
				      			<c:choose>
				      				<c:when test="${book_category eq 'A'}">
				      					<c:out value="${DbCode.BOOK_CATEGORY_A}"/>
				      				</c:when>
				      				<c:when test="${book_category eq 'B'}">
				      					<c:out value="${DbCode.BOOK_CATEGORY_B}"/>
				      				</c:when>
				      				<c:when test="${book_category eq 'C'}">
				      					<c:out value="${DbCode.BOOK_CATEGORY_C}"/>
				      				</c:when>
				      				<c:when test="${book_category eq 'D'}">
				      					<c:out value="${DbCode.BOOK_CATEGORY_D}"/>
				      				</c:when>
				      				<c:when test="${book_category eq 'E'}">
				      					<c:out value="${DbCode.BOOK_CATEGORY_E}"/>
				      				</c:when>
				      				<c:when test="${book_category eq 'F'}">
				      					<c:out value="${DbCode.BOOK_CATEGORY_F}"/>
				      				</c:when>
				      				<c:when test="${book_category eq 'G'}">
				      					<c:out value="${DbCode.BOOK_CATEGORY_G}"/>
				      				</c:when>
					      			<c:otherwise>
										<c:out value="${DbCode.BOOK_CATEGORY_H}"/>
									</c:otherwise>
								</c:choose>
							<br>
			      			<b>상태</b>: 
			      			<c:set var="rentCount" value="${book.rentCount}"/>
							<c:choose>
								<c:when test="${rentCount > 0}">
									<c:out value="대출가능 (${rentCount}권)"/>
								</c:when>
								<c:otherwise>
									<c:out value="대출불가"/>
								</c:otherwise>
							</c:choose><br>
	      				</a>
	      			</p>
	      			<p>
	      				<span class="w3-large w3-left">
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
		      			</span>
	      			</p>
	      		</div> 
	      	</td>
	        <td style="padding-top: 140px!important; width: 140px">
	        	<c:choose>
					<c:when test="${memberNo > 0}">
						
						<c:set var="favorCount" value="${book.favorCount}"/>
	        			<c:choose>
							<c:when test="${favorCount > 0}">
								<button class="w3-button w3-dark-grey w3-right booklist-td-btn" onclick="location.href='bookFavoritePro.do?isbn=${book.isbn}&favor=delete'">관심삭제<i class="fa fa-arrow-right"></i></button>
							</c:when>
							<c:otherwise>
								<button class="w3-button w3-red w3-right booklist-td-btn" onclick="location.href='bookFavoritePro.do?isbn=${book.isbn}&favor=insert'">관심등록<i class="fa fa-arrow-right"></i></button>
							</c:otherwise>
						</c:choose>
	        	
			        	<c:set var="rentCount" value="${book.rentCount}"/>
			        	<c:choose>
							<c:when test="${rentCount > 0}">
								<%-- <button class="w3-button w3-blue w3-right booklist-td-btn" onclick="location.href='bookRentPro.do?isbn=${book.isbn}'">대출신청<i class="fa fa-arrow-right"></i></button> --%>
								<button class="w3-button w3-blue w3-right booklist-td-btn" onclick="bookRent(${book.isbn})">대출신청<i class="fa fa-arrow-right"></i></button>
							</c:when>
							<c:otherwise>
								<button class="w3-button w3-dark-grey w3-right booklist-td-btn">대출불가<i class="fa fa-arrow-right"></i></button>
							</c:otherwise>
						</c:choose>
						
					</c:when>
	        	</c:choose>
	      	</td>
	      </tr>
      </c:forEach>
    </table><br>
  </div>
  
  	<!-- Pagination -->
  	<div class="w3-center w3-padding-32 w3-xlarge">
    	<div class="w3-bar">
    		<c:set var="nowPage" value="${pageInfo.nowPage}"/>
    		<c:set var="startPage" value="${pageInfo.startPage}"/>
    		<c:set var="endPage" value="${pageInfo.endPage}"/>
    		<c:set var="maxPage" value="${pageInfo.maxPage}"/>
		    <c:choose>
				<c:when test="${nowPage <= 1}">
					<a href="#" class="w3-bar-item w3-button w3-hover-black">«</a>		
				</c:when>
				<c:otherwise>
					<a href="bookList.do?page=${nowPage - 1}&category=${category}&searchType=${searchType}&searchWord=${searchWord}" class="w3-bar-item w3-button w3-hover-black">«</a>
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1"> <!-- for문 -->
				<c:choose>
					<c:when test="${i == nowPage}">
						<a href="#" class="w3-bar-item w3-black w3-button">${i}</a>		
					</c:when>
					<c:otherwise>
						<a href="bookList.do?page=${i}&category=${category}&searchType=${searchType}&searchWord=${searchWord}" class="w3-bar-item w3-button w3-hover-black">${i}</a>					
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			 <c:choose>
				<c:when test="${nowPage >= maxPage}">
					<a href="#" class="w3-bar-item w3-button w3-hover-black">»</a>		
				</c:when>
				<c:otherwise>
					<a href="bookList.do?page=${nowPage + 1}&category=${category}&searchType=${searchType}&searchWord=${searchWord}" class="w3-bar-item w3-button w3-hover-black">»</a>
				</c:otherwise>
			</c:choose>
    	</div>
  	</div>  	
    <!------------------------------ 메인 내용 ---------------------------------->
  
  	<!--------------- footer ---------------->
	<jsp:include page="../../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
