<%@page import="vo.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.book.BookBean"%>
<%@page import="vo.ReviewBean"%>
<%@page import="vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	ReviewBean reviewBean = (ReviewBean) request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
	BookBean bookBean = (BookBean) request.getAttribute("image");
	MemberBean memberBean = (MemberBean) request.getAttribute("no");
%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/book_list.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/star-rating.css">
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
  
  
  
  	<!------------------------------ 메인 내용 ---------------------------------->
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-col m2" style="max-width: 334px;">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-display-container">
          <img src="<%=bookBean.getImage() %>" style="width: 99%;" >
        </div>
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-col m9" style="margin-bottom: 10px;">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom  book-content" style="height: 282px">
      						
        <h4 class="w3-text-grey"><i class="fa fa-bookmark fa-fw w3-margin-right w3-large w3-text-teal"></i><%=bookBean.getTitle() %>  </h4>
        <div class="w3-container">
          <p class="w3-large"><b class="w3-opacity">저자 : </b><%=bookBean.getAuthor() %></p>
          <p class="w3-large"><b class="w3-opacity">출판사 : </b><%=bookBean.getPublisher() %></p>
          <p class="w3-large"><b class="w3-opacity">출판일 : </b><%=bookBean.getPublish_date() %></p>
          <p class="w3-large"><b class="w3-opacity">카테고리 : </b><%=bookBean.getCategory() %></p>
          <p class="w3-large"><b class="w3-opacity">평점 : </b>
          		<i class="far fa-star w3-text-orange"></i>
      			<i class="fa fa-star w3-text-orange"></i>
      			<i class="fa fa-star w3-text-orange"></i>
      			<i class="fa fa-star w3-text-orange"></i>
      			<i class="fas fa-star-half-alt w3-text-orange"></i>(평점 : 9) </p>
      			<hr>
      						
        </div>
      </div>
     </div>
	
	<!-- Center Column -->
	<div class="w3-col m11">
      <div class="w3-container w3-card w3-white">
        <div class="w3-center" style="margin-top: 30px">
          <h3><%=reviewBean.getTitle() %></h3>
          <h5><span class="w3-opacity">by <%=memberBean.getName() %><br><%=reviewBean.getReg_date() %> &nbsp;&nbsp;&nbsp;  조회수(<%=reviewBean.getReadcount() %>)</span></h5>
        </div>
        <div class="review-content w3-justify w3-margin">
        
        	<!-- 글내용 넣는곳 -->
          	<p>
          	<center><%=reviewBean.getContent() %></center>
		   	</p>
		   	<!-- 글내용 넣는곳 -->
							   
		  	
		<a href="reviewList.rv?page=<%=nowPage%>" class="w3-right" style="margin-left: 10px">목록</a>
		
<%-- 		<a href="reviewDeleteForm.rv?review_num=<%=reviewBean.getNo()%>&page=<%=nowPage%>" class="w3-right" style="margin-left: 10px">삭제</a>  --%>
<%-- 		<a href="reviewUpdateForm.rv?review_num=<%=reviewBean.getNo()%>&page=<%=nowPage%>" class="w3-right">수정</a> --%>
		  </p>
        </div>
        					
    
      </div>
    <!-- End Right Column -->
    </div>
    
  <!-- End Grid -->
  </div>
  
  <!-- End Page Container -->
</div>  	
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
