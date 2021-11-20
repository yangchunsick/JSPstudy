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

	<%-- forward 태그 --%>
	<%-- 기존 요청(request) + 새로운 파라미터 추가 가능 --%>
	
	<%request.setCharacterEncoding("UTF-8");%>
	<jsp:forward page="02_forward_tagC.jsp">
		<jsp:param value="44" name="age" />
		<jsp:param value="1월22일" name="b_day"/>
	</jsp:forward>
	
	<%-- B에서 b_day를 추가 했다.
		 문자열로 추가 했기 때문에 request.setCharacterEncoding("UTF-8")로 인코딩을 해줘야함
		 하지 않으면 문자열을 인식을 해주ㅠ지 못함 ㅜㅡㅜ
	 --%>
	
	

</body>
</html>