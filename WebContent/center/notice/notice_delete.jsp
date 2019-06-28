<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// BoardFronController 에서 파라미터 읽어와서 setAttribute() 로 전달한 경우
// 	int board_num = (Integer)request.getAttribute("board_num");
// 	String nowPage = (String)request.getAttribute("page");

// BoardFrontController 에서 파라미터를 읽지 않고, view 페이지에서 바로 가져올 경우
	int no= Integer.parseInt(request.getParameter("no"));
	String nowPage = request.getParameter("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이티윌 도서관</title>
<style>
	#passForm {
		width: 400px;
		margin: auto;
		border: 1px solid orange;
	}
</style>
</head>
<body>
	<!-- 공지사항 글 삭제 -->
	<section id="passForm">
		<form action="noticeDeletePro.bo?no=<%=no%>" name="deleteForm" method="post">
			<input type="hidden" name="page" value="<%=nowPage %>" />
			<table>
				<!-- <tr>
					<td><label>글 비밀번호 : </label></td>
					<td><input type="password" name="notice_pass"></td>
				</tr> -->
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>










