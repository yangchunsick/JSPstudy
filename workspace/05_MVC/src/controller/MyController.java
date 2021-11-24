package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lotto;
import model.Now;
import model.Today;

// @WebServlet({"/today.do", "/now.do"})

// suffix 값이 .do인 모든 요청을 처리하세요
@WebServlet("*.do")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyController() {
		super();
	}

	// 요청을 받은 걸 처리하는 곳
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// request를 알아내는 작업
		int begin = request.getRequestURI().lastIndexOf("/");
		String command = request.getRequestURI().substring(begin + 1); // "/MVC/today.do"  MVC/ 까지 4인데 그뒤로 부터 5부터 다 가져오라는 뜻임

		// request(요청)에 따른 선택
		switch (command) {
		case "today.do":
			Today today = new Today();
			today.execute(request, response);
			break;

		case "now.do":
			Now now = new Now();
			now.execute(request, response);
			break;
		
		case "lotto.do":
			Lotto lotto = new Lotto();
			lotto.execute(request, response);
			break;
		}
		
		
		// 응답 view로 이동
		// request를 전달하는 forward
		// 각 Model이 request에 결과를 저장 해 두었음.
		request.getRequestDispatcher("views/output.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
