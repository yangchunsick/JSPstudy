package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Board;
import mybatis.config.DBService;

public class BoardDao {
	
	/* MyBatis는 SqlSession 객체를 이용해서 DB에 접근한다. */
	/* SqlSession 객체는 메소드 별로 만들고 닫는 것이 좋다. SqlSession.close */
	/* SqlSession 객체를 만들 수 있는 SqlSessionFactory를 준비해 둔다. */
	
	
	/*DBSerivce에서 구현 했음*/
	private SqlSessionFactory factory; 
	
																				/* DBService랑 깐부사이라고 생각하셈 */
	/* singleton noticeDao 자기 자신을 객체로 만드는 행위 */
	private static BoardDao instance;
	private BoardDao() {
		factory = DBService.getInstance().getFactory(); /* factory는 DBService로부터 얻어 왔다 */
	}
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	/* 목록 추가 */
	public int insertBoard(Board board) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.board.insertBoard", board);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	/* 목록 불러오기 */
	public List<Board> selectBoardList(){
		SqlSession ss = factory.openSession();
		List<Board> list = ss.selectList("dao.board.selectBoardList");
		ss.close();
		return list;
	}
	
	/* 상세 보기 */
	public Board selectBoardView(Long bNo) {
		SqlSession ss = factory.openSession();
		Board board = ss.selectOne("dao.board.selectBoardView", bNo);
		ss.close();
		return board;
	}
}
