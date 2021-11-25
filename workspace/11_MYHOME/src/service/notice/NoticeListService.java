package service.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import common.Page;
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
		// 1. 전체 공지사항 갯수 구하기
		int totalRecord = NoticeDao.getInstance().selectTotalCount();
		
		// 2. 현제 페이지 번호 확인하기
		// Page가 안 넘어오면 page = 1로 처리함
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 3. 페이징에 필요한 모든 계산 처리하기
		// 페이지 객체를 만든다
		Page p = new Page();
		p.setPageEntity(totalRecord, page);
		
		// 4. String < 1 2 3 4 5 > 만들기
		String pageEntity = p.getPageEntity("list.notice");
		
		// beginRecord ~ endRecord 목록 가져오기
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRecord", p.getBeginRecord());
		map.put("endRecord", p.getEndRecord());
		
		List<Notice> list = NoticeDao.getInstance().selectNoticeList(map);
							/*list라는 이름으로 위에 있는 list를 저장한다.*/
		
		// list.jsp로 보낼 데이터들
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("list", list);
		request.setAttribute("pageEntity", pageEntity);
		
		// 각 페이지의 게시글 시작 번호
		request.setAttribute("startNum", totalRecord - (page - 1) * p.getRecordPerpage());
		
		
		return new ModelAndView("notice/list.jsp", false);
	}

}
