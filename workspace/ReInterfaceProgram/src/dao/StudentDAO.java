package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Student;

public class StudentDAO {

	private static StudentDAO instance;

	private StudentDAO() {

	}

	public static StudentDAO getInstance() {
		if (instance == null) {
			instance = new StudentDAO();
		}
		return instance;
	}


	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;


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
		List<Student> list = new ArrayList<Student>();
		try {
			con = getConnection();
			sql = "SELECT SNO, NAME, MIDTERM, FINALTERM, PASS FROM STUDENT";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setSno(rs.getString(1));	
				student.setName(rs.getString(2));	
				student.setMidterm(rs.getInt(3));	
				student.setFinalterm(rs.getInt(4));	
				student.setPass(rs.getString(5));	
				list.add(student);					
			}
			
		}catch(Exception e) {
			e.printStackTrace();			
		}finally {
			close(con, ps, rs);
		}
		return list;								
	}

	public int insertStudent(Student student) throws Exception {
		int result = 0;
		con = getConnection();
		sql = "INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, student.getSno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getMidterm());
		ps.setInt(4, student.getFinalterm());
		ps.setString(5, student.getPass());
		result = ps.executeUpdate();
		close(con, ps, null);
		return result;
	}
	
	public int deleteStudent(String sno) throws Exception {
		int result = 0;
		try {
			con = getConnection();
			sql = "DELETE FROM STUDENT WHERE SNO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, sno);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
		
	}
	
	
	
	

}
