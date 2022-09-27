package test.com.club.model;

public interface ClubDB_oracle {
   String DRIVER_NAME = "oracle.jdbc.OracleDriver";
   String URL = "jdbc:oracle:thin:@localhost:1521:xe";
   String USER = "GOLFMATE";
   String PASSWORD = "admin1234";
   
   String SQL_INSERT = "insert into club(club_id,club_leader,club_name,club_desc,gender,age,"
         + "location,cdate,close,club_img) values(?,?,?,?,?,?,?,sysdate,?,?)";
   
//   String SQL_SELECT_ALL_new = "select * from club order by club_id desc"; 
   String SQL_SELECT_ALL_new = "select club_id, club_leader,club_name,cdate,club_img from club order by club_id desc";
   String SQL_SELECT_ALL_clubname = "select * from club order by clubname asc";
//   String SQL_SELECT_ALL_clubcount = "select * from club order by;//뷰쓰기
   String SQL_SELECT_ONE = "select * from club where club_id=?";
   String SQL_UPDATE = "update club set club_name=?,club_desc=?,gender=?,age=?,location=?,close=?,club_img=? where club_id=?";
   String SQL_DELETE = "delete from club where club_id=?";
   
   //공개모임의 모임명,모임리더,성별,나이,지역 검색
   String SQL_SEARCH_LIST_CLUB_NAME = "select * from club where club_name like ? and close='0' order by club_id desc";
   String SQL_SEARCH_LIST_CLUB_LEADER = "select * from club where club_leader like ? and close='0' order by club_id desc";
   String SQL_SEARCH_LIST_GENDER = "select * from club where gender like ? and close='0' order by club_id desc";
   String SQL_SEARCH_LIST_AGE = "select * from club where age=? and close='0' order by club_id desc";
   String SQL_SEARCH_LIST_LOCATION = "select * from club where location like ? and close='0' order by club_id desc";
   
   String SQL_INSERT_CLUBLEADER = "insert into clubmember(cm_id,club_id,member_id,qualified,status,cmdate,cmtype) "
                           + "values(seq_clubmember.nextval,?,?,1,1,sysdate,2)";
   String SQL_CLUB_ID = "select seq_club.nextval from dual";
   
   String SQL_LEADERINFO = "select m.name, m.gender, m.birth, m.location ,m.img_name, m.m_age "
         + "from club c left outer join member m on c.club_leader = m.member_id where c.club_id=?";


}