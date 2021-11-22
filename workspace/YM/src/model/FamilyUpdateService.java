package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.FamilyDao;
import dto.Family;

public class FamilyUpdateService implements FamilyService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		// 파라미터
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String depart = request.getParameter("depart");
		String birthday = request.getParameter("birthday");
		
		// FAMILY 테이블로 보낼 Family 객체 (DTO)
		Family family = new Family();
		family.setNum(num);
		family.setName(name);
		family.setDepart(depart);
		family.setBirthday(birthday);
		
		// family 테이블에서 수정
		int result = FamilyDao.getInstance().updateFamily(family);
		
		// 응답 처리
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('수정 성공')");
			out.println("location.href='/YM/familyDetail.do?num=" + num + "'");
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		
		// ModelAndView 없이 변환
		return null;
	}

}
