package test.com.clubmember.model;

import java.sql.Date;
import java.util.Objects;

public class ClubMemberVO {
   
   private long cm_id; // 모임구성원번호
   private long club_id; // 모임ID
   private String member_id; // 구성원ID
   private int result; // 가입조건만족여부
   private int status; // 가입상태
   private Date cmdate; // 가입일자
   private int cmtype;  // 멤버구분

   public ClubMemberVO() {
      // TODO Auto-generated constructor stub
   }

   public ClubMemberVO(long cm_id, long club_id, String member_id, int result, int status, Date cmdate, int cmtype) {
      super();
      this.cm_id = cm_id;
      this.club_id = club_id;
      this.member_id = member_id;
      this.result = result;
      this.status = status;
      this.cmdate = cmdate;
      this.cmtype = cmtype;
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

   public int getResult() {
      return result;
   }

   public void setResult(int result) {
      this.result = result;
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

   @Override
   public int hashCode() {
      return Objects.hash(club_id, cm_id, cmdate, cmtype, member_id, result, status);
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
      return club_id == other.club_id && cm_id == other.cm_id && Objects.equals(cmdate, other.cmdate)
            && cmtype == other.cmtype && Objects.equals(member_id, other.member_id) && result == other.result
            && status == other.status;
   }

   @Override
   public String toString() {
      return "ClubMemberVO [cm_id=" + cm_id + ", club_id=" + club_id + ", member_id=" + member_id + ", result="
            + result + ", status=" + status + ", cmdate=" + cmdate + ", cmtype=" + cmtype + "]";
   }

   
   
}