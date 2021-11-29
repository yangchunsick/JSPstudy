<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>InterfaceProgram</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style>
	.wrap{
		width: 100%;
		
	}

	.form_box{
		display: inline-block;
		float: left;
		margin-right: 20px;
		
	}

	.list_box{
		display: inline-block;
	}
	table{
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		border-collapse: collapse;
	}
	td{
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		padding: 1px 30px;
	}
</style>

<script>
	$(document).ready(function(){
		fnMemberList();
		fnMemberInsert();
		fnMemberDelete();
	});
	
	function fnMemberList() {
		$.ajax({
			url: '/InterfaceProgram2/list.do', 			// 요청 경로
			type: 'get',								// 목록을 받아오는 방식
			dataType: 'json',							// 받아 오는 데이터의 타입
			success: function (members) {				// MemberListService에서 List목록을 members로 지정 했음 정상적으로 받아 왔을 때
				$('#member_list').empty();				// member_list를 한 번 비워줌
				$.each(members, function (i, member) {	// 받아 온 값들을
					$('<tr>')							
					.append($('<td>').text(member.no))
					.append($('<td>').text(member.name))
					.append($('<td>').text(member.age))
					.append($('<td>').text(member.birthDay))
					.append($('<td>').text(member.regDate))
					.append($('<td>').html('<input type="button" value="삭제" class="delete_btn" data-no="' + member.no + '">') )
					.appendTo('#member_list');
				});
			},
			error : function(xhr){
				alert('목록 가져오기 오류 발생');
			}
		});
	}// end fnMemberList
	
	function fnMemberInsert() {
		$('#insert_btn').on('click', function() {
			let regno = /^[0-9]{6}$/;
			if(!regno.test($('#no').val())){
				alert('회원 번호는 6자리 숫자 입니다.');
				return;
			}
			$.ajax({
				url : '/InterfaceProgram2/insert.do',
				type : 'post',
				data : $('#f').serialize(), // form안에 입력한 param들을 모두 보낸다..!
				dataType : 'json',
				success : function(obj) {
					alert('회원 등록이 성공했습니다.');
					fnMemberList();
					$('#no').val('');
					$('#name').val('');
					$('#age').val('');
					$('#birthDay').val('');
				},
				error: function(xhr) {
					switch (xhr.status) {
					case 2001: 
					case 2002: 
					case 2003:
					case 2004:
					case 2005:
						alert(xhr.responseText);
					}
				}
			}); // end $.ajax
		});
	} // end fnMemberInert
	
	function fnMemberDelete() {
		$('body').on('click', '.delete_btn', function() { 
			let delNo = $(this).data('no');
			if(confirm('회원번호' + delNo + '회원의 번호를 삭제 할까요?')){
				$.ajax({
					url: '/InterfaceProgram2/delete.do',
					type: 'get',
					data: 'no='+ delNo,
					dataType: 'json',
					success: function (obj) {
						if(obj.result){
							alert('회원번호'+ delNo +'회원 정보가 삭제되었습니다.');
							fnMemberList();
						}else{
							alert('삭제 실패1');
						}
					},
					error: function() {
						alert('삭제 실패2');
					}
				});
			}
			
		}); 
	} // end fnMemberDelete
	
	
	
</script>
</head>
<body>

	<div class="wrap">
		<div class="form_box">
			<form id="f">
				<input type="text" id="no" name="no" placeholder="회원번호(6자리)"> <br>
				<input type="text" id="name" name="name" placeholder="회원명"> <br>
				<input type="text" id="age" name="age" placeholder="나이"> <br>
				<input type="text" id="birthDay" name="birthDay" placeholder="생일"> <br>
				<input type="button" id="insert_btn" value="등록하기"> 
			</form>
		</div>
	
		<div class="list_box">
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