<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%	
		// 1. session 삭제
		// session.removeAttribute("name");
		// session.removeAttribute("age");
	
		// 2. session을 초기화(전체 삭제)			// ex 장바구니
		session.invalidate();
	
		// 이동
		response.sendRedirect("01_sessionB.jsp");
	%>


