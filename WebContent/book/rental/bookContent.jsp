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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/star-rating.css">
<!------------------------ append css ------------------------------>

<!------------------------ append js ------------------------------>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<!------------------------ append js ------------------------------>
<script type="text/javascript">
	$(document).ready(function(index) {
		$('.rating-group').click(function() {
			$(this).html(function(index, html) {
				var grade = $('.rating__input:checked').val();
				$('#start_grade').text(" (" + grade + ")");
			});
		});
		
		$('#write_comment').submit(function() {
			var grade = $('.rating__input:checked').val();
			var content = $('#commnet_content').val();
			
			if (grade == 0) {
				alert("평점을 선택하세요.");
				$('.rating__input').focus();
				return false;
			}
			
			if (content.length == 0) {
				alert("한줄평 내용을 입력하세요.");
				$('#commnet_content').focus();
				return false;
			}
		});
		
	});
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
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-quarter" style="max-width: 334px;">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <c:choose>
			<c:when test="${book.image != null}">
				<img src="${book.image}" style="width: 98%;" >		
			</c:when>
			<c:otherwise>
				<%-- <img src="${pageContext.request.contextPath}/images/book/no-image.png" width="246" height="349"> --%>
				<img src="${pageContext.request.contextPath}/images/book/no-book-image.jpg" style="width: 98%;" >
			</c:otherwise>
		  </c:choose>
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird" style="margin-bottom: 10px;">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom  book-content">
        <h3 class="w3-text-grey w3-padding-16"><i class="fa fa-bookmark fa-fw w3-margin-right w3-xlarge w3-text-teal"></i>${book.title}</h3>
        <div class="w3-container">
            <p class="w3-large"><b class="w3-opacity">저자: </b>${book.author}</p>
          <p class="w3-large"><b class="w3-opacity">출판사: </b>${book.publisher}</p>
          <p class="w3-large"><b class="w3-opacity">출판일: </b>${book.publish_date}</p>
          <p class="w3-large"><b class="w3-opacity">카테고리</b>: 
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
          <p class="w3-large"><b class="w3-opacity">상태: </b>
          	<c:set var="rentCount" value="${book.rentCount}"/>
				<c:choose>
					<c:when test="${rentCount > 0}">
						<c:out value="대출가능 (${rentCount}권)"/>
					</c:when>
					<c:otherwise>
						<c:out value="대출불가"/>
					</c:otherwise>
			</c:choose>
          
          </p>
          <p class="w3-large"><b class="w3-opacity">평점 : </b>
				<c:set var="averageGrade" value="${book.averageGrade}"/>
      			<c:choose>
					<c:when test="${averageGrade >= 10}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 9}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fas fa-star-half-alt w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 8}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 7}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fas fa-star-half-alt w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 6}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 5}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fas fa-star-half-alt w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 4}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 3}">
						<i class="fa fa-star w3-text-orange"></i><i class="fa fa-star w3-text-orange"></i><i class="fas fa-star-half-alt w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 2}">
						<i class="fa fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>
					<c:when test="${averageGrade >= 1}">
						<i class="fas fa-star-half-alt w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i><i class="far fa-star w3-text-orange"></i>
						<c:out value="(평점: ${averageGrade})"/>
					</c:when>				
					<c:otherwise>
						<c:out value="등록된 평점이 없습니다."/>
					</c:otherwise>
				</c:choose>
      	  </p>
      	  <br>
      	  
      	  	<c:choose>
				<c:when test="${memberNo > 0}">
					
					<c:set var="favorCount" value="${book.favorCount}"/>
		        	<c:choose>
						<c:when test="${favorCount > 0}">
							<span><button class="w3-button w3-padding-large w3-white w3-border w3-large" 
							onclick="location.href='bookFavoritePro.do?isbn=${book.isbn}&favor=delete'"><b>관심 삭제 »</b></button></span>
						</c:when>
						<c:otherwise>
							<span><button class="w3-button w3-padding-large w3-white w3-border w3-large" 
							onclick="location.href='bookFavoritePro.do?isbn=${book.isbn}&favor=insert'"><b>관심 등록 »</b></button></span>
						</c:otherwise>
					</c:choose>
		        	
	       			<c:set var="rentCount" value="${book.rentCount}"/>
		        	<c:choose>
						<c:when test="${rentCount > 0}">
							<span><button class="w3-button w3-padding-large w3-white w3-border w3-large" 
							onclick="location.href='bookRentPro.do?isbn=${book.isbn}'"><b>대출 신청 »</b></button></span>
						</c:when>
						<c:otherwise>
							<span><button class="w3-button w3-padding-large w3-white w3-border w3-large"><b>대출 불가 »</b></button></span>
						</c:otherwise>
					</c:choose>
				</c:when>				
       		</c:choose>
       		<c:choose>
				<c:when test="${type eq 'list'}">
					<span><button class="w3-button w3-padding-large w3-white w3-border w3-large" onclick="location.href='bookList.do'"><b>도서 목록 »</b></button></span>
				</c:when>
				<c:when test="${type eq 'best'}">
					<span><button class="w3-button w3-padding-large w3-white w3-border w3-large" onclick="location.href='bookBestRent.do'"><b>대출 목록 »</b></button></span>
				</c:when>
			</c:choose>
          <hr>
        </div>
      </div>
     </div>
	
	<!-- Center Column -->
	<div class="w3-col m11">
      <div class="w3-container w3-card w3-white">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>책 소개</h2>
        <div class="w3-container">
          <h5 class="w3-opacity"><b>${description}</b></h5>
          <%-- <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>${description}</h6> --%>
          <%-- <p>${description}</p> --%>
          <hr>
        </div>
        <h2 class="w3-text-grey w3-padding-16" style="margin:0"><i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>한줄평</h2>
        
        <c:set var="memberNo" value="${memberNo}"/>
        <c:choose>
			<c:when test="${memberNo > 0}">
			
				<form action="bookCommentWritePro.do" id="write_comment" method="post">
					<input type="hidden" name="isbn" value="${book.isbn}">
					<input type="hidden" name="page" value="${page}">
					<div id="half-stars-example" style="display: inline-block;">
			    		<div class="rating-group">
			        		<input class="rating__input rating__input--none" checked name="grade" id="rating2-0" value="0" type="radio">
			        		<label aria-label="0 stars" class="rating__label" for="rating2-0">&nbsp;</label>
			        		<label aria-label="0.5 stars" class="rating__label rating__label--half" for="rating2-05"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-05" value="1" type="radio">
			        		<label aria-label="1 star" class="rating__label" for="rating2-10"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-10" value="2" type="radio">
			        		<label aria-label="1.5 stars" class="rating__label rating__label--half" for="rating2-15"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-15" value="3" type="radio">
			        		<label aria-label="2 stars" class="rating__label" for="rating2-20"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-20" value="4" type="radio">
			        		<label aria-label="2.5 stars" class="rating__label rating__label--half" for="rating2-25"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-25" value="5" type="radio">
			        		<label aria-label="3 stars" class="rating__label" for="rating2-30"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-30" value="6" type="radio">
			        		<label aria-label="3.5 stars" class="rating__label rating__label--half" for="rating2-35"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-35" value="7" type="radio">
			        		<label aria-label="4 stars" class="rating__label" for="rating2-40"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-40" value="8" type="radio">
			        		<label aria-label="4.5 stars" class="rating__label rating__label--half" for="rating2-45"><i class="rating__icon rating__icon--star fa fa-star-half"></i></label>
			        		<input class="rating__input" name="grade" id="rating2-45" value="9" type="radio">
			        		<label aria-label="5 stars" class="rating__label" for="rating2-50"><i class="rating__icon rating__icon--star fa fa-star"></i></label>
			       			<input class="rating__input" name="grade" id="rating2-50" value="10" type="radio">
			    		</div>
					</div>
			        <!--------------------------- 별점 ---------------------------------->
			        <span class="w3-xxlarge" id="start_grade"> (1 ~ 10)</span>
			        <br>
			        <textarea class="w3-input w3-border book-comment-input" placeholder="내용을 입력해주세요." id="commnet_content" name="content"></textarea>
			        <span><button type="submit" class="w3-button w3-padding-large w3-white w3-border w3-large" style="vertical-align: top; height: 79px"><b>등록</b></button></span>
				</form>
				
			</c:when>
			<c:otherwise>
				<div class="w3-container">
		          <h5 class="w3-opacity"><b>한줄평은 로그인한 회원만 등록할 수 있습니다.</b></h5>
        		</div>
			</c:otherwise>
		</c:choose>
		
      <!-------------------------------- 댓글 -------------------------------------->
      <c:forEach var="bookComment" items="${bookCommentList}">
       	<div class="w3-row">
	       <hr>
	     		<div class="w3-col m2 text-center">
	       			<c:set var="imageSrc" value="${bookComment.imageSrc}"/>
	        		<c:set var="image" value="${bookComment.image}"/>
					<c:choose>
						<c:when test="${image != null}">
							<c:choose>
								<c:when test="${imageSrc eq 'file'}">
									<img class="w3-circle" src="${pageContext.request.contextPath}/images/member/${image}" style="width:96px;height:96px">
								</c:when>
								<c:otherwise>
									<img class="w3-circle" src="${image}" style="width:96px;height:96px">		
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
							<img class="w3-circle" src="${pageContext.request.contextPath}/images/member/no-profile-image.png" style="width:96px;height:96px">		
						</c:otherwise>
					</c:choose>
	     		</div>
	     		<div class="w3-col m10 w3-container">
	       		<c:set var="reg_date" value="${bookComment.reg_date}"/>
        		<h4>${bookComment.name}
        		&nbsp;&nbsp;&nbsp;&nbsp;
        		<span class="w3-opacity w3-medium">${fn:substring(reg_date, 0, 19)}
        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        		<c:if test="${memberNo eq bookComment.member_no}">
        			<a href="bookCommentDeletePro.do?isbn=${book.isbn}&page=${pageInfo.nowPage}&bookCommentNo=${bookComment.no}">삭제</a>        		
        		</c:if>
        		</span>
        		</h4>
	       		     <c:set var="grade" value="${bookComment.grade}"/>
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
	       		<p>${bookComment.content}</p><br>
	     		</div>
		</div>
	  </c:forEach>
      <!-------------------------------- 댓글 ---------------------------------------->
      
      </div>
    <!-- End Right Column -->
    </div>
    
  <!-- End Grid -->
  </div>
  
  <!-- End Page Container -->
</div>  	
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
