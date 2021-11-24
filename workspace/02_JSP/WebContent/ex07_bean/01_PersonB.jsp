<%@page import="ex07_bean.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>

	<%
	
		request.setCharacterEncoding("UTF-8"); // 인코딩 작업
	
		String name = request.getParameter("name"); // 아무런값을 입력하지 않았을 때 null값일 때
		if(name.isEmpty()){
			name = "민경태";
		}
		String strAge = request.getParameter("age");
		int age = strAge.isEmpty() ? 44 : Integer.parseInt(strAge); // 아무런값을 입력 하지 않았을 때 44로 출력한다.
		
		// 1. Bean 만들기
		Person p1 = new Person();
		p1.setName(name);
		p1.setAge(age);
		
		// 2. Bean 만들기
		Person p2 = new Person(name, age);
		
		// EL ->> ${} 사용을 위해서 pageContext에 속성으로 저장
		pageContext.setAttribute("p1", p1);
		pageContext.setAttribute("p2", p2);
		
	%>
	
	<h1>이름 : <%=p1.getName()%></h1>
	<h1>나이 : <%=p1.getAge() %></h1>
	
	<h1>이름 : ${p2.getName()}</h1>
	<h1>나이 : ${p2.getAge()}</h1>
	
	<h1>이름 : ${p2.name}</h1>  <%-- 내부적으로 찾아서 호출 함 --%>
	<h1>나이 : ${p2.age}</h1>
	
	
	
	


</body>
</html>