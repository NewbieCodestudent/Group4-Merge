package test.com.comment.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class CommentVO {
	private long comment_id; // 댓글 번호
	private long board_id; // 게시글번호
	private String comment; // 댓글 내용
	private String commenter; // 댓글 작성자
	private Timestamp cdate; // 댓글 작성 일자
	public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}
	public long getBoard_id() {
		return board_id;
	}
	public void setBoard_id(long board_id) {
		this.board_id = board_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	public Timestamp getCdate() {
		return cdate;
	}
	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}
	public long getComment_id() {
		return comment_id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(board_id, cdate, comment, comment_id, commenter);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentVO other = (CommentVO) obj;
		return board_id == other.board_id && Objects.equals(cdate, other.cdate)
				&& Objects.equals(comment, other.comment) && comment_id == other.comment_id
				&& Objects.equals(commenter, other.commenter);
	}
	@Override
	public String toString() {
		return "CommentVO [comment_id=" + comment_id + ", board_id=" + board_id + ", comment=" + comment
				+ ", commenter=" + commenter + ", cdate=" + cdate + "]";
	}
}
