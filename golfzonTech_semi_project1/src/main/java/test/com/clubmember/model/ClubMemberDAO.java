package test.com.clubmember.model;

import java.util.List;

import test.com.club.model.ClubVO;

public interface ClubMemberDAO {
	public int insert(ClubMemberVO vo);//가입대기신청
	public int update(ClubMemberVO vo);//가입승인
	public int update_cmtype(ClubMemberVO vo);//권한부여,삭제
	public int delete(ClubMemberVO vo);//모임 탈퇴(개인)
	public int kick(ClubMemberVO vo);//모임원 강퇴(모임장)
//	public String invite(MemberVO vo); // 비공개 초대 (return String URL)
//	public int decline(); // 초대 거절
	public ClubMemberVO selectOne(ClubMemberVO vo);//??
	public List<ClubMemberVO> clubmembers(ClubVO vo);//모임가입멤버 목록
	public List<ClubMemberVO> waiting(ClubVO vo);//모임가입대기멤버 목록
	public List<ClubMemberVO> selectAll();//??
	public String createQuery(String location, String gender, int age);
	boolean isqualified(long club_id, String member_id, String query);
	boolean isMember(ClubVO vo);
	ClubMemberVO selectStatus(ClubMemberVO vo);

}
