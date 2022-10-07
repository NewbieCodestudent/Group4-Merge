package test.com.comment.model;

import java.util.List;

import test.com.board.model.BoardVO;

public interface CommentDAO {

	public int insert(CommentVO vo); // 댓글 작성
	public int delete_one(CommentVO vo); // 댓글 삭제
	public int delete_all(BoardVO vo); // 댓글 삭제
	public boolean isCommenter(CommentVO vo); // 댓글 작성자, 접속자 동일 여부 확인
	public List<CommentVO> selectAll(long board_id); // 댓글 전체조회
	
}
