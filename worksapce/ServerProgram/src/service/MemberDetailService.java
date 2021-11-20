package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class MemberDetailService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int no = Integer.parseInt(request.getParameter("num"));
		
		MemberDTO memberDTO = MemberDAO.getInstance().selectMemberInfoByNum(no);
		
		
		request.setAttribute("member", memberDTO);
		
		return new ModelAndView("views/Detail.jsp", false);
	}

}
