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
	
	<h1>Example List</h1>
	<a href="${pageContext.request.contextPath}/teacher/addExample?questionNo=${list[0].questionNo}">선택지등록</a>
	<table border="1">
		<tr>
			<th>exampleIdx</th>
			<th>exampleTitle</th>
			<th>exampleOx</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.exampleIdx}</td>
				<td>${e.exampleTitle}</td>
				<td>${e.exampleOx}</td>
				<td>
					<a href="${pageContext.request.contextPath}/teacher/removeExample?exampleNo=${e.exampleNo}">
						삭제
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<form method="get" action="${pageContext.request.contextPath}/teacher/exampleList">
		<input type="text" name="searchWord">
		<button type="submit">시험검색</button>
	</form>
	<!-- 페이징 -->
	<div>
		<a href="${pageContext.request.contextPath}/teacher/exampleList?currentPage=1&searchWord=${searchWord}">처음</a>
		<c:if test="${currentPage > 10}">
			<a href="${pageContext.request.contextPath}/teacher/exampleList?currentPage=${beginPaging-10}&searchWord=${searchWord}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${beginPaging}" end="${endPaging}">
			<c:if test="${currentPage eq i}">
				<a style="color: red" href="${pageContext.request.contextPath}/teacher/exampleList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
			</c:if>
			<c:if test="${currentPage ne i}">
				<a href="${pageContext.request.contextPath}/teacher/exampleList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
			</c:if>
		</c:forEach>
		<c:if test="${endPaging < lastPage}">
			<a href="${pageContext.request.contextPath}/teacher/exampleList?currentPage=${beginPaging+10}&searchWord=${searchWord}">다음</a>
		</c:if>		
		<c:if test="${currentPage <= lastPage}">
			<a href="${pageContext.request.contextPath}/teacher/exampleList?currentPage=${lastPage}&searchWord=${searchWord}">마지막</a>
		</c:if>
	</div>
</body>
</html>