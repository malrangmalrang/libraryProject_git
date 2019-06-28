<%@page import="vo.PageInfo"%>
<%@page import="vo.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>아이티윌 도서관</title>
<script type="text/javascript">
	function memberDetail() {
		window.open("../pages/bestRental.jsp", "",
				"width=325,height=100,left=300, top=400");
	}
	
	
	function classify(str){
        var Class=str;
		location.href="memberManageList.mm?Class="+Class;
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
    <h1><b>회원 관리</b></h1>
    <%String Class;
											if(request.getParameter("Class")==null) {
												Class="전체";
											}
											else {
												Class=request.getParameter("Class");
											}
									%>
<!--     <select class="w3-right" style="height: 39px;margin: 10px;font-size: 20px;"> -->
<!--     	<option selected="selected">정렬기준</option> -->
<!--   		<option value="subject">제목</option> -->
<!--   		<option value="author">저자</option> -->
<!--   		<option value="isbn">ISBN</option> -->
<!-- 	</select> -->
									<select name="type" class="w3-right" style="height: 39px;margin: 10px;font-size: 20px;" onchange="classify(this.value)">
										<option value="전체" <%if(Class.equals("전체")){%>selected="selected" <%} %>>전체</option>
										<option value="미승인" <%if(Class.equals("미승인")){%>selected="selected" <%} %>>미승인</option>
										<option value="교육생" <%if(Class.equals("교육생")){%>selected="selected" <%} %>>교육생</option>
										<option value="수료생" <%if(Class.equals("수료생")){%>selected="selected" <%} %>>수료생</option>
										<option value="중도탈락" <%if(Class.equals("중도탈락")){%>selected="selected" <%} %>>중도탈락</option>
										<option value="강사" <%if(Class.equals("강사")){%>selected="selected" <%} %>>강사</option>
										<option value="관리자" <%if(Class.equals("관리자")){%>selected="selected" <%} %>>관리자</option>
	
	
									</select>
	<form action="memberManageModifyMember.mm?Class=<%=Class%>" method="post">
    <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
      <tr class="w3-black w3-large" style="font-weight: bold;">
      	<td></td>
      	<td>번호</td>
      	<td>아이디</td>
      	<td>이름</td>
      	<td>성별</td>
      	<td>생년월일</td>
      	<td>전화번호</td>
      	<td>주소1</td>
      	<td>주소2</td>
      	<td>우편번호</td>
      	<td>등급</td>
      	<td>가입날짜</td>
      </tr>
      
      		<%ArrayList memberList=(ArrayList)request.getAttribute("memberList");
			      		PageInfo pageInfo=(PageInfo)request.getAttribute("pageInfo");
			int listCount = pageInfo.getListCount();
			int nowPage = pageInfo.getPage();
			int maxPage = pageInfo.getMaxPage();
			int startPage = pageInfo.getStartPage();
			int endPage = pageInfo.getEndPage();%>
  					<%for(int i=0; i<memberList.size(); i++){
  						MemberBean memberBean=new MemberBean();
  						memberBean=(MemberBean)memberList.get(i);
  						%>
      
      
      										<tr class="memberManage_table_tr">
											<td><input type="checkbox" class="w3-check" value="<%=memberBean.getNo()%>" name="check"></td>
									        <td><%=memberBean.getNo()%></td>
											<td><%=memberBean.getEmail()%></td>
											<td><%=memberBean.getName()%></td>
											<td><%if(memberBean.getGender().equals("1")){%> 남 <%} else {%> 여 <%}%></td>
											<td><%=memberBean.getBirth()%></td>
											<td><%=memberBean.getPhone()%></td>
<%-- 											<td><%=memberBean.getImage()%></td> --%>
											<td><%=memberBean.getAddress1()%></td>
											<td><%=memberBean.getAddress2()%></td>
											<td><%=memberBean.getPostcode()%></td>
											<td><%=memberBean.getType()%></td>
											<td><%=memberBean.getReg_date()%></td>
										</tr>
										<%
										}
										%>


    </table><br>
    
										<input type="submit" value="변경" class="w3-right w3-button w3-black">
    										<select name="update_type" class="w3-right" style="height: 39px;margin-right: 5px;font-size: 20px;">
											<option value="전체">전체</option>
											<option value="미승인">미승인</option>
											<option value="교육생">교육생</option>
											<option value="수료생">수료생</option>
											<option value="중도탈락">중도탈락</option>
											<option value="강사">강사</option>
											<option value="관리자">관리자</option>
										</select> 
										
	</form>
		  	<div class="w3-center w3-padding-32 w3-xlarge">
    	<div class="w3-bar">
									<%
if(request.getParameter("booksearch")==null){
	%>
	<%if(nowPage <= 1) {%>
			<span class="w3-bar-item w3-button w3-hover-black">«</span>
	<%} else {%>
			<a href="memberManageList.mm?page=<%=nowPage - 1%>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">«</a>&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) { 
			if(i == nowPage) { %>
				<span class="w3-bar-item w3-black w3-button"><%=i %></span>
		<%} else { %>
				<a href="memberManageList.mm?page=<%=i %>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">[<%=i %>]</a>&nbsp;
		<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) { %>
			<span class="w3-bar-item w3-button w3-hover-black">»</span>
	<%} else { %>
			<a href="memberManageList.mm?page=<%=nowPage + 1 %>&Class=<%=Class%>" class="w3-bar-item w3-button w3-hover-black">»</a>
	<%} %>
	<%
	
	
	%>
	
<!-- 	//검색기능필요할떄 필요한 페이징(현재사용x) -->
	<%
	}else if(request.getParameter("booksearch")!=null){
		%>
		<section id="pageList">
		<%if(nowPage <= 1) {%>
				«&nbsp;
		<%} else {%>
				<a href="memberManageList.mm?page=<%=nowPage - 1%>&booksearch=<%=request.getParameter("booksearch")%>" class="w3-bar-item w3-button w3-hover-black">«</a>&nbsp;
		<%} %>
		
		<%for(int i = startPage; i <= endPage; i++) { 
				if(i == nowPage) { %>
					[<%=i %>]
			<%} else { %>
					<a href="memberManageList.mm?page=<%=i %>&booksearch=<%=request.getParameter("booksearch")%>" class="w3-bar-item w3-button w3-hover-black">[<%=i %>]</a>&nbsp;
			<%} %>
		<%} %>
		
		<%if(nowPage >= maxPage) { %>
				»
		<%} else { %>
				<a href="memberManageList.mm?page=<%=nowPage + 1 %>&booksearch=<%=request.getParameter("booksearch")%>" class="w3-bar-item w3-button w3-hover-black">»</a>
		<%} %>
		</section>
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
