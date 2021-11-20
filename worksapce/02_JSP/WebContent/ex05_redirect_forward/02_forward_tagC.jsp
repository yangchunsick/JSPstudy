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
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");		// 문자열을 받아 올 때
		int age = Integer.parseInt(request.getParameter("age"));	// int로 받아 올 때
		String b_day = request.getParameter("b_day");
		
		// 정수는 문자열(String)으로 인식하여 받아 올 수 있지만
		// 문자열은 정수(int)로 받을 수 없다.
	%>
	<h1>이름 : <%=name%></h1>
	<h1>나이 : <%=age%></h1>
	<h1>생일 : <%=b_day%></h1>

</body>
</html>