package test.com.activity.model;

public class ActivityMain {

	public static void main(String[] args) {
		System.out.println("Hello, Activity()....");
		ActivityVO vo = new ActivityVO();
		ActivityDAO dao = new ActivityDAOimpl();
		// act_name, act_leader
		
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		System.out.println("ts: "+ ts);
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.HOUR, -3);
//	    Timestamp later = new Timestamp(cal.getTime().getTime());
//	    System.out.println("later: "+ later);
//		
		String qstmt = dao.createQuery_qual("남", "서울", 90); // gender/ location/ age
		System.out.println(qstmt);
		boolean flag = dao.isQualified(1500L, "test8", qstmt);
		System.out.println("flag: "+flag);
	} // end main()
} // end class()