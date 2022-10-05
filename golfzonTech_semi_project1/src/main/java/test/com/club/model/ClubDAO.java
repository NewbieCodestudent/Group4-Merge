package test.com.club.model;

import java.util.List;

import test.com.clubmember.model.ClubMemberVO;



public interface ClubDAO {
   
   public int insert(ClubVO vo);
   public int update(ClubVO vo);
   public int delete(ClubVO vo);
   public ClubVO selectOne(ClubVO vo);
   public List<ClubVO> selectAll(String order);
   public List<ClubVO> searchList(String query, String searchWord);
   public int insertleader(ClubMemberVO vo);
   public ClubVO leaderinfo(ClubVO vo);
   public List<ClubVO> selectAllMyClub(ClubVO vo);
   public String createQuery_searchList(String location, String gender, int age, String searchKey);
   public List<ClubVO> selectAllByID();
}