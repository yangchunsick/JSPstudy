package service.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.NoticeDao;
import dto.Notice;

public class NoticeListService implements NoticeService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		// 상세 보기할 때 session에 올려둔 notice를 제거
		Notice notice = (Notice) session.getAttribute("notice");
		if(notice != null) {
			session.removeAttribute("notice");
		}
		
		// 상세 보기 할 때 session에 올려 둔 open을 제거
		if(session.getAttribute("open") != null) {
			session.removeAttribute("open");
		}
		
		// 페이징 
		// 1. 현재 페이지 번호 확인하기
		
		
		// 2. 현제 페이지 번호 확인하기
		// Page가 안 넘어오면 page = 1로 처리함
		
		
		// 3. 페이징에 필요한 모든 계산 처리하기
		
		
		// 4. String < 1 2 3 4 5 > 만들기
		
			
		List<Notice> list = NoticeDao.getInstance().selectNoticeList();
							/*list라는 이름으로 위에 있는 list를 저장한다.*/
		
		request.setAttribute("list", list);
		
		
		
		return new ModelAndView("notice/list.jsp", false);
	}

}
