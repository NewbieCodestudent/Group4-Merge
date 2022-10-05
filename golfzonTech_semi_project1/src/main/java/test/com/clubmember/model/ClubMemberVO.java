package test.com.clubmember.model;

import java.sql.Date;
import java.util.Objects;

public class ClubMemberVO {
	
	private long cm_id; // 모임구성원번호
	private long club_id; // 모임ID
	private String member_id; // 구성원ID
	private int qualified; // 가입조건만족여부
	private int status; // 가입상태
	private Date cmdate; // 가입일자
	private int cmtype;  // 멤버구분

	private String name; // 회원 이름
	private String m_gender; // 회원 성별
	private Date birthday; // 회원 생년월일
	private String m_location; // 회원 지역
	private String img_name;
	private int m_age;
	
	public ClubMemberVO() {
		// TODO Auto-generated constructor stub
	}

	public long getCm_id() {
		return cm_id;
	}

	public void setCm_id(long cm_id) {
		this.cm_id = cm_id;
	}

	public long getClub_id() {
		return club_id;
	}

	public void setClub_id(long club_id) {
		this.club_id = club_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getQualified() {
		return qualified;
	}

	public void setQualified(int qualified) {
		this.qualified = qualified;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCmdate() {
		return cmdate;
	}

	public void setCmdate(Date cmdate) {
		this.cmdate = cmdate;
	}

	public int getCmtype() {
		return cmtype;
	}

	public void setCmtype(int cmtype) {
		this.cmtype = cmtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getM_gender() {
		return m_gender;
	}

	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getM_location() {
		return m_location;
	}

	public void setM_location(String m_location) {
		this.m_location = m_location;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public int getM_age() {
		return m_age;
	}

	public void setM_age(int m_age) {
		this.m_age = m_age;
	}

	@Override
	public String toString() {
		return "ClubMemberVO [cm_id=" + cm_id + ", club_id=" + club_id + ", member_id=" + member_id + ", qualified="
				+ qualified + ", status=" + status + ", cmdate=" + cmdate + ", cmtype=" + cmtype + ", name=" + name
				+ ", m_gender=" + m_gender + ", birthday=" + birthday + ", m_location=" + m_location + ", img_name="
				+ img_name + ", m_age=" + m_age + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, club_id, cm_id, cmdate, cmtype, img_name, m_age, m_gender, m_location, member_id,
				name, qualified, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClubMemberVO other = (ClubMemberVO) obj;
		return Objects.equals(birthday, other.birthday) && club_id == other.club_id && cm_id == other.cm_id
				&& Objects.equals(cmdate, other.cmdate) && cmtype == other.cmtype
				&& Objects.equals(img_name, other.img_name) && m_age == other.m_age
				&& Objects.equals(m_gender, other.m_gender) && Objects.equals(m_location, other.m_location)
				&& Objects.equals(member_id, other.member_id) && Objects.equals(name, other.name)
				&& qualified == other.qualified && status == other.status;
	}

	
	
}
