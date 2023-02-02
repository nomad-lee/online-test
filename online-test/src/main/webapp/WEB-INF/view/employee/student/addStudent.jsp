<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Add Student</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/employee/student/addStudent">
		<table border="1">
			<tr>
				<td>studentId</td>
				<td><input type="text" name="studentId"></td>
			</tr>
			<tr>
				<td>studentPw</td>
				<td><input type="text" name="studentPw"></td>
			</tr>
			<tr>
				<td>studentName</td>
				<td><input type="text" name="studentName"></td>
			</tr>	
		</table>
		<button type="submit">학생추가</button>
	</form>
</body>
</html>