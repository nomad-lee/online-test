<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- empMenu include -->
	<div>
		<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"></c:import>
	</div>
	
	<h1>Add Employee</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/employee/addEmp">
		<table border="1">
			<tr>
				<td>empId</td>
				<td><input type="text" name="empId"></td>
			</tr>
			<tr>
				<td>empPw</td>
				<td><input type="text" name="empPw"></td>
			</tr>
			<tr>
				<td>empName</td>
				<td><input type="text" name="empName"></td>
			</tr>	
		</table>
		<button type="submit">사원추가</button>
	</form>
</body>
</html>