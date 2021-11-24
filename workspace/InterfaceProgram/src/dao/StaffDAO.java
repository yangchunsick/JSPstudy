package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Staff;
import mybatis.config.DBService;

public class StaffDAO {
	
	private SqlSessionFactory factory;
	private static StaffDAO instance = new StaffDAO();
	private StaffDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static StaffDAO getInstance() {
		return instance;
	}
	
	/*********/
	
	public List<Staff> selectStaffListt(){
		SqlSession ss = factory.openSession();
		List<Staff> list = ss.selectList("dao.staff.selectStaffListt");
		ss.close();
		return list;
	}
	
	public int insertStaff(Staff staff) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.staff.insertStaff", staff);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	/****/	
	
	public Staff selecStaffBysNo(String sNo) {
		SqlSession ss = factory.openSession();
		Staff staff = ss.selectOne("dao.staff.selecStaffBysNo", sNo);
		ss.close();
		return staff;
	}
	
	

}
