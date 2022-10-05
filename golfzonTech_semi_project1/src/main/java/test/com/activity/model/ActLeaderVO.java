package test.com.activity.model;

import java.util.Objects;

public class ActLeaderVO {
	private long act_id;
	private String leader_id;
	private String fname;
	private String name;
	private String gender;
	private int age;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getAct_id() {
		return act_id;
	}
	public String getLeader_id() {
		return leader_id;
	}
	public void setLeader_id(String leader_id) {
		this.leader_id = leader_id;
	}
	public void setAct_id(long act_id) {
		this.act_id = act_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public int hashCode() {
		return Objects.hash(act_id, age, fname, gender, leader_id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActLeaderVO other = (ActLeaderVO) obj;
		return act_id == other.act_id && age == other.age && Objects.equals(fname, other.fname)
				&& Objects.equals(gender, other.gender) && Objects.equals(leader_id, other.leader_id)
				&& Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "ActLeaderVO [act_id=" + act_id + ", leader_id=" + leader_id + ", fname=" + fname + ", name=" + name
				+ ", gender=" + gender + ", age=" + age + "]";
	}
}
