package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BoardDTO;
import dto.ReplyDTO;
import mybatis.config.DBService;

public class BoardDAO {

	/* StudentDao의 모든 메소드는 factory에서 SqlSession을 얻어 낸다. */
	private SqlSessionFactory factory;
	private static BoardDAO instance;
	private BoardDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	// 1. board list
	public List<BoardDTO> selectBoardList() {
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("dao.board.selectBoardList");
		ss.close();
		return list;
	}
	
	// 2. total Count
	public int getTotalCount() {
		SqlSession ss = factory.openSession();
		int totalCount = ss.selectOne("dao.board.getTotalCount");		// select로 돌리고 결과는 한 줄이다.
		ss.close();
		return totalCount;
	}
	
	
	// 3. insert board content
	public int insertBoard(BoardDTO boardDTO) {
		SqlSession ss = factory.openSession(false); // prevent auto commit
		int result = ss.insert("dao.board.insertBoard", boardDTO);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	// 4. select one
	public BoardDTO selectBoardByNo(Long no) {
		SqlSession ss = factory.openSession();
		BoardDTO boardDTO = ss.selectOne("dao.board.selectBoardByNo", no);
		ss.close();
		return boardDTO;
	}
	
	//  update board content's hit
	public int updateBoardHit (Long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.board.updateBoardHit", no);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	// 5. insert reply
	public int insertReply(ReplyDTO replyDTO) {		// Reply type, reply 
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.board.insertReply", replyDTO);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	// 6. select replies list
	public List<ReplyDTO> selectReplyList(Long no) {	// 받아와서 넘겨주기.
		SqlSession ss = factory.openSession();
		List<ReplyDTO> list = ss.selectList("dao.board.selectReplyList", no);	// 여기로 넘겨줌
		ss.close();
		return list;
	}
	
	// max hit
	public List<BoardDTO> selectMaxHit() {
		SqlSession ss = factory.openSession();
		List<BoardDTO> maxHit = ss.selectList("dao.board.selectMaxHit");
		ss.close();
		return maxHit;
	}	
	
	// 7. delete board
	public int deleteBoard(Long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("dao.board.deleteBoard", no);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}

	
	
}
