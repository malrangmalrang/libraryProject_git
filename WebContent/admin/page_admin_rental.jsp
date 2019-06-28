<%@page import="vo.admin.RentalJoinBean"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>아이티윌 도서관</title>
<script type="text/javascript">
	function memberDetail() {
		window.open("./templates/pages/bestRental.jsp", "",
				"width=325,height=100,left=300, top=400");
	}
	
	function rentalManageClassify(str){
		var rmclass=str;
		location.href="rentalManageList.rm?Class="+str;
	}
</script>
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
<%
request.setCharacterEncoding("UTF-8");
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
  <div class="w3-container">
    <h1><b>대출 관리</b></h1>
<!--     <form action="rentalManageSearch.rm" method="post"> -->
<!-- 									<select name="rentalSearchSelectValue" class="w3-right" style="height: 39px;margin: 10px;font-size: 20px;"> -->
<!-- 										<option value="대여자" selected="selected">대여자</option> -->
<!-- 										<option value="도서명" selected="selected">도서명</option> -->
<!-- 									</select> -->
<!-- 									<input type="text" name="rentalSearchText"><input type="submit" value="검색"> -->
<!-- 									</form> -->
									
					
			<%
									String rmClass;
									if(request.getParameter("Class")==null){
										rmClass="전체";
									}
									else{
									rmClass=request.getParameter("Class");
									}
									%>				
					
									
    <select name="type" class="w3-right" onchange="rentalManageClassify(this.value)" style="height: 39px;margin: 10px;font-size: 20px;">
      <option value="전체" <%if(rmClass.equals("전체")){%>selected="selected" <%} %>>전체</option>
										<option value="신청" <%if(rmClass.equals("신청")){%>selected="selected" <%} %>>신청</option>
										<option value="대출중" <%if(rmClass.equals("대출중")){%>selected="selected" <%} %>>대출중</option>
										<option value="반납" <%if(rmClass.equals("반납")){%>selected="selected" <%} %>>반납</option>
										<option value="연체" <%if(rmClass.equals("연체")){%>selected="selected" <%} %>>연체</option>
										<option value="분실/파손" <%if(rmClass.equals("분실/파손")){%>selected="selected" <%} %>>분실/파손</option>
										<option value="대여일자" <%if(rmClass.equals("대여일자")){%>selected="selected" <%} %>>대여일자</option>
										<option value="반납일자" <%if(rmClass.equals("반납일자")){%>selected="selected" <%} %>>반납일자</option>
										<option value="반납기한" <%if(rmClass.equals("반납기한")){%>selected="selected" <%} %>>반납기한</option>
										<option value="대여자" <%if(rmClass.equals("대여자")){%>selected="selected" <%} %>>대여자</option>
										<option value="도서명" <%if(rmClass.equals("도서명")){%>selected="selected" <%} %>>도서명</option>
	</select>
	
	
	<form action="rentalManageModify.rm?type=<%=rmClass%>" method="post">
    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
      <tr class="w3-black w3-large" style="font-weight: bold;">
<!--       	<td><input class="w3-check" type="checkbox"></td> -->
		<td></td>
      	<td class="admin-two">번호</td>
      	<td>도서명</td>
      	<td>대여자</td>
      	<td class="admin-two">상태</td>
      	<td>대여일자</td>
      	<td>반납예정</td>
      	<td>반납일자</td>
      </tr>
      
      		<%ArrayList rentalList=(ArrayList)request.getAttribute("rentalList");
      		PageInfo pageInfo=(PageInfo)request.getAttribute("pageInfo");
											int listCount = pageInfo.getListCount();
											int nowPage = pageInfo.getPage();
											int maxPage = pageInfo.getMaxPage();
											int startPage = pageInfo.getStartPage();
											int endPage = pageInfo.getEndPage();%>
										<%for(int i=0; i<rentalList.size(); i++){
											  RentalJoinBean rentalJoinBean=new RentalJoinBean();
											  rentalJoinBean=(RentalJoinBean)rentalList.get(i);
											  %>
											  
					<tr  class="memberManage_table_tr">
											<td style="width: 25px;" class="col_chk">
											<input id="chkall"  <%if(rentalJoinBean.getStatus().equals("반납")){%> type="hidden"<%} else{%> type="checkbox" value="<%=rentalJoinBean.getNo()%>" class="w3-check" name="rentalCheck" <%}%>"></td>
<%-- 												<%if(wishBookJoinBean.getStatus().equals("Accept")){%> type="hidden"<%} else{%> type="checkbox" value="<%=wishBookJoinBean.getNo()%>" name="wishcheck" <%}%> --%>
											 	<td class="admin-two"><%=rentalJoinBean.getNo()%></td>
											 	<td><%=rentalJoinBean.getTitle()%></td>
											 	<td><%=rentalJoinBean.getEmail()%></td>
											 	<td class="admin-two"><%=rentalJoinBean.getStatus()%></td>
											    <td ><%=rentalJoinBean.getRent_date()%></td>
											    <td><%=rentalJoinBean.getDue_date()%></td>
											    <td><%=rentalJoinBean.getReturn_date() %></td>
											    
											   
											    
										</tr>
										
										<%}
										%>
											  
    </table><br>
	
									<input type="submit" value="변경" class="updateButton w3-button w3-right w3-black">
									<select name="modify_type" class="w3-right" style="height: 39px;margin-right: 5px;font-size: 20px;">
											<option value="대출중">대출중</option>
											<option value="반납">반납</option>
											<option value="분실/파손">분실/파손</option>
										</select> 
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
			<a href="rentalManageList.rm?page=<%=nowPage - 1%>&Class=<%=rmClass%>" class="w3-bar-item w3-button w3-hover-black">«</a>&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) { 
			if(i == nowPage) { %>
				<span class="w3-bar-item w3-black w3-button"><%=i %></span>
		<%} else { %>
				<a href="rentalManageList.rm?page=<%=i %>&Class=<%=rmClass%>" class="w3-bar-item w3-button w3-hover-black">[<%=i %>]</a>&nbsp;
		<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) { %>
			<span class="w3-bar-item w3-button w3-hover-black">»</span>
	<%} else { %>
			<a href="rentalManageList.rm?page=<%=nowPage + 1 %>&Class=<%=rmClass%>" class="w3-bar-item w3-button w3-hover-black">»</a>
	<%} %>
	</section>
	<%
	
	
	%>
	</div>
	</div>
	
<!-- 	검색 페이징 (파라미터필요할때) 현재사용 x -->
	<%
	}else if(request.getParameter("booksearch")!=null){
		%>
		<section id="pageList">
		서치
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
		</section>
		<%
		
		
		%>
	  <%
	}
	%>
  </div>
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
