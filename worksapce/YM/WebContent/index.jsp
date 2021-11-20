<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="css/index960.css">
</head>
<body>

	<header>

			<div class="out_and_search">
				<a href="loginView.do">로그인</a> <label for="search">상품검색</label> <input
					type="text" class="search_box" id="search" name="search"
					placeholder="Search..">
			</div>

		<!-- 로고 -->
		<h1 class="logo">
			<a href="#"></a>
		</h1>

		<!-- 메뉴바 -->
		<nav class="menu">
			<a href="#">베스트</a> <a href="#">세일중</a> <a href="#">기획전</a> <a
				href="#">이벤트</a> <a href="#">스토어</a>
		</nav>
	</header>

	<section>

		<div class="item">
			<h2>건조기</h2>
			<img src="./images/product-1.jpg" alt="건조기">
			<ul>
				<li>트롬의류건조기</li>
				<li>히트펌프식</li>
				<li>16kg</li>
				<li>모던스테인레스</li>
				<li>1,669,000원</li>
			</ul>
			<div class="btn">
				<button>바로구매</button>
				<button>장바구니</button>
			</div>
		</div>
		<div class="item">
			<h2>일반세탁기</h2>
			<img src="./images/product-2.jpg" alt="일반세탁기">
			<ul>
				<li>통돌이</li>
				<li>DD모터</li>
				<li>16kg</li>
				<li>미드프리실버</li>
				<li>574,500원</li>
			</ul>
			<div class="btn">
				<button>바로구매</button>
				<button>장바구니</button>
			</div>
		</div>

		<div class="item">
			<h2>의류관리기</h2>
			<img src="./images/product-3.jpg" alt="의류관리기">
			<ul>
				<li>트롬스타일러</li>
				<li>히트펌프식</li>
				<li>상의5벌,하의1벌</li>
				<li>린넨블랙</li>
				<li>1,504,000원</li>
			</ul>
			<div class="btn">
				<button>바로구매</button>
				<button>장바구니</button>
			</div>
		</div>

		<div class="item">
			<h2>드림세탁기</h2>
			<img src="./images/product-4.jpg" alt="드림세탁기">
			<ul>
				<li>트윈워시</li>
				<li>DD모터</li>
				<li>21kg+4kg</li>
				<li>모던스테인레스</li>
				<li>1,546,000원</li>
			</ul>
			<div class="btn">
				<button>바로구매</button>
				<button>장바구니</button>
			</div>
		</div>
	</section>

	<footer>
		<divc class="crop"> <span>|</span>
		<a href="#">회사소개</a>
		<span>|</span> <a href="#">이용약관</a>
		<span>|</span> <a href="#">전자금융거래약관</a>
		<span>|</span> <a href="#">개인정보처리방침</a>
		<span>|</span> <a href="#">고객센터</a>
		<span>|</span>
		</div>
	</footer>

</body>
</html>