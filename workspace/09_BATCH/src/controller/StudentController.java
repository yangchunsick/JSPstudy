package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.StudentDeleteService;
import model.StudentDetailService;
import model.StudentInsertService;
import model.StudentListService;
import model.StudentService;
import model.StudentUpdateService;

/* 가장 완벽한 형태 (여지껏 우리가 작성했던 것 중에서...) */


@WebServlet("*.do")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");	// ajax 작성 시, 달라질 수 있는 부분임.
		/*
		 String[] arr = request.getRequestURI().split("/");		//   이 방법을 사용할 시에는 /BATCH/list.do의 list.do만 사용할 수 있음   그래서   /BATCH/student/list.do는 다른 방법으로 코드를 짜야 한다.
		 String command = arr[arr.length - 1];
		*/
		/* 이 방법은 중요하니 익혀두도록하자 mapping 값이 여러개일 경우 아래 방법으로만 가능하다. */
		String requestURI = request.getRequestURI();																		//   /BATCH/student/list.do       
		String contextPath = request.getContextPath();																		// 	 /BATCH
		String command = requestURI.substring(contextPath.length() + 1);  						// contextPath + 1 == contextPath를 제외한 값 	--> student/list.do
		
		ModelAndView mav = null;
		StudentService studentService = null; 													// 모든 model들의 type을 잡아두고 아래 switch문에서 method를 채워준다.
		switch(command) {
		case "studentList.do":
		studentService = new StudentListService();
			break;
		case "insertForm.do" :
			mav = new ModelAndView("views/studentInsert.jsp", false);
			break;
		case "insertStudent.do" :
		studentService = new StudentInsertService();
			break;
		case "deleteStudent.do" :
		studentService = new StudentDeleteService();
			break;	
		case "studentDetail.do" :
		studentService = new StudentDetailService();
			break;
		case "updateStudent.do" :
		studentService = new StudentUpdateService();
			break;
		}
		
		if (studentService != null) {
			try {														// Exception을 직접 받기 위한 try를 작성할 것 ---> 100% 예외 처리됨. IOE or Servlet E 는 위에서 해주지만 다른 예외 처리를 위해 작성.(interface에서 던지는 exception임)
				mav = studentService.execute(request, response);		// 모든 실행의 결과는 mav이다.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//command의 실행 결과로 mav가 반환됨.
		if (mav != null) {				// mav 가 null이 아니면
			if(mav.isRedirect()) {		// redirect 여부 확인
					response.sendRedirect(mav.getView());										// mav가 getView(이동할 장소)를 가지고 redirect response를 한다.
			} else {
					request.getRequestDispatcher(mav.getView()).forward(request, response);		// 아니면 forward한다 request와 response를 들고.
			}
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
