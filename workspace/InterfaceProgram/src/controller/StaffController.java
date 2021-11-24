package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StaffInsertService;
import service.StaffListService;
import service.StaffService;

@WebServlet("*.do")
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public StaffController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextpath = request.getContextPath();
		String command = requestURI.substring(contextpath.length() + 1);
		
		StaffService service = null;
		
		switch (command) {
		case "selectStaffList.do":
			service = new StaffListService();
			break;
		
		case "insertStaff.do":
			service = new StaffInsertService();
			break;
			
		}
		
		if(service != null) {
			service.execute(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
