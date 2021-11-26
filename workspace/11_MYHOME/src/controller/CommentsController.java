package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.comments.CommentsDeleteService;
import service.comments.CommentsInsertService;
import service.comments.CommentsListService;
import service.comments.CommentsService;

@WebServlet("*.comments")
public class CommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CommentsController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* MVC 패턴이 불가능한 CommentsController*/
		/* ModelAndView가 없음 */
		
		/* 요청/응답 처리 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		/* JSP 요청 확인 (경로 확인) */
		String requestURI = request.getRequestURI();
		String contextpath = request.getContextPath();
		String command = requestURI.substring(contextpath.length() + 1);

		// 모든 Notice Service들은 인터페이스 NoticeService를 구현하므로
		// NoticeService 타입의 객체이다.
		CommentsService service = null;		
	
		
		switch(command) {
		case "insert.comments":
			service = new CommentsInsertService();
			break;
		
		case "list.comments":
			service = new CommentsListService();
			break;
			
		case "delete.comments":
			service = new CommentsDeleteService();
			break;
		}
		
		
		if(service != null) {
			try {
				service.execute(request, response);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
