<%@page import="vo.MemberBean"%>
<%@page import="vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardBean article = (BoardBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page"); // String 타입으로 setAttribute() 메서드에 저장했을 경우
// 	String nowPage = request.getAttribute("page").toString(); // int 타입으로 setAttribute() 메서드에 저장했을 경우
%>    
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
<link rel="stylesheet" href="./css/book_list.css">
<!------------------------ append css ------------------------------>
<%
MemberBean memberBean = (MemberBean)session.getAttribute("memberBean");
%>
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
  	<div class="w3-content w3-margin-top" style="max-width:1400px;">

  		<!-- The Grid -->
  		<div class="w3-row-padding">
  
  			<!-- Center Column -->
			<div class="w3-col m11">
      			<div class="w3-container w3-card w3-white">
        			<div class="w3-center" style="margin-top: 30px">
          				<h3><%= article.getTitle() %></h3>
          				<%-- <h5><span class="w3-opacity"><%= article.getNotice_name() %><br><%= article.getNotice_readcount() %> &nbsp;&nbsp;&nbsp; <%= article.getNotice_date() %></span></h5> --%>
        			</div>
        			<div class="review-content w3-justify w3-margin">
        			
        				<!-- 글내용 넣는곳 -->
          				<p><%= article.getContent()%>
					    </p>
					    <!-- 글내용 넣는곳 -->
					    
					    
       					<p>
		  					<a href="noticeList.bo?no=<%=article.getNo()%>&page=<%=nowPage %>" class="w3-right" style="margin-left: 10px">목록</a>
       					<%if(memberBean!=null){%>
       					<% if(memberBean.getType().equals("관리자")){%>
		  					 <a href="noticeDeleteForm.bo?no=<%=article.getNo()%>&page=<%=nowPage %>" class="w3-right" style="margin-left: 10px">삭제</a>
		  					<a href="noticeModifyForm.bo?no=<%=article.getNo()%>&page=<%=nowPage %>" class="w3-right"style="margin-left: 10px">수정</a>
		  				<%} %>
		  				<%} %>
		  				</p>
       				</div>
      			</div>
      		</div>
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
