package model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.FamilyDao;
import dto.Family;

public class FamilyListService implements FamilyService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* DB 접근을 위한 FamilyDao */
		FamilyDao dao = FamilyDao.getInstance();
		
		/* Family 목록 가져오기 */
		List<Family> list = dao.selectAllFamily();
		
		/* 전체 Family 수 가져오기 */
		int totalCount = dao.getTotalCount();
		
		/* 위에 작업 했던 것들을 request에 저장해서 보낸다 */
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		
		
		return new ModelAndView("views/familyList.jsp", false);
	}


}
