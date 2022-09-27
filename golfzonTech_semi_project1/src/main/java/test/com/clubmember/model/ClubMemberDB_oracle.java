package test.com.clubmember.model;

public interface ClubMemberDB_oracle {
   String DRIVER_NAME = "oracle.jdbc.OracleDriver";
   String URL = "jdbc:oracle:thin:@localhost:1521:xe";
   String USER = "GOLFMATE";
   String PASSWORD = "admin1234";
   //가입만족여부는 일단 1로 해놓고 나중에 가입조건비교로직 구현할떼 ?로 수정필요
   String SQL_INSERT = "insert into clubmember(cm_id,club_id,member_id,qualified,status,cmdate,cmtype) "
         + "values(seq_cm_id.nextval,?,?,1,1,sysdate,0)";
   String SQL_DELETE_ALL = "delete from clubmember where club_id = ?";
   
}