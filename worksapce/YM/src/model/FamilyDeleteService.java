package model;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.FamilyDao;

public class FamilyDeleteService implements FamilyService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// int num String으로 변환
		int num = Integer.parseInt(request.getParameter("num"));
				
		// 파라미터 (num 전달이 없으면 "99999" 사용)
		Optional<String> optNum = Optional.ofNullable(request.getParameter("num"));
		int result = FamilyDao.getInstance().deleteFamily(num);
		
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('삭제 성공')");
			out.println("location.href='/YM/familyList.do'");
			out.println("</script>");
			out.close();
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()'");
			out.println("</script>");
			out.close();
		}
		
		
		// ModelAndView 없이 변화
		return null;
	}

}
