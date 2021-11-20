package model;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.FamilyDao;
import dto.Family;

public class FamilyDetailService implements FamilyService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//
		int num = Integer.parseInt(request.getParameter("num"));
		/*
		int num = Integer.parseInt(request.getParameter("num"));
		if(num == null)
			num = "99999";
		*/
		// FAMILY 테이블에서 num 조직원 정보 가져오기 dto
		Family family = FamilyDao.getInstance().selectFamilyInfoByNum(num);
		
		// request에 가져온 학생 정보 저장하기
		request.setAttribute("family", family);
		
		// views/familyDetail.jsp로 foward 처리
		return new ModelAndView("views/familyDetail.jsp", false);
	}

}
