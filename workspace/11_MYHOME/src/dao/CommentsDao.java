package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Comments;
import mybatis.config.DBService;

public class CommentsDao {
	
	/* MyBatis는 SqlSession 객체를 이용해서 DB에 접근한다. */
	/* SqlSession 객체는 메소드 별로 만들고 닫는 것이 좋다. SqlSession.close */
	/* SqlSession 객체를 만들 수 있는 SqlSessionFactory를 준비해 둔다. */
	
	
	/*DBSerivce에서 구현 했음*/
	private SqlSessionFactory factory; 
	
																				/* DBService랑 깐부사이라고 생각하셈 */
	/* singleton noticeDao 자기 자신을 객체로 만드는 행위 */
	private static CommentsDao instance;
	private CommentsDao() {
		factory = DBService.getInstance().getFactory(); /* factory는 DBService로부터 얻어 왔다 */
	}
	public static CommentsDao getInstance() {
		if(instance == null) {
			instance = new CommentsDao();
		}
		return instance;
	}
	
	/* 추가 */
	public int insertComments(Comments comment) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.comments.insertComments", comment);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	public List<Comments> selectCommentsList(Long bNo){
		SqlSession ss = factory.openSession();
		List<Comments> list = ss.selectList("dao.comments.selectCommentsList", bNo);
		ss.close();
		return list;
	}
	
	
	  /* delete comment : update */
	   public int deleteComments(Long cNo) {
	      SqlSession ss = factory.openSession(false);
	      int result = ss.update("dao.comments.deleteComments", cNo);
	      if(result > 0) ss.commit();
	      ss.close();
	      return result;
	   }
	


	
}
