<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/book_list.css">
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
   <div class="w3-container">
   
   	 <h1><b>내가 쓴 리뷰</b></h1>
   	 
    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white w3-card book-list-table">
      <tr>
        <td>
        	<span class="w3-left w3-xxlarge" style="margin-right: 20px;"><b>1</b></span>
        	<img src="${pageContext.request.contextPath}/images/main/sampleimg.jpg" class="w3-left" style="margin-left: 5px;">
        	<div>
        		<h4 class="w3-text-teal"><b>목이 너무 깁니다 다 입력할 수가 업어요오오오오오오오오오오오오오오오오오오오오오오오오오오오오오</b></h4>
        		<h4 class="review-list-writer"><img src="${pageContext.request.contextPath}/images/main/sampleimg.jpg" class="w3-circle" style="width:50px; margin:0">
        			&nbsp;&nbsp;자전차왕 엄북동
        			<span class="w3-opacity">&nbsp;&nbsp;Sep 29, 2014, 9:12 PM</span>
        			<span class=review-list-commentlike><i class="far fa-comment-dots"></i>(5) <i class="far fa-thumbs-up"></i>(5)</span>
        		</h4>
				<a class="w3-left review-list-content">
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
				</a>
			</div>
      	</td>
      </tr>
      		
      <tr>
        <td>
        	<span class="w3-left w3-xxlarge" style="margin-right: 20px;"><b>1</b></span>
        	<img src="${pageContext.request.contextPath}/images/main/sampleimg.jpg" class="w3-left" style="margin-left: 5px;">
        	<div>
        		<h4 class="w3-text-teal"><b>목이 너무 깁니다 다 입력할 수가 업어요오오오오오오오오오오오오오오오오오오오오오오오오오오오오오</b></h4>
        		<h4 class="review-list-writer"><img src="./sample-img/hahaha.gif" class="w3-circle" style="width:50px; margin:0">
        			&nbsp;&nbsp;자전차왕 엄북동
        			<span class="w3-opacity">&nbsp;&nbsp;Sep 29, 2014, 9:12 PM</span>
        			<span class=review-list-commentlike><i class="far fa-comment-dots"></i>(5) <i class="far fa-thumbs-up"></i>(5)</span>
        		</h4>
				<a class="w3-left review-list-content">
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
				</a>
			</div>
      	</td>
      </tr>
      
      <tr>
        <td>
        	<span class="w3-left w3-xxlarge" style="margin-right: 20px;"><b>1</b></span>
        	<img src="./sample-img/sampleimg.jpg" class="w3-left" style="margin-left: 5px;">
        	<div>
        		<h4 class="w3-text-teal"><b>목이 너무 깁니다 다 입력할 수가 업어요오오오오오오오오오오오오오오오오오오오오오오오오오오오오오</b></h4>
        		<h4 class="review-list-writer"><img src="./sample-img/hahaha.gif" class="w3-circle" style="width:50px; margin:0">
        			&nbsp;&nbsp;자전차왕 엄북동
        			<span class="w3-opacity">&nbsp;&nbsp;Sep 29, 2014, 9:12 PM</span>
        			<span class=review-list-commentlike><i class="far fa-comment-dots"></i>(5) <i class="far fa-thumbs-up"></i>(5)</span>
        		</h4>
				<a class="w3-left review-list-content">
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
					오늘 술한잔 했습니다. 영화가 잘안되도 좋습니다. 열심히 연기하였습니다. 자전차왕 엄북동 이거하나만 기억해주십쇼.......
				</a>
			</div>
      	</td>
      </tr>
      
    </table><br>
  </div>
  
  	<!-- Pagination -->
  	<div class="w3-center w3-padding-32 w3-xlarge">
    	<div class="w3-bar">
      		<a href="#" class="w3-bar-item w3-button w3-hover-black">«</a>
      		<a href="#" class="w3-bar-item w3-black w3-button">1</a>
      		<a href="#" class="w3-bar-item w3-button w3-hover-black">2</a>
      		<a href="#" class="w3-bar-item w3-button w3-hover-black">3</a>
      		<a href="#" class="w3-bar-item w3-button w3-hover-black">4</a>
      		<a href="#" class="w3-bar-item w3-button w3-hover-black">»</a>
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
