package test.com.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import test.com.activity.model.ActivityVO;
import test.com.board.model.BoardDAO;
import test.com.board.model.BoardDAOimpl;
import test.com.board.model.BoardVO;
import test.com.comment.model.CommentDAOimpl;
import test.com.comment.model.CommentVO;

/**
 * Servlet implementation class BoardController
 */
@WebServlet({ "/board_insert.do", "/board_insertOK.do", "/board_update.do", "/board_updateOK.do", "/board_delete.do",
		"/board_deleteOK.do", "/board_selectAll.do", "/board_selectOne.do", "/board_searchList.do" })
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
			Long club_id = Long.parseLong(request.getParameter("club_id"));
			// String member_id = (String) session.getAttribute("user_id");

			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				BoardVO vo = new BoardVO();
				vo.setClub_id(club_id);
				vo.setWriter(member_id);

				boolean isLeader = dao.isLeader(vo);

				request.setAttribute("isLeader", isLeader);
				request.getRequestDispatcher("BOARD/insert.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}

		} else if (sPath.equals("/board_update.do")) {
			BoardVO vo = new BoardVO();

			if (request.getSession().getAttribute("member_id") != null) {
				vo.setBoard_id(Long.parseLong(request.getParameter("board_id")));
				BoardVO vo1 = dao.selectOne(vo);
				request.setAttribute("vo1", vo1);
				System.out.println(vo1);
				request.getRequestDispatcher("BOARD/update.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}

		} else if (sPath.equals("/board_delete.do")) {
			if (request.getSession().getAttribute("member_id") != null) {
				response.sendRedirect("home.do");
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/board_deleteOK.do")) {
			if (request.getSession().getAttribute("member_id") != null) {
				String club_id = request.getParameter("club_id");
				String board_id = request.getParameter("board_id");
				String club_name = request.getParameter("club_name");
				club_name = URLEncoder.encode(club_name, "UTF-8");
				System.out.println(club_id + " : " + board_id);
				if (board_id != null) {
					System.out.println("board_id : " + board_id);
					System.out.println("deleting....");
					BoardVO vo = new BoardVO();
					vo.setBoard_id(Long.parseLong(board_id));
					vo.setClub_id(Long.parseLong(club_id));

					int delete_all_result = new CommentDAOimpl().delete_all(vo);
					System.out.println(delete_all_result);

					BoardDAO dao = new BoardDAOimpl();
					int result = dao.delete(vo);
					if (result == 1) {
						System.out.println("delete num : " + vo.getBoard_id());
						response.sendRedirect("board_selectAll.do?club_id=" + club_id+"&club_name="+club_name);
					} else {
						System.out.println("warning: delete failed...");
						response.sendRedirect("board_delete.do?club_id=" + club_id+"&club_name="+club_name);
					}
				} else {
					System.out.println("return => null");
					response.sendRedirect("board_selectOne.do?club_id=" + club_id + "&club_name="+club_name+"&board_id=" + board_id);
				}
			} else {
				response.sendRedirect("login.do");
			}

		} else if (sPath.equals("/board_selectOne.do")) {
			Long board_id = Long.parseLong(request.getParameter("board_id"));
			Long club_id = Long.parseLong(request.getParameter("club_id"));
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				BoardVO vo = new BoardVO();
				vo.setBoard_id(board_id);
				vo.setClub_id(club_id);
				vo.setWriter(member_id);

				BoardVO vo1 = dao.selectOne(vo);
				List<CommentVO> vos = new CommentDAOimpl().selectAll(board_id);
				boolean isWriter = dao.isWriter(vo);
				boolean isLeader = dao.isLeader(vo);

				request.setAttribute("vo1", vo1);
				request.setAttribute("vos", vos);
				request.setAttribute("isWriter", isWriter);
				request.setAttribute("isLeader", isLeader);

//				System.out.println(vo1);
//				System.out.println(vos);
				request.getRequestDispatcher("BOARD/selectOne.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/board_selectAll.do")) {
			// 정렬 기준을 최신순(내림차순으로 설정)
			// 만약 정렬값 입력 시 입력한 값으로 변경, 입력값 없을 시 desc로 기본 설정

			if (request.getSession().getAttribute("member_id") != null) {
				Long club_id = Long.parseLong(request.getParameter("club_id"));
				String order = request.getParameter("order") == null ? "desc" : request.getParameter("order");
				System.out.println("club_id: " + club_id + ", order: " + order);

				List<BoardVO> vos_notice = dao.selectAll_notice(club_id);
				List<BoardVO> vos_common = dao.selectAll_common(club_id, order);

				System.out.println("vos_notice: " + vos_notice);
				System.out.println("vos_common: " + vos_common);

				request.setAttribute("notices", vos_notice);
				request.setAttribute("commons", vos_common);
				request.getRequestDispatcher("BOARD/selectAll.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/board_searchList.do")) {
			if (request.getSession().getAttribute("member_id") != null) {
				Long club_id = Long.parseLong(request.getParameter("club_id"));
				System.out.println("club_id: "+club_id);
				System.out.println(request.getParameter("searchKey"));
				System.out.println(request.getParameter("searchWord"));
				List<BoardVO> vos_notice = dao.selectAll_notice(club_id);
				List<BoardVO> vos_common = dao.searchList(club_id, request.getParameter("searchKey"),
						request.getParameter("searchWord"));
				request.setAttribute("notices", vos_notice);
				request.setAttribute("commons", vos_common);
				request.getRequestDispatcher("BOARD/selectAll.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sPath = request.getServletPath();
		System.out.println("doPost(): " + sPath);
		if (sPath.equals("/board_insertOK.do")) {

			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				String dir_path = request.getServletContext().getRealPath("/upload");
				System.out.println(dir_path);

				int fileSizeMax = 1024 * 1024 * 100;

				boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

				if (isMultipartContent) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(fileSizeMax);

					ServletFileUpload sfu = new ServletFileUpload(factory);
					sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한
					long club_id = 0L;
					String club_name = null;
					String title = "";
					String content = "";
					String writer = member_id;
					String fname = "";
					int notice = 0;

					try {
						List<FileItem> items = sfu.parseRequest(request);
						for (FileItem item : items) {
							if (item.isFormField()) { // st_name,,,, 받기
								if (item.getFieldName().equals("title")) {
									title = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
								}
								if (item.getFieldName().equals("content")) {
									content = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
								}
								if (item.getFieldName().equals("content")) {
									content = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
								}
								if (item.getFieldName().equals("notice")) {
									notice = Integer.parseInt(item.getString("UTF-8"));
								}
								if (item.getFieldName().equals("club_id")) {
									club_id = Long.parseLong(item.getString("UTF-8"));
								}
								if (item.getFieldName().equals("club_name")) {
									club_name = item.getString("UTF-8");
								}

							} else { // file정보받기.
								fname = FilenameUtils.getName(item.getName());
								if (item.getSize() != 0) {
									fname = writer + "_" + fname;
								}
								File saveFile = new File(dir_path, fname);

								// 파일 경로에 폴더가 없을 경우 파일을 만들어주는 코드
								if (!saveFile.exists()) {
									new File(dir_path).mkdirs();
								}

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

//					System.out.println(club_id);
//					System.out.println(title);
//					System.out.println(content);
//					System.out.println(writer);
//					System.out.println(fname);
//					System.out.println(notice);

					// 에러 발생 상황에 따라서 flag 값을 0, 1, 2로 설정
					// (정상/ 게시글명 누락/ 내용글 누락)
					
					club_name = URLEncoder.encode(club_name, "UTF-8");
					
					int flag = 0;
					if (title == null) {
						System.out.println("null! ---> title: " + title);
						flag = 1;
						request.setAttribute("flag", flag);
						response.sendRedirect("board_insert.do?club_id=" + club_id);
					} else if (content == null) {
						System.out.println("null! ---> content: " + content);
						flag = 2;
						request.setAttribute("flag", flag);
						response.sendRedirect("board_insert.do?club_id=" + club_id);
					} else {
						BoardVO vo = new BoardVO();
						vo.setClub_id(club_id);
						vo.setTitle(title);
						vo.setContent(content);
						vo.setWriter(writer);
						vo.setFname(fname.length() == 0 ? "golfclub.png": fname);
						vo.setNotice(notice);

						int result = dao.insert(vo);
						if (result == 1) {
							response.sendRedirect("board_selectAll.do?club_id=" + club_id+"&club_name="+club_name);
						} else {
							response.sendRedirect("board_insert.do?club_id=" + club_id+"&club_name="+club_name);
						}
					}
				}
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/board_updateOK.do")) {
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {

				String dir_path = request.getServletContext().getRealPath("/upload");
				System.out.println(dir_path);

				int fileSizeMax = 1024 * 1024 * 100;

				boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

				if (isMultipartContent) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(fileSizeMax);

					ServletFileUpload sfu = new ServletFileUpload(factory);
					sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한

//					vo1.setTitle("NOTICE122");
//					vo1.setContent("NOTICE221_UPDATED again");
//					vo1.setFname("fname.jpg");
//					vo1.setNotice(1);
//					vo1.setBoard_id(5);

					long club_id = 0L;
					String club_name = null;
					long board_id = 0L;
					String title = "";
					String content = "";
					String writer = "member_id";
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
									content = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
								}
								if (item.getFieldName().equals("club_name")) {
									club_name = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
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
								if (item.getSize() != 0) {
									fname = writer + "_" + fname;
								}
								File saveFile = new File(dir_path, fname);

								// 파일 경로에 폴더가 없을 경우 파일을 만들어주는 코드
								if (!saveFile.exists()) {
									new File(dir_path).mkdirs();
								}

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

					club_name = URLEncoder.encode(club_name, "UTF-8");
					
					// 에러 발생 상황에 따라서 flag 값을 0, 1, 2로 설정
					// (정상/ 게시글명 누락/ 내용글 누락)
					int flag = 0;
					if (title == null) {
						System.out.println("null! ---> title: " + title);
						flag = 1;
						request.setAttribute("flag", flag);
						response.sendRedirect("board_insert.do?club_id=" + club_id);
					} else if (content == null) {
						System.out.println("null! ---> content: " + content);
						flag = 2;
						request.setAttribute("flag", flag);
						response.sendRedirect("board_insert.do?club_id=" + club_id);
					} else {

						BoardVO vo = new BoardVO();
						vo.setTitle(title);
						vo.setContent(content);
						vo.setFname(fname.length() == 0 ? "golfclub.png": fname);
						vo.setNotice(notice);
						vo.setBoard_id(board_id);

						int result = dao.update(vo);
						if (result == 1) {
							response.sendRedirect("board_selectOne.do?club_id=" + club_id + "&board_id=" + board_id+"&club_name="+club_name);
						} else {
							response.sendRedirect("board_update.do?club_id=" + club_id + "&board_id=" + board_id+"&club_name="+club_name);
						}
					}
				} // end if ~ else << inner

			} else {
				response.sendRedirect("login.do");
			}
		} // end if ~ else << outer
	} // end doPost()...
} // end class
