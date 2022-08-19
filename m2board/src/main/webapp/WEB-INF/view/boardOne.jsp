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
	<h1>상세보기</h1>
	<table border="1">
		<c:forEach var="b" items="${list}">
			<tr>
				<td>번호</td>
				<td>${b.boardNo}</td>
			</tr>		
		
		
		
		
		</c:forEach>
	</table>
</body>
</html>