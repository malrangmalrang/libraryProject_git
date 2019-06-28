<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="${pageContext.request.contextPath}/css/layout.css"
	rel="stylesheet" type="text/css" media="all">
<script type="text/javascript">
	function emailFind() {
		//새창열기
		window.open("emailFind.jsp", "winname", "width=500,height=300");
	}
</script>


</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String email = (String) session.getAttribute("email");
		if (email != null) {
	%>
	<script type="text/javascript">
		opener.location.reload();
		self.close();
	</script>
	<%
		}
	%>
	<form action="loginPro.me" method="post" id="login">
		<div id="logincss">
			<h2>로그인</h2>
			<table>
				<tr>
					<th><label>E-mail</label></th>
					<td><input type="text" name="email" class="input_box"></td>
					<td rowspan="2" class="logintd"><input type="submit" value="로그인" class="loginbtnsb"></td>
				</tr>
				<tr>
					<th><label>Password</label></th>
					<td><input type="password" name="password" class="input_box"></td>
				</tr>
			</table>
			<div id="loginBtn">
				<input type="button" value="아이디 찾기" onclick="emailFind()"
					class="btn"> <input type="button" value="비밀번호 찾기"
					class="btn">
			</div>
		</div>
	</form>

</body>
</html>