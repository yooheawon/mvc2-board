<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 페이지 입니다.</h1>
	   <form method="post" action="loginController">
      <label for="member_id">아이디</label>
      <input type="text" name="member_id" id="member_id">
      <label for="userPw">비밀번호</label>
      <input type="password" name="member_pw" id="member_pw">
      <button type="submit"> 로그인 하기 </button>
   	  </form>
   	  <a href="${pageContext.request.contextPath}/addMember">회원가입</a>
</body>
</html>