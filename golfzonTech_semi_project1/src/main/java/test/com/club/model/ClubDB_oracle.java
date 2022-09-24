package test.com.club.model;

public interface ClubDB_oracle {
	String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "GOLFMATE";
	String PASSWORD = "admin1234";
	
	String SQL_INSERT = "insert into club(club_id,club_leader,club_name,club_desc,gender,age,"
			+ "location,cdate,close,club_img) values(seq_club.nextval,?,?,?,?,?,?,sysdate,?,?)";
	
//	String SQL_SELECT_ALL_new = "select * from club order by club_id desc"; 
	String SQL_SELECT_ALL_new = "select club_id, club_leader,club_name,cdate,club_img from club order by club_id desc";
	String SQL_SELECT_ALL_clubname = "select * from club order by clubname asc";
//	String SQL_SELECT_ALL_clubcount = "select * from club order by;//뷰쓰기
	String SQL_SELECT_ONE = "select * from club where club_id=?";
	String SQL_UPDATE = "update club set club_name=?,club_desc=?,gender=?,age=?,location=?,close=?,club_img=? where club_id=?";
}
