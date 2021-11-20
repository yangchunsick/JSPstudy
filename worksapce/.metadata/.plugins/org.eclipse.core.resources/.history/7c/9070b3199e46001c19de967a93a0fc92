package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.FamilyDao;
import dto.Family;

public class FamilyInsertService implements FamilyService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// DB에서 전달 받을 파라미터
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String dapart = request.getParameter("depart");
		String birthday = request.getParameter("birthday");
		
		// family 테이블로 보낼 family 객체(DTO)
		Family family = new Family();
		family.setNum(num);
		family.setName(name);
		family.setDepart(dapart);
		family.setBirthday(birthday);
		
		// family 테이블에 삽입
		int result = FamilyDao.getInstance().insertFamily(family);
		
		// 응답처리
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('등록 성공')");
			out.println("location.href='/YM/familyList.do'");
			out.println("</script>");
			out.close();
		}else {
			out.println("<script>");
			out.println("alert('등록 실패')");
			out.println("history.back()'");
			out.println("</script>");
			out.close();
		}
		
		
		return null;
	}

}
