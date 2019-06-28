<%@page import="vo.admin.WishBookJoinBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>희망도서관리</title>
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
					<%ArrayList wishBookList=(ArrayList)session.getAttribute("wishBookDetailList");
					  WishBookJoinBean imgBean=(WishBookJoinBean)wishBookList.get(0);
					%>
					
					
     		<div class="container">
     			<h1>희망도서상세정보</h1>
      			<hr>
      			<table class="w3-table w3-striped  w3-border w3-hoverable w3-white" style="border-color: black!important">
      			<tr>      			<td colspan="4"><h2><%=imgBean.getTitle()%></h2></td> </tr>
      			<tr> <td colspan="4"><div style="text-align: center;"><img src="<%=imgBean.getImage() %>"></div></td></tr>
      			<tr> <td>저자</td> <td><%=imgBean.getAuthor() %></td> <td>출판사</td> <td><%=imgBean.getPublisher() %></td></tr>
      			<tr> <td>출판일</td> <td><%=imgBean.getPublish_date() %></td> <td>가격</td> <td><%=imgBean.getPrice() %></td></tr>
      			<tr> <td>ISBN</td> <td><%=imgBean.getIsbn() %></td> <td>상태</td> <td><%=imgBean.getStatus() %></td></tr>
      			</table>
      			
      			

      			<br><br>
				<table class="w3-table w3-striped w3-border w3-hoverable w3-white" style="border-color: black!important">
				<tr><td>번호</td>
					<td>신청자</td>
					<td>등록일</td>

					</tr>

  					<%for(int i=0; i<wishBookList.size(); i++){
  						WishBookJoinBean wishBookJoinBean=new WishBookJoinBean();
  						wishBookJoinBean=(WishBookJoinBean)wishBookList.get(i);
  						%>
  						<tr>
											<td><%=wishBookJoinBean.getNo()%></td>
											<td><%=wishBookJoinBean.getEmail()%></td>
											<td><%=wishBookJoinBean.getReg_date()%></td>
								
										</tr>
										<%
										}
										%>
  					
					
				</table>
      		</div>

</body>
</html>
