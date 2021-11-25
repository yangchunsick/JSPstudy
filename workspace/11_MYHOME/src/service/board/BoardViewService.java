package service.board;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDao;
import dto.Board;

public class BoardViewService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 파라미터 처리
		Optional<String> opt = Optional.ofNullable(request.getParameter("bNo"));
		Long bNo = Long.parseLong(opt.orElse("0"));
		
		// DB에서 Board 가져오기
		Board board = BoardDao.getInstance().selectBoardView(bNo);
		// 이동 
		if(board != null) { // 검색된 결과가 있으면
			
			// view.jsp로 보낼 데이터 저장
			request.setAttribute("board", board);
			
			
			return new ModelAndView("board/view.jsp",false);
		}else { // 검색된 결과가 없으면
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글 정보 확인 불가')");
			out.println("history.back()'");
			out.println("</script>");
			out.close();
			return null;
		}
	}

}
