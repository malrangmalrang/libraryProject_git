<%@page import="vo.admin.BookList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>도서관리</title>
<script type="text/javascript">
function del(bookno){
	var str=confirm('삭제하시겠습니까');
	if(str==true){
		location.href="bookManagedetailDelete.bm?no="+bookno;
	}
}
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
<%
request.setCharacterEncoding("UTF-8");
BookList bookListVo=new BookList();
// bookListVo=(BookList)session.getAttribute("BookManageListDetail");
bookListVo=(BookList)request.getAttribute("BookManageListDetail");

%>

     		<div class="container">
     			<h1>도서상세정보</h1>
      			<hr>
      			<form action="bookManagedetailUpdatePro.bm?no=<%=bookListVo.getNo() %>" method="post">
				<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
					<tr><td>번호</td> <td><%=bookListVo.getNo() %></td>  </tr>
					<tr><td>도서명</td> <td><%=bookListVo.getTitle() %></td>   </tr>
					<tr><td>저자</td> <td><%=bookListVo.getAuthor() %></td> </tr>
					<tr><td>출판사</td> <td><%=bookListVo.getPublisher() %></td>  </tr>
					<tr><td>출판일</td> <td><%=bookListVo.getPublish_date() %></td> </tr>
					<tr><td>가격</td> <td><%=bookListVo.getPrice() %></td>  </tr>
					<tr><td>ISBN</td> <td><%=bookListVo.getIsbn() %></td> </tr>
					<tr><td>이미지</td> <td><div style="text-align: center;"><img src="<%=bookListVo.getImage() %>" style="width: 200px"></div></td></tr>
					<tr><td>상태</td>  
					    <td><select name="status">
        					<option value="대출가능" <%if(bookListVo.getStatus().equals("대출가능")){ %>selected="selected"   <%}%>>대출가능</option>
        					<option value="신청" <%if(bookListVo.getStatus().equals("신청")){ %>selected="selected"   <%}%>>신청</option>
        					<option value="대출중" <%if(bookListVo.getStatus().equals("대출중")){ %>selected="selected"   <%}%>>대출중</option>
        					<option value="분실/파손" <%if(bookListVo.getStatus().equals("분실/파손")){ %>selected="selected"   <%}%>>분실/파손</option>     
        	  				 </select></td>    
					</tr>
					<tr><td>키워드1</td><td><input type="text" name="keyword1"  value="<%=bookListVo.getKeyword1() %>"></td>   </tr>
					<tr><td>키워드2</td> <td><input type="text" name="keyword2" value="<%=bookListVo.getKeyword2() %>"></td>    </tr>
					<tr><td>키워드3</td>  <td><input type="text" name="keyword3" value="<%=bookListVo.getKeyword3() %>"></td>
					<tr><td>카테고리</td>
						<td><select name="category">
		        			<option value="A" <%if(bookListVo.getCategory().equals("A")){ %>selected="selected"   <%}%>>프로그래밍</option>
		        			<option value="B" <%if(bookListVo.getCategory().equals("B")){ %>selected="selected"   <%}%>>네트워크</option>
		        			<option value="C" <%if(bookListVo.getCategory().equals("C")){ %>selected="selected"   <%}%>>서버</option>
		        			<option value="D" <%if(bookListVo.getCategory().equals("D")){ %>selected="selected"   <%}%>>웹&디자인</option>
		        			<option value="E" <%if(bookListVo.getCategory().equals("E")){ %>selected="selected"   <%}%>>오라클</option>
		        			<option value="F" <%if(bookListVo.getCategory().equals("F")){ %>selected="selected"   <%}%>>IT&자격증</option>
		        			<option value="G" <%if(bookListVo.getCategory().equals("G")){ %>selected="selected"   <%}%>>자기계발</option>
		        			<option value="H" <%if(bookListVo.getCategory().equals("H")){ %>selected="selected"   <%}%>>기타</option>     
		        	  		 </select></td>
 					</tr>
					<tr><td>대출코드</td> <td><input type="text" name="rent_code" value="<%=bookListVo.getRent_code() %>"></td>     </tr>
					<tr><td>바코드</td> <td><input type="text" name="bar_code" value="<%=bookListVo.getBar_code()%>"></td></tr>
					<tr><td>등록일</td> <td><%=bookListVo.getReg_date() %></td>   </tr>
				</table>
      			
				
     			<div class="clearfix w3-center">
       				        <input type="submit" value="완료" class="join-button admin-book-btn">
        					<input type="reset" value="리셋" class="join-button admin-book-btn w3-orange">
       						<input type="button" value="삭제" class="join-button admin-book-btn w3-red" onclick="del(<%=bookListVo.getNo()%>)">
     		 	</div>
     		 	</form>
			</div>

</body>
</html>
