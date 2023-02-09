<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- studentMenu include -->
	<div>
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"></c:import>
	</div>
	
	<h1>${loginStudent.studentName}님 비밀번호 수정</h1>
	<form method="post" action="${pageContext.request.contextPath}/student/modifyStudentPw">
		<table border="1">
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="oldPw"></td>
			</tr>
			<tr>
				<td>새로운 비밀번호</td>
				<td><input type="password" name="newPw"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>