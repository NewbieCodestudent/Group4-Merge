package test.com.member.model;

import java.io.Serializable;
import java.util.Objects;

public class MemberVO implements Serializable{
	private String member_id; //회원 아이디
	private String pw; // 회원 비밀번호
	private String name; // 회원 이름
	private String gender; // 회원 성별
	private String birthday; // 회원 생년월일
	private String location; // 회원 지역
	private String img_name; //회원 프로필 사진명
	
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}


	public MemberVO(String member_id, String pw, String name, String gender, String birthday, String location,
			String img_name) {
		super();
		this.member_id = member_id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.location = location;
		this.img_name = img_name;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getImg_name() {
		return img_name;
	}


	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}


	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", pw=" + pw + ", name=" + name + ", gender=" + gender
				+ ", birthday=" + birthday + ", location=" + location + ", img_name=" + img_name + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(birthday, gender, img_name, location, member_id, name, pw);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(gender, other.gender)
				&& Objects.equals(img_name, other.img_name) && Objects.equals(location, other.location)
				&& Objects.equals(member_id, other.member_id) && Objects.equals(name, other.name)
				&& Objects.equals(pw, other.pw);
	}

	
}