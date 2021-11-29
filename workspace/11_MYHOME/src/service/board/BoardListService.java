package service.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import common.Page;
import dao.BoardDao;
import dto.Board;

public class BoardListService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 파라미터 page 
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));	// 최근에 등록한 게시물을 먼저 보니까 1 페이지를 기본 값으로 쓴다.
		
		// page 계산을 위해서 전체 레코드 갯수
		int totalRecord = BoardDao.getInstance().selectTotalCount();
		
		// 페이징 처리 Page 객체 생성 및 데이터 계산
		Page p = new Page();
		p.setPageEntity(totalRecord, page); // common에서 미리 계산을 해 놨음
		
		// beginRecord, endRecord를 DB로 보낼 Map
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRecord", p.getBeginRecord());
		map.put("endRecord", p.getEndRecord());
		
		// beginRedord ~ endRecord 사이 목록 가져오기
		// 게시물 목록을 가져오기
		List<Board> list = BoardDao.getInstance().selectBoardList(map);
				
		// board/list.jsp로 보낼 데이터
		// request에 저장하기
		request.setAttribute("list", list);
		request.setAttribute("pageEntity", p.getPageEntity("list.board"));
		request.setAttribute("startNum", totalRecord - (page - 1) * p.getRecordPerpage());
		
		return new ModelAndView("board/list.jsp", false);
	}

}