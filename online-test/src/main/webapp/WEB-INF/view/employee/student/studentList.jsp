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
	<a href="${pageContext.request.contextPath}/employee/student/addStudent">학생등록</a>
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
					<a href="${pageContext.request.contextPath}/employee/student/removeStudent?studentNo=${e.studentNo}">
						삭제
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<form method="get" action="${pageContext.request.contextPath}/employee/student/studentList">
		<input type="text" name="searchWord">
		<button type="submit">이름검색</button>
	</form>
	<div>
		<c:if test="${currentPage > 0 and currentPage <= endPage}">
			<a href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${1}&searchWord=${searchWord}">처음으로</a>
			<a href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a>				      
			<c:if test="${endPage - currentPage <= 0 and endPage > 4}">
				<a class="page-link" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage-4}&searchWord=${searchWord}">${currentPage-4}</a>
			</c:if>		    	
			<c:if test="${endPage - currentPage <= 1 and endPage > 3}">
		    	<a class="page-link" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage-3}&searchWord=${searchWord}">${currentPage-3}</a>
	    	</c:if>		    	
			<c:if test="${currentPage >= 3}">
				<a class="page-link" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage-2}&searchWord=${searchWord}">${currentPage-2}</a>
	    	</c:if>		    	
			<c:if test="${currentPage >= 2}">
				<a class="page-link" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage-1}&searchWord=${searchWord}">${currentPage-1}</a>
	    	</c:if>
			<c:if test="${currentPage > 0}">
				<a class="page-link" style="color: red" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage}&searchWord=${searchWord}">${currentPage}</a>
	    	</c:if>
			<c:if test="${endPage - currentPage >= 1}">
				<a class="page-link" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage+1}&searchWord=${searchWord}">${currentPage+1}</a>
	    	</c:if>
			<c:if test="${endPage - currentPage >= 2}">
				<a class="page-link" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage+2}&searchWord=${searchWord}">${currentPage+2}</a>
	    	</c:if>
			<c:if test="${currentPage <= 2 and endPage > 3}">
	   	 		<a class="page-link" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage+3}&searchWord=${searchWord}">${currentPage+3}</a>
	    	</c:if>
			<c:if test="${currentPage <= 1 and endPage > 4}">
	    		<a class="page-link" href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage+4}&searchWord=${searchWord}">${currentPage+4}</a>
    		</c:if>
			<a href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a>
			<a href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${endPage}&searchWord=${searchWord}">끝으로</a>
		</c:if>
	</div>
</body>
</html>