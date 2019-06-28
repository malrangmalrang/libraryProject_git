<%@page import="vo.admin.WishBookJoinBean"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.WishBookBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>아이티윌 도서관</title>
<script type="text/javascript">
function update(){
	var f=document.wishBookManageList;
	f.action="wishBookManageListUpdate.wm";
	f.submit();
}

function refuse(){
	var f=document.wishBookManageList;
	f.action="wishBookManageListRefuse.wm";
	f.submit();
}

function classify(str){
	var Class=str;
	location.href="wishBookManageList.wm?Class="+Class;
}

function detail(title,status){
	window.open("wishBookManageListDetail.wm?title="+title+"&status="+status,"상세정보", "width=1000, height=800");
}

function a(){
	alert("확인");
}
<%-- detail(<%=wishBookJoinBean.getTitle()%>, <%=wishBookJoinBean.getStatus()%>) --%>
</script>
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
  	<h1><b>희망 도서 관리</b></h1>
 								<%String Class;
											if(request.getParameter("Class")==null) {
												Class="Wishing";
											}
											else {
												Class=request.getParameter("Class");
											}
									%>
	<select name="type" onchange="classify(this.value)">
	
								<option value="Wishing" <%if(Class.equals("Wishing")){%>selected="selected" <%} %>>신청</option>
								<option value="Accept" <%if(Class.equals("Accept")){%>selected="selected" <%} %>>승인</option>
								<option value="Refuse" <%if(Class.equals("Refuse")){%>selected="selected" <%} %>>거절</option>
								
					
								</select>
	 <form method="post" name="wishBookManageList">							
    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
      <tr class="w3-black w3-large" style="font-weight: bold;">
<!--       	<td><input class="w3-check" type="checkbox"></td> -->
		<td></td>
      	<td>도서명</td>
      	<td>저자</td>
      	<td>출판사</td>
      	<td>출판일</td>
      	<td>가격</td>
      	<td>isbn</td>
      	<td>상태</td>
      	<td>신청자수</td>
      	<td>상세보기</td>
      </tr>
      
      				<%ArrayList wishBookList=(ArrayList)session.getAttribute("wishBookList");
      				PageInfo pageInfo=(PageInfo)session.getAttribute("pageInfo");
					int listCount = pageInfo.getListCount();
					int nowPage = pageInfo.getPage();
					int maxPage = pageInfo.getMaxPage();
					int startPage = pageInfo.getStartPage();
					int endPage = pageInfo.getEndPage();%>
  					<%for(int i=0; i<wishBookList.size(); i++){
  						WishBookJoinBean wishBookJoinBean=new WishBookJoinBean();
  						wishBookJoinBean=(WishBookJoinBean)wishBookList.get(i);
  						%>
  						
 										<tr>
<%-- 											<th><input type="checkbox" value="<%=wishBookJoinBean.getNo()%>" name="wishcheck"></th> --%>
													<td><input <%if(wishBookJoinBean.getStatus().equals("승인") || wishBookJoinBean.getStatus().equals("거절")){%> type="hidden"<%} else{%> class="w3-check" type="checkbox" value="<%=wishBookJoinBean.getTitle()%>" name="wishcheck" <%}%>></td>
											<td><%=wishBookJoinBean.getTitle()%></td>
											<td><%=wishBookJoinBean.getAuthor()%></td>
											<td><%=wishBookJoinBean.getPublisher()%></td>
											<td><%=wishBookJoinBean.getPublish_date()%></td>
											<td><%=wishBookJoinBean.getPrice()%></td>
											<td><%=wishBookJoinBean.getIsbn()%></td>
											<td><%=wishBookJoinBean.getStatus()%></td>
											<td><%=wishBookJoinBean.getCount()%></td>
					
<%-- 											<th><a href=" " onclick="Detail(<%=wishBookJoinBean.getTitle()%>)">상세보기</a></th> --%>
											<td><input type="button" value="상세보기" onclick="detail('<%=wishBookJoinBean.getTitle()%>','<%=wishBookJoinBean.getStatus()%>')">
											</td>
										</tr>
										<%
										}
										%>					
    </table><br>
    			<input type="button" value="체크항목추가" class="w3-button w3-black w3-right" onclick="update()" style="margin-left: 5px">
				<input type="button" value="체크항목거절" class="w3-button w3-black w3-right" onclick="refuse()">
    </form>
    
      	<div class="w3-center w3-padding-32 w3-xlarge">
    	<div class="w3-bar">
    		<%
if(request.getParameter("booksearch")==null){
	%>
	<section id="pageList">
	<%if(nowPage <= 1) {%>
			<span class="w3-bar-item w3-button w3-hover-black">«</span>
	<%} else {%>
			<a href="wishBookManageList.wm?page=<%=nowPage - 1%>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">«</a>&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) { 
			if(i == nowPage) { %>
				<span class="w3-bar-item w3-black w3-button"><%=i %></span>
		<%} else { %>
				<a href="wishBookManageList.wm?page=<%=i %>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">[<%=i %>]</a>&nbsp;
		<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) { %>
			<span class="w3-bar-item w3-button w3-hover-black">»</span>
	<%} else { %>
			<a href="wishBookManageList.wm?page=<%=nowPage + 1 %>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">»</a>
	<%} %>
	</section>
	<%
	
	
	%>
	
<!-- 	검색페이징 부분 (파라미터필요할때) 현재사용X -->
	<%
	}else if(request.getParameter("booksearch")!=null){
		%>
		<%if(nowPage <= 1) {%>
				«&nbsp;
		<%} else {%>
				<a href="bookManageListSearch.bm?page=<%=nowPage - 1%>&booksearch=<%=request.getParameter("booksearch")%>" class="w3-bar-item w3-button w3-hover-black">«</a>&nbsp;
		<%} %>
		
		<%for(int i = startPage; i <= endPage; i++) { 
				if(i == nowPage) { %>
					[<%=i %>]
			<%} else { %>
					<a href="bookManageListSearch.bm?page=<%=i %>&booksearch=<%=request.getParameter("booksearch")%>" class="w3-bar-item w3-button w3-hover-black">[<%=i %>]</a>&nbsp;
			<%} %>
		<%} %>
		
		<%if(nowPage >= maxPage) { %>
				»
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
  </div>   	
  <!------------------------------ 메인 내용 ---------------------------------->
    
  	<!--------------- footer ---------------->
	<jsp:include page="../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
