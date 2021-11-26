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
<style>
	label{
		display: block;
		margin-top: 10px;
	}
	textarea {
		width: 178px; height: 90px;
	}
</style>
<script>
	$(document).ready(function (){
		fnUpdateBoard();
		fnFileCheck();
	});
	
	function fnUpdateBoard() {
		$('#update_btn').on('click', function(){
			if( $('#title').val() == '${param.title}' && 		// 기존 제목과 같거나
				$('#content').val() == '${param.content}' &&	// 기존 내용과 같거나
				$('#fileName').val() == '' ){
				alert('변경할 내용이 없음.');
				return;
			}
			$('#f').attr('action', 'update.board');		// action을 주는 속성은 attr
			$('#f').submit();
		}); // #insert_btn
	}// end fnemptyCheck
	
	function fnFileCheck(){
		$('#fileName').on('change', function(){
			/*
				이벤트 대상
				event.target
				this
				#fileName
			*/
			
			/* 첨부 파일의 확장자 제한하기 */
			/*
				파일명.확장자
				마지막 . 인덱스 : 3  (lastIndexOf())
				3 + 1 인덱스부터 끝까지 추출  (substring())
			*/
			/* 확장자 제한 */
			let fullname = $(this).val();
			let extension = fullname.substring(fullname.lastIndexOf('.') + 1).toUpperCase();	//toUpperCase(); 대문자로 변환해서 받아 옴
			let extList = ['JPG', 'JPEG', 'PNG', 'GIF'];
			if ($.inArray(extension, extList) == -1) {  // 배열에 찾는 요소가 없으면 -1을 반환
				alert('확장자가 jpg, jpeg, png, gif인 파일만 업로드 할 수 있습니다.');
				$(this).val('');  // 첨부된 파일명을 빈 문자열로 바꿈 => 첨부가 없어짐.
				return false;
			}
			
			/* 첨부 파일의 용량 제한하기 */
			let maxSize = 10 * 1024 * 1024;  // 10메가 * 1024킬로바이트 * 1024바이트
			let fileSize = $(this)[0].files[0].size;
			if (maxSize < fileSize) {
				alert('10MB 이하의 파일만 업로드 할 수 있습니다.');
				$(this).val('');
				return false;
			}
		});
	} // end fnFileCheck
</script>
</head>
<body>

	<div>
			<!-- 파일 첨부 폼 
					1. method="post"
					2. enctype="multipart/form-data"
			 -->
			 
		<form id="f" method="post" enctype="multipart/form-data">
			
			<label for="writer">작성자</label>
			<input type="text" value="${param.writer}" readonly>
			<!-- ${param.writer}  -->
			
			<label for="title">제목</label>
			<input type="text" id="title" name="title" value="${param.title}">
			
			<label for="content">내용</label>
			<textarea id="content" name="content">${param.content}</textarea>

			<div>
				<img width="300px" src="${param.path}/${param.saveName}">
			</div>
			
			<label for="fileName">새로 첨부하기</label>
			<input type="file" id="fileName" name="fileName">				<!-- 새로 첨부할 파일 -->

			<input type="hidden" name="bNo" value="${param.bNo}">
			
			<!-- <input type="hidden" name="path" value="${param.path}"> -->
			<!-- ↑에는 서비스로 보내지 못하니 자바 코드를 넣어 path를 session에 저장한다. -->
			<% session.setAttribute("path", request.getParameter("path")); %>
			
			<input type="hidden" name="saveName" value="${param.saveName}">	<!-- 기존 첨부된 파일 -->
			
			<br><br>
			
			<div class="btn_area">
				<input type="button" id="update_btn" value="수정 완료">
				<input type="reset" value="reset">
				<input type="button" value="목록" onclick="location.href='list.board'">
			</div>
			
		</form>

	</div>












</body>
</html>