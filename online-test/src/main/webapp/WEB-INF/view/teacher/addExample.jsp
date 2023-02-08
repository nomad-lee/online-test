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
	
	<h1>Add Example</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/teacher/addExample">
		<table border="1">
			<input type="hidden" name="questionNo" value="${questionNo}" readonly>
			<tr>
				<td>exampleIdx</td>
				<td><input type="text" name="exampleIdx"></td>
			</tr>
			<tr>
				<td>exampleTitle</td>
				<td><input type="text" name="exampleTitle"></td>
			</tr>
			<tr>
				<td>exampleOx</td>
				<td><input type="date" name="exampleOx"></td>
			</tr>	
		</table>
		<button type="submit">선택지추가</button>
	</form>
</body>
</html>