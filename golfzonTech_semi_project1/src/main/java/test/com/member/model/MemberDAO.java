package test.com.member.model;

import java.util.List;

public interface MemberDAO {
	
	public int insert(MemberVO vo);
	public int update(MemberVO vo);
	public int delete(MemberVO vo);
	public MemberVO selectOne(MemberVO vo);
	public MemberVO idCheck(MemberVO vo);
	public MemberVO login(MemberVO vo);
	public List<String> selectGender(); // 비동기 통신(성별)
	public List<String> selectLocation(); // 비동기 통신(지역)
}
