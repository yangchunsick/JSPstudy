<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	/* redirect : 기존 요청(request)을 전달하지 않는다. */
	
	/* 강제로 전달하시오. */
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	
	response.sendRedirect("04_sendRedirectC.jsp?name=" + URLEncoder.encode(name, "UTF-8") + "&age=" + age);
	
	// A에서 param을 전달 받은 것을 B에서 재 등록을 하고 C로 강제 전달한다.
%>