<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.warp{
	width: 700px; height:200px;
	margin: 200px auto;
}
.msg{
	display: block;
	margin: 0 auto;
	padding: 10px;
	text-align: center;
}

</style>

<body>
 
	<script>
		setTimeout(function() {
			location.href = '/YM/familyList.do';
		}, 2000);
	</script>
 
	<div class="warp">
		<img alt="Yangmily" src="./img/Yangmily_logo.png">
		<h1 class="msg">Yangmily 관리 목록으로 이동합니다.</h1>
	</div>
</body>
</html>