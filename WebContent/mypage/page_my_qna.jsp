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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
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
    
    <h1><b>내가 쓴 Q&A</b></h1>
    
    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
      <tr class="w3-red w3-large" style="font-weight: bold;">
      	<td class="qna-num">번호</td>
      	<td>제목</td>
      	<td class="qna-writer">작성자</td>
      	<td class="qna-date">작성일</td>
      	<td class="qna-count">조회수</td>
      </tr>
      <tr class="w3-center">
        <td class="qna-num">1</td>
        <td><a href="#">도서명도서명도서명도서명도서명도서명도서명</a></td>
        <td class="qna-writer">꼴뚜기왕자</td>
        <td class="qna-date">2019-05-03</td>
        <td class="qna-count">999</td>
      </tr>
      <tr class="w3-center">
        <td class="qna-num">1</td>
        <td><a href="#">도서명도서명도서명도서명도서명도서명도서명</a></td>
        <td class="qna-writer">꼴뚜기왕자</td>
        <td class="qna-date">2019-05-03</td>
        <td class="qna-count">999</td>
      </tr>
      <tr class="w3-center">
        <td class="qna-num">1</td>
        <td><a href="#">도서명도서명도서명도서명도서명도서명도서명</a></td>
        <td class="qna-writer">꼴뚜기왕자</td>
        <td class="qna-date">2019-05-03</td>
        <td class="qna-count">999</td>
      </tr>
      <tr class="w3-center">
        <td class="qna-num">1</td>
        <td><a href="#">도서명도서명도서명도서명도서명도서명도서명</a> &nbsp;&nbsp;&nbsp;<i class="fa fa-check" style="color: green"></i></td>
        <td class="qna-writer">꼴뚜기왕자</td>
        <td class="qna-date">2019-05-03</td>
        <td class="qna-count">999</td>
      </tr>
    </table>
  </div>
  
    <!-- Pagination -->
  	<div class="w3-center w3-padding-32 w3-large">
    	<div class="w3-bar">
      		<a href="#" class="w3-bar-item w3-button w3-hover-red">«</a>
      		<a href="#" class="w3-bar-item w3-red w3-button">1</a>
      		<a href="#" class="w3-bar-item w3-button w3-hover-red">2</a>
      		<a href="#" class="w3-bar-item w3-button w3-hover-red">3</a>
      		<a href="#" class="w3-bar-item w3-button w3-hover-red">4</a>
      		<a href="#" class="w3-bar-item w3-button w3-hover-red">»</a>
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
