package test.com.activity.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ActivityVO {
	private long act_id; // 액티비티ID
	private String act_leader; // 액티비티 개설자(id)
	private String leader_name; // 액티비티 주최자(name)
	private int cc_id; // ccID
	private String cc_name; // cc명
	private long club_id; // 비공개여부 (클럽ID)
	private String club_name; // 비공개여부 (클럽명)
	private String act_name; // 액티비티명
	private String act_content; // 액티비티 설명
	private String gender; // 액티비티 가입조건(성별)
	private int age; // 액티비티 가입조건(연령대)
	private String location; // 액티비티 가입조건(지역)
	private int cost; // 액티비티 비용 (조건X)
	private Timestamp rdate; // 액티비티 라운딩 날짜
	private Timestamp adate; // 액티비티 모집 마감일자
	private String act_fname; // 액티비티 등록 파일명
	private int status; // 액티비티 마감 여부
	private String leader_fname; // 액티비티 리더 프로필 사진명 -> member
	public ActivityVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLeader_name() {
		return leader_name;
	}
	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}
	public String getCc_name() {
		return cc_name;
	}
	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}
	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}
	public long getClub_id() {
		return club_id;
	}
	public void setClub_id(long club_id) {
		this.club_id = club_id;
	}
	public long getAct_id() {
		return act_id;
	}
	public void setAct_id(long act_id) {
		this.act_id = act_id;
	}
	public String getAct_leader() {
		return act_leader;
	}
	public void setAct_leader(String act_leader) {
		this.act_leader = act_leader;
	}
	public int getCc_id() {
		return cc_id;
	}
	public void setCc_id(int cc_id) {
		this.cc_id = cc_id;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getAct_content() {
		return act_content;
	}
	public void setAct_content(String act_content) {
		this.act_content = act_content;
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Timestamp getRdate() {
		return rdate;
	}
	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	public Timestamp getAdate() {
		return adate;
	}
	public void setAdate(Timestamp adate) {
		this.adate = adate;
	}
	public String getAct_fname() {
		return act_fname;
	}
	public void setAct_fname(String act_fname) {
		this.act_fname = act_fname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLeader_fname() {
		return leader_fname;
	}
	public void setLeader_fname(String leader_fname) {
		this.leader_fname = leader_fname;
	}
	@Override
	public int hashCode() {
		return Objects.hash(act_content, act_fname, act_id, act_leader, act_name, adate, age, cc_id, cc_name, club_id,
				club_name, cost, gender, leader_fname, leader_name, location, rdate, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityVO other = (ActivityVO) obj;
		return Objects.equals(act_content, other.act_content) && Objects.equals(act_fname, other.act_fname)
				&& act_id == other.act_id && Objects.equals(act_leader, other.act_leader)
				&& Objects.equals(act_name, other.act_name) && Objects.equals(adate, other.adate) && age == other.age
				&& cc_id == other.cc_id && Objects.equals(cc_name, other.cc_name) && club_id == other.club_id
				&& Objects.equals(club_name, other.club_name) && cost == other.cost
				&& Objects.equals(gender, other.gender) && Objects.equals(leader_fname, other.leader_fname)
				&& Objects.equals(leader_name, other.leader_name) && Objects.equals(location, other.location)
				&& Objects.equals(rdate, other.rdate) && status == other.status;
	}
	@Override
	public String toString() {
		return "ActivityVO [act_id=" + act_id + ", act_leader=" + act_leader + ", leader_name=" + leader_name
				+ ", cc_id=" + cc_id + ", cc_name=" + cc_name + ", club_id=" + club_id + ", club_name=" + club_name
				+ ", act_name=" + act_name + ", act_content=" + act_content + ", gender=" + gender + ", age=" + age
				+ ", location=" + location + ", cost=" + cost + ", rdate=" + rdate + ", adate=" + adate + ", act_fname="
				+ act_fname + ", status=" + status + ", leader_fname=" + leader_fname + "]";
	}
}
