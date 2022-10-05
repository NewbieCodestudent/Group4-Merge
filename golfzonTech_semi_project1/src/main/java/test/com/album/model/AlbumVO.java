package test.com.album.model;

import java.sql.Timestamp;
import java.util.Objects;

public class AlbumVO {
	private long album_id; // 앨범 게시글 번호
	private long club_id; // 모임 id
	private String title; // 앨범 제목
	private String fname; // 앨범 파일명
	private Timestamp wdate; // 업로드 일자
	private String writer; // 업로드한 회원의 id (Session)
	
	public AlbumVO() {
		super();
	}
	public long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(long album_id) {
		this.album_id = album_id;
	}
	public long getClub_id() {
		return club_id;
	}
	public void setClub_id(long club_id) {
		this.club_id = club_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(album_id, club_id, fname, title, wdate, writer);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumVO other = (AlbumVO) obj;
		return album_id == other.album_id && Objects.equals(club_id, other.club_id)
				&& Objects.equals(fname, other.fname) && Objects.equals(title, other.title)
				&& Objects.equals(wdate, other.wdate) && Objects.equals(writer, other.writer);
	}
	
	@Override
	public String toString() {
		return "AlbumVO [album_id=" + album_id + ", club_id=" + club_id + ", title=" + title + ", fname=" + fname
				+ ", wdate=" + wdate + ", writer=" + writer + "]";
	}
}
