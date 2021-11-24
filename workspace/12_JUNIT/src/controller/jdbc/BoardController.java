package controller.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.jdbc.BoardDeleteService;
import service.jdbc.BoardInsertService;
import service.jdbc.BoardListService;
import service.jdbc.BoardService;

@WebServlet("*.jdbc")
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
		case "selectBoardList.jdbc":
			service = new BoardListService();
			break;
		
		case "insertBoard.jdbc":
			service = new BoardInsertService();
			break;
			
		case "deleteBoard.jdbc":
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
