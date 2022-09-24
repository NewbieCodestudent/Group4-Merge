package test.com.board.model;

import java.util.ArrayList;
import java.util.List;

public class BoardMain {

	public static void main(String[] args) {
		System.out.println("Hello, Board....");
		BoardVO vo = new BoardVO();
		BoardDAO dao = new BoardDAOimpl();
//		vo.setBoard_id(1);
//		vo.setClub_id(1);
//		vo.setTitle("title");
//		vo.setContent("content");
//		vo.setWriter("kim");
//		vo.setFname("img_0001.png");
//		System.out.println("vo: "+vo);
//		List<BoardVO> vos= dao.searchList(20, "title", "n");
//		System.out.println(vos);
		vo.setBoard_id(0);
		vo.setWriter(null);
		
	} // end main()
} // end class