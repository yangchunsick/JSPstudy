package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDAO {

	private static MemberDAO instance;

	private MemberDAO() {

	}

	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
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
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null)
				con.close();
			if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**/
	
	public List<Member> selectMemberList(){
		List<Member> list = new ArrayList<Member>();
		try {
			con = getConnection();
			sql = "SELECT NO, NAME, AGE, BIRTHDAY, REGDATE FROM MEMBER ORDER BY NO ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setNo(rs.getString(1));
				member.setName(rs.getString(2));
				member.setAge(rs.getInt(3));
				member.setBirthday(rs.getString(4));
				member.setRegdate(rs.getDate(5));
				list.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	public int insertMember(Member member)throws Exception{
		int result = 0;
		con = getConnection();
		sql = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, SYSDATE)";
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getNo());
		ps.setString(2, member.getName());
		ps.setInt(3, member.getAge());
		ps.setString(4, member.getBirthday());
		result = ps.executeUpdate();
		close(con, ps, rs);
		return result;
	}
	
	
	
	
	
	

}
