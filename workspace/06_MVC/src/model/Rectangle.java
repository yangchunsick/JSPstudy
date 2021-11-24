package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class Rectangle implements Shape {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		String strWidth = request.getParameter("width");
		int width = Integer.parseInt(strWidth);
		
		String strHeight = request.getParameter("height");
		int height = Integer.parseInt(strHeight);

		int area = width * height;
		
		// 응답처리
		// 1. 결과를 반환한다.
		// 2. 응답 view를 결정한다.
		// 3. 이동 방식(redirect, forward)을 결정한다.
		
		
		// 응답처리 -1
		// 1) 결과를 반환하는 경우 : request에 결과 저장 + forward 이동
		// 2) 결과 반환이 없는 경우 : redirect 이동
		request.setAttribute("area", area);
		
		
		// 응답 처리 -2 : ModelAndView 클래스가 담당
		// 1) 응답 View : 앞으로 이동할 View(JSP)의 경로를 작성
		// 2) 이동 방식 : forward 또는 redirect
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView("views/rectangle.jsp");
		modelAndView.setRedirect(false); // Redirect 실행 할 작업이 아니다 forward 하겠다.
		
		
		// ModelAndView 객체를 컨트롤러에 반환
		return modelAndView;
		
	}

}
