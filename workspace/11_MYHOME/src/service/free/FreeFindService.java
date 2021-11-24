package service.free;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import common.Page;
import dao.FreeDao;
import dto.Free;

public class FreeFindService implements FreeService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 파라미터
		String column = request.getParameter("column");
		String query = request.getParameter("query");

		// DB로 보낼 HashMap
		Map<String, String> map = new HashMap<String, String>();
		map.put("column", column);
		map.put("query", "%" + query + "%");

		// 페이징
		// 1. Page 객체 만들기
		Page p = new Page();

		// 2. 검색된 게시글의 전체 갯수 구하기
		int totalRecord = FreeDao.getInstance().selectFindCount(map);
		p.setTotalRecord(totalRecord);
		
		// 3. 검색된 게시글을 이용한 페이지 갯수 구하기
		p.setTotalPage();
		
		// 4. 현재 페이지 번호 확인하기
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		p.setPage(page);
		
		// 5. beginRecord, endRecord 계산하기
		p.setBeginRecord();
		p.setEndRecord();
		
		// 6. beginRecord ~ endRecord 사이 목록 가져오기 //놓침
		// 기존에 검색어 관련 map이 있음. 거기에 beginRecord, endRecord 추가해야 함
		map.put("beginRecord", p.getBeginRecord() + "");
		map.put("endRecord", p.getEndRecord() + "");
		
		// 검색 결과 가져오기
		List<Free> list = FreeDao.getInstance().findFree(map);

		// 7. beginpage, endpage 계산기
		p.setBeginPage();
		p.setEndPage();
		
		// list.jsp로 넘어갈 애들
		request.setAttribute("column", column);
		request.setAttribute("query", query);		
		request.setAttribute("p", p);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("list", list);

		return new ModelAndView("free/findList.jsp", false);
	}

}
