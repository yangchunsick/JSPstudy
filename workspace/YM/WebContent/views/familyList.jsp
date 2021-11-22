<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yangmily</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<style>
body {
	background-color: black;
	color: white;
}

.wrap {
	width: 800px;
	margin: 0 auto;
}

.title {
	text-align: center;
	color: white;
}

table {
	width: 100%;
	border-collapse: collapse;
}

td {
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
	text-align: center;
	padding: 3px 5px;
}

.add_btn {
	width: 100px;
	height: 50px;
	margin: 20px 350px;
	font-size: 15px;
}
</style>
<script>
	$(document).ready(function() {
		/* 수정 버튼 */
		$('.update_btn').on('click', function(){
			location.href = '/YM/familyDetail.do?num=' + $(this).data('num');
			
		});
		
		/* 수정 버튼 */
		$('.delete_btn').on('click', function(){
			location.href = '/YM/familyDelete.do?num=' + $(this).data('num');
			
		});

	});
</script>
</head>
<body>

	<div class="wrap">
		<h1 class="title">Yangmily 전체 조직원 목록</h1>
		<div>
			<input class="add_btn"
				onclick="location.href = '/YM/familyInsertForm.do'" type="button"
				value="조직원 추가">
		</div>
		<table>
			<caption>전체 조직원 수 : ${totalCount}명</caption>
			<thead>
				<tr>
					<td>번호</td>
					<td>이름</td>
					<td>부서</td>
					<td>생년월일</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${totalCount eq 0}">
					<tr>
						<td colspan="4"><strong>등록된 Yangmily 조직원이 없습니다.</strong></td>
					</tr>
				</c:if>
				<c:if test="${totalCount gt 0}">
					<c:forEach items="${list}" var="family">
						<tr>
							<td>${family.num}</td>
							<td>${family.name}</td>
							<td>${family.depart}</td>
							<td>${family.birthday}</td>
							<td>
								<input class="update_btn" data-num="${family.num}" type="button" value="수정">
								<input class="delete_btn" data-num="${family.num}" type="button" value="삭제">
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

	</div>
</body>
</html>