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
					$('<tr>')							// <tr> 태그를 추가
					.append($('<td>').text(member.no))	// <td> 태그를 추가 td태그 안에는 member.no가 넘어 올 예정
					.append($('<td>').text(member.name))// 						   member.name 넘어 올 예정
					.append($('<td>').text(member.age))	// 						   
					.append($('<td>').text(member.birthDay))
					.append($('<td>').text(member.regDate))
					.append($('<td>').html('<input type="button" value="삭제" class="delete_btn" data-no="' + member.no + '">')) // 해당 번호를 가지고 있는 회원 목록을 지우기 위해 data 속성을 사용 회원의 번호는 member.no로 가져옴
					.appendTo('#member_list');	// 위에 tr태그와 td 태그들을 member_list 안에 넣었음
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
				url : '/InterfaceProgram2/insert.do',		// 요청 경로
				type : 'post',								// 전송 방식은 POST로 숨겨서 보냄
				data : $('#f').serialize(), 				// form안에 입력한 param들을 모두 보낸다..!
				dataType : 'json',							// 데이터 타입은 JSON 방식을 이용
				success : function(obj) {					// 성공 했을 경우 obj에 저장 (?)
					alert('회원 등록이 성공했습니다.');			// 성공 했을 경우 성공 알림 메시지를 띄움
					fnMemberList();							// 회원을 추가 했으니 새로 목록을 띄움
					$('#no').val('');						// 회원을 정상 적으로 추가 했을 때 input 태그 안에 있는 값들이 남아 있길래 val('') 비워 줌
					$('#name').val('');
					$('#age').val('');
					$('#birthDay').val('');
				},
				error: function(xhr) {						// 회원 추가가 실패 했을 경우
					switch (xhr.status) {					// 예외 처리를 하나씩 잡아 줬음
					case 2001: 								// 예를 들어 나이는 정수로 입력을 해야되는데 문자열로 입력을 했다던가
					case 2003:								// 나이에 대한 범위를 벗어나서 입력을 했다던가
					case 2002: 								// 입력란을 모두 충족 시키지 못 했다던가
					case 2004:								// 일어 날 수 있는 오류들을 Exception 예외 처리로 잡아 줬음
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