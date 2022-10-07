package test.com.album.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
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

import test.com.activity.model.ActivityVO;
import test.com.activitymember.model.ActivityMemberDAOimpl;
import test.com.activitymember.model.ActivityMemberVO;
import test.com.album.model.AlbumDAO;
import test.com.album.model.AlbumDAOimpl;
import test.com.album.model.AlbumVO;

/**
 * 1. 작성자: 이주희 (백엔드)
 * 2. 기능: 앨범의 CRUD기능 구현
 */
@WebServlet({ "/album_insertOK.do", "/album_updateOK.do", "/album_deleteOK.do", "/album_selectAll.do", "/album_searchList.do" })
public class AlbumController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlbumDAO dao = new AlbumDAOimpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlbumController() {
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
		if (sPath.equals("/album_selectAll.do")) {
			if (request.getSession().getAttribute("member_id") != null) {
				long club_id = Long.parseLong(request.getParameter("club_id"));
				// 정렬 기준을 최신 순으로 설정
				String order = request.getParameter("order") == null ? "desc" : request.getParameter("order");
//				System.out.println("club_id: " + club_id+"/ order: "+order);
				
				AlbumVO vo = new AlbumVO();
				vo.setClub_id(club_id);
				
				List<AlbumVO> album_list = dao.selectAll(vo, order);
//				System.out.println("album_list: "+album_list);
				request.setAttribute("album_list", album_list);
				request.getRequestDispatcher("ALBUM/selectAll.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}

		} else if (sPath.equals("/album_deleteOK.do")) {
			if (request.getSession().getAttribute("member_id") != null) {
				long club_id = Long.parseLong(request.getParameter("club_id").trim());
				long album_id = Long.parseLong(request.getParameter("album_id").trim());
				// 정렬 기준을 최신 순으로 설정
//				System.out.println("album_id: " + album_id);
//				System.out.println("club_id: " + club_id);
				
				AlbumVO vo = new AlbumVO();
				vo.setClub_id(club_id);
				vo.setAlbum_id(album_id);
				
				int delete_result = dao.delete(vo);
				if (delete_result == 1) {
//					System.out.println("delete Success");
					request.setAttribute("delete_result", delete_result);
					response.sendRedirect("album_selectAll.do?club_id="+club_id);
				} else {
//					System.out.println("delete failed");
					request.setAttribute("delete_result", delete_result);
					response.sendRedirect("album_selectAll.do?club_id="+club_id);
				}
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/album_searchList.do")) {
			if (request.getSession().getAttribute("member_id") != null) {
				long club_id = Long.parseLong(request.getParameter("club_id"));
				String searchKey = request.getParameter("searchKey");
				String searchWord = request.getParameter("searchWord");
//				System.out.println("searchKey: "+ searchKey+", searchWord: "+searchWord);
				
				List<AlbumVO> album_list = dao.searchList(club_id, searchKey, searchWord);
				request.setAttribute("album_list", album_list);
				request.getRequestDispatcher("ALBUM/selectAll.jsp?club_id="+club_id).forward(request, response);
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
		if (sPath.equals("/album_insertOK.do")) {

			if (request.getSession().getAttribute("member_id") != null) {

				String dir_path = request.getServletContext().getRealPath("/upload");
//				System.out.println(dir_path);
				
				long club_id = 0;
				String club_name = null; // 모임명
				String title = null; // 앨범명
				String fname = null; // 앨범 소개글
				String writer = null; // 앨범 게시자

				int fileSizeMax = 1024 * 1024 * 100;

				boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

				if (isMultipartContent) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(fileSizeMax);

					ServletFileUpload sfu = new ServletFileUpload(factory);
					sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한

					try {
						List<FileItem> items = sfu.parseRequest(request);
						for (FileItem item : items) {
							if (item.isFormField()) { // st_name,,,, 받기
								if (item.getFieldName().equals("club_id")) {
									club_id = Long.parseLong(item.getString("UTF-8"));
								}
								if (item.getFieldName().equals("club_name")) {
									club_name = item.getString("UTF-8");
								}
								if (item.getFieldName().equals("album_title")) {
									// 공백만 입력됐을 경우 null 값으로 변환
									title = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8").trim();
								}
								if (item.getFieldName().equals("insert_writer")) {
									writer = item.getString("UTF-8");
								}
							} else { // file정보받기.
								 fname = FilenameUtils.getName(item.getName());
								if (item.getSize() != 0) {
									fname = writer + "_" + fname;
									File saveFile = new File(dir_path, fname);

									// 파일 경로에 폴더가 없을 경우 파일을 만들어주는 코드
									if (!saveFile.exists())
										new File(dir_path).mkdirs();

									try {
										item.write(saveFile);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}

							} // end else

						} // end for << items
					} catch (FileUploadException e) {
						e.printStackTrace();
					}

					System.out.println(club_id);
					System.out.println(title);
					System.out.println(fname);
					System.out.println(writer);
					
					club_name = URLEncoder.encode(club_name, "UTF-8");
					
					// 에러 발생 상황에 따라서 int status_album_insert 값을 0으로 설정
					// (0: 제목 누락)
					int status_album_insert = 0;
					if (title == null) { // 제목을 작성하지 않고 제출했을 경우
						System.out.println("null! ---> title: " + title);
						status_album_insert = 1;
						request.setAttribute("status: ", status_album_insert);
						response.sendRedirect("album_selectAll.do?club_id="+club_id+"&club_name="+club_name);
					} else if(fname.equals("deafult_img.png")){ // 파일값이 설정되지 않아 기본 값으로 설정 될 경우
						System.out.println("null! ---> fname: " + fname);
						status_album_insert = 2;
						request.setAttribute("status: ", status_album_insert);
						response.sendRedirect("album_selectAll.do?club_id="+club_id+"&club_name="+club_name);
						
					} else {
						AlbumVO vo = new AlbumVO();
						vo.setClub_id(club_id);
						vo.setTitle(title);
						vo.setFname(fname.length() == 0 ? "golfclub.png": fname);
						vo.setWriter(writer);
						System.out.println("input vo: " + vo);
						int result = dao.insert(vo);
						System.out.println("insert result: " + result);
						
						if (result == 1) {
							response.sendRedirect("album_selectAll.do?club_id="+club_id+"&club_name="+club_name);
						} else {
							response.sendRedirect("album_selectAll.do?club_id="+club_id+"&club_name="+club_name);
						}
					}
				}
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/album_updateOK.do")) {
			
			if (request.getSession().getAttribute("member_id") != null) {

				String dir_path = request.getServletContext().getRealPath("/upload");
//				System.out.println(dir_path);
				
				long club_id = 0L;
				long album_id = 0L;
				String title = null; // 액티비티명
				String fname = null; // 액티비티 소개글
				String writer = null;
				String club_name = null;

				int fileSizeMax = 1024 * 1024 * 100;

				boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

				if (isMultipartContent) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(fileSizeMax);

					ServletFileUpload sfu = new ServletFileUpload(factory);
					sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한

					try {
						List<FileItem> items = sfu.parseRequest(request);
						for (FileItem item : items) {
							if (item.isFormField()) { // st_name,,,, 받기
								if (item.getFieldName().equals("club_id")) {
									club_id = Long.parseLong(item.getString("UTF-8"));
								}
								if (item.getFieldName().equals("club_name")) {
									club_name = item.getString("UTF-8");
								}
								if (item.getFieldName().equals("update_id")) {
									album_id = Long.parseLong(item.getString("UTF-8"));
								}
								if (item.getFieldName().equals("selectOne_title")) {
									// 공백만 입력됐을 경우 null 값으로 변환
									title = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8").trim();
								}
								if (item.getFieldName().equals("selectOne_writer")) {
									writer = item.getString("UTF-8");
								}
							} else { // file정보받기.
								fname = FilenameUtils.getName(item.getName());
								if (item.getSize() != 0) {
									fname = writer + "_" + fname;
									File saveFile = new File(dir_path, fname);

									// 파일 경로에 폴더가 없을 경우 파일을 만들어주는 코드
									if (!saveFile.exists())
										new File(dir_path).mkdirs();

									try {
										item.write(saveFile);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}

							} // end else

						} // end for << items
					} catch (FileUploadException e) {
						e.printStackTrace();
					}

//					System.out.println(club_id);
//					System.out.println(album_id);
//					System.out.println(title);
//					System.out.println(fname);
//					System.out.println(writer);

					club_name = URLEncoder.encode(club_name, "UTF-8");
					
					// 에러 발생 상황에 따라서 int status_album_insert 값을 1으로 설정
					// (1: 제목 누락, 2: 파일 누락)
					int status_album_insert = 0;
					if (title == null) { // 제목을 작성하지 않고 제출했을 경우
						System.out.println("null! ---> title: " + title);
						status_album_insert = 1;
						request.setAttribute("status: ", status_album_insert);
						response.sendRedirect("album_selectAll.do?club_id="+club_id+"&club_name="+club_name);
					} else if(fname.equals("deafult_img.png")){ // 파일값이 설정되지 않아 기본 값으로 설정 될 경우
						System.out.println("null! ---> fname: " + fname);
						status_album_insert = 2;
						request.setAttribute("status: ", status_album_insert);
						response.sendRedirect("album_selectAll.do?club_id="+club_id+"&club_name="+club_name);
						
					} else {
						AlbumVO vo = new AlbumVO();
						vo.setAlbum_id(album_id);
						vo.setClub_id(club_id);
						vo.setTitle(title);
						vo.setFname(fname.length() == 0 ? "golfclub.png": fname);
						vo.setWriter(writer);
						System.out.println("update vo: " + vo);
						int result = dao.update(vo);
						System.out.println("update result: " + result);
						
						if (result == 1) {
							response.sendRedirect("album_selectAll.do?club_id="+club_id+"&club_name="+club_name);
						} else {
							response.sendRedirect("album_selectAll.do?club_id="+club_id+"&club_name="+club_name);
						}
					}
				}
			} else {
				response.sendRedirect("login.do");
			}
		}
	}
}
