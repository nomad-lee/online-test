<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath}/employee/empList">사원관리</a>
	<!-- 등록시 ID체크(employee + teacher + student) -->
	<a href="${pageContext.request.contextPath}/employee/teacher/teacherList">강사관리</a>
	<!-- 강사목록, 강사삭제 -->
	<a href="${pageContext.request.contextPath}/employee/student/studentList">학생관리</a>
	<!-- 학생목록, 학생삭제 -->
	<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath}/employee/modifyEmpPw">비밀번호수정</a>
</div>