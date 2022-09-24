package test.com.board.controller;

import java.io.File;
import java.io.IOException;
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
import org.apache.commons.io.FilenameUtils;

import test.com.board.model.BoardDAO;
import test.com.board.model.BoardDAOimpl;
import test.com.board.model.BoardVO;
import test.com.comment.model.CommentDAOimpl;
import test.com.comment.model.CommentVO;

/**
 * Servlet implementation class BoardController
 */
@WebServlet({ "/board_insert.do", "/board_insertOK.do", "/board_update.do", "/board_updateOK.do", "/board_delete.do", "/board_deleteOK.do",
		"/board_selectAll.do", "/board_selectOne.do", "/board_searchListOK.do" })
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardDAO dao = new BoardDAOimpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sPath = request.getServletPath();
		System.out.println("doGet(): " + sPath);
		if (sPath.equals("/board_insert.do")) {
//			System.out.println(request.getParameter("club_id"));
			request.getRequestDispatcher("board/insert.jsp").forward(request, response);
		} else if (sPath.equals("/board_update.do")) {
			BoardVO vo = new BoardVO();
			vo.setBoard_id(Long.parseLong(request.getParameter("board_id")));
			BoardVO vo1 = dao.selectOne(vo);
			request.setAttribute("vo1", vo1);
			System.out.println(vo1);
			request.getRequestDispatcher("board/update.jsp").forward(request, response);
		} else if (sPath.equals("/board_delete.do")) {
			request.getRequestDispatcher("board/delete.jsp").forward(request, response);
		} else if (sPath.equals("/board_deleteOK.do")) {
			String club_id = request.getParameter("club_id");
			String board_id = request.getParameter("board_id");
			System.out.println(club_id +" : "+ board_id);
			if (board_id != null) {
				System.out.println("board_id : " + (Long.parseLong(board_id)));
				System.out.println("deleting....");
				BoardVO vo = new BoardVO();
				vo.setBoard_id(Long.parseLong(board_id));

				BoardDAO dao = new BoardDAOimpl();
				int result = dao.delete(vo);
				if (result == 1) {
					System.out.println("delete num : " + vo.getBoard_id());
					response.sendRedirect("board_selectAll.do?club_id="+club_id);
				} else {
					System.out.println("warning: delete failed...");
					response.sendRedirect("board_delete.do?club_id=" + club_id);
				}
			} else {
				System.out.println("return => null");
				response.sendRedirect("board_selectOne.do?club_id="+club_id+"&&board_id=" + board_id);
			}

		} else if (sPath.equals("/board_selectOne.do")) {
//			System.out.println(request.getParameter("club_id"));
			Long board_id = Long.parseLong(request.getParameter("board_id"));
			BoardVO vo = new BoardVO();
			vo.setBoard_id(board_id);
			BoardVO vo1 = dao.selectOne(vo);
			List<CommentVO> vos = new CommentDAOimpl().selectAll(board_id);
			request.setAttribute("vo1", vo1);
			request.setAttribute("vos", vos);
			System.out.println(vo1);
			System.out.println(vos);
			request.getRequestDispatcher("board/selectOne.jsp").forward(request, response);
		} else if (sPath.equals("/board_selectAll.do")) {
			// 정렬 기준을 최신순(내림차순으로 설정)
			// 만약 정렬값 입력 시 입력한 값으로 변경, 입력값 없을 시 desc로 기본 설정
			Long club_id = Long.parseLong(request.getParameter("club_id"));
			String order = request.getParameter("order") == null ? "desc" : request.getParameter("order");
			System.out.println("club_id: " + club_id + ", order: " + order);

			List<BoardVO> vos_notice = dao.selectAll_notice(club_id);
			List<BoardVO> vos_common = dao.selectAll_common(club_id, order);

			System.out.println("vos_notice: " + vos_notice);
			System.out.println("vos_common: " + vos_common);
			
			request.setAttribute("notices", vos_notice);
			request.setAttribute("commons", vos_common);

			request.getRequestDispatcher("board/selectAll.jsp").forward(request, response);
		} else if (sPath.equals("/board_searchListOK.do")) {
			Long club_id = Long.parseLong(request.getParameter("club_id"));
//			System.out.println("club_id: "+club_id);
			System.out.println(request.getParameter("searchKey"));
			System.out.println(request.getParameter("searchWord"));
			List<BoardVO> vos = dao.searchList(club_id, request.getParameter("searchKey"), request.getParameter("searchWord"));
			request.setAttribute("vos", vos);
			request.getRequestDispatcher("board/searchList.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sPath = request.getServletPath();
		System.out.println("doPost(): " + sPath);
		if (sPath.equals("/board_insertOK.do")) {
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
				long club_id = Long.parseLong(request.getParameter("club_id"));
				String title = "";
				String content = "";
				String writer = "admin1";
				String fname = "";
				int notice = 0;

				try {
					List<FileItem> items = sfu.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) { // st_name,,,, 받기
							if (item.getFieldName().equals("title")) {
								title = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("content")) {
								content = item.getString("UTF-8");
							}
//							if (item.getFieldName().equals("writer")) {
//								writer = item.getString("UTF-8");
//							}
							if (item.getFieldName().equals("notice")) {
								notice = Integer.parseInt(item.getString("UTF-8"));
							}

						} else { // file정보받기.
							fname = FilenameUtils.getName(item.getName());

							File saveFile = new File(dir_path, fname);

							// 파일 경로에 폴더가 없을 경우 파일을 만들어주는 코드
							if (!saveFile.exists())
								new File(dir_path).mkdirs();

							try {
								item.write(saveFile);
							} catch (Exception e) {
								e.printStackTrace();
							}

						} // end else

					} // end for << items
				} catch (FileUploadException e) {
					e.printStackTrace();
				}

				System.out.println(club_id);
				System.out.println(title);
				System.out.println(content);
				System.out.println(writer);
				System.out.println(fname);
				System.out.println(notice);

				BoardVO vo = new BoardVO();
				vo.setClub_id(club_id);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setFname(fname.length() == 0 ? "img_0001.png" : fname);
				vo.setNotice(notice);

				int result = dao.insert(vo);
				if (result == 1) {
					response.sendRedirect("board_selectAll.do?club_id="+club_id);
				} else {
					response.sendRedirect("board_insert.do?club_id="+club_id);
				}
			} // end if ~ else
		} else if (sPath.equals("/board_updateOK.do")) {
			String dir_path = request.getServletContext().getRealPath("/uploadimg");
			System.out.println(dir_path);
			
			int fileSizeMax = 1024 * 1024 * 100;

			boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

			if (isMultipartContent) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(fileSizeMax);

				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한

//				vo1.setTitle("NOTICE122");
//				vo1.setContent("NOTICE221_UPDATED again");
//				vo1.setFname("fname.jpg");
//				vo1.setNotice(1);
//				vo1.setBoard_id(5);

				long club_id = 0L;
				long board_id = 0L;
				String title = "";
				String content = "";
				String fname = "";
				int notice = 0;

				try {
					List<FileItem> items = sfu.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) { // st_name,,,, 받기
							if (item.getFieldName().equals("title")) {
								title = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("content")) {
								content = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("notice")) {
								notice = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("club_id")) {
								club_id = Long.parseLong(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("board_id")) {
								board_id = Long.parseLong(item.getString("UTF-8"));
							}

						} else { // file정보받기.
							fname = FilenameUtils.getName(item.getName());

							File saveFile = new File(dir_path, fname);

							// 파일 경로에 폴더가 없을 경우 파일을 만들어주는 코드
							if (!saveFile.exists())
								new File(dir_path).mkdirs();

							try {
								item.write(saveFile);
							} catch (Exception e) {
								e.printStackTrace();
							}

						} // end else

					} // end for << items
				} catch (FileUploadException e) {
					e.printStackTrace();
				}

				System.out.println(title);
				System.out.println(content);
				System.out.println(fname);
				System.out.println(notice);
				System.out.println(club_id);
				System.out.println(board_id);

				BoardVO vo = new BoardVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setFname(fname.length() == 0 ? "img_0001.png" : fname);
				vo.setNotice(notice);
				vo.setBoard_id(board_id);

				int result = dao.update(vo);
				if (result == 1) {
					response.sendRedirect("board_selectOne.do?club_id="+club_id+"&&board_id="+board_id);
				} else {
					response.sendRedirect("board_update.do?club_id="+club_id+"&&board_id=" + board_id);
				}
			} // end if ~ else << inner
		} // end if ~ else << outer
	} // end doPost()...
} // end class
