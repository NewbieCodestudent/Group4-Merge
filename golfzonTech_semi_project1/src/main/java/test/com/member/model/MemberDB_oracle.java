package test.com.member.model;

public interface MemberDB_oracle {
	String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "GOLFMATE";
	String PASSWORD = "admin1234";
	
	String SQL_INSERT = "insert into member(member_id,pw,name,gender,birth,location,img_name) "
			+ "values(?,?,?,?,?,?,?)";
	String SQL_UPDATE = "update member set pw=?,location=?,img_name=? where member_id=?";
	String SQL_DELETE = "delete from member where member_id=?";
	String SQL_SELECT_ONE = "select * from member where member_id=?";
	String SQL_ID_CHECK = "select * from member where member_id=?";
	String SQL_LOGIN = "select * from member where member_id=? and pw=?";
	
	String SQL_CAL_AGE = "update member set m_age=TRUNC(MONTHS_BETWEEN(sysdate, birth)/12) where member_id=?";
}
