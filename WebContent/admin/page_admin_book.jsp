<%@page import="vo.admin.BookList"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function bookdetail(i){
	window.open(" bookManageListDetail.bm?no="+i,"상세정보", "width=1000, height=800");
 }
</script>
<title>아이티윌 도서관</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<!------------------------ append css ------------------------------>


</head>
<body class="w3-light-grey">
<%request.setCharacterEncoding("UTF-8"); %>
<%String Class;
if(request.getParameter("Class")==null){
	Class="All";
}
else{
	Class=request.getParameter("Class");
}
%>


<!----------- Sidebar/menu ------------>
<jsp:include page="../inc/sidebar.jsp" />
<!----------- Sidebar/menu ------------>

     
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main main-start-admin">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  
  
  
  	<!------------------------------ 메인 내용 ---------------------------------->
  	<!-- Header -->
  	<header id="portfolio">
    	<div class="w3-container">
    		<h1><b>도서 관리</b></h1>
	
			<!----- 도서 검색 input ----->
			        <form action="bookManageListSearch.bm" method="post">
				<select class="category-select">
  					<option value="subject">제목</option>
				</select>    
    			<input type="text" name="booksearch" placeholder="검색어를 입력해주세요." class="common-search" style="width:70%">
    			<button type="submit" class="common-search-button"><i class="fa fa-search"></i></button>
    		</form>
   			<!----- 도서 검색 input ----->
    
    		<br>
    		<br>
    		<div class="w3-section w3-bottombar w3-padding-16">
      			<span class="w3-margin-right w3-xlarge">카테고리 : </span> 
      			<button class="w3-button w3-black" onclick="location.href='bookManageList.bm?Class=All'">ALL</button>
      			<button class="w3-button w3-white" onclick="location.href='bookManageList.bm?Class=A'">프로그래밍</button>
      			<button class="w3-button w3-white" onclick="location.href='bookManageList.bm?Class=B'">네트워크</button>
      			<button class="w3-button w3-white" onclick="location.href='bookManageList.bm?Class=C'">서버</button>
      			<button class="w3-button w3-white" onclick="location.href='bookManageList.bm?Class=D'">웹&디자인</button>
      			<button class="w3-button w3-white" onclick="location.href='bookManageList.bm?Class=E'">오라클</button>
      			<button class="w3-button w3-white" onclick="location.href='bookManageList.bm?Class=F'">IT&자격증</button>
      			<button class="w3-button w3-white" onclick="location.href='bookManageList.bm?Class=G'">자기계발</button>
      			<button class="w3-button w3-white" onclick="location.href='bookManageList.bm?Class=H'">기타</button>
    		</div>
    	</div>
  	</header>
  	
  <div class="w3-container">
  <form action="bookManageListCheckDelete.bm" method="post">
    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
      <tr class="w3-black w3-large" style="font-weight: bold;">
      	<td></td>
      	<td>번호</td>
      	<td>도서명</td>
      	<td>저자</td>
      	<td>출판사</td>
      	<td>isbn</td>
      	<td>상태</td>
      	<td>대출코드</td>
      	<td>바코드</td>
      	<td>등록일</td>
      </tr>
      
      <% 
ArrayList bookList=(ArrayList)request.getAttribute("bookList");
PageInfo pageInfo=(PageInfo)request.getAttribute("pageInfo");
int listCount = pageInfo.getListCount();
int nowPage = pageInfo.getPage();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
%>

<%for(int i=0; i<bookList.size(); i++){
	BookList bookListVo=new BookList();
	bookListVo=(BookList)bookList.get(i);
%>
      
      
<!--       <tr> -->
<!--       	<td><input class="w3-check" type="checkbox"></td> -->
<!--         <td>1</td> -->
<!--         <td><a onclick="bookpopup()">도서명도서명도서명도서명도서명도서명도서명도서명도서명도서명</a></td> -->
<!--         <td>김은기, 이진훈, 김은영</td> -->
<!--         <td>인피니티북스</td> -->
<!--         <td>2019-09-12</td> -->
<!--         <td>9788983799067</td> -->
<!--         <td>대출</td> -->
<!--         <td>A-006</td> -->
<!--         <td>9788983799067</td> -->
<!--         <td>2020-02-31</td> -->
<!--       </tr> -->

<tr>
<td><input type="checkbox" class="w3-check" value="<%=bookListVo.getNo()%>" name="check"></td>
<td><%=bookListVo.getNo() %></td>
<td><a href=" " onclick="bookdetail(<%=bookListVo.getNo()%>)"><%=bookListVo.getTitle() %></a></td>
<td><%=bookListVo.getAuthor() %></td>
<td><%=bookListVo.getPublisher() %></td>
<td><%=bookListVo.getIsbn() %></td>
<td><%=bookListVo.getStatus() %></td>
<td><%=bookListVo.getRent_code() %></td>
<td><%=bookListVo.getBar_code() %></td>
<td><%=bookListVo.getReg_date() %></td>
</tr>
<%
}
%>
    </table><br>
    <input type="submit" class="w3-button w3-dark-grey w3-right" value="체크항목삭제">
    
    </form>
  </div>
  
  
   	<!-- Pagination -->
  	<div class="w3-center w3-padding-32 w3-xlarge">
    	<div class="w3-bar">
<!--       		<a href="#" class="w3-bar-item w3-button w3-hover-black">«</a> -->
<!--       		<a href="#" class="w3-bar-item w3-black w3-button">1</a> -->
<!--       		<a href="#" class="w3-bar-item w3-button w3-hover-black">2</a> -->
<!--       		<a href="#" class="w3-bar-item w3-button w3-hover-black">3</a> -->
<!--       		<a href="#" class="w3-bar-item w3-button w3-hover-black">4</a> -->
<!--       		<a href="#" class="w3-bar-item w3-button w3-hover-black">»</a> -->
      		
      		<%
if(request.getParameter("booksearch")==null){
	%>
	<%if(nowPage <= 1) {%>
			<span class="w3-bar-item w3-button w3-hover-black">«</span>
	<%} else {%>
			<a href="bookManageList.bm?page=<%=nowPage - 1%>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">«</a>&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) { 
			if(i == nowPage) { %>
				<span class="w3-bar-item w3-black w3-button"><%=i %></span>
		<%} else { %>
				<a href="bookManageList.bm?page=<%=i %>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">[<%=i %>]</a>&nbsp;
		<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) { %>
			<span class="w3-bar-item w3-button w3-hover-black">»</span>
	<%} else { %>
			<a href="bookManageList.bm?page=<%=nowPage + 1 %>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">»</a>
	<%} %>
	<%
	
	
	%>
	
	<%
	}else if(request.getParameter("booksearch")!=null){
		%>
		<%if(nowPage <= 1) {%>
				<span class="w3-bar-item w3-button w3-hover-black">«</span>
		<%} else {%>
				<a href="bookManageListSearch.bm?page=<%=nowPage - 1%>&booksearch=<%=request.getParameter("booksearch")%>" class="w3-bar-item w3-button w3-hover-black">«</a>&nbsp;
		<%} %>
		
		<%for(int i = startPage; i <= endPage; i++) { 
				if(i == nowPage) { %>
					<span class="w3-bar-item w3-black w3-button"><%=i %></span>
			<%} else { %>
					<a href="bookManageListSearch.bm?page=<%=i %>&booksearch=<%=request.getParameter("booksearch")%>" class="w3-bar-item w3-button w3-hover-black">[<%=i %>]</a>&nbsp;
			<%} %>
		<%} %>
		
		<%if(nowPage >= maxPage) { %>
				<span class="w3-bar-item w3-button w3-hover-black">»</span>
		<%} else { %>
				<a href="bookManageListSearch.bm?page=<%=nowPage + 1 %>&booksearch=<%=request.getParameter("booksearch")%>" class="w3-bar-item w3-button w3-hover-black">»</a>
		<%} %>
		<%
		
		
		%>
	  <%
	}
	%>
      		
      		
      		
    	</div>
  	</div>  	
  <!------------------------------ 메인 내용 ---------------------------------->
  
<!------------------------------------------  팝업  ------------------------------------------>
     <div id="bookpop" class="modal">
  		<span onclick="document.getElementById('bookpop').style.display='none'" class="close" title="창닫기">&times;</span>
 		<form class="modal-content animate" action="#">
    		<div class="container">
     			<h1>책수정</h1>
      			<hr>
				<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
					<tr><td>번호</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>도서명</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>저자</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>출판사</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>출판일</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>가격</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>ISBN</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>이미지</td><td><img src="./sample-img/hahaha.gif"></td></tr>
					<tr><td>상태</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>키워드1</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>키워드2</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>키워드3</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>카테고리</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>대출코드</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>바코드</td><td><input type="text" style="width: 90%"></td></tr>
					<tr><td>등록일</td><td><input type="text" style="width: 90%"></td></tr>
				</table>
      			
				
     			<div class="clearfix w3-center">
       		 		<button type="submit" class="join-button admin-book-btn w3-orange">취소</button>
       		 		<button type="submit" class="join-button admin-book-btn w3-red">삭제</button>
       				<button type="button" onclick="document.getElementById('bookpop').style.display='none'" class="join-button admin-book-btn">수정</button>
     		 	</div>
   		 	</div>
  		</form>
	 </div>
<!------------------------------------------   팝업  ------------------------------------------>   
  
  	<!--------------- footer ---------------->
	<jsp:include page="../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
