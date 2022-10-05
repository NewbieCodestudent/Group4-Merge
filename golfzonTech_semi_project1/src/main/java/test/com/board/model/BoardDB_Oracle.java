package test.com.board.model;

public interface BoardDB_Oracle {

	String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "GOLFMATE";
	String PASSWORD = "admin1234";
	
	String SQL_BOARD_INSERT = "INSERT INTO BOARD (BOARD_ID,CLUB_ID,WRITER,TITLE,CONTENT,FNAME,WDATE,NOTICE) "
			+ "VALUES(SEQ_BOARD.nextval,?,?,?,?,?,sysdate,?)";
	String SQL_BOARD_UPDATE = "UPDATE BOARD SET TITLE = ?,CONTENT = ?,FNAME = ?,WDATE = SYSDATE ,NOTICE = ? WHERE BOARD_ID = ?";
	String SQL_BOARD_DELETE = "delete from board where board_id = ?";
	//  SQL_BOARD_SELECT_ALL_NOTICE: 최신 공지글이 위로 오도록 정렬
	String SQL_BOARD_SELECT_ALL = "select * from (select board_id, title, club_id, writer, wdate, notice from board where club_id = ? order by wdate desc) where rownum <= 4";
	String SQL_BOARD_SELECT_ALL_NOTICE = "select * from (select * from board where board.club_id =  ?)"
			+ "where notice = 1 order by WDATE desc, Board_id desc";
	// SQL_BOARD_SELECT_ALL_COMMONS_DESC: 최신 게시글이 위으로 오도록 정렬
	String SQL_BOARD_SELECT_ALL_COMMONS_DESC = "select * from (select * from board where board.club_id =  ?) "
			+ "where notice = 0 order by WDATE desc, Board_id desc";
	// SQL_BOARD_SELECT_ALL_COMMONS_ASC: 최신 게시글이 밑으로 오도록 정렬
	String SQL_BOARD_SELECT_ALL_COMMONS_ASC = "select * from (select * from board where board.club_id =  ?)"
			+ "where notice = 0 order by WDATE asc, Board_id desc";
	String SQL_BOARD_SELECT_ONE = "select * from board where board_id = ?";
	String SQL_BOARD_IS_CLUBMEMBER = "SELECT * FROM (SELECT * FROM CLUBMEMBER WHERE CLUB_ID = ? AND STATUS = 1) WHERE MEMBER_ID = ?";
	String SQL_BOARD_IS_WRITER = "SELECT * FROM BOARD WHERE BOARD_ID=? AND WRITER= ?";
	String SQL_BOARD_IS_MEMBER = "SELECT * FROM CLUBMEMBER WHERE CLUB_ID = ? AND MEMBER_ID = ?";
	String SQL_BOARD_IS_LEADER = "SELECT * FROM CLUBMEMBER WHERE CLUB_ID = ? AND MEMBER_ID = ? and CMTYPE = ANY(1, 2)";
	String SQL_BOARD_SEARCH_LIST_TITLE = "SELECT * FROM (SELECT * FROM BOARD WHERE CLUB_ID = ?) WHERE NOTICE = 0 AND UPPER(TITLE) LIKE UPPER(?)";
	String SQL_BOARD_SEARCH_LIST_CONTENT = "SELECT * FROM (SELECT * FROM BOARD WHERE CLUB_ID = ?) WHERE NOTICE = 0 AND UPPER(CONTENT) LIKE UPPER(?)";
	
}
