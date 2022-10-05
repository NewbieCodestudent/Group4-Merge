package test.com.clubmember.model;

public interface ClubMemberDB_oracle {
	String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "GOLFMATE";
	String PASSWORD = "admin1234";
	String SQL_INSERT = "insert into clubmember(cm_id,club_id,member_id,qualified,status,cmdate,cmtype) "
			+ "values(seq_clubmember.nextval,?,?,1,0,sysdate,0)";
	String SQL_DELETE = "delete from clubmember where cm_id=(select cm_id from clubmember where member_id=? and club_id=?)";
	String SQL_SELECT_ALL = "select club_id, club_leader,club_name,cdate,club_img from club order by club_id desc";
	String SQL_SELECT_ALL_WAIT = "select cm.cm_id, cm.cmtype, m.name, m.gender, m.m_age,cm.status from clubmember cm "
			+ "left outer join member m on cm.member_id = m.member_id where cm.club_id=? and cm.status=0" ;
	String SQL_SELECT_ALL_CLUBMEMBERS = "select cm.cm_id, cm.cmtype, m.name, m.gender, m.m_age, cm.status from clubmember cm "
			+ "left outer join member m on cm.member_id = m.member_id where cm.club_id=? and cm.status=1 order by cm.cmtype desc";
	String SQL_ACCEPT = "update clubmember set status=1 where cm_id=?";
	String SQL_GRANT_REVOKE = "update clubmember set cmtype=? where cm_id=?";
	String SQL_KICK = "delete from clubmember where cm_id=?";
	String SQL_SELECT_ONE = "select * from clubmember where cm_id=?";
	String SQL_COMPARE = "select * from club, member where club_id=? and member_id=?";

}
