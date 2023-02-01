<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Add Teacher</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/teacher/addTeacher">
		<table border="1">
			<tr>
				<td>teacherId</td>
				<td><input type="text" name="teacherId"></td>
			</tr>
			<tr>
				<td>teacherPw</td>
				<td><input type="text" name="teacherPw"></td>
			</tr>
			<tr>
				<td>teacherName</td>
				<td><input type="text" name="teacherName"></td>
			</tr>	
		</table>
		<button type="submit">강사추가</button>
	</form>
</body>
</html>