<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>

	<%
		/*
			쿠키 이름 : address
			유호 기간 : 7일
			쿠키 값   : 서울시 마포구 서강로 (공백이 있으면 안 됨.)
		*/
		
		// 공백, 콤마(,) 등 쿠키에서 사용할 수 없는 문자들은
		// 인코딩처리를 하여 사용한다.
		// URLEncoder.encode(값, 인코딩);
	
		// 1. 쿠키 만들기	
		Cookie cookie = new Cookie("address", URLEncoder.encode("서울 마포구 서강로", "UTF-8"));
	
		// 2. 쿠키 유효기간
		cookie.setMaxAge(7 * 24 * 60 * 60);	
		
		// 3. 쿠키 저장
		// 사용자에게 돌려줘야 하기 때문에 response를 사용한다.
		response.addCookie(cookie);	
		
	%>
	<%-- 인코딩 된 값은 디코딩한다 인코딩의 반댓말 --%>
	<%-- URLDecoder.decode(값, 인코딩); --%>
	<h1>이름 : <%=cookie.getName()%></h1>
	<h1>유효 기간 : <%=cookie.getMaxAge()%></h1>
	<h1>쿠키 값 : <%=URLDecoder.decode(cookie.getValue(), "UTF-8")%></h1>






</body>
</html>