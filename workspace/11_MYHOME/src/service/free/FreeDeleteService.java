package service.free;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.FreeDao;

public class FreeDeleteService implements FreeService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Optional<String> opt = Optional.ofNullable(request.getParameter("fNo"));
		Long fNo = Long.parseLong(opt.orElse("0")); /* 전달 되는 값이 없으면 0번으로 처리하는데 0번은 없으니까 실패 처리가 될 것임 */

		int result = FreeDao.getInstance().deleteFree(fNo);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시물 삭제 성공')");
			out.println("location.href='list.free'");
			out.println("</script>");
			out.close();
		}else {
			out.println("<script>");
			out.println("alert('게시물 삭제 실패')");
			out.println("history.back()");		// 뒤로 두번 가서 view로 되돌아 가는 거임 
			out.println("</script>");
			out.close();
		}
		
		return null;
	}

}
