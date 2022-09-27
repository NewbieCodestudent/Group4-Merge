package test.com.clubmember.model;

import java.util.List;

import test.com.club.model.ClubVO;
import test.com.member.model.MemberVO;

public interface ClubMemberDAO {
	//clubmemberdao 보류..
	public int insert(ClubMemberVO vo);
	public int update(ClubMemberVO vo);
	public int delete_one(ClubMemberVO vo);
	public int delete_all(ClubVO vo);
//	public String invite(MemberVO vo); // 비공개 초대 (return String URL)
//	public int accept(); // 초대 승낙
//	public int decline(); // 초대 거절
	public ClubMemberVO selectOne(ClubMemberVO vo);
	public List<ClubMemberVO> selectAll();
	public List<ClubMemberVO> searchList(String searchKey, String searchWord);

}
