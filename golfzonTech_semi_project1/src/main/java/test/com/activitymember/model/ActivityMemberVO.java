package test.com.activitymember.model;

import java.util.Objects;

public class ActivityMemberVO {
	private long am_id; // 액티비티 참가원 아이디
	private String member_id; // 회원 아이디
	private long act_id; // 액티비티 아이디
	private int qualified; // 가입 자격 충족 시 1
	private int approved; // 가입 승인 시 1
	private int amtype; // 리더일 시 0, 회원일 시 1
	public ActivityMemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getAm_id() {
		return am_id;
	}
	public void setAm_id(long am_id) {
		this.am_id = am_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public long getAct_id() {
		return act_id;
	}
	public void setAct_id(long act_id) {
		this.act_id = act_id;
	}
	public int getQualified() {
		return qualified;
	}
	public void setQualified(int qualified) {
		this.qualified = qualified;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public int getAmtype() {
		return amtype;
	}
	public void setAmtype(int amtype) {
		this.amtype = amtype;
	}
	@Override
	public int hashCode() {
		return Objects.hash(act_id, am_id, amtype, approved, member_id, qualified);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityMemberVO other = (ActivityMemberVO) obj;
		return act_id == other.act_id && am_id == other.am_id && amtype == other.amtype && approved == other.approved
				&& Objects.equals(member_id, other.member_id) && qualified == other.qualified;
	}
	@Override
	public String toString() {
		return "ActivityMemberVO [am_id=" + am_id + ", member_id=" + member_id + ", act_id=" + act_id + ", qualified="
				+ qualified + ", approved=" + approved + ", amtype=" + amtype + "]";
	}
} // end class