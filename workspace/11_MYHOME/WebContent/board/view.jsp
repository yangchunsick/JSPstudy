<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
   <script>
      $(document).ready(function(){

         $('#delete_btn').on('click', function(){
            if (confirm('게시글에 달린 모든 댓글도 함께 삭제됩니다. 삭제할까요?')) {
               $('#f').attr('action', 'delete.board');
               $('#f').submit();
            }   
         }) // delete_btn click event            == click has no basic event, except submit.
         
         $('#update_btn').on('click', function(){
            $('#f').attr('action', 'updateForm.board');
            $('#f').submit();
         }) // update_btn click event
         
   <%-- insert btn  --%>      
         $('#insert_btn').on('click', function(){
            if ( $('#content').val() == '' ) {
               alert('댓글 내용 필수');
               $('#content').focus();
               return;
            }
            $.ajax({
               url: 'insert.comments',         
               type: 'post',
               data: $('#comments_form').serialize(),   // 모든걸 보내는 serialize
               dataType: 'json',
               success: function(obj){               // obj : {"result" : 0} 또는 {"result" : 1}
                  fnCommentsList();
                  $('#content').val('');            // 쓴 댓글의 댓글란 지우기
               },// success
               error: function(xhr){
                  alert(xhr.responseText);
               }
            }); // ajax
         }); // insert_btn click event
         
         // 리스트 보여주기
         fnCommentsList();
         // 댓글 삭제
         fnDeleteComments();
         
      }); // load event
      
      function fnCommentsList(){
         
         $.ajax({
            
            url: 'list.comments',
            type: 'get',
            data: 'bNo=${board.bNo}',
            dataType: 'json',
            success: function(comments){   // comments : [{}, {}, {}, ...]
               $('#comments_list').empty();
               $.each(comments, function(i, comment){
                  if (comment.state == 0)   {   // 정상 댓글이면
                     if ( '${loginUser.id}' == comment.writer ) {   // 관리자만 삭제 가능하게끔 하려면..여기에 조건을 하나 더 줌
                        $('<ul>')
                        .append( $('<li>').text(comment.writer) )
                        .append( $('<li>').text(comment.content) )
                        .append( $('<li>').text(comment.created) )
                        .append( $('<li>').html('<a class="delete_comments_link" data-cno="' + comment.cNo + '">삭제</a>') ) // param : cNo
                        .appendTo('#comments_list');
                     } else {
                        $('<ul>')
                        .append( $('<li>').text(comment.writer) )
                        .append( $('<li>').text(comment.content) )
                        .append( $('<li>').text(comment.created) )
                        .append( $('<li>').html('') )
                        .appendTo('#comments_list')
                     } // if
                     
                  } else if (comment.state == -1) {   // 삭제 된 댓글이면 
                     $('<ul>')
                     .append( $('<li>').text('') )
                     .append( $('<li>').text('삭제된 댓글입니다.') )
                     .append( $('<li>').text('') )
                     .append( $('<li>').html('') )
                     .appendTo('#comments_list');
                     
                  }
                  
               }); //for
            
            },//success
            error: function(xhr){
               alert(xhr.responseText);
            }
         }) // ajax
         
      }//fncommentslist
      
      function fnDeleteComments(){
         $('body').on('click', '.delete_comments_link', function(event){      // page load가 시작 되었을 때, 확인되지 않으므로 body tag를 잡음
            if ( confirm('댓글을 삭제할까요?')) {
               $.ajax({
                   url: 'delete.comments',
                  type: 'get',
                  data: 'cNo=' + $(this).data('cno'),   // this -> delete_comments_btn   :: !!!!!data 속성은 대소문자가 무시 됨 주의 !!!!!
                  success: function(){
                     fnCommentsList();
                  },
                  error: function(xhr){
                     alert(xhr.responseText);
                  }
               }); //ajax
            }
         }); // click fn
      }//delete function
      
   </script>
	<style>
		#comments_list > ul{
			width: 800px;
			margin-top: 10px; padding: 0;
			list-style-type: none;
			display: flex;
		}
		#comments_list > ul > li:nth-of-type(1) { width: 100px; }
		#comments_list > ul > li:nth-of-type(2) { width: 400px; }
		#comments_list > ul > li:nth-of-type(3) { width: 100px; }
		#comments_list > ul > li:nth-of-type(4) { width: 100px; }
	</style>
</head>
<body>

	<div>
		<input type="button" value="목록" onclick="location.href='list.board'">
		<c:if test="${loginUser.id == board.writer}"> <!-- 로그인한 User와 작성자와 같다면 -->
			<form id="f" method="post">
				<input type="hidden" name="bNo" value="${board.bNo}">
				<input type="hidden" name="path" value="storage/${year}/${month}/${day}">	<!-- 파일이 있는 경로 -->
				<input type="hidden" name="saveName" value="${board.saveName}">				<!-- 파일 이름 -->
				<input type="hidden" name="title" value="${board.title}">
				<input type="hidden" name="content" value="${board.content}">
				<input type="hidden" name="writer" value="${board.writer}">
				<input type="button" id="update_btn" value="수정">							<!-- 수정은 Session으로 해야한다.. -->
				<input type="button" id="delete_btn" value="삭제">
			</form>
		</c:if>
	</div>

	<div>
		<table>
			<tbody>
				<tr>
					<td>작성자</td>
					<td>${board.writer}</td>
					<td>작성일자</td>
					<td>${board.created}</td>
					<td>수정일자</td>
					<td>${board.lastModified}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="5">${board.title}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="5">${board.content}</td>
				</tr>
				<tr>
					<td colspan="6">
						<img width="640px" src="storage/${year}/${month}/${day}/${board.saveName}" alt="${board.fileName}">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<hr>
	<!-- 댓글 입력란 -->
	
	<div>
		<form id="comments_form">
			<table border="1">
				<tbody>
					<tr>
						<td rowspan="2">
							<textarea rows="3" cols="80" id ="content" name="content"></textarea>
							<input type="hidden" name="writer" value="${loginUser.id}">
							<input type="hidden" name="bNo" value="${board.bNo}">
						</td>
						<td>
							${loginUser.id}(${loginUser.name})
						</td>
					</tr>
					<tr>
						<c:if test="${loginUser != null}">
							<td>
								<!-- 로그인 한 사람만 가능함 -->
								<input type="button" id="insert_btn" value="댓글 작성">
							</td>
						</c:if>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
	<!-- 댓글 목록 -->
	<div id="comments_list">
		<ul>
			<li>작성자</li>
			<li>내용</li>
			<li>작성일자</li>
			<li>삭제</li>
		</ul>
	</div>

</body>
</html>