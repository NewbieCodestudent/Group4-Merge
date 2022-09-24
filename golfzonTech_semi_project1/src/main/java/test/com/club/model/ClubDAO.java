package test.com.club.model;

import java.util.List;

import test.com.member.model.MemberVO;

public interface ClubDAO {
	
	public int insert(ClubVO vo);
	public int update(ClubVO vo);
	public int delete(ClubVO vo);
	public ClubVO selectOne(ClubVO vo);
	public List<String> selectGender(); // 비동기 통신(성별)
	public List<String> selectAge(); // 비동기 통신(연령대)
	public List<String> selectLocation(); // 비동기 통신(지역)
	
	public List<ClubVO> selectAll();
	public List<ClubVO> searchList(String searchKey, String searchWord);

}
