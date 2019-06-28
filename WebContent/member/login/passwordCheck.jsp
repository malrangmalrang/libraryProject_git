<%@page import="java.sql.Connection"%>
<%@page import="static db.JdbcUtil.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/book_list.css">
<!------------------------ append css ------------------------------>

<script type="text/javascript">
	function ok() {
		window.close();
	}
</script>
</head>
<body> 
	<div class="w3-container w3-center">
	<%
		//String =password파라미터 가져오기
		String password = request.getParameter("password");
	%>
		<h2>PASSWORD값</h2>
		<input type="text" value="<%=password%>" class="join-input" style="width:50%">
		<input type="button" value="확인완료" onclick="ok()" class="join-button" style="width:150px;  height: 48px;">




	</div>
</body>
</html>