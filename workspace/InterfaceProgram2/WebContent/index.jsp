<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Home Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style>
* {
	padding: 0;
	margin: 0;
	box-sizing: border-box;
}

.wrap {
	width: 1500px;
	padding: 10px;
	margin: 0 auto;
	display: flex;
}

.input_area {
	width: 300px;
	padding: 10px;
}

.list_area {
	width: 1200px;
	padding: 10px;
}

.list_area table {
	width: 100%;
	border-collapse: collapse;
}

.list_area table td {
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
	padding: 3px 5px;
	text-align: center;
}
</style>

<script>
	$(document).ready(function(){
		fnMemberList();
	});
	
	function fnMemberList() {
		$.ajax({
			url: 'memberList.do', 			// 요청 경로
			type: 'get',					// 목록을 받아오는 방식
			dataType: 'json',				// 받아 오는 데이터의 타입
			success: function (members) {	// MemberListService에서 List목록을 members로 지정 했음 정상적으로 받아 왔을 때
				$.each(member, function (i, member) {
					
				})
			}
		});
	}// fnMemberList
	
</script>
</head>
<body>
	<div class="wrap">
		<div class="input_area">
			<form id="f" action="">
				<input type="text" id="no" name="no" placeholder="회원번호(6자리)"> <br>
				<input type="text" id="name" name="name" placeholder="회원명"> <br>
				<input type="text" id="age" name="age" placeholder="나이"> <br>
				<input type="text" id="birthDay" name="birthDay" placeholder="생일"> <br>
				<input type="button" id="insert_btn" value="등록하기"> 
			</form>
		</div>
		<div class="list_area">
			<table>
				<thead>
					<tr>
						<td>회원번호</td>
						<td>회원명</td>
						<td>나이</td>
						<td>생일</td>
						<td>가입일</td>
						<td>비고</td>
					</tr>
				</thead>
				<tbody id="member_list">

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>