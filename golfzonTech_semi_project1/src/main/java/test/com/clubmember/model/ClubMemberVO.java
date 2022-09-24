package test.com.clubmember.model;

import java.sql.Date;

public class ClubMemberVO {
	
	private long cm_id; // 모임구성번호
	private long club_id; // 모임ID
	private long memeber_id; // 구성원ID
	private int result; // 가입조건만족여부
	private int status; // 가입상태
	private Date cmdate; // 가입일자
	private int cmtype;  // 멤버구분

}
