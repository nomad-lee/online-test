<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	<h1>Paper List</h1>
	<table border="1">
		<tr>
			<th>testTitle</th>
			<th>testDate</th>
			<th>시험응시/점수확인</th>
		</tr>
		<c:forEach var="t" items="${list}">
			<tr>
				<td>
					${t.testTitle}
				</td>
				<td>${t.testDate}</td>
				<c:set var="date" value="<%=new java.util.Date()%>" />
				<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" var="today"/>
				<td>
					<c:choose>
						<c:when test="${today eq t.testDate}">
							<a type="button" href="${pageContext.request.contextPath}/student/testOne?testNo=${t.testNo}">
								응시
							</a>	
						</c:when>
						<c:when test="${today < t.testDate}">
							<a type="button" href="${pageContext.request.contextPath}/student/paperOne?testNo=${t.testNo}">
								점수확인
							</a>
						</c:when>
						<c:otherwise>
							시험대기
						</c:otherwise>
					</c:choose>					
				</td>
			</tr>
		</c:forEach>
	</table>
	<form method="get" action="${pageContext.request.contextPath}/student/paperList">
		<input type="text" name="searchWord">
		<button type="submit">시험검색</button>
	</form>
	<!-- 페이징 -->
	<div>
		<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=1&searchWord=${searchWord}">처음</a>
		<c:if test="${currentPage > 10}">
			<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=${beginPaging-10}&searchWord=${searchWord}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${beginPaging}" end="${endPaging}">
			<c:if test="${currentPage eq i}">
				<a style="color: red" href="${pageContext.request.contextPath}/teacher/testList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
			</c:if>
			<c:if test="${currentPage ne i}">
				<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
			</c:if>
		</c:forEach>
		<c:if test="${endPaging < lastPage}">
			<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=${beginPaging+10}&searchWord=${searchWord}">다음</a>
		</c:if>		
		<c:if test="${currentPage <= lastPage}">
			<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=${lastPage}&searchWord=${searchWord}">마지막</a>
		</c:if>
	</div>
</body>
</html>