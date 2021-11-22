<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<style>
        body {
            background-color: black;
            color: white;
        }
        
        .wrap {
            width: 800px;
            margin: 200px auto;
        }
        
        .title {
            text-align: center;
            color: white;
        }
        
        .numcolor{
        	color: white;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
        
        td {
            height: 30px;
            border-top: 1px solid gray;
            border-bottom: 1px solid gray;
            text-align: center;
        }
        
        td input{
            width: 190px; height: 30px;
        }
        
        tfoot input {
            width: 90px;
            height: 50px;
            background-color: gray;
            border: 0;
        }
        
        tfoot input:hover {
            cursor: pointer;
            background-color: white;
        }
        </style>
	<script type="text/javascript">
	
		$(document).ready(function(){
			

		});

	</script>
</head>
<body>

	<div class="wrap">
		<h1 class="title">${family.name} 상세 정보</h1>
		<form id="f" action="/YM/familyupdate.do" method="post">
			<table>
				<thead>
					<tr>
						<td>번호</td>
						<td>이름</td>
						<td>부서</td>
						<td>생년월일</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="numcolor">
							<input type="hidden" name="num" value="${family.num}">
							${family.num}
						</td>
						<td><input type="text" name="name" id="name" value="${family.name}"></td>
						<td><input type="text" name="depart" id="depart" value="${family.depart}"></td>
						<td><input type="text" name="birthday" id="birthday" value="${family.birthday}"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<input type="submit" id="update_btn" value="정보 수정하기">
							<input type="reset" value="정보 초기화">
							<input type="button" value="조직원 목록" onclick="location.href='/YM/familyList.do'">						
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

</body>
</html>