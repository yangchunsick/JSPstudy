<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	/* 입력 없으면 빈 문자열("") */
	String userId = request.getParameter("userId");
	String userPw = request.getParameter("userPw");
	
	/* 체크 안 하면 널(null) */
	String checkSaveId = request.getParameter("checkSaveId");

	/*
		체크 하면 아이디를 쿠키에 저장한다.
		체크 안 하면 아이디를 쿠키에서 삭제한다.
	*/
	
	if(checkSaveId != null){ // 널이 아니면 !! / 체크 한 상황
		Cookie cookie = new Cookie("userId", userId); // 저장할 놈
		cookie.setMaxAge(15 * 24 * 60 * 60); // 유효 기간
		response.addCookie(cookie); 
	} else { // 체크를 안 했을 경우 !!
		Cookie[] cookies = request.getCookies(); 
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("userId")){ 
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
	}
	// 로그인 화면으로 돌아가자
	response.sendRedirect("06_saveIdA.jsp");
	

%>