package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Student;

public class StudentDAO {

	/* Singleton */
	private static StudentDAO instance;

	private StudentDAO() {

	}

	public static StudentDAO getInstance() {
		if (instance == null) {
			instance = new StudentDAO();
		}
		return instance;
	}

	/* field */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;

	/* method */
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "1111";
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	private void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 메소드 구현 **/
	
	/** 학생 목록 반환  **/
	public List<Student> selectStudentList(){
		List<Student> list = new ArrayList<Student>();	// JSON 형식의
		try {
			con = getConnection();
			sql = "SELECT SNO, NAME, MIDTERM, FINALTERM, PASS FROM STUDENT";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {	// rs가 데이터를 가지고 있냐 ?
				Student student = new Student();
				student.setSno(rs.getString(1));	// DB 요소 인덱스 번호
				student.setName(rs.getString(2));	// DB 요소 인덱스 번호
				student.setMidterm(rs.getInt(3));	// DB 요소 인덱스 번호
				student.setFinalterm(rs.getInt(4));	// DB 요소 인덱스 번호
				student.setPass(rs.getString(5));	// DB 요소 인덱스 번호
				list.add(student);					// list에 set 어쩌구 저쩌구를 다 담았음
			}
			
		}catch(Exception e) {
			e.printStackTrace();			
		}finally {
			close(con, ps, rs);
		}
		return list;								// list를 반환
	}
	
	
	
	

}
