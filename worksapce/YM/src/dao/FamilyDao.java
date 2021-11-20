package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Family;
import mybatis.config.DBService;

public class FamilyDao {

	/* Dao의 모든 메소드는 factory에서 SqlSession을 얻어 낸다.*/
	private SqlSessionFactory factory;
	
	private static FamilyDao instance;
	private FamilyDao() {
		factory = DBService.getInstance().getFactory();
	}
	
	public static FamilyDao getInstance() {
		if(instance == null) {
			instance = new FamilyDao();
		}
		return instance;
	}
	
	/* 자바와 연결하기 위해 */
	
	/* Family 목록 */
	public List<Family> selectAllFamily(){
		SqlSession ss = factory.openSession();
		List<Family> list = ss.selectList("dao.family.selectAllFamily");
		ss.close();
		return list;
	}

	/* 전체 수 구하기 */
	public int getTotalCount() {
		SqlSession ss = factory.openSession();
		int totalCount = ss.selectOne("dao.family.getTotalCount");
		ss.close();
		return totalCount;
	}
	
	/* 조직 추가하기 */
	public int insertFamily(Family family) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.family.insertFamily", family);
		ss.commit();
		ss.close();
		return result;
	}
	
	/* 조직원 정보 조회 */
	public Family selectFamilyInfoByNum(int num) {
		SqlSession ss = factory.openSession();
		Family family = ss.selectOne("dao.family.selectFamilyInfoByNum", num);
		ss.close();
		return family;
	}
	
	/* 조직원 정보 수정 */
	public int updateFamily(Family family) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.family.updateFamily", family);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	/* 조직원 정보 삭제 */
	public int deleteFamily(int num) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("dao.family.deleteFamily", num);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
