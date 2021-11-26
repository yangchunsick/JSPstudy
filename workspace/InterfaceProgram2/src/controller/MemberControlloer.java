package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MemberService;

@WebServlet("/MemberControlloer")
public class MemberControlloer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberControlloer() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String requestURI = request.getRequestURI();
		String contextpath = request.getContextPath();
		String command = requestURI.substring(contextpath.length() + 1);

		MemberService service = null;
		
		if(service != null) {
			service.execute(request, response);
		}
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
