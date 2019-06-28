<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

</head>
<body>

	
	<form action="./passwordFindAction.me" id="login" method="post" name="fr">
	
	<fieldset class="fs">
    <legend>비밀번호찾기</legend>
	
	이메일 입력 :<input type="text" placeholder="이메일입력" name="email"><br>
	전화번호 입력 :<input type="text" placeholder="전화번호입력" name="phone"><br>
	
	<input type="submit" value="비밀번호찾기" >
	</form>
	
</body>
</html>