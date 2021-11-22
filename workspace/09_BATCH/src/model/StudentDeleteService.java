package model;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.StudentDao;

public class StudentDeleteService implements StudentService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// parameter (sno 전달이 없으면 "99999" 사용)
		Optional<String> optSno = Optional.ofNullable(request.getParameter("sno"));
		String sno = optSno.orElse("99999");
		
		// STUDENT 테이블에서 삭제
		int result = StudentDao.getInstance().deleteStudent(sno);
		
		// 응답 처리
		PrintWriter out = response.getWriter();
		if(result > 0) {			// 성공시 보낼 script
			out.println("<script>");
			out.println("alert('삭제 성공')");
			out.println("location.href='/BATCH/studentList.do'");	// redirect 이동
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
					
		return null;		// 이미 이동이 이뤄졌으므로 ModelAndView 없이 null return;
	}

}
