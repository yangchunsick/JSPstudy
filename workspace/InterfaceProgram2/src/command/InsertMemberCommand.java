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
			String no = request.getParameter("no");														// 사용자가 클라이언트에서 입력한 값들을 불러 옴
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String birthDay = request.getParameter("birthDay");
			
			if(age < 0 || age > 100) {								
				throw new RuntimeException("나이는 0 ~ 100 사이만 가능합니다.");						// 나이 입력에 대한 조건을 줬음 0 이상 100이하의 정수만 입력 할 수 있음
			}
			
			if(name.isEmpty() || no.isEmpty() || birthDay.isEmpty()) throw new NullPointerException();	// 이름, 번호, 생년월일을 입력하지 않았을 경우 입력을 막음
			
			Member member = new Member();																// Member dto를 member 객체로 만들어서					
			member.setNo(no);																			// dao를 갈 수 있게 작업 함
			member.setName(name);
			member.setAge(age);
			member.setBirthDay(birthDay);
			
			int result = MemberDao.getInstance().insertMember(member);									// dao에 보냄
			
			JSONObject obj = new JSONObject();															// JSON 방식으로 보낼 것이기 때문에 JSONObject를 obj이라는 객체로 만들어서 저장 했음
			obj.put("result", result > 0);																// result가 0이 아닐 때 실행 (?)

			response.setContentType("application/json; charset=UTF-8");									// JSON 방식으로 인코딩
			PrintWriter out = response.getWriter();														//
			out.println(obj);																			// obj 객체를 jsp로 보냄
			out.close();																				// 정상적으로 실행이 됐을 경우 닫음
			
		} catch (NumberFormatException e) {																// 오류가 발생 했을 경우 catch해서 해당하는 오류를 잡고 예외 처리를 함
																										// 해당하는 오류에 대한 메세지를  사용자에게 알려줌
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
