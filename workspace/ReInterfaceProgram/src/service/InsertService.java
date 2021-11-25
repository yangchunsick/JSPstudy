package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.StudentDAO;
import dto.Student;

public class InsertService implements StudentService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			int midterm = Integer.parseInt(request.getParameter("midterm"));
			int finalterm = Integer.parseInt(request.getParameter("finalterm"));	
						
			double ave = (midterm + finalterm) / 2.0;
			String pass = null;
			if(ave >= 70) {
				pass = "Y";
			}else {
				pass = "N";
			}
			
			Student student = new Student();
			student.setSno(sno);
			student.setName(name);
			student.setMidterm(midterm);
			student.setFinalterm(finalterm);
			student.setPass(pass);
			
			int result = StudentDAO.getInstance().insertStudent(student);
			
			JSONObject obj = new JSONObject();
			obj.put("result", result > 0);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(obj);
			out.close();
			
		}catch (NumberFormatException e) {			// int로 받아야하는데 String이 오거나 , String으로 받아야하는데 int가 오거나 
			response.setContentType("text/plain; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("점수는 정수만 입력 가능 합니다.");
						
			response.setStatus(2001);		
			
		}catch (SQLIntegrityConstraintViolationException e) {
			response.setContentType("text/plain; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("이미 존재하는 학번은 추가할 수 없습니다.");

			response.setStatus(2003);
		} catch (SQLException e) {
			response.setContentType("text/plain; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("잘못된 데이터가 전달 되었습니다.");
			
			response.setStatus(2004);			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
				
		
	}

}
