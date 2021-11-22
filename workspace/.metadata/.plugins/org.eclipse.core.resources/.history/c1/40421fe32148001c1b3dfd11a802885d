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
		
		List<Notice> list = NoticeDao.getInstance().selectNoticeList();
							/*list라는 이름으로 위에 있는 list를 저장한다.*/
		
		request.setAttribute("list", list);
		
		
		
		return new ModelAndView("notice/list.jsp", false);
	}

}
