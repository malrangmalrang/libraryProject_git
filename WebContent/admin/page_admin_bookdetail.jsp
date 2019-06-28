<%@page import="vo.admin.BookList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>도서관리</title>
<script>
function up(bookno){
	var str=confirm('수정하시겠습니까?');
	if(str==true){
		location.href="bookManagedetailUpdate.bm?no="+bookno;
	}
}

function del(bookno){
	var str=confirm('삭제하시겠습니까');
	if(str==true){
		location.href="bookManagedetailDelete.bm?no="+bookno;
	}
}
</script>
<title>W3.CSS Template</title>
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
<%
request.setCharacterEncoding("UTF-8");
BookList bookListVo=new BookList();
// bookListVo=(BookList)session.getAttribute("BookManageListDetail");
bookListVo=(BookList)request.getAttribute("BookManageListDetail");

%>
     		<div class="container">
     			<h1>도서정보</h1>
      			<hr>
				<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
					<tr><td>번호</td> <td><%=bookListVo.getNo() %></td>  </tr>
					<tr><td>도서명</td> <td><%=bookListVo.getTitle() %></td>   </tr>
					<tr><td>저자</td> <td><%=bookListVo.getAuthor() %></td> </tr>
					<tr><td>출판사</td> <td><%=bookListVo.getPublisher() %></td>  </tr>
					<tr><td>출판일</td> <td><%=bookListVo.getPublish_date() %></td> </tr>
					<tr><td>가격</td> <td><%=bookListVo.getPrice() %></td>  </tr>
					<tr><td>ISBN</td> <td><%=bookListVo.getIsbn() %></td> </tr>
					<tr><td>이미지</td> <td><div style="text-align: center;"><img src="<%=bookListVo.getImage() %>" style="width:200px"></div></td></tr>
					<tr><td>상태</td> <td><%=bookListVo.getStatus() %></td>     </tr>
					<tr><td>키워드1</td>  <%if(bookListVo.getKeyword1()==null){%><td></td> <%} else{ %><td><%=bookListVo.getKeyword1() %></td>  <%} %>   </tr>
					<tr><td>키워드2</td> <%if(bookListVo.getKeyword2()==null){%><td></td> <%} else { %><td><%=bookListVo.getKeyword2() %></td>   <%} %></tr>
					<tr><td>키워드3</td>  <%if(bookListVo.getKeyword3()==null){%><td></td> <%} else{ %><td><%=bookListVo.getKeyword3() %></td>  <%} %></tr>
					<tr><td>카테고리</td> <td><%=bookListVo.getCategory() %></td>     </tr>
					<tr><td>대출코드</td> <td><%=bookListVo.getRent_code() %></td>     </tr>
					<tr><td>바코드</td> <td><%=bookListVo.getBar_code() %></td> </tr>
					<tr><td>등록일</td> <td><%=bookListVo.getReg_date() %></td>   </tr>
				</table>
				
     			<div class="clearfix w3-center">
<!--        				        <input type="button" value="수정" class="join-button admin-book-btn" onclick="location.href='bookManagedetailUpdate.bm'"> -->
       				        <input type="button" value="수정" class="join-button admin-book-btn" onclick="up(<%=bookListVo.getNo() %>)">
       						<input type="button" value="삭제" class="join-button admin-book-btn w3-red" onclick="del(<%=bookListVo.getNo() %>)">
     		 	</div>
			</div>

</body>
</html>
