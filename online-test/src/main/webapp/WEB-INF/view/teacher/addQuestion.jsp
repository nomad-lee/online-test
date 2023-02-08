<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- teacherMenu include -->
	<div>
		<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp"></c:import>
	</div>
	
	<h1>Add Question</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/teacher/addQuestion">
		<table border="1">
			<input type="hidden" name="testNo" value="${testNo}" readonly>
			<tr>
				<td>questionIdx</td>
				<td><input type="text" name="questionIdx"></td>
			</tr>
			<tr>
				<td>questionTitle</td>
				<td><input type="text" name="questionTitle"></td>
			</tr>	
		</table>
		<button type="submit">문제추가</button>
	</form>
</body>
</html>