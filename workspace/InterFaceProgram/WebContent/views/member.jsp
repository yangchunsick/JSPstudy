<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<title>Document</title>
<style type="text/css">
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
		fnSelectMemberList();
		fnInsertMember();
		
	});
	
	function fnSelectMemberList() {
		$.ajax({
			url : '/IFP/selectMemberList.do', // 요청
			type: 'get', // 타입
		//  data: 		 // 서버로 보내는 파라미터
			dataType : 'json',
			success : function(arr){	// 왜 arr를 적었는지 어떤게 왔는지 알고 싶으면 arr로 가서 보면 됌 
				// 기존 목록 지우기
				$('#member_list').empty();
				// $.each(배열, function(인덱스, 요소) {});
				$.each(arr, function(i, member) {
					$('<tr>')
					.append($('<td>').text(member.no))
					.append($('<td>').text(member.name))
					.append($('<td>').text(member.age))
					.append($('<td>').text(member.birthday))
					.append($('<td>').text(member.regdate))
					.append($('<td>').html('<input type="hidden" name="no" value="'+ member.no +'"><input type="button" value="삭제" class="delete_btn">'))
					.appendTo('#member_list');
				});
			},
			error: function(){
				// 에러 처리할게 없어서 기본 구상만 채웠음.
				alert('실패');
			}
		});
	} //end
	
	function fnInsertMember() {
	    $('#insert_btn').on('click', function(){
	        if($('#no').val().length != 6){
	            alert('게시글 번호는 6자리 입니다.');
	            return;
	        }
	        $.ajax({
	            url: '/IFP/insertMember.do', // 요청
	            type: 'post',
	            data: $('#f').serialize(), // 폼의 모든 요소를 파라미터로 보냄.
	            dataType: 'json', 		// 받아오는 건 json 타입의 데이터
	            success: function(obj) {
	                alert(obj.result);
	                fnSelectMemberList();
	            },
	            error: function(xhr){ // 응답 텍스트는 xhr 객체에 responseText 프로퍼티로 전달됨.
	                if(xhr.status == 1111){	// response.setStatus(1); 코드로 보낸 값을 받음
	                    alert(xhr.responseText);					
	                }
	            }
	        });
	    });		
	}// end

	
	
</script>
</head>
<body>

	<div class="wrap">
		<div class="input_area">
			<form id="f" action="">
				<input type="text" name="no" id="no" placeholder="6자리 번호"><br>
				<input type="text" name="name" id="name" placeholder="회원명"><br>
				<input type="text" name="age" id="age" placeholder="나이"><br>
				<input type="text" name="birthday" id="birthday" placeholder="생일"><br>
				<input type="button" value="등록" id="insert_btn">
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