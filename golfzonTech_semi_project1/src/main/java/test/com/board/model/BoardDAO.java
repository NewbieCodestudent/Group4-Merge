package test.com.board.model;

import java.util.List;

public interface BoardDAO {
	public int insert(BoardVO vo);
	public int update(BoardVO vo);
	// 게시글 수정 (공지글 수정 기능 포함), 일반글 -> 게시글 wdate 갱신
	public int delete(BoardVO vo);
	public boolean isWriter(BoardVO vo);
	public BoardVO selectOne(BoardVO vo);
	public List<BoardVO> selectAll_notice(long club_id); // 공지글을 불러오는 매서드
	public List<BoardVO> selectAll_common(long club_id, String order); // 일반 게시글을 불러오는 매서드 (정렬방식: order -> desc/ asc)
	public List<BoardVO> searchList(long club_id, String searchKey, String searchWord); // 일반 게시글 중 키워드 검색을 하는 매서드
}