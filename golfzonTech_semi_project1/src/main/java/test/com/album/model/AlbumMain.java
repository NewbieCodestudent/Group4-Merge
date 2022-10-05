package test.com.album.model;

import oracle.sql.TIMESTAMP;

public class AlbumMain {
	public static void main(String[] args) {
		System.out.println("Hello, main....");
		AlbumVO vo = new AlbumVO();
		AlbumDAO dao = new AlbumDAOimpl();
		vo.setClub_id(20);
		vo.setTitle("fist_trial");
		vo.setFname("test.jpg");
		vo.setWriter("test_writer");
		int flag = dao.insert(vo);
		System.out.println("flag: "+flag);
	} // end main()
} // end class