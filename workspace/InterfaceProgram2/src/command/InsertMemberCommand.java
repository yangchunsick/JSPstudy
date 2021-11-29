package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.MemberDao;
import dto.Member;

public class InsertMemberCommand implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		try {
			String no = request.getParameter("no");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String birthDay = request.getParameter("birthDay");
			
			if(age < 0 || age > 100) {
				throw new RuntimeException("나이는 0 ~ 100 사이만 가능합니다.");
			}
			
			if(name.isEmpty() || no.isEmpty() || birthDay.isEmpty()) throw new NullPointerException();
			
			Member member = new Member();
			member.setNo(no);
			member.setName(name);
			member.setAge(age);
			member.setBirthDay(birthDay);
			
			int result = MemberDao.getInstance().insertMember(member);
			
			JSONObject obj = new JSONObject();
			obj.put("result", result > 0);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(obj);
			out.close();
			
		} catch (NumberFormatException e) {
			
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("나이는 정수만 입력 가능합니다.");
			response.setStatus(2001);
			
		} catch (NullPointerException e) {
			
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("입력 데이터를 확인하세요.");
			response.setStatus(2005);
			
		} catch (RuntimeException e) {
			
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("나이는 0~100 사이만 입력 가능합니다.");
			response.setStatus(2002);
			
		} catch (SQLIntegrityConstraintViolationException e) {

			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("동일한 회원번호는 입력할 수 없습니다.");
			response.setStatus(2003);
			
		} catch (SQLException e) {

			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("입력 데이터를 확인하세요.");
			response.setStatus(2004);	
			
		} 

	}

}
