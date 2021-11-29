package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.MemberDao;
import dto.Member;

public class SelectMemberListCommand implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<Member> list = MemberDao.getInstance().selectMemberList();			// 회원들의 목록을 가져옴
		
		JSONArray members = new JSONArray(list);								// JSON 방식으로 보내기 위해서 JSONObject로 담음
		
		response.setContentType("application/json; charset=UTF-8");				// JSON 형식의 데이터로 변화
		
		PrintWriter out = response.getWriter();									// 출력
		out.println(members);
		out.close();
	}

}
