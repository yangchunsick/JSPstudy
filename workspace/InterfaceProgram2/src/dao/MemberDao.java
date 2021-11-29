package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDao {
	
	private static MemberDao instance;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
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
			if (con != null) {con.close();}
			if (ps != null) {ps.close();}
			if (rs != null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 목록 가져오기 */
	public List<Member> selectMemberList(){
		List<Member> list = new ArrayList<Member>();
		try {
			con = getConnection();
			sql = "SELECT NO, NAME, AGE, BIRTHDAY, REGDATE, SYSDATE FROM MEMBER";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setNo(rs.getString(1));
				member.setName(rs.getString(2));
				member.setAge(rs.getInt(3));
				member.setBirthDay(rs.getString(4));
				member.setRegDate(rs.getDate(5));
				list.add(member);				
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	/* 등록 */
	
	public int insertMember(Member member) throws SQLIntegrityConstraintViolationException, SQLException{
		int result = 0;
		con = getConnection();
		sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, SYSDATE)";
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getNo());
		ps.setString(2, member.getName());
		ps.setInt(3, member.getAge());
		ps.setString(4, member.getBirthDay());
		result = ps.executeUpdate();
		close(con, ps, null);
		return result;
	}
	
	public int deleteMember(String no) {
		int result = 0;
		try {
			con = getConnection();
			sql = "DELETE FROM MEMBER WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, no);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
}
