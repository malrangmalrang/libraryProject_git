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
<div class="w3-main main-start-admin">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  
  
  
  	<!------------------------------ 메인 내용 ---------------------------------->
  <div class="w3-container">
    <h1><b>대출 관리</b></h1>
    <select class="w3-right" style="height: 39px;margin: 10px;font-size: 20px;">
    	<option selected="selected">정렬기준</option>
  		<option value="subject">제목</option>
  		<option value="author">저자</option>
  		<option value="isbn">ISBN</option>
	</select>
    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
      <tr class="w3-black w3-large" style="font-weight: bold;">
      	<td><input class="w3-check" type="checkbox"></td>
      	<td class="admin-two">번호</td>
      	<td>도서명</td>
      	<td>대여자</td>
      	<td class="admin-two">상태</td>
      	<td>대여일자</td>
      	<td>반납예정</td>
      	<td>반납일자</td>
      </tr>
      <tr>
      	<td><input class="w3-check" type="checkbox"></td>
        <td class="admin-two">1</td>
        <td>Do it! 안드</td>
        <td>dododo123@naver.com</td>
        <td class="admin-two">대출</td>
        <td>2019-05-06</td>
        <td>2019-05-21</td>
        <td>2019-05-21</td>
      </tr>
    </table><br>
    <button class="w3-button w3-dark-grey w3-right">무슨버튼? <i class="fa fa-arrow-right"></i></button>
	<select class="w3-right" style="height: 39px;margin-right: 5px;font-size: 20px;">
  		<option value="subject">제목</option>
  		<option value="author">저자</option>
  		<option value="isbn">ISBN</option>
	</select>
  </div>
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
