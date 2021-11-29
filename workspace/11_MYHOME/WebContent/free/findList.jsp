<%@page import="dto.Notice"%>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.css"
	integrity="sha512-4wfcoXlib1Aq0mUtsLLM74SZtmB73VHTafZAvxIp/Wk9u1PpIsrfmTvK0+yKetghCL8SHlZbMyEcV8Z21v42UQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script>
	
</script>
<style type="text/css">
a {
	text-decoration: none;
	color: black;
}

.page {
	color: red;
}

.content_td {
	width: 200px;
}

.reply_link {
	width: 40px;
	height: 13px;
	float: right;
	font-size: 10px;
	color: red;
}

.reply_link:hover {
	cursor: pointer;
}

.reply_form {
	display: none;
}
</style>
<script>
$(document).ready(function(){
	$('.reply_link').on('click', function(){
		$(this).parent().parent().next().toggleClass('reply_form');
	});
});
</script>
</head>
<body>
	<h1>
		<a href="index.jsp">WEB PAGE</a>
	</h1>

	<!-- 검색란 -->
	<form action="find.free">
		<!-- option의 value를 DB 칼럼명으로 직접 사용함. -->
		<select name="column">
			<option value="WRITER">작성자</option>
			<option value="CONTENT">내용</option>
			<option value="ALL">작성자+내용</option>
		</select> <input type="text" name="query">
		<button>검색</button>
		<input type="button" value="전체 보기" onclick="location.href='list.free'">
	</form>

	<hr>
	<!-- 작성 링크 -->
	<div>
		<!-- 로그인 한 유저가 not null일 때 로그인 한 경우에만  -->
		<c:if test="${loginUser.id != null}">
			<a href="insertForm.free">새글작성</a>
		</c:if>
	</div>
	
	<hr>

	<!-- 목록 -->
	전체 게시글 : ${totalRecord}개
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>작성자</td>
				<td style="text-align: center;">내용</td>
				<td>조회수</td>
				<td>최종 수정일</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="6" style="text-align: center;">게시물이 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="free">
					<c:if test="${free.state == 0 }">
						<tr>
							<td style="text-align: center;">${free.fNo}</td>
							<td>${free.writer}</td>
							<td class="content_td"><c:if
									test="${free.content.length() < 20}">
									<!-- depth만큼 들여쓰기 -->
									<c:forEach begin="1" end="${free.depth}">
										&nbsp;&nbsp;										
									</c:forEach>
									<!-- 댓글(depth 1 이상) [Re] 표시 -->
									<c:if test="${free.depth >= 1}">
										[Re]&nbsp;
									</c:if>
									<!-- 20자 이내는 그대로 표시 -->
									<a href="view.free?fNo=${free.fNo}" title="${free.content}">${free.content}</a>

								</c:if> <!-- 20자 넘어가면 20자까지만 표시 --> <c:if
									test="${free.content.length() >= 20}">
									<a href="view.free?fNo=${free.fNo}">${free.content.substring(0, 20)}</a>
								</c:if> <a class="reply_link">댓글달기</a></td>

							<td style="text-align: center;">${free.hit}</td>
							<td>${free.lastModified}</td>
							<!-- 로그인 사람과 작성자가 같을 때 -->
								<td style="width: 20px; height: 20px;">
							<c:if test="${loginUser.id == free.writer}">
								<a id="delete_link" href="delete.free?fNo=${free.fNo}"><i class="far fa-times-circle"></i></a> 
								<script type="text/javascript">
									$('#delete_link').on('click', function() {
									if (confirm('삭제할까요?') == false) {
									event.preventDefault();
									return false;
								}
								return true;
							});
							</script>
							</c:if>
							</td>
						</tr>
						<tr class="reply_form">
							<td colspan="6">
								<!-- 원글의 DEPTH, GROUPNO, GROUPORD를 넘겨줌 -->
								<form action="insertReply.free" method="post">
									<input type="hidden" name="depth" value="${free.depth}">
									<input type="hidden" name="groupNo" value="${free.groupNo}">
									<input type="hidden" name="groupOrd" value="${free.groupOrd}">
									<input type="text" name="writer" value="${loginUser.id}"
										readonly> <input type="text" name="content"
										placeholder="내용">
									<button>댓글 달기</button>
								</form>
							</td>
						</tr>
					</c:if>

					<c:if test="${free.state == -1 }">
						<tr>
							<td colspan="6" style="text-align: center;" title="삭제된 게시글 입니다.">삭제된 게시글 입니다.</td>
						</tr>
					</c:if>

				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" style="text-align: center; padding: 0 5px">
					<!-- 1 페이지로 이동 : 현재 1 페이지라면 1 페이지로 이동하는 링크가 필요 없음 --> 
					<c:if test="${p.page == 1}"> 
						&nbsp;◀◀ &nbsp; 
					</c:if> 
					<c:if test="${p.page != 1}">
						<a href="find.free?column=${column}&query=${query}&page=1">◀◀</a>&nbsp;
					</c:if> 
					<!-- 이전 블록으로 이동 : 1블록은 이전 블록이 없음 --> 
					<c:if test="${p.page <= p.pagePerBlock}">
						◀&nbsp;
					</c:if> 
					<c:if test="${p.page > p.pagePerBlock}">
						<a href="find.free?column=${column}&query=${query}&page=${p.beginPage -1}">◀</a>&nbsp;
					</c:if>
						<!-- 페이지 번호 : 현재 페이지는 링크가 없다 현재 페이지로 재이동이 불가능 하다. --> 
						<c:forEach var="i" begin="${p.beginPage}" end="${p.endPage}">
						<c:if test="${p.page == i }">
							<span class="page">${i}</span>
						</c:if>
						<c:if test="${p.page != i}">
							<a href="find.free?column=${column}&query=${query}&page=${i}">${i}</a>
						</c:if>
					</c:forEach> 
					<!-- 다음 블록으로 이동 : 마지막 블록은 링크가 필요 없음 --> 
					<c:if test="${p.endPage == p.totalPage}">
						▶ &nbsp;
					</c:if>
					<c:if test="${p.endPage != p.totalPage}">
						<a href="find.free?column=${column}&query=${query}&page=${p.endPage +1}">▶</a>&nbsp;
					</c:if> <!-- 마지막 페이지로 이동 : 마지막 페이지는 링크가 필요 없음 --> <c:if
						test="${p.page == p.totalPage}">
						▶▶&nbsp;
					</c:if> <c:if test="${p.page != p.totalPage}">
						<a href="find.free?column=${column}&query=${query}&page=${p.totalPage}">▶▶</a>&nbsp;
					</c:if>
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>