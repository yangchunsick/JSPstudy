<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- 평균 --%>
	<c:set var="avg" value="${(param.kor + param.eng + param.mat) / 3}" />

	<%-- 학점 --%>
	<c:set var="kor" value="${param.kor}"/>
	<c:set var="eng" value="${param.eng}"/>
	<c:set var="mat" value="${param.mat}"/>
	
	
		<c:choose>
			<c:when test="${agv - 90 >= 0}">평균 : ${avg}A</c:when>
			<c:when test="${agv - 80 >= 0}">평균 : ${avg}B</c:when>
			<c:when test="${agv - 70 >= 0}">평균 : ${avg}C</c:when>
			<c:when test="${agv - 60 >= 0}">평균 : ${avg}D</c:when>
			<c:otherwise>평균 : ${avg} F</c:otherwise>
		</c:choose>
	

</body>
</html>