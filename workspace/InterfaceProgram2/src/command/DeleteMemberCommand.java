package command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.MemberDao;

public class DeleteMemberCommand implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			
			String no = request.getParameter("no");								// 삭제 할 때 회원의 no를 요청 받아와서 삭제 처리
			
			int result = MemberDao.getInstance().deleteMember(no);				// dao에 보냄
			
			JSONObject obj = new JSONObject();									// JSONObject에 저장
			obj.put("result", result > 0);
			
			response.setContentType("application/json; charset=UTF-8");			// JSON 형식으로 변환
			PrintWriter out = response.getWriter();
			out.println(obj);
			out.close();
			
		} catch (Exception e) {													// 혹시 모르는 오류를 잡기위해 입섹션 처리
			e.printStackTrace();
		}
		
	}

}
