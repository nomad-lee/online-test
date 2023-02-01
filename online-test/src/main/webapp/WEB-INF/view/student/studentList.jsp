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
	
	<h1>Student List</h1>
	<a href="${pageContext.request.contextPath}/student/addStudent">학생등록</a>
	<table border="1">
		<tr>
			<th>studentId</th>
			<th>studentName</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.studentId}</td>
				<td>${e.studentName}</td>
				<td>
					<a href="${pageContext.request.contextPath}/student/removeStudent?studentNo=${e.studentNo}">
						삭제
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/student/studentList?currentPage=${currentPage-1}">이전</a>
		<a href="${pageContext.request.contextPath}/student/studentList?currentPage=${currentPage+1}">다음</a>
	</div>
</body>
</html>