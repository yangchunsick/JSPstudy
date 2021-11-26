package service.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDao;

public class BoardDeleteService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 파라미터
		String param = request.getParameter("bNo");		// optional 대신에 사용
		if(param == null || param.isEmpty()) {			// 받은 값이 비어 있거나 없으면
			param = "0";								// 0으로 지정
		}
		Long bNo = Long.parseLong(param);
		String path = request.getParameter("path");
		String saveName = request.getParameter("saveName");
		
		// 첨부 삭제
		// 필요한 것 번호, 파일이 저장된 경로, 파일의 이름
		String realPath = request.getServletContext().getRealPath(path);	// 실제 경로를 알아내기
		File file = new File(realPath ,saveName); // (파일이 있는 경로, 파일 이름) 
		if(file.exists()) { // 해당 경로에 파일이 있으면 삭제
		   file.delete();
		}
		
		
		//DB 삭제 
		int result = BoardDao.getInstance().deleteBoard(bNo);
		
		// 실행 여부
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글 삭제 성공')");
			out.println("location.href='index.jsp'");
			out.println("</script>");
			out.close();
		}else {
			out.println("<script>");
			out.println("alert('게시글 삭제 실패')");
			out.println("history.back()'");
			out.println("</script>");
			out.close();
		}
		
		
		return null;
	}

}
