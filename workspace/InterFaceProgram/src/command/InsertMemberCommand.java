package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.MemberDAO;
import dto.Member;

public class InsertMemberCommand implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String no = request.getParameter("no");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String birthday = request.getParameter("birthday");
			
			Member member = new Member();
			member.setNo(no);
			member.setName(name);
			member.setAge(age);
			member.setBirthday(birthday);
			
			int result = MemberDAO.getInstance().insertMember(member);
			
			JSONObject obj = new JSONObject();
			obj.put("result", result > 0);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(obj);
			out.close();
			
			} catch (SQLIntegrityConstraintViolationException e) {
				response.setContentType("text/plain; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				out.println("동일한 회원 번호는 입력할 수 없습니다.");
				
				response.setStatus(9000);
		}catch (SQLException e) {
			response.setContentType("text/plain; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("나이는 0~ 100만 입력할 수 있습니다.");
		}catch (Exception e) {
			response.setContentType("text/plain; charset=UTF-8");
			
			// 에러 메세지 전달
			PrintWriter out = response.getWriter();
			out.println("알 수 없는 에러가 발생 했습니다.");
			
			// 에러 코드 전달
			response.setStatus(2003); // 에러 코드 2003 발생 (2003은 맘대로 정했음)
		}

	}

}
