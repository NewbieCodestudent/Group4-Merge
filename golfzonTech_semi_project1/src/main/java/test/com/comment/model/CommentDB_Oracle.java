package test.com.comment.model;

public interface CommentDB_Oracle {

	String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "GOLFMATE";
	String PASSWORD = "admin1234";
	
	String SQL_COMMENT_INSERT = "INSERT INTO CMT (COMMENT_ID,BOARD_ID,COMMENTER,CONTENT,CDATE)VALUES(SEQ_CMT.nextval,?,?,?, TO_DATE(SYSDATE))";
	String SQL_COMMENT_DELETE = "DELETE FROM CMT WHERE COMMENT_ID = ?";
	String SQL_COMMENT_SELECT_ALL = "SELECT * FROM CMT WHERE BOARD_ID = ? ORDER BY CDATE DESC, COMMENT_ID DESC";
	String SQL_COMMENT_IS_COMMENTER = "SELECT * FROM CMT WHERE COMMENT_ID=? AND COMMENTER=?";
}