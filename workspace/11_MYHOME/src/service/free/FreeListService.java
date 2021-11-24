package service.free;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import common.Page;
import dao.FreeDao;
import dto.Free;

public class FreeListService implements FreeService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// 조회수 증가에서 사용된 session의 open 속성 제거하기
		HttpSession session = request.getSession();
		if(session.getAttribute("open") != null) {
			session.removeAttribute("open");
		}
		
		// 페이징 처리
		// 1. 페이징 객체 만들기
		Page p = new Page();

		// 2. 전체 게시글의 개수 구하기
		int totalRecord = FreeDao.getInstance().selectTotalCount();	// 전체 게시물 개수 구하기
		p.setTotalRecord(totalRecord);
		
		// 3. 전체 페이지의 갯수 구하기
		p.setTotalPage(); // common에 있는 page. 호출 하기

		// 4. 현재 페이지 번호 확인하기
		//	1) page가 안 넘어오면 page = 1로 처리함
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		p.setPage(page);
		
		// 5. beginRecord, endRecord 계산하기
		p.setBeginRecord();
		p.setEndRecord();
		
		// 6. beginRecord ~ endRecord 사이 목록 가져오기
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRecord", p.getBeginRecord());
		map.put("endRecord", p.getEndRecord());
		
		List<Free> list = FreeDao.getInstance().selectFreeList(map); // 목록 가져오기
		
		// 7. beginPage ~ endPage 계산하기
		p.setBeginPage();
		p.setEndPage();
		
		// 8. Page 객체를 List.jsp에서 사용할 수 있도록 저장하기
		request.setAttribute("p", p);
		
		
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("list", list);
		
		return new ModelAndView("free/list.jsp", false);
	}

}
