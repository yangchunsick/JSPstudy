package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Free;
import mybatis.config.DBService;

public class FreeDao {

	private SqlSessionFactory factory;
	
	private static FreeDao instance;
	private FreeDao() {
		factory = DBService.getInstance().getFactory();
	}
	public static FreeDao getInstance() {
		if(instance == null) {
			instance = new FreeDao();
		}
		return instance;
	}
	
	// 목록 가져오기
	public List<Free> selectFreeList(Map<String, Integer> map){
		SqlSession ss = factory.openSession();
		List<Free> list = ss.selectList("dao.free.selectFreeList", map);
		ss.close();
		return list;
	}
	
	// 전체 게시물 수 구하기
	public int selectTotalCount() {
		SqlSession ss = factory.openSession();
		int totalCount = ss.selectOne("dao.free.selectTotalCount");
		ss.close();
		return totalCount;
	}
	
	/* 게시물 추가하기 */
	public int insertFree(Free free) {
		SqlSession ss = factory.openSession();
		int result = ss.insert("dao.free.insertFree", free);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	/* 게시물 상세 보기 */
	public Free selectFreeByfNo(Long fNo) {
		SqlSession ss = factory.openSession();
		Free free = ss.selectOne("dao.free.selectFreeByfNo", fNo);
		ss.close();
		return free;
	}
	
	/* 게시물 조회수 증가 */
	public int updateHit(Long fNo) {
		SqlSession ss = factory.openSession();
		int result = ss.insert("dao.free.updateHit", fNo);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	/* 게시물 수정 */
	public int updateFree(Free free) {
		SqlSession ss = factory.openSession();
		int result = ss.insert("dao.free.updateFree", free);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	/* 게시물 삭제 status를 0에서 -1로 수정 함으로 삭제 처리가 되는 것임 */
	public int deleteFree(Long fNo) {
		SqlSession ss = factory.openSession();
		int result = ss.insert("dao.free.deleteFree", fNo);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}

	
	
	
	
	
	
}
