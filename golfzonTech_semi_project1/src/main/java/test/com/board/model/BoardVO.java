package test.com.board.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class BoardVO {
	private long board_id; // 게시글 번호
	private long club_id; // 해당 모임ID
	private String writer; // 작성자 id 
	private String title; // 게시글 제목
	private String content; // 게시글 내용
	private String fname; // 업로드 파일명
	private Timestamp wdate; // 작성일자
	private int notice; // 공지글 여부 (일반글: 0, 공지글: 1)
	// generating constructor
	public BoardVO() {
//		System.out.println("BoardVO()...");
	}
	// generating getter and setter
	public long getBoard_id() {
		return board_id;
	}
	public void setBoard_id(long board_id) {
		this.board_id = board_id;
	}
	public long getClub_id() {
		return club_id;
	}
	public void setClub_id(long club_id) {
		this.club_id = club_id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Timestamp getWdate() {
		return wdate;
	}
	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}
	public int getNotice() {
		return notice;
	}
	public void setNotice(int notice) {
		this.notice = notice;
	}
	// generating hashcode and equals
	@Override
	public int hashCode() {
		return Objects.hash(board_id, club_id, content, fname, notice, title, wdate, writer);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardVO other = (BoardVO) obj;
		return board_id == other.board_id && club_id == other.club_id && Objects.equals(content, other.content)
				&& Objects.equals(fname, other.fname) && notice == other.notice && Objects.equals(title, other.title)
				&& Objects.equals(wdate, other.wdate) && Objects.equals(writer, other.writer);
	}
	// generating toString()
	@Override
	public String toString() {
		return "BoardVO [board_id=" + board_id + ", club_id=" + club_id + ", writer=" + writer + ", title=" + title
				+ ", content=" + content + ", fname=" + fname + ", wdate=" + wdate + ", notice=" + notice + "]";
	}
}
