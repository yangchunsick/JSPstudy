<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<title>Document</title>
<style>
table {
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
}

tr {
	text-align: left;
	padding-right: 50px;
}

td {
	text-align: left;
	padding-right: 50px;
}
</style>
<script>
	$(document).ready(function() {
		fnSelectStaff();
		fnInsertStaff();
	});

	function fnSelectStaff() {
		$.ajax({
			url : 'selectStaffList.do',
			type : 'get',
			dataType : 'json',
			success : function(arr) {
				$('#staff_list').empty();
				$.each(arr, function(i, staff) {
					$('<tr>')
					.append($('<td>').text(staff.sNo))
					.append($('<td>').text(staff.name))
					.append($('<td>').text(staff.dept))
					.append($('<td>').text(staff.regDate))
					.appendTo('#staff_list');
				});
			},
			error : function() {
				alert('실패');
			}
		});
	} //end

	function fnInsertStaff() {
		$('#insert_btn').on('click', function(){
	        let regsNo = /^[0-9]{5}$/; 
			if(!regsNo.test($('#sNo').val())){
                alert('사원번호는 5자리 숫자 입니다.');
                return;
            }
			let regdept = /^[가-힣]{3,5}$/;
			if(!regdept.test($('#dept').val())){
				alert('부서는 3~5자리 한글 입니다.');
				return;
			}
		$.ajax({
			url : 'insertStaff.do',
			type : 'post',
			data : $('#f').serialize(),
			dataType : 'json',
			success : function(obj) {
				alert('사원 등록이 성공했습니다.');
				fnSelectStaff();
			},
			error: function(xhr){
				if(xhr.status == 1001 || xhr.status == 1002){
					alert(xhr.responseText);
				}
			}
		});
	});
}// end


		
</script>
</head>
<body>

	<h1>사원 정보 등록프로그램</h1>
	<form id="f" action="">
		<input type="text" name="sNo" id="sNo" placeholder="사원번호"> <input
			type="text" name="name" id="name" placeholder="사원명"> <input
			type="text" name="dept" id="dept" placeholder="부서명"> <input
			type="submit" id="insert_btn" value="등록하기">
	</form>
	<hr>
	<table>
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>부서명</td>
				<td>입사일</td>
			</tr>
		</thead>
		<tbody id="staff_list">

		</tbody>
	</table>

</body>
</html>