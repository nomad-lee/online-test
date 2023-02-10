<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- teacherMenu include -->
	<div>
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"></c:import>
	</div>
	
	<h1>test</h1>
	<table border="1">
		<tr>
			<c:forEach var="q" items="${question}">
				<tr>
					<th>${q.questionIdx}. ${q.questionTitle}</th>
				</tr>
				<c:forEach var="e" items="${example}">
					<tr>
						<td>
	      					<input type="radio" name="exampleNo" value="${e.exampleNo}">
	      					<label for="exampleNo">${e.exampleIdx}. ${e.exampleTitle}</label>
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tr>
	</table>
</body>
</html>