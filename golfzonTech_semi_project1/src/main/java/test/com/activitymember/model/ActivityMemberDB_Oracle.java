package test.com.activitymember.model;

public interface ActivityMemberDB_Oracle {

	String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "GOLFMATE";
	String PASSWORD = "admin1234";
	
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
