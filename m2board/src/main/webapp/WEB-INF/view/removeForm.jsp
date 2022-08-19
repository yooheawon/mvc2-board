<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원탈퇴 페이지 입니다</h1>
	<h3>(탈퇴를 원하시면 비밀번호를 다시 한 번 입력해 주세요)</h3>
	<form action="removeMember" method="post">
	<input type = "hidden" name="member_id" value=${memberLogin.memberId}>
	<label for="member_pw">비밀번호</label>
	<input type="password" id="member_pw"  name="member_pw">
	<button type ="submit">탈퇴</button>
	</form>
</body>
</html>