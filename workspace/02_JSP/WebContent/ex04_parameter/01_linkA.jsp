<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 1. <a> 태그로 파라미터 보내기 --%>
	<a href="/JSP/ex04_parameter/01_linkB.jsp">이동</a>
	<br>
	<a href="01_linkB.jsp">이동</a>
	<br>
	<a href="01_linkB.jsp?name=민경태&age=44">전송</a> <%-- 강제로 실어서 보낸다 --%>
	
	
	<%-- 2. location 객체로 파라미터 보내기 --%>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$('.btn_send').on('click', function(){
				let cmd = $(this).data('cmd');
				location.href = '01_linkB.jsp?cmd=' + cmd;
			});
		});
	</script>
	<input type="button" value="시간" data-cmd="time" class="btn_send">
	<input type="button" value="날짜" data-cmd="date" class="btn_send">

</body>
</html>