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
	<h3>회원 정보</h3>
	<div>
		<a href="${pageContext.request.contextPath }"></a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>회원</th>
				<th>회원 이름</th>
				<th>주소</th>
				<th>상세주소</th>
				<th>가입 시간</th>
				<th>최종 수정 시간</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td>${memberLogin.memberId}</td>		
				<td>${memberLogin.memberName}</td>		
				<td>${memberLogin.memberAddr}</td>		
				<td>${memberLogin.memberDetailAddr}</td>		
				<td>${memberLogin.createDate}</td>		
				<td>${memberLogin.updateDate}</td>		
			</tr>
		</tbody>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/boardList">게시판 목록</a>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/updateMember">정보수정</a>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/removeMember">회원탈퇴</a>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/logoutController">로그아웃</a>
	</div>
</body>
</html>