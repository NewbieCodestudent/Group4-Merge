package test.com.activitymember.model;

public interface ActivityMemberDB_Oracle {

	String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "GOLFMATE";
	String PASSWORD = "admin1234";
	
	String SQL_ACTIVITY_INSERT = "INSERT INTO ACTIVITY (ACT_ID,CC_ID,CLUB_ID,ACT_LEADER,ACT_NAME, ACT_CONTENT, GENDER, AGE, LOCATION, COST, RDATE, ADATE, FNAME, STATUS)"
			+ "VALUES(SEQ_ACTIVITY.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	// SQL_ACTIVITY_SELECT_ALL_COMMONS_DESC: 최신 액티비티가 위로 오도록 정렬
	String SQL_ACTIVITY_SELECT_ALL_ID = "select a.act_id, a.status, a.fname, a.act_name, m.img_name, a.act_leader, a.rdate, a.adate "
			+ "from activity a left outer join member m on a.act_leader = m.member_id order by a.act_id desc";
	// SQL_ACTIVITY_SELECT_ALL_COMMONS_ASC: 오래된 액티비티가 위로 오도록 정렬
	String SQL_ACTIVITY_SELECT_ALL_ADATE = "select a.act_id, a.status, a.fname, a.act_name, m.img_name, a.act_leader, a.rdate, a.adate "
			+ "from activity a left outer join member m on a.act_leader = m.member_id order by a.adate asc";
	String SQL_ACTIVITY_FIND_CLUB_BY_ID = "select club_id, club_name from club where club_id = any(select club_id from CLUBMEMBER where member_id = ? and status = 1)";
	String SQL_ACTIVITY_FIND_CC_ID = "select * from cc";
	String SQL_FIND_LEADER_BY_ID = "select img_name, name, gender, birth, member_id from member where member_id = (select member_id from act_member where act_id = ? and amtype = 1)";
	String SQL_FIND_ACT_ID = "select max(act_id) as act_id from activity where ACT_LEADER = ? and ACT_NAME = ?";
	
	String SQL_ACT_MEMBER_INSERT = "INSERT INTO ACT_MEMBER (AM_ID,MEMBER_ID,ACT_ID,QUALIFIED, APPROVED, AMTYPE) "
			+ "VALUES(SEQ_ACT_MEMBER.nextval,?,?,?,?,?)";
	String SQL_ACT_MEMBER_JOIN = "select * from act_member where act_id = ? and approved = 1 order by amtype desc";
	String SQL_ACT_MEMBER_NOT_JOIN = "select * from act_member where act_id = ? and approved = 0";
	String SQL_ACT_MEMBER_UPDATE = "update act_member set APPROVED=? where am_id = ?";
	String SQL_ACT_MEMBER_DELETE_ONE = "delete from act_member where am_id = ?";
	String SQL_ACT_MEMBER_DELETE_ALL = "delete from act_member where act_id = ?";
	String SQL_ACT_MEMBER_IS_ACT_LEADER = "select member_id from act_member where act_id = ? and amtype = 1 and member_id = ?";
	String SQL_ACT_MEMBER_GET_APPLIED_MEMBER = "select am_id, member_id from act_member where act_id=? and member_id = ?";
	
	
}
