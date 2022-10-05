package test.com.activity.model;

public interface ActivityDB_Oracle {

	String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "GOLFMATE";
	String PASSWORD = "admin1234";
	
	String SQL_ACTIVITY_INSERT = "INSERT INTO ACTIVITY (ACT_ID,CC_ID,CLUB_ID,ACT_LEADER,ACT_NAME, ACT_CONTENT, GENDER, AGE, LOCATION, COST, RDATE, ADATE, FNAME, STATUS)"
			+ "VALUES(SEQ_ACTIVITY.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	String SQL_ACTIVITY_UPDATE = "UPDATE ACTIVITY SET CC_ID = ?, GENDER = ?, AGE = ?, LOCATION = ?, ACT_CONTENT = ?, COST = ?, RDATE = ?, ADATE = ?, FNAME = ?, ACT_NAME = ? WHERE ACT_ID = ?";
	String SQL_ACTIVITY_DELETE = "DELETE FROM ACTIVITY WHERE ACT_ID = ?";
	String SQL_ACTIVITY_DELETE_ALL = "DELETE FROM ACTIVITY WHERE CLUB_ID = ?";
	String SQL_ACTIVITY_SELECT_ONE = "select act_name, act_content,a.location, a.gender, fname, a.age, cost, rdate, adate, status,a.cc_id, cc_name, a.club_id, club_name "
			+ "from activity a left outer join cc c on a.cc_id = c.cc_id "
			+ "left outer join club c on a.club_id =c.club_id where act_id = ?";
	// SQL_ACTIVITY_SELECT_ALL_COMMONS_DESC: 최신 액티비티가 위로 오도록 정렬
	String SQL_ACTIVITY_SELECT_ALL_ID = "select a.act_id,a.club_id, a.status, a.fname, a.act_name, m.img_name, a.act_leader, a.rdate, a.adate "
			+ "from activity a left outer join member m on a.act_leader = m.member_id where status = 0 and club_id = ? order by a.act_id desc";
	// SQL_ACTIVITY_SELECT_ALL_COMMONS_ASC: 오래된 액티비티가 위로 오도록 정렬
	String SQL_ACTIVITY_SELECT_ALL_ADATE = "select a.act_id,a.club_id, a.status, a.fname, a.act_name, m.img_name, a.act_leader, a.rdate, a.adate "
			+ "from activity a left outer join member m on a.act_leader = m.member_id where status = 0 and club_id = ? order by a.adate asc";
	String SQL_ACTIVITY_SELECT_ALL_ID_EXPIRED = "select a.act_id,a.club_id, a.status, a.fname, a.act_name, m.img_name, a.act_leader, a.rdate, a.adate "
			+ "from activity a left outer join member m on a.act_leader = m.member_id where status = 1 order by a.act_id desc";
	// SQL_ACTIVITY_SELECT_ALL_COMMONS_ASC: 오래된 액티비티가 위로 오도록 정렬
	String SQL_ACTIVITY_SELECT_ALL_ADATE_Activity = "select a.act_id, a.status, a.fname, a.act_name, m.img_name, a.act_leader, a.rdate, a.adate "
			+ "from activity a left outer join member m on a.act_leader = m.member_id  where status = 1 order by a.adate asc";
	String SQL_ACTIVITY_FIND_CLUB_BY_ID = "select club_id, club_name from club where club_id = any(select club_id from CLUBMEMBER where member_id = ? and status = 1)";
	String SQL_ACTIVITY_FIND_CC_ID = "select * from cc";
	String SQL_FIND_LEADER_BY_ID = "select img_name, name, gender, birth, member_id from member where member_id = (select member_id from act_member where act_id = ? and amtype = 1)";
	String SQL_FIND_ACT_ID = "select max(act_id) as act_id from activity where ACT_LEADER = ? and ACT_NAME = ?";
	String SQL_ACTIVITY_IS_WRITER = "SELECT * FROM ACTIVITY_MEMBER WHERE ACT_ID=? AND MEMBER_ID= ?";
	String SQL_ACTIVITY_CHANGE_STATUS_SIZE = "UPDATE ACTIVITY SET STATUS = ? WHERE ACT_ID = ?";
	String SQL_ACTIVITY_CHANGE_STATUS_DATE = "UPDATE ACTIVITY SET STATUS = 1 WHERE CURRENT_TIMESTAMP > ADATE";
	String SQL_ACTIVITY_SELECT_ALL_BY_ID = "select act_id, act_name, activity.club_id, club_name, adate, rdate, fname, status from activity left outer join club on activity.club_id = club.club_id where act_id = any(select act_id from act_member where member_id = ?) order by act_id desc";
	String SQL_ACTIVITY_SELECT_ALL_BY_CLUB = "select act_id, act_name, club_id, act_leader, adate, rdate, fname, status from activity where status = 0 and club_id = ? order by act_id desc";
	String SQL_ACTIVITY_SELECT_ALL_BY_LOC = "select act_id, club_id, act_name, act_leader, fname, adate, rdate, img_name, fname from activity a left outer join member m on a.act_leader = m.member_id where status = 0 and club_id = 0 and a.location = ? order by act_id desc";
	String SQL_ACTIVITY_SELECT_ALL_BY_DATE = "select act_id, club_id, act_name, act_leader, fname, adate, rdate, img_name, fname from activity a left outer join member m on a.act_leader = m.member_id where status = 0 and club_id = 0 order by adate asc";
	String SQL_ACTIVITY_GET_CLUB_NAME = "select club_name from club where club_id = ?";
	
}
