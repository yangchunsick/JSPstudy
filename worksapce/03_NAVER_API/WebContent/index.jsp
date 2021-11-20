<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- <%=request.getContextPath()%> contextpath 확인 해보기--%>

	<h1>로그인으로 이동합니다.</h1>
	<a href="<%=request.getContextPath()%>/LoginServlet">로그인하러 가기</a>
	<br> <%-- ↑ 동일한 코드임 ↓ --%>
	<a href="/naver.api/LoginServlet">로그인하러 가기</a>
	<%-- a태그를 통해 이동하게 되면 get방식으로 넘어간다. --%>


</body>
</html>