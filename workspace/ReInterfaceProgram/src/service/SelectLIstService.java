package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.StudentDAO;
import dto.Student;

public class SelectLIstService implements StudentService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/** 학생 목록을 JSON 형식으로 변경한 뒤 응답 처리 **/

		// 1. DB에서 학생 목록을 가져오기 // StudentDAO를 이용하여 목록을 가져옴
		List<Student> list = StudentDAO.getInstance().selectCommentsList();		// Dao에서 SelectStudent를 가져와라
		
		// 2. 가져온 학생 목록을  JSON 형식으로 변경하기
		JSONArray students = new JSONArray(list);	// list를 JSON 형식으로 변환
		
		// 3. 변경한 JSON 형식의 목록을 응답 처리하기 (response)
		response.setContentType("application/json; charset=UTF-8");		// JSON 형식으로 응답 하기
		
		PrintWriter out = response.getWriter();
		out.println(students);	// success function() 괄호 안으로 가는 거임
		out.close();
		
		
	}

}
