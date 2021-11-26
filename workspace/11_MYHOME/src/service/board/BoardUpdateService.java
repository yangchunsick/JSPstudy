package service.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.ModelAndView;
import dao.BoardDao;
import dto.Board;

public class BoardUpdateService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// realPath
		HttpSession session = request.getSession();
		String path = (String) session.getAttribute("path");
		String realPath = request.getServletContext().getRealPath(path);

		// MultipartRequest 객체 생성
		MultipartRequest mr = new MultipartRequest(request, realPath, 10 * 1024 * 1024, "UTf-8", new DefaultFileRenamePolicy());
		
		/* 첨부 파일 수정 하기*/
		// 기존에 첨부 되어 있던 파일
		String saveName = mr.getParameter("saveName");
		File previous = new File(realPath, saveName);
		
		// 새로 추가 하려는 파일
		File present = mr.getFile("fileName");
		
		// 새 첨부가 있으면 기존 첨부를 지움
		if (present != null) {
			if (previous.exists()) {
				previous.delete();
			}
		}	
		/*  수정하기 */		
		// 수정 할 게시글 정보들
		Long bNo = Long.parseLong(mr.getParameter("bNo"));
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		// DB로 보낼 Board  (수정 내용을 저장한 Board)
		Board board = new Board();
		board.setbNo(bNo);
		board.setTitle(title);
		board.setContent(content);
		if (present != null) {		// 새 첨부가 있으면 올린 이름, 저장 이름 모두 변경 / 새 첨부가 없으면 기존 첨부명을 사용
			board.setFileName(mr.getOriginalFileName("fileName"));		// 올린 이름
			board.setSaveName(mr.getFilesystemName("fileName"));		// 저장된 이름
		}
		
		int result = BoardDao.getInstance().updateBoard(board);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('수정 성공 !')");
			out.println("location.href='view.board?bNo="+ bNo + "'");
			out.println("</script>");
			out.close();
		}else {
			out.println("<script>");
			out.println("alert('수정 실패..')");
			out.println("history.back()'");
			out.println("</script>");
			out.close();
		}
		
		
		return null;
	}
	
}

