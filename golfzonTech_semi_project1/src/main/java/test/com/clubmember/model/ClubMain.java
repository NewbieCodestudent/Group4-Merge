package test.com.clubmember.model;

public class ClubMain {

	public static void main(String[] args) {


		ClubMemberDAO  dao = new ClubMemberDAOimpl();
		String query = dao.createQuery("서울", "남", 70);
		System.out.println(query);
	}

}
