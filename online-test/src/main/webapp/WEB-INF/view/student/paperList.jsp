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
						<c:when test="${today > t.testDate}">
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
</body>
</html>