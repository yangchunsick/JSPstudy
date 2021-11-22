package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.FamilyDeleteService;
import model.FamilyDetailService;
import model.FamilyInsertService;
import model.FamilyListService;
import model.FamilyService;
import model.FamilyUpdateService;

@WebServlet("*.do")
public class FamilyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FamilyController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* 인코딩 작업 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		/* 요청을 알아내는 작업 */
		String requestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String command = requestURI.substring(ContextPath.length() + 1);

		/* MAV Import */
		ModelAndView mav = null;

		/* FamilyService */
		FamilyService familyService = null;

		/* switch */
		switch (command) {
		
		/* 조직원 목록 */
		case "familyList.do":
			familyService = new FamilyListService();
			break;

		/* 조직원 추가 화면으로 이동 */
		case "familyInsertForm.do":
			mav = new ModelAndView("views/familyInsert.jsp", false);
			break;

		/* 조직원 추가 */
		case "familyinsert.do":
			familyService = new FamilyInsertService();
			break;
			
		/* 조직원 상세 정보 */
		case "familyDetail.do":
			familyService = new FamilyDetailService();
			break;
			
		/* 조직원 정보 수정 */
		case "familyupdate.do":
			familyService = new FamilyUpdateService();
			break;

		case "familyDelete.do":
			familyService = new FamilyDeleteService();
			break;
			
		}

		if (familyService != null) {
			try {
				mav = familyService.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (mav != null) {
			if (mav.isRedirect()) {
				response.sendRedirect(mav.getView());
			} else {
				request.getRequestDispatcher(mav.getView()).forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
