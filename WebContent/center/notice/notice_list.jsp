<%@page import="vo.MemberBean"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 조회된 게시물 정보를 담은 ArrayList 객체(articleList)와 페이지 정보를 담은 PageInfo 객체(pageInfo)를
	// request.getAttribute() 메서드로 가져오기
	ArrayList<BoardBean> articleList = (ArrayList<BoardBean>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");

	MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
	
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
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
<link rel="stylesheet" href="./css/table.css">
<!------------------------ append css ------------------------------>

</head>
<body class="w3-light-grey">

	<!----------- Sidebar/menu ------------>
	<jsp:include page="../../inc/sidebar.jsp" />
	<!----------- Sidebar/menu ------------>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main main-start">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>  Menu
		</button>



		<!------------------------------ 메인 내용 ---------------------------------->
		<!-- Header -->
		<header id="portfolio">
			<div class="w3-container">
				<h1>
					<b>공지사항</b>
				</h1>

				<!----- 도서 검색 input ----->
				<form>
					<select class="category-select">
						<option value="subject">제목</option>
<!-- 						<option value="author">저자</option> -->
<!-- 						<option value="isbn">ISBN</option> -->
					</select> <input type="text" name="search" placeholder="검색어를 입력해주세요."
						class="common-search" style="width: 40%;">
					<button type="submit" name="search_button"
						class="common-search-button">
						<i class="fa fa-search"></i>
					</button>
				</form>
				<!----- 도서 검색 input ----->
				<!--  <button class="write-btn w3-right">글쓰기</button>  -->
				<%
				if(memberBean!=null){
					if (memberBean.getType().equals("관리자")) {
				%>


				<a href="noticeWriteForm.bo?no=&page=<%=nowPage%>"
					class="write-btn w3-right">글쓰기</a>

				<%
					}
				}
				%>
			</div>
		</header>

		<br>

		<div class="w3-container">
			<table
				class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
				<%
					if (articleList != null && listCount > 0) {
				%>

				<tr class="w3-red w3-large" style="font-weight: bold;">
					<td class="qna-num">번호</td>
					<td>제목</td>
					<td class="qna-writer">작성자</td>
					<td class="qna-date">작성일</td>
					<td class="qna-count">조회수</td>
				</tr>


				<%
					for (int i = 0; i < articleList.size(); i++) {
				%>
				<tr>
					<td align="center"><%=articleList.get(i).getNo()%></td>
					<td><a
						href="noticeDetail.bo?no=<%=articleList.get(i).getNo()%>&page=<%=nowPage%>">
							<%=articleList.get(i).getTitle()%>
					</a></td>
					<td align="center">관리자</td>
					<td align="center"><%=articleList.get(i).getReg_date()%></td>
					<td align="center"><%=articleList.get(i).getReadcount()%></td>
				</tr>

				<%
					}
				%>
			</table>
		</div>
		<div class="w3-center w3-padding-32 w3-large">
			<div class="w3-bar">
				<section id="pageList">

					<%
						if (nowPage <= 1) {
					%>
					<a href="noticeList.bo?page=<%=nowPage - 1%>"
						class="w3-bar-item w3-button w3-hover-red">«</a>&nbsp;
					<%
						} else {
					%>
					<a href="noticeList.bo?page=<%=nowPage - 1%>"
						class="w3-bar-item w3-button w3-hover-red">«</a>&nbsp;
					<%
						}
					%>

					<%
						for (int i = startPage; i <= endPage; i++) {
								if (i == nowPage) {
					%>
					<a href="noticeList.bo?page=<%=i%>"
						class="w3-bar-item w3-button w3-hover-red">[<%=i%>]
					</a>&nbsp;
					<%
						} else {
					%>
					<a href="noticeList.bo?page=<%=i%>"
						class="w3-bar-item w3-button w3-hover-red">[<%=i%>]
					</a>&nbsp;
					<%
						}
					%>
					<%
						}
					%>

					<%
						if (nowPage >= maxPage) {
					%>
					<a href="noticeList.bo?page=<%=nowPage + 1%>"
						class="w3-bar-item w3-button w3-hover-red">»</a>
					<%
						} else {
					%>
					<a href="noticeList.bo?page=<%=nowPage + 1%>"
						class="w3-bar-item w3-button w3-hover-red">»</a>
					<%
						}
					%>
				</section>
				<%
					} else {
				%>
				<section id="emptyArea">등록된 글이 없습니다</section>
				<%
					}
				%>
			</div>
		</div>










		<!-- Pagination -->
		<!-- <div class="w3-center w3-padding-32 w3-large">
    	<div class="w3-bar"> -->
		<%-- 	<a href="boardList.bo?page=<%=nowPage - 1%>" class="w3-bar-item w3-button w3-hover-red">«</a>
      		<a href="boardList.bo?page=<%=i %>" class="w3-bar-item w3-red w3-button">[<%=i %>]</a>
      		<a href="boardList.bo?page=<%=i %>" class="w3-bar-item w3-button w3-hover-red">[<%=i %>]</a>
      		<a href="boardList.bo?page=<%=i %>" class="w3-bar-item w3-button w3-hover-red">[<%=i %>]</a>
      		<a href="boardList.bo?page=<%=i %>" class="w3-bar-item w3-button w3-hover-red">[<%=i %>]</a>
      		<a href="boardList.bo?page=<%=nowPage + 1 %>" class="w3-bar-item w3-button w3-hover-red">»</a>  --%>
		<!--  	</div>
  	</div> -->
		<!------------------------------ 메인 내용 ---------------------------------->



		<!--------------- footer ---------------->
		<jsp:include page="../../inc/footer.jsp" />
		<!--------------- footer ---------------->

		<!-- End page content -->
	</div>
</body>
</html>
