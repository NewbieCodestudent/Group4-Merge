package test.com.activitymember.model;

import java.util.List;

import test.com.activity.model.ActivityVO;

public interface ActivityMemberDAO {
	
	public int insert(ActivityMemberVO vo);
	public int update(ActivityMemberVO vo);
	public int deleteOne(ActivityMemberVO vo);
	public int deleteAll(ActivityVO vo);
	public boolean isActLeader(ActivityMemberVO vo);
	public ActivityMemberVO getAppliedMember(ActivityMemberVO vo);
	public ActivityMemberVO selectOne(ActivityMemberVO vo);
	public List<ActivityMemberVO> selectALL_joined(ActivityMemberVO vo);
	public List<ActivityMemberVO> selectALL_not_joined(ActivityMemberVO vo);

}
