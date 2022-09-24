package test.com.club.model;

import java.sql.Date;
import java.util.Objects;

public class ClubVO {
	private long club_id; // 모임ID
	private String club_leader; // 모임장명
	private String club_name; // 모임이름
	private String club_desc; // 모임 설명
	private String gender; // 모임 가입 조건(성별)
	private String age; // 모입 가입 조건 (연령대)
	private String location;
	private Date cdate; // 모임 개설일
	private int close;//공개비공개여부
	private String club_img;
	
	public ClubVO() {
		// TODO Auto-generated constructor stub
	}

	public ClubVO(long club_id, String club_leader, String club_name, String club_desc, String gender, String age,
			String location, Date cdate, int close, String club_img) {
		super();
		this.club_id = club_id;
		this.club_leader = club_leader;
		this.club_name = club_name;
		this.club_desc = club_desc;
		this.gender = gender;
		this.age = age;
		this.location = location;
		this.cdate = cdate;
		this.close = close;
		this.club_img = club_img;
	}

	public long getClub_id() {
		return club_id;
	}

	public void setClub_id(long club_id) {
		this.club_id = club_id;
	}

	public String getClub_leader() {
		return club_leader;
	}

	public void setClub_leader(String club_leader) {
		this.club_leader = club_leader;
	}

	public String getClub_name() {
		return club_name;
	}

	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}

	public String getClub_desc() {
		return club_desc;
	}

	public void setClub_desc(String club_desc) {
		this.club_desc = club_desc;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public int getClose() {
		return close;
	}

	public void setClose(int close) {
		this.close = close;
	}

	public String getClub_img() {
		return club_img;
	}

	public void setClub_img(String club_img) {
		this.club_img = club_img;
	}

	@Override
	public String toString() {
		return "ClubVO [club_id=" + club_id + ", club_leader=" + club_leader + ", club_name=" + club_name
				+ ", club_desc=" + club_desc + ", gender=" + gender + ", age=" + age + ", location=" + location
				+ ", cdate=" + cdate + ", close=" + close + ", club_img=" + club_img + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, cdate, close, club_desc, club_id, club_img, club_leader, club_name, gender, location);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClubVO other = (ClubVO) obj;
		return Objects.equals(age, other.age) && Objects.equals(cdate, other.cdate) && close == other.close
				&& Objects.equals(club_desc, other.club_desc) && club_id == other.club_id
				&& Objects.equals(club_img, other.club_img) && Objects.equals(club_leader, other.club_leader)
				&& Objects.equals(club_name, other.club_name) && Objects.equals(gender, other.gender)
				&& Objects.equals(location, other.location);
	}

	
	
}
