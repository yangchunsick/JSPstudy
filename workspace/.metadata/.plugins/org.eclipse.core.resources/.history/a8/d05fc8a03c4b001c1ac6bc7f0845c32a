<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Home Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script>
	
</script>
</head>
<body>

	<header>
		<h1>WEB PAGE TITLE</h1>
		<nav>
			<ul>
				<li><a href="list.notice">공지사항</a></li>
			</ul>
		</nav>
		<c:if test="${loginUser != null}">
			<div>
				<h3>${loginUser.name}님 반갑습니다.<input type="button" value="logout" onclick="location.href='logout.member'"></h3>
			</div>
		</c:if>
	</header>

	<section>

			<!-- Session에 loginUser가 없으면 로그인이 안된거임 -->
			<!-- 그래서 if문을 사용해서 null이면 로그인 박스를 보여주고 -->
			<!-- Session에 loginUser가 null이 아니면  로그인을 했다는 화면을 보여주는 것임-->
		<c:if test="${loginUser == null }">
			<div>
				<form action="login.member" method="post">
					<input type="text" name="id" placeholder="ID"><br> <input
						type="password" name="pw" placeholder="PW"><br>
					<br>
					<button>login</button>
					<a href="joinForm.member">회원가입</a>
				</form>
			</div>
		</c:if>
	</section>


</body>
</html>