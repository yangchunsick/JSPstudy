package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.board.BoardDeleteService;
import service.board.BoardInsertService;
import service.board.BoardListService;
import service.board.BoardService;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public BoardController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		/* 요청/응답 처리 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		/* JSP 요청 확인 (경로 확인) */
		String requestURI = request.getRequestURI();
		String contextpath = request.getContextPath();
		String command = requestURI.substring(contextpath.length() + 1);
		
		BoardService service = null;
		
		switch(command) {
		case "selectBoardList.do":
			service = new BoardListService();
			break;
		
		case "insertBoard.do":
			service = new BoardInsertService();
			break;
			
		case "deleteBoard.do":
			service = new BoardDeleteService();
			break;
			
		}
		
		// 실행 코드
		if(service != null) {
			service.execute(request, response);
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
}
