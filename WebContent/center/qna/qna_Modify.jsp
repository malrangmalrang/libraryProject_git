<%@page import="vo.BoardBean"%>
<%@page import="vo.MemberBean"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<%
	MemberBean memberBean = (MemberBean) request.getAttribute("memberBean");
	BoardBean article = (BoardBean) request.getAttribute("article");
	String email = memberBean.getEmail();
	String name = memberBean.getName();
	int nowPage = Integer.parseInt(request.getAttribute("page")+"");
%>
<script type="text/javascript">
//<br>을 엔터로
$(document).ready(function(){
	var str = document.getElementById("board_content_original").value;
	str = str.split("<br>").join("\r\n")
	document.getElementById("board_content").value = str;
});

//작성시 엔터키 가능
function enterToBr(){
	var str = document.getElementById("board_content").value;
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br>');
	document.getElementById("board_content_original").value = str;
// 	str = str.replace('\r\n', '<br>');
}
</script>
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
					<b>Q&A</b>
				</h1>
			</div>
		</header>

		<br> <br>

		<div class="w3-container">

			<form action="qnaModifyPro.bo" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="email" value="<%=memberBean.getEmail()%>">
				<input type="hidden" name="page" value="<%=nowPage%>">
				<input type="hidden" name="board_num" value="<%=article.getNo()%>">
				<input type="hidden" name="member_no"
					value="<%=article.getMember_no()%>"> <input type="hidden"
					name="nowUser_no" value="<%=article.getMember_no()%>">

				<table>
					<tr>
						<td class="td_left"><label for="board_name">이름</label></td>
						<td class="td_right" width="100px;"><input type="text"
							name="board_name" required="required" readonly="readonly"
							value="<%=memberBean.getName()%>" width="150px;" /></td>
<!-- 						<td width="150px;"><label for="wantAlert">이메일로답변받기</label></td> -->
<!-- 						<td> -->
							<%
								if (article.getEmail_reply() == 1) {
							%>
<!-- 							<input type="radio" -->
<!-- 							id="email_reply_yes" value=1 name="email_reply" -->
<!-- 							style="display: inline; width: 50px;" checked="checked">네<input -->
<!-- 							type="radio" id="email_reply_no" value=0 name="email_reply" -->
<!-- 							style="display: inline; width: 50px;">아니오 -->
							<%
								} else {
							%>
<!-- 							<input type="radio" id="email_reply_yes" value=1 -->
<!-- 							name="email_reply" style="display: inline; width: 50px;">네<input -->
<!-- 							type="radio" id="email_reply_no" value=0 name="email_reply" -->
<!-- 							checked="checked" style="display: inline; width: 50px;">아니오 -->
							<%
								}
							%>
<!-- 						</td> -->
					</tr>
					<tr>
						<td class="td_left"><label for="board_subject">제목</label></td>
						<td class="td_right" colspan="3"><input type="text"
							name="board_subject" required="required"
							value="<%=article.getTitle()%>" /></td>
					</tr>
					<tr>
						<td class="td_left"><label for="board_content">내용</label></td>
						<td class="td_right" colspan="3"><textarea id="board_content"
								cols="40" rows="15" required="required"></textarea>
								<textarea id="board_content_original"
								name="board_content" hidden="hidden"><%=article.getContent()%></textarea></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td class="td_left"><label for="board_file">파일 첨부</label></td> -->
<!-- 						<td class="td_right" colspan="3"><input type="file" -->
<!-- 							name="board_file"/></td> -->
<!-- 					</tr> -->
				</table>
				<div>
					<input name="submit" onclick="enterToBr()" type="submit" value="수정완료">
					&nbsp; <input name="reset" type="button" value="취소" onclick="history.back()">
				</div>
			</form>
			<br>
		</div>
		<!------------------------------ 메인 내용 ---------------------------------->



		<!--------------- footer ---------------->
		<jsp:include page="../../inc/footer.jsp" />
		<!--------------- footer ---------------->

		<!-- End page content -->
	</div>
</body>
</html>
