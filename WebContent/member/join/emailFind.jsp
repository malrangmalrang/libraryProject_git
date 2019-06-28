<%@page import="java.sql.Connection"%>
<%@page import="static db.JdbcUtil.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>이메일 찾기</title>
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

	<form action="emailFind.me" id="join" method="post" name="fr">
	
	<h2>이름 입력 : </h2> <input type="text" value="이름입력" class="join-input" name="name" style="width:50%"> 
	<h2>전화번호 입력 :</h2> <input type="text" value="전화번호입력" class="join-input" name="phone" style="width:150px;  height: 48px;">
	
	<input type="submit" value="이메일찾기" class="join-button" >
	</form>



	</div>
</body>
</html>