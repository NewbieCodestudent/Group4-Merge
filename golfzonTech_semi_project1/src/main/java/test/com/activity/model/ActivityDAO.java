package test.com.activity.model;

import java.util.List;
import java.util.Map;

import test.com.club.model.ClubVO;

public interface ActivityDAO {
	
	public Map<Long, Integer> insert(ActivityVO vo); // 액티비티 생성
	public int update(ActivityVO vo); // 액티비티 설정
	public int delete(ActivityVO vo); // 액티비티 삭제
	public int deleteAll(ClubVO vo); // 액티비티 삭제 (해당 클럽의 모든 액티비티 삭제 => 액티비티원까지 자동 삭제)
	public int changeStatusBySize(ActivityVO vo); 
	public void changeStatusByDate(); 
	public String createQuery_search(String location, String gender, String searchKey, int age);
	public String createQuery_qual(String location, String gender, int age);
	public String getClubName(ClubVO vo);
	public boolean isLeader(ActivityVO vo);
	public boolean isQualified(Long act_id, String member_id, String qstmt);
	public Map<Long, String> findClubById(ActivityVO vo);
	public Map<Long, String> findCC_Id();
	public ActLeaderVO findLeaderById(ActivityVO vo);
	public ActivityVO selectOne(ActivityVO vo); // 액티비티 선택
	public List<ActivityVO> selectAll(String order, long club_id); // 전체 액티비티 출력
	public List<ActivityVO> selectAllByID(String member_id); // 접속자가 가입한 전체 액티비티 출력
	public List<ActivityVO> selectAllByClub(Long club_id); // 접속자가 가입한 전체 액티비티 출력
	public List<ActivityVO> selectAllByLoc(String location); // 지정 지역의 전체 액티비티 출력 (최신 상위 5개 선정)
	public List<ActivityVO> selectAllByDate(); // 최신 액티비티 순으로 상위 5개 액티비티 출력
	public List<ActivityVO> searchList(long club_id, String qstmt, String searchWord); // 액티비티 키워드 출력

}
