<%@page import="vo.admin.WishBookBean"%>
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

     		<div class="container">
     			<h1>희망도서상세정보</h1>
      			<hr>
      			<form action="wishBookManageListUpdatePro.wm" method="post">
				<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
				<tr class="w3-black w3-large" style="font-weight: bold;">
											<td>도서명</td>
											<td>저자</td>
											<td>출판사</td>
											<td>출판일</td>
											<td>가격</td>
											<td>ISBN</td>
											<td>이미지</td>
											<td>상태</td>
											<td>키워드1</td>
											<td>키워드2</td>
											<td>키워드3</td>
											<td>카테고리</td>

										</tr>
						<%ArrayList wishBookUpdateList=(ArrayList)session.getAttribute("wishBookUpdateList");%>
  					<%for(int i=0; i<wishBookUpdateList.size(); i++){
//   						WishBookBean wishBookBean=new WishBookBean();
//   						WishBookBean=(WishBookBean)wishBookUpdateList.get(i);
							WishBookBean wishBookBean=new WishBookBean();
							wishBookBean=(WishBookBean)wishBookUpdateList.get(i);
							
  						%>
  						
  							<tr>
											<td><%=wishBookBean.getTitle()%></td>
											<td><%=wishBookBean.getAuthor()%></td>
											<td><%=wishBookBean.getPublisher()%></td>
											<td><%=wishBookBean.getPublish_date()%></td>
											<td><%=wishBookBean.getPrice()%></td>
											<td><%=wishBookBean.getIsbn()%></td>
											<td><div style="text-align: center;"><img src="<%=wishBookBean.getImage()%>"></div></td>
											<td><input type="text" name="<%=wishBookBean.getNo()%>status" value="대출가능" readonly="readonly"></td>                                
											<td><input type="text" name="<%=wishBookBean.getNo()%>keyword1"></td>
											<td><input type="text" name="<%=wishBookBean.getNo()%>keyword2"></td>
											<td><input type="text" name="<%=wishBookBean.getNo()%>keyword3"></td>
											<td><select name="<%=wishBookBean.getNo()%>category">
														<option value="A">프로그래밍</option>
														<option  value="B">네트워크</option>
														<option  value="C">서버</option>
														<option  value="D">웹&디자인</option>
														<option  value="E">오라클</option>
														<option  value="F">IT&자격증</option>
														<option  value="G">자기계발</option>
														<option  value="H">기타</option>
											       </select></td>
										</tr>
										<%
										}
										%>
  					
					
				</table>
				<div class="clearfix w3-center">
       				        <input type="submit" value="추가" class="join-button admin-book-btn">
        					<input type="reset" value="취소" class="join-button admin-book-btn w3-orange">
     		 	</div>
				
					</form>
      		</div>

</body>
</html>
