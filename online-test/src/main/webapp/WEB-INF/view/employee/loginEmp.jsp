<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 로그인 전 -->
	<c:if test="${loginEmp == null}">
		<h1>로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/loginEmp">
			<table border="1">
				<tr>
					<td>empId</td>
					<td><input type="text" name="empId"></td>
				</tr>
				<tr>
					<td>empPw</td>
					<td><input type="password" name="empPw"></td>
				</tr>
			</table>
			<button type="submit">로그인</button>
		</form>
	</c:if>
	
	<!-- 로그인 상태 -->
	<c:if test="${loginEmp != null}">
		${loginEmp.empName}님 반갑습니다
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
	</c:if>
</body>
</html>