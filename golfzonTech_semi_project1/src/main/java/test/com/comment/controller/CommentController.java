package test.com.comment.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import oracle.sql.TIMESTAMP;
import test.com.comment.model.CommentDAO;
import test.com.comment.model.CommentDAOimpl;
import test.com.comment.model.CommentVO;

/**
 * Servlet implementation class CommentController
 */
@WebServlet({"/comment_insertOK.do", "/comment_deleteOK.do"})
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentDAO dao = new CommentDAOimpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sPath = request.getServletPath();
		System.out.println("doGet(): "+sPath);
		if (sPath.equals("/comment_deleteOK.do")) {
			long club_id = Long.parseLong(request.getParameter("club_id"));
			long board_id = Long.parseLong(request.getParameter("board_id"));
			long comment_id = Long.parseLong(request.getParameter("comment_id"));
			
			CommentVO vo = new CommentVO();
			vo.setComment_id(comment_id);
			
			int result = dao.delete(vo);
			System.out.println("result: "+result);
			
			response.sendRedirect("board_selectOne.do?club_id="+club_id+"&&board_id="+board_id);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sPath = request.getServletPath();
		System.out.println("doPost(): "+sPath);
		if (sPath.equals("/comment_insertOK.do")) {
			String dir_path = request.getServletContext().getRealPath("/uploadimg");
			System.out.println(dir_path);

			int fileSizeMax = 1024 * 1024 * 100;

			boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

			if (isMultipartContent) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(fileSizeMax);

				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한
//				System.out.println("club_id: "+request.getParameter("club_id"));
				long club_id = 0L;
				long board_id = 0L;
				String comment = "";
//				String commenter = (String) session.getAttribute("user_id");
				String commenter = "admin1";

				try {
					List<FileItem> items = sfu.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) { // st_name,,,, 받기
							if (item.getFieldName().equals("club_id")) {
								club_id = Long.parseLong(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("board_id")) {
								board_id = Long.parseLong(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("comment")) {
								comment = item.getString("UTF-8");
							}
						} 
//						else { // file정보받기.
//							fname = FilenameUtils.getName(item.getName());
//
//							File saveFile = new File(dir_path, fname);
//
//							// 파일 경로에 폴더가 없을 경우 파일을 만들어주는 코드
//							if (!saveFile.exists())
//								new File(dir_path).mkdirs();
//
//							try {
//								item.write(saveFile);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//
//						} // end else

					} // end for << items
				} catch (FileUploadException e) {
					e.printStackTrace();
				}

				System.out.println("club_id: "+club_id);
				System.out.println("board_id: "+board_id);
				System.out.println("comment: "+comment);
				System.out.println("commenter: "+commenter);

				CommentVO vo = new CommentVO();
				vo.setBoard_id(board_id);
				vo.setComment(comment);
				vo.setCommenter(commenter);
//				vo.setCdate(new Date(System.currentTimeMillis()));
				System.out.println("vo: "+vo);
				int result = dao.insert(vo);
				System.out.println("result : "+result);
				response.sendRedirect("board_selectOne.do?club_id="+club_id+"&&board_id="+board_id);
//				if (result == 1) {
//					response.sendRedirect("board_selectOne.do?club_id="+club_id+"&&board_id="+board_id);
//				} else {
//					response.sendRedirect("board_insert.do?club_id="+club_id);
//				}
			} // end if ~ else
		}
	}

}
