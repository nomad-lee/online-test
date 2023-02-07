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
	<c:if test="${loginEmp == null && loginStudent == null && loginTeacher == null}">
		<h1>사원 로그인</h1>
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
		<h1>학생 로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/loginStudent">
			<table border="1">
				<tr>
					<td>studentId</td>
					<td><input type="text" name="studentId"></td>
				</tr>
				<tr>
					<td>studentpPw</td>
					<td><input type="password" name="studentpPw"></td>
				</tr>
			</table>
			<button type="submit">로그인</button>
		</form>
		<h1>강사 로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/loginTeacher">
			<table border="1">
				<tr>
					<td>teacherId</td>
					<td><input type="text" name="teacherId"></td>
				</tr>
				<tr>
					<td>teacherPw</td>
					<td><input type="password" name="teacherPw"></td>
				</tr>
			</table>
			<button type="submit">로그인</button>
		</form>
	</c:if>
	
	<!-- 로그인 상태 -->
	<c:if test="${loginEmp != null}">
		${loginEmp.empName}님 반갑습니다
	</c:if>
	<c:if test="${loginStudent != null}">
		${loginStudent.studentName}님 반갑습니다
	</c:if>
	<c:if test="${loginTeacher != null}">
		${loginTeacher.teacherName}님 반갑습니다
	</c:if>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
</body>
</html>