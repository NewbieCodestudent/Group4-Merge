package test.com.club.model;

import java.sql.Date;
import java.util.Objects;

public class ClubVO {
	private long club_id; // 모임ID
	private String club_leader; // 모임장명
	private String club_name; // 모임이름
	private String club_desc; // 모임 설명
	private String gender; // 모임 가입 조건(성별)
	private int age; // 모입 가입 조건 (연령대)
	private String location;
	private Date cdate; // 모임 개설일
	private int close;//공개비공개여부
	private String club_img;
	
	//클럽리더 insert
	private long cm_id;
	private String member_id;
	private int qualified;
	private int status;
	private Date cmdate;
	private int cmtype;
	
	//member 테이블
//	private String member_id; //회원 아이디
	private String name; // 회원 이름
	private String m_gender; // 회원 성별
	private Date birthday; // 회원 생년월일
	private String m_location; // 회원 지역
	private String img_name;
	private int m_age;
	
	public ClubVO() {
		// TODO Auto-generated constructor stub
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




	public int getAge() {
		return age;
	}




	public void setAge(int age) {
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




	public long getCm_id() {
		return cm_id;
	}




	public void setCm_id(long cm_id) {
		this.cm_id = cm_id;
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
		return "ClubVO [club_id=" + club_id + ", club_leader=" + club_leader + ", club_name=" + club_name
				+ ", club_desc=" + club_desc + ", gender=" + gender + ", age=" + age + ", location=" + location
				+ ", cdate=" + cdate + ", close=" + close + ", club_img=" + club_img + ", cm_id=" + cm_id
				+ ", member_id=" + member_id + ", qualified=" + qualified + ", status=" + status + ", cmdate=" + cmdate
				+ ", cmtype=" + cmtype + ", name=" + name + ", m_gender=" + m_gender + ", birthday=" + birthday
				+ ", m_location=" + m_location + ", img_name=" + img_name + ", m_age=" + m_age + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, birthday, cdate, close, club_desc, club_id, club_img, club_leader, club_name, cm_id,
				cmdate, cmtype, gender, img_name, location, m_age, m_gender, m_location, member_id, name, qualified,
				status);
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
		return Objects.equals(age, other.age) && Objects.equals(birthday, other.birthday)
				&& Objects.equals(cdate, other.cdate) && close == other.close
				&& Objects.equals(club_desc, other.club_desc) && club_id == other.club_id
				&& Objects.equals(club_img, other.club_img) && Objects.equals(club_leader, other.club_leader)
				&& Objects.equals(club_name, other.club_name) && cm_id == other.cm_id
				&& Objects.equals(cmdate, other.cmdate) && cmtype == other.cmtype
				&& Objects.equals(gender, other.gender) && Objects.equals(img_name, other.img_name)
				&& Objects.equals(location, other.location) && m_age == other.m_age
				&& Objects.equals(m_gender, other.m_gender) && Objects.equals(m_location, other.m_location)
				&& Objects.equals(member_id, other.member_id) && Objects.equals(name, other.name)
				&& qualified == other.qualified && status == other.status;
	}

	
}
