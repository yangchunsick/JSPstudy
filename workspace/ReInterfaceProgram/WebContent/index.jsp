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
<script>
	$(document).ready(function(){
		fnStudentList();
	});
	/* 목록 */
	function fnStudentList() {
		// 학생 목록을 요청하는 방법 : studentList.do
		$.ajax({
			url : 'studentList.do', // 요청
			type: 'get',		    // 목록을 불러오는 건 get 방식으로 하는게 좋다.
			dataType: 'json',		// 받아 오는 데이터의 타입은 JSON이다.
			success: function(students) {
				// students를 반복문으로 목록 생성 작업
				$.each(students, function(i, student){
					$('<tr>')								// tr 태그를 만들어서 그 안에
					.append($('<td>').text(student.sno))	// td 태그를 만들고 students 안에 있는 sno를 꺼낸다 !
					.appendTo('#student_list');				// student_list에 넣는다 !
				});
			},
			error : function(xhr) {	// 오류 발생
				alert('목록 가져오기 오류 발생');
			}
		}); // end ajax
	} // end fnStudentList
	/**/
	
</script>
</head>
<body>

	<table border="1">
		<thead>
			<tr>
				<td>학번</td>
				<td>학생명</td>
				<td>중간 고사</td>
				<td>기말 고사</td>
				<td>이수 여부</td>
			</tr>
		</thead>
		<tbody id="student_list"></tbody>
	</table>
	
</body>
</html>