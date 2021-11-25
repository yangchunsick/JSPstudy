package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.StudentDAO;
import dto.Student;

class StudentTestCase {

	@BeforeEach
	void 선행작업(){
		
		Student student = new Student();
		student.setSno("33333");
		student.setName("테스트학생");
		student.setMidterm(50);
		student.setFinalterm(100);
		int result = 0;
				try{
					StudentDAO.getInstance().insertStudent(student);
				}catch(Exception e){
					assertEquals(0, result, "학생 등록에 문제가 있습니다.");														
				}
	}	
	
	
	
	@AfterEach
	void 후처리작업() throws Exception {
		int result = StudentDAO.getInstance().deleteStudent("33333");
		assertEquals(1, result, "알수 없는 오류");
	}
	
	@Test
	void test() {
		System.out.println("ss");
	}
	
}
