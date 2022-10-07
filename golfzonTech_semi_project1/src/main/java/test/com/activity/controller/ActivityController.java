package test.com.activity.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
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

import test.com.activity.model.ActLeaderVO;
import test.com.activity.model.ActivityDAO;
import test.com.activity.model.ActivityDAOimpl;
import test.com.activity.model.ActivityVO;
import test.com.activitymember.model.ActivityMemberDAO;
import test.com.activitymember.model.ActivityMemberDAOimpl;
import test.com.activitymember.model.ActivityMemberVO;
import test.com.club.model.ClubVO;

/**
 * 1. 작성자: 이주희 (백엔드)
 * 2. 기능: 공개/비공개 액티비티와 액티비티 참여원 데이터의 CRUD
 */
@WebServlet({ "/activity_insert.do", "/activity_insertOK.do", "/activity_update.do", "/activity_updateOK.do",
		"/activity_delete.do", "/activity_deleteOK.do", "/activity_selectOne.do", "/activity_selectAll.do",
		"/activity_searchList.do", "/club_activity.do", "/club_activity_insert.do", "/club_activity_insertOK.do",
		"/club_activity_selectOne.do", "/club_activity_update.do", "/club_activity_updateOK.do", "/club_activity_deleteOK.do"})
public class ActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ActivityDAO dao = new ActivityDAOimpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivityController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sPath = request.getServletPath();
		System.out.println("doGet(): " + sPath);
		if (sPath.equals("/activity_insert.do")) {
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				ActivityVO vo = new ActivityVO();
				vo.setAct_leader(member_id);

				Map<Long, String> club = dao.findClubById(vo);
				Map<Long, String> cc = dao.findCC_Id();
				System.out.println("CC: "+cc);
				request.setAttribute("club", club);
				request.setAttribute("cc", cc);
				request.getRequestDispatcher("ACTIVITY/insert.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/club_activity_insert.do")) {
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				ActivityVO vo = new ActivityVO();
				vo.setAct_leader(member_id);

				Map<Long, String> club = dao.findClubById(vo);
				Map<Long, String> cc = dao.findCC_Id();
				System.out.println("CC: "+cc);
				request.setAttribute("club", club);
				request.setAttribute("cc", cc);
				request.getRequestDispatcher("CLUB/club_activity_insert.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/activity_update.do")) {
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				long act_id = Long.parseLong(request.getParameter("act_id"));

				ActivityVO vo = new ActivityVO();
				vo.setAct_id(act_id);

				ActivityVO vo1 = dao.selectOne(vo);
				Map<Long, String> cc = dao.findCC_Id();

				request.setAttribute("cc", cc);
				request.setAttribute("activity_info", vo1);
				request.getRequestDispatcher("ACTIVITY/update.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/club_activity_update.do")) {
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				long act_id = Long.parseLong(request.getParameter("act_id"));

				ActivityVO vo = new ActivityVO();
				vo.setAct_id(act_id);

				ActivityVO vo1 = dao.selectOne(vo);
				Map<Long, String> cc = dao.findCC_Id();

				request.setAttribute("cc", cc);
				request.setAttribute("activity_info", vo1);
				request.getRequestDispatcher("CLUB/club_activity_update.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/activity_deleteOK.do")) {
			Long act_id = Long.parseLong(request.getParameter("act_id"));
//			System.out.println(act_id);

			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				ActivityVO vo = new ActivityVO();
				vo.setAct_id(act_id);
				// 삭제 시 FK위반으로 해당 액티비티의 참여원 테이블을 먼저 삭제한 후 액티비티 테이블 삭제
				int delete_all_result = new ActivityMemberDAOimpl().deleteAll(vo); // 해당 액티비티의 액티비티원을 삭제
				System.out.println("delete_all_result: " + delete_all_result);

				int flag = dao.delete(vo); // 액티비티 삭제
				System.out.println("flag: "+flag);
				if (flag == 1) {
					response.sendRedirect("activity_selectAll.do"); // 성공 시 전체 페이지로
				} else {
					response.sendRedirect("activity_selectOne.do?act_id=" + act_id); // 실패 시 상세 페이지로 이동
				}
			} else {
				response.sendRedirect("login.do");
			}

		} else if (sPath.equals("/club_activity_deleteOK.do")) {
			Long act_id = Long.parseLong(request.getParameter("act_id"));

			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			long club_id = Long.parseLong(request.getParameter("club_id"));
			String club_name = request.getParameter("club_name");
			club_name = URLEncoder.encode(club_name, "UTF-8");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				ActivityVO vo = new ActivityVO();
				vo.setAct_id(act_id);
				// 삭제 시 FK위반으로 해당 액티비티의 참여원 테이블을 먼저 삭제한 후 액티비티 테이블 삭제
				int delete_all_result = new ActivityMemberDAOimpl().deleteAll(vo); // 해당 액티비티의 액티비티원을 삭제
				System.out.println("delete_all_result: " + delete_all_result);

				int flag = dao.delete(vo); // 액티비티 삭제
				System.out.println("flag: "+flag);
				if (flag == 1) {
					response.sendRedirect("club_activity.do?club_id="+club_id+"&club_name="+club_name); // 성공 시 전체 페이지로
				} else {
					response.sendRedirect("club_activity_selectOne.do?act_id=" + act_id+"&club_id="+club_id+"&club_name="+club_name); // 실패 시 상세 페이지로 이동
				}
			} else {
				response.sendRedirect("login.do");
			}

		} else if (sPath.equals("/activity_selectOne.do")) {
			// 최신 액티비티 순으로 정렬
			Long act_id = Long.parseLong(request.getParameter("act_id"));
			Long club_id = 0L;
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				if (request.getParameter("club_id") != null) {
					club_id = Long.parseLong(request.getParameter("club_id"));
				}
				// 마감일자가 지난 액티비티를 마감으로 변경
				dao.changeStatusByDate();

				// 액티비티 객체 생성 후 액티비티ID와 접속 중인 유저 ID를 객체에 저장
				ActivityVO vo = new ActivityVO();
				vo.setAct_id(act_id);
				vo.setAct_leader(member_id);

				// 액티비티 객체 생성 후 액티비티ID와 접속 중인 유저 ID와 해당 유저의 모임ID를 객체에 저장
				ActivityMemberVO vo1 = new ActivityMemberVO();
				vo1.setAct_id(act_id);
				vo1.setMember_id(member_id);
//			System.out.println("vo1: "+vo1);

				// 해당 액티비티의 리더의 정보, 액티비티의 정보 그리고 접속자가 리더인지 확인하는 값을 호출한다.
				ActLeaderVO leader_info = dao.findLeaderById(vo);
				ActivityVO activity_info = dao.selectOne(vo);

				// 해당 액티비티에 참가 신청을 한 참여원의 명단을 호출
				ActivityMemberDAO dao_atm = new ActivityMemberDAOimpl();
				ActivityMemberVO vo2 = new ActivityMemberVO();
				vo2.setAct_id(act_id);
				List<ActivityMemberVO> act_joined_member = dao_atm.selectALL_joined(vo2);
				List<ActivityMemberVO> act_not_joined_member = dao_atm.selectALL_not_joined(vo2);
				ActivityMemberVO applied_member = dao_atm.getAppliedMember(vo1);
				boolean isApplied = member_id.equals(applied_member.getMember_id()) ? true : false;
				boolean isLeader = leader_info.getLeader_id().equals(member_id); // 접속 ID와 DB에서 조회한 LeaderID가 같을 경우
																					// True로 저장

				boolean isQualified = dao.isQualified(act_id, member_id, dao.createQuery_qual(activity_info.getGender(),
						activity_info.getLocation(), activity_info.getAge()));

				request.setAttribute("leader_info", leader_info); // 개설자 정보
				request.setAttribute("activity_info", activity_info); // 액티비티 정보
				request.setAttribute("isLeader", isLeader); // 접속자의 해당 액티비티 리더 여부
				request.setAttribute("isQualified", isQualified); // 접속자의 해당 액티비티 지원자격여부
				request.setAttribute("isApplied", isApplied); // 접속자의 해당 액티비티 지원여부
				request.setAttribute("applied_member", applied_member); // 접속자의 해당 액티비티 지원여부
				request.setAttribute("act_joined_member", act_joined_member); // 승인된 참여자
				request.setAttribute("act_not_joined_member", act_not_joined_member); // 승인대기중인 참여자

				request.getRequestDispatcher("ACTIVITY/selectOne.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/club_activity_selectOne.do")) {
			// 최신 액티비티 순으로 정렬
			Long act_id = Long.parseLong(request.getParameter("act_id"));
			Long club_id = 0L;
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				if (request.getParameter("club_id") != null) {
					club_id = Long.parseLong(request.getParameter("club_id"));
				}
				// 마감일자가 지난 액티비티를 마감으로 변경
				dao.changeStatusByDate();

				// 액티비티 객체 생성 후 액티비티ID와 접속 중인 유저 ID를 객체에 저장
				ActivityVO vo = new ActivityVO();
				vo.setAct_id(act_id);
				vo.setAct_leader(member_id);

				// 액티비티 객체 생성 후 액티비티ID와 접속 중인 유저 ID와 해당 유저의 모임ID를 객체에 저장
				ActivityMemberVO vo1 = new ActivityMemberVO();
				vo1.setAct_id(act_id);
				vo1.setMember_id(member_id);

				// 해당 액티비티의 리더의 정보, 액티비티의 정보 그리고 접속자가 리더인지 확인하는 값을 호출한다.
				ActLeaderVO leader_info = dao.findLeaderById(vo);
				ActivityVO activity_info = dao.selectOne(vo);

				// 해당 액티비티에 참가 신청을 한 참여원의 명단을 호출
				ActivityMemberDAO dao_atm = new ActivityMemberDAOimpl();
				ActivityMemberVO vo2 = new ActivityMemberVO();
				vo2.setAct_id(act_id);
				List<ActivityMemberVO> act_joined_member = dao_atm.selectALL_joined(vo2);
				List<ActivityMemberVO> act_not_joined_member = dao_atm.selectALL_not_joined(vo2);
				ActivityMemberVO applied_member = dao_atm.getAppliedMember(vo1);
				boolean isApplied = member_id.equals(applied_member.getMember_id()) ? true : false;
				boolean isLeader = leader_info.getLeader_id().equals(member_id); // 접속 ID와 DB에서 조회한 LeaderID가 같을 경우
																					// True로 저장

				boolean isQualified = dao.isQualified(act_id, member_id, dao.createQuery_qual(activity_info.getGender(),
						activity_info.getLocation(), activity_info.getAge()));

				request.setAttribute("leader_info", leader_info); // 개설자 정보
				request.setAttribute("activity_info", activity_info); // 액티비티 정보
				request.setAttribute("isLeader", isLeader); // 접속자의 해당 액티비티 리더 여부
				request.setAttribute("isQualified", isQualified); // 접속자의 해당 액티비티 지원자격여부
				request.setAttribute("isApplied", isApplied); // 접속자의 해당 액티비티 지원여부
				request.setAttribute("applied_member", applied_member); // 접속자의 해당 액티비티 지원여부
				request.setAttribute("act_joined_member", act_joined_member); // 승인된 참여자
				request.setAttribute("act_not_joined_member", act_not_joined_member); // 승인대기중인 참여자

				request.getRequestDispatcher("CLUB/club_activity_selectOne.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/activity_selectAll.do")) {
			// 최신 액티비티 순으로 정렬
			Long club_id = 0L;
			if (request.getParameter("club_id") != null) {
				club_id = Long.parseLong(request.getParameter("club_id"));
			}
			String order = request.getParameter("order") == null ? "id" : request.getParameter("order");

			// 마감일자가 지난 액티비티를 마감으로 변경
			dao.changeStatusByDate();

			List<ActivityVO> vos = dao.selectAll(order, club_id);
			request.setAttribute("vos", vos);
			request.setAttribute("club_id", club_id);

			request.getRequestDispatcher("ACTIVITY/selectAll.jsp").forward(request, response);
		} else if (sPath.equals("/club_activity.do")) {
			// 최신 액티비티 순으로 정렬
			Long club_id = 0L;
			if (request.getParameter("club_id") != null) {
				club_id = Long.parseLong(request.getParameter("club_id"));
			}
			String order = request.getParameter("order") == null ? "id" : request.getParameter("order");

			// 마감일자가 지난 액티비티를 마감으로 변경
			dao.changeStatusByDate();

			List<ActivityVO> vos = dao.selectAll(order, club_id);
			System.out.println(vos);
			request.setAttribute("vos", vos);
			request.setAttribute("club_id", club_id);

			request.getRequestDispatcher("CLUB/club_activity.jsp").forward(request, response);
		} else if (sPath.equals("/activity_searchList.do")) {
			// 최신 액티비티 순으로 정렬
			Long club_id = 0L;
			if (request.getParameter("club_id") != null) {
				club_id = Long.parseLong(request.getParameter("club_id"));
			}

			String location = request.getParameter("location");
			int age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			String searchKey = request.getParameter("searchKey");
			String searchWord = request.getParameter("searchWord");

			// 넘겨온 값을 query를 작성해주는 createQuery매서드에 입력
			String query = dao.createQuery_search(location, gender, searchKey, age);
//			System.out.println("query: "+query);
//			 마감일자가 지난 액티비티를 마감으로 변경
			dao.changeStatusByDate();

			List<ActivityVO> vos = dao.searchList(club_id, query, searchWord);
			request.setAttribute("vos", vos);
			request.setAttribute("club_id", club_id);

			request.getRequestDispatcher("ACTIVITY/selectAll.jsp").forward(request, response);
		}
	} // end doGet()

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sPath = request.getServletPath();
		System.out.println("doPost(): " + sPath);
		if (sPath.equals("/activity_insertOK.do")) {

			HttpSession session = request.getSession();
			String act_leader = (String) session.getAttribute("member_id");
			System.out.println("act_leader:" + act_leader);

			String dir_path = request.getServletContext().getRealPath("/upload");
			System.out.println(dir_path);

			int fileSizeMax = 1024 * 1024 * 100;

			boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

			if (isMultipartContent) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(fileSizeMax);

				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한
				long club_id = 0; // 공개, 비공개 가입 여부(공개=> 0, 비공개 => !0)
				String club_name = null;
				int cost = 0; // 비용
				int cc_id = 0; // CC명
				int age = 0; // 연령대
				String act_name = null; // 액티비티명
				String act_content = null; // 액티비티 소개글
				String gender = null; // 액티비티 성별
				String location = null; // 액티비티 지역
				String fname = null; // 액티비티 프로필명
				// 더미변수 생성
				String rdate_str = null; // 액티비티 라운딩 일자
				String adate_str = null; // 액티비티 마감일자

				try {
					List<FileItem> items = sfu.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) { // st_name,,,, 받기
							if (item.getFieldName().equals("club_id")) {
								club_id = Long.parseLong(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("cost")) {
								cost = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("cc_id")) {
								cc_id = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("age")) {
								age = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("act_name")) {
								// 공백만 입력됐을 경우 null 값으로 변환
								act_name = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8").trim();
							}
							if (item.getFieldName().equals("club_name")) {
								club_name = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("act_content")) {
								// 공백만 입력됐을 경우 null 값으로 변환
								act_content = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8").trim();
							}
							if (item.getFieldName().equals("gender")) {
								gender = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("location")) {
								location = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("rdate")) {
								System.out.println("rdate_str: " + rdate_str);
								rdate_str = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("adate")) {
								System.out.println("adate_str: " + adate_str);
								adate_str = item.getString("UTF-8");
							}
						} else { // file정보받기.
							fname = FilenameUtils.getName(item.getName());
							if (item.getSize() != 0) {
								fname = act_leader + "_" + fname;
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

//				System.out.println(act_leader);
//				System.out.println(club_id);
//				System.out.println(cost);
//				System.out.println(cc_id);
//				System.out.println(cost);
//				System.out.println(age);
//				System.out.println(act_name);
//				System.out.println(act_content);
//				System.out.println(gender);
//				System.out.println(location);
//				System.out.println(fname);
//				System.out.println(rdate_str);
//				System.out.println(adate_str);

				// 에러 발생 상황에 따라서 flag 값을 0, 1, 2, 3으로 설정
				// (정상/ 액티비티명 누락/ 라운딩 날짜 누락/ 마감일 날짜 누락)
				int status = 0;
				if (act_name == null) {
					System.out.println("null! ---> act_name: " + act_name);
					status = 1;
					request.setAttribute("status: ", status);
					response.sendRedirect("activity_insert.do");
				} else if (rdate_str == null) {
					System.out.println("null! ---> rdate_str: " + rdate_str);
					status = 2;
					request.setAttribute("status", status);
					response.sendRedirect("activity_insert.do");

				} else if (adate_str == null) {
					System.out.println("null! ---> adate_str: " + adate_str);
					status = 3;
					request.setAttribute("flag_null", status);
					response.sendRedirect("activity_insert.do");
				} else {
					ActivityVO vo = new ActivityVO();
					ClubVO vo_club = new ClubVO();
					vo_club.setClub_id(club_id);
					club_name = dao.getClubName(vo_club);
					if (club_name != null) {
						club_name = URLEncoder.encode(club_name, "UTF-8");
					} else {
						club_name = "";
					}
					
					// String으로 넘어온 Timestamp 값을 변경해주는 코드
					Timestamp rdate = Timestamp.valueOf(rdate_str.replace("T", " ") + ":00.00");

					Timestamp adate = Timestamp.valueOf(adate_str.replace("T", " ") + ":00.00");

					vo.setCc_id(cc_id);
					vo.setClub_id(club_id);
					vo.setAct_leader(act_leader);
					vo.setAct_name(act_name);
					vo.setAct_content(act_content);
					vo.setGender(gender);
					vo.setAge(age);
					vo.setLocation(location);
					vo.setCost(cost);
					vo.setRdate(rdate);
					vo.setAdate(adate);
					vo.setAct_fname(fname.length() == 0 ? "golfclub.png": fname);
					// 생성시에는 모집여부 값을 0으로 지정
					vo.setStatus(0);

					// 날짜 오기입 시 (마감일이 라운딩 날짜보다 늦을 경우)
					if (adate.before(new Timestamp(System.currentTimeMillis()))) {
						System.out.println("date setting error! --> adate before current time");
						response.sendRedirect("activity_insert.do?club_id="+club_id+"&club_name="+club_name);
					} else if (rdate.before(adate)) {
						System.out.println("date setting error!");
						response.sendRedirect("activity_insert.do?club_id="+club_id+"&club_name="+club_name);
					} else {

						System.out.println("VO: " + vo);
						Map<Long, Integer> result = dao.insert(vo);
						System.out.println("insert's result: " + result);

						long act_id = 0L;
						int flag_insert = 0;
						for (Long key : result.keySet()) {
							act_id = key;
							flag_insert = result.get(key);
						}
						// 입력 성공 시 activity_member 테이블에 act_leader 값을 입력해준다.
						if (flag_insert == 1) {

							// activity_member insert 만든 후 다시 돌아옵니다....
							ActivityMemberVO vo1 = new ActivityMemberVO();
							vo1.setAct_id(act_id);
							vo1.setMember_id(act_leader);
							// 리더의 경우 모든 자격, 승인여부, 멤버타입을 1로 설정해준다.(자격충족, 승인됨, 리더)
							vo1.setQualified(1);
							vo1.setApproved(1);
							vo1.setAmtype(1);

							// activity_member에 act_leader 값을 입력 성공 시 1의 가지게 하는 변수를 생성
							int flag_act_member = 0;
							flag_act_member = new ActivityMemberDAOimpl().insert(vo1);
							System.out.println("flag_act_member: " + flag_act_member);
							if (flag_act_member == 1) {
								// 액티비티 참여원 입력 성공 시
								// 액티비티 전체 페이지로 이동
								if (club_id == 0) {
									response.sendRedirect("activity_selectAll.do?club_id="+ club_id + "&club_name=" + club_name);
								} else {
									response.sendRedirect("activity_selectAll.do?club_id=" + club_id + "&club_name=" + club_name);
								}
							} else {
								// 액티비티 참여원 입력 실패 시 생성된 액티비티 삭제 후 액티비티 생성 창으로 이동
								ActivityVO vo2 = new ActivityVO();
								vo2.setAct_id(act_id);
								dao.delete(vo2);
								// 실패 시 status = 4번을 넘김
								System.out.println(
										"failed to create activity_member! ---> flag_act_member: " + flag_act_member);
								status = 4;
								request.setAttribute("status", status);
								response.sendRedirect("activity_insert.do");
							}
						} else {
							response.sendRedirect("activity_insert.do");
						}
					}
				}
			} // end if ~ else
		} else if (sPath.equals("/club_activity_insertOK.do")) {

			HttpSession session = request.getSession();
			String act_leader = (String) session.getAttribute("member_id");

			String dir_path = request.getServletContext().getRealPath("/upload");

			int fileSizeMax = 1024 * 1024 * 100;

			boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

			if (isMultipartContent) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(fileSizeMax);

				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한
//				String act_leader = (String) session.getAttribute("user_id");
				long club_id = 0; // 공개, 비공개 가입 여부(공개=> 0, 비공개 => !0)
				String club_name = null;
				int cost = 0; // 비용
				int cc_id = 0; // CC명
				int age = 0; // 연령대
				String act_name = null; // 액티비티명
				String act_content = null; // 액티비티 소개글
				String gender = null; // 액티비티 성별
				String location = null; // 액티비티 지역
				String fname = null; // 액티비티 프로필명
				// 더미변수 생성
				String rdate_str = null; // 액티비티 라운딩 일자
				String adate_str = null; // 액티비티 마감일자

				try {
					List<FileItem> items = sfu.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) { // st_name,,,, 받기
							if (item.getFieldName().equals("club_id")) {
								club_id = Long.parseLong(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("cost")) {
								cost = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("cc_id")) {
								cc_id = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("age")) {
								age = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("act_name")) {
								// 공백만 입력됐을 경우 null 값으로 변환
								act_name = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8").trim();
							}
							if (item.getFieldName().equals("club_name")) {
								club_name = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("act_content")) {
								// 공백만 입력됐을 경우 null 값으로 변환
								act_content = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("gender")) {
								gender = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("location")) {
								location = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("rdate")) {
								System.out.println("rdate_str: " + rdate_str);
								rdate_str = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("adate")) {
								System.out.println("adate_str: " + adate_str);
								adate_str = item.getString("UTF-8");
							}
						} else { // file정보받기.
							 fname = FilenameUtils.getName(item.getName());
							if (item.getSize() != 0) {
								fname = act_leader + "_" + fname;
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

				// 에러 발생 상황에 따라서 flag 값을 0, 1, 2, 3으로 설정
				// (정상/ 액티비티명 누락/ 라운딩 날짜 누락/ 마감일 날짜 누락)
				int status = 0;
				if (act_name == null) {
					System.out.println("null! ---> act_name: " + act_name);
					status = 1;
					request.setAttribute("status: ", status);
					response.sendRedirect("activity_insert.do");
				} else if (rdate_str == null) {
					System.out.println("null! ---> rdate_str: " + rdate_str);
					status = 2;
					request.setAttribute("status", status);
					response.sendRedirect("activity_insert.do");

				} else if (adate_str == null) {
					System.out.println("null! ---> adate_str: " + adate_str);
					status = 3;
					request.setAttribute("flag_null", status);
					response.sendRedirect("activity_insert.do");
				} else {
					ActivityVO vo = new ActivityVO();

					// String으로 넘어온 Timestamp 값을 변경해주는 코드
					Timestamp rdate = Timestamp.valueOf(rdate_str.replace("T", " ") + ":00.00");

					Timestamp adate = Timestamp.valueOf(adate_str.replace("T", " ") + ":00.00");

					vo.setCc_id(cc_id);
					vo.setClub_id(club_id);
					vo.setAct_leader(act_leader);
					vo.setAct_name(act_name);
					vo.setAct_content(act_content);
					vo.setGender(gender);
					vo.setAge(age);
					vo.setLocation(location);
					vo.setCost(cost);
					vo.setRdate(rdate);
					vo.setAdate(adate);
					vo.setAct_fname(fname.length() == 0 ? "golfclub.png": fname);
					// 생성시에는 모집여부 값을 0으로 지정
					vo.setStatus(0);
					
					club_name = URLEncoder.encode(club_name, "UTF-8");
					
					// 날짜 오기입 시 (마감일이 라운딩 날짜보다 늦을 경우)
					if (adate.before(new Timestamp(System.currentTimeMillis()))) {
						System.out.println("date setting error! --> adate before current time");
						response.sendRedirect("club_activity_insert.do?club_id="+club_id+"&club_name="+club_name);
					} else if (rdate.before(adate)) {
						System.out.println("date setting error!");
						response.sendRedirect("club_activity_insert.do?club_id="+club_id+"&club_name="+club_name);
					} else {

						System.out.println("VO: " + vo);
						Map<Long, Integer> result = dao.insert(vo);
						System.out.println("insert's result: " + result);

						long act_id = 0L;
						int flag_insert = 0;
						for (Long key : result.keySet()) {
							act_id = key;
							flag_insert = result.get(key);
						}
						// 입력 성공 시 activity_member 테이블에 act_leader 값을 입력해준다.
						if (flag_insert == 1) {

							// activity_member insert 만든 후 다시 돌아옵니다....
							ActivityMemberVO vo1 = new ActivityMemberVO();
							vo1.setAct_id(act_id);
							vo1.setMember_id(act_leader);
							// 리더의 경우 모든 자격, 승인여부, 멤버타입을 1로 설정해준다.(자격충족, 승인됨, 리더)
							vo1.setQualified(1);
							vo1.setApproved(1);
							vo1.setAmtype(1);

							// activity_member에 act_leader 값을 입력 성공 시 1의 가지게 하는 변수를 생성
							int flag_act_member = 0;
							flag_act_member = new ActivityMemberDAOimpl().insert(vo1);
							System.out.println("flag_act_member: " + flag_act_member);
							if (flag_act_member == 1) {
								// 액티비티 참여원 입력 성공 시
								// 액티비티 전체 페이지로 이동
								if (club_id == 0) {
									response.sendRedirect("club_activity.do?club_id="+ club_id + "&club_name=" + club_name);
								} else {
									response.sendRedirect("club_activity.do?club_id=" + club_id + "&club_name=" + club_name);
								}
							} else {
								// 액티비티 참여원 입력 실패 시 생성된 액티비티 삭제 후 액티비티 생성 창으로 이동
								ActivityVO vo2 = new ActivityVO();
								vo2.setAct_id(act_id);
								dao.delete(vo2);
								// 실패 시 status = 4번을 넘김
								System.out.println(
										"failed to create activity_member! ---> flag_act_member: " + flag_act_member);
								status = 4;
								request.setAttribute("status", status);
								response.sendRedirect("club_activity_insert.do?club_id="+ club_id + "&club_name=" + club_name);
							}
						} else {
							response.sendRedirect("club_activity_insert.do?club_id="+ club_id + "&club_name=" + club_name);
						}
					}
				}
			} // end if ~ else
		} else if (sPath.equals("/activity_updateOK.do")) {

			HttpSession session = request.getSession();
			String act_leader = (String) session.getAttribute("member_id");
			System.out.println("act_leader:" + act_leader);
			
			String dir_path = request.getServletContext().getRealPath("/upload");
			System.out.println(dir_path);

			int fileSizeMax = 1024 * 1024 * 100;

			boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

			if (isMultipartContent) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(fileSizeMax);

				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한
				long act_id = 0L;
				long club_id = 0L;
				String club_name = null;
				String act_name = null;
				int cost = 0; // 비용
				int cc_id = 0; // CC명
				int age = 0; // 연령대
				String act_content = null; // 액티비티 소개글
				String gender = null; // 액티비티 성별
				String location = null; // 액티비티 지역
				String fname = null; // 액티비티 프로필명

				Timestamp rdate = null; // 액티비티 라운딩 일자
				Timestamp adate = null; // 액티비티 마감일자
				// timestamp로 변환하기 전 일자 데이터를 문자열 형으로 받아오기 위해 생성한 변수
				String rdate_str = null;
				String adate_str = null;

				try {
					List<FileItem> items = sfu.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) { // st_name,,,, 받기
							if (item.getFieldName().equals("cost")) {
								cost = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("cc_id")) {
								cc_id = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("age")) {
								age = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("act_id")) {
								act_id = Long.parseLong(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("act_name")) {
								act_name = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("act_content")) {
								// 공백만 입력됐을 경우 null 값으로 변환
								act_content = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("gender")) {
								gender = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("club_name")) {
								club_name = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("location")) {
								location = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("rdate")) {
								rdate_str = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
							}
							if (item.getFieldName().equals("adate")) {
								adate_str = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
							}
							if (item.getFieldName().equals("act_id")) {
								act_id = Long.parseLong(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("club_id")) {
								club_id = Long.parseLong(item.getString("UTF-8"));
							}
						} else { // file정보받기.
							fname = FilenameUtils.getName(item.getName());
							if (item.getSize() != 0) {
								fname = act_leader + "_" + fname;
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
				System.out.println(act_id);
				System.out.println(club_id);
				System.out.println(act_leader);
				System.out.println(cost);
				System.out.println(cc_id);
				System.out.println(cost);
				System.out.println(age);
				System.out.println(act_content);
				System.out.println(gender);
				System.out.println(location);
				System.out.println(rdate_str == null ? "null" : "not null");
				System.out.println(adate_str == null ? "null" : "not null");
				System.out.println(fname);

				if (rdate_str != null && adate_str != null) {
					System.out.println("rdate_str: " + rdate_str);
					System.out.println("adate_str: " + adate_str);
					rdate = Timestamp.valueOf(rdate_str.replace("T", " ") + ":00.00");

					adate = Timestamp.valueOf(adate_str.replace("T", " ") + ":00.00");
				}

				club_name = URLEncoder.encode(club_name, "UTF-8");
					
				// 에러 발생 상황에 따라서 status 값을 0, 1, 2, 3, 4, 5으로 설정
				// (정상/ 액티비티명 누락/ 날짜 누락/ 날짜 오기입 (마감일자가 라운딩 날자보다 늦는 경우)/ insert구문 에러)
				int status = 0;
				if (rdate_str == null || adate_str == null) {
					System.out.println("null! ---> rdate_str: " + rdate_str);
					System.out.println("null! ---> rdate_str: " + rdate_str);
					status = 2;
					request.setAttribute("status", status);
					response.sendRedirect("activity_update.do?club_id=" + club_id + "&act_id=" + act_id + "&club_name=" + club_name);
				} else if (rdate.before(adate)) { // 날짜 오기입 시 (마감일이 라운딩 날짜보다 늦을 경우)
					System.out.println("date setting error!");
					status = 3;
					request.setAttribute("status", status);
					response.sendRedirect("activity_update.do?club_id=" + club_id + "&&act_id=" + act_id + "&club_name=" + club_name);
				} else if (fname == null) {
					System.out.println("null! ---> rdate_str: " + rdate_str);
					status = 4;
					request.setAttribute("status", status);
					response.sendRedirect("activity_update.do?club_id=" + club_id + "&&act_id=" + act_id + "&club_name=" + club_name);
				} else {
					ActivityVO vo = new ActivityVO();
					// String으로 넘어온 Timestamp 값을 변경해주는 코드

					vo.setAct_id(act_id);
					vo.setAct_name(act_name);
					vo.setCc_id(cc_id);
					vo.setAct_content(act_content);
					vo.setGender(gender);
					vo.setAge(age);
					vo.setLocation(location);
					vo.setCost(cost);
					vo.setRdate(rdate);
					vo.setAdate(adate);
					vo.setAct_fname(fname.length() == 0 ? "golfclub.png": fname); // 사진 업로드가 되지 않았을 경우 기본 이미지로 변경

					System.out.println("VO: " + vo);
					int flag = dao.update(vo);
					System.out.println("flag: " + flag);

					if (flag == 0) {
						System.out.println("update failed!");
						status = 4;
						request.setAttribute("status", status);
						response.sendRedirect("activity_update.do?club_id=" + club_id + "&&act_id=" + act_id + "&club_name=" + club_name);
					} else {
						if (club_id == 0) {
							response.sendRedirect("activity_selectAll.do");
						} else {
							response.sendRedirect("activity_selectAll.do?club_id=" + club_id + "&club_name=" + club_name);
						}
					}
				}
			}
		} else if (sPath.equals("/club_activity_updateOK.do")) {

			HttpSession session = request.getSession();
			String act_leader = (String) session.getAttribute("member_id");
			System.out.println("act_leader:" + act_leader);

			String dir_path = request.getServletContext().getRealPath("/upload");	
			System.out.println(dir_path);

			int fileSizeMax = 1024 * 1024 * 100;

			boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

			if (isMultipartContent) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(fileSizeMax);

				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한
				long act_id = 0L;
				long club_id = 0L;
				String club_name = null;
				String act_name = null;
				int cost = 0; // 비용
				int cc_id = 0; // CC명
				int age = 0; // 연령대
				String act_content = null; // 액티비티 소개글
				String gender = null; // 액티비티 성별
				String location = null; // 액티비티 지역
				String fname = null; // 액티비티 프로필명

				Timestamp rdate = null; // 액티비티 라운딩 일자
				Timestamp adate = null; // 액티비티 마감일자
				// timestamp로 변환하기 전 일자 데이터를 문자열 형으로 받아오기 위해 생성한 변수
				String rdate_str = null;
				String adate_str = null;

				try {
					List<FileItem> items = sfu.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) { // st_name,,,, 받기
							if (item.getFieldName().equals("cost")) {
								cost = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("cc_id")) {
								cc_id = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("age")) {
								age = Integer.parseInt(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("act_name")) {
								act_name = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("act_content")) {
								// 공백만 입력됐을 경우 null 값으로 변환
								act_content = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("gender")) {
								gender = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("club_name")) {
								club_name = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("location")) {
								location = item.getString("UTF-8");
							}
							if (item.getFieldName().equals("rdate")) {
//								System.out.println("rdate_str: " + rdate_str);
								rdate_str = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
							}
							if (item.getFieldName().equals("adate")) {
//								System.out.println("adate_str: " + adate_str);
								adate_str = item.getString("UTF-8").isBlank() ? null : item.getString("UTF-8");
							}
							if (item.getFieldName().equals("act_id")) {
								act_id = Long.parseLong(item.getString("UTF-8"));
							}
							if (item.getFieldName().equals("club_id")) {
								club_id = Long.parseLong(item.getString("UTF-8"));
							}
						} else { // file정보받기.
							fname = FilenameUtils.getName(item.getName());
							if (item.getSize() != 0) {
								fname = act_leader + "_" + fname;
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

				if (rdate_str != null && adate_str != null) {
					System.out.println("rdate_str: " + rdate_str);
					System.out.println("adate_str: " + adate_str);
					rdate = Timestamp.valueOf(rdate_str.replace("T", " ") + ":00.00");

//					System.out.println(adate_str.replace("T", " ") + ":00.00");
					adate = Timestamp.valueOf(adate_str.replace("T", " ") + ":00.00");
				}
				
				club_name = URLEncoder.encode(club_name, "UTF-8");

				// 에러 발생 상황에 따라서 status 값을 0, 1, 2, 3, 4, 5으로 설정
				// (정상/ 액티비티명 누락/ 날짜 누락/ 날짜 오기입 (마감일자가 라운딩 날자보다 늦는 경우)/ insert구문 에러)
				int status = 0;
				if (rdate_str == null || adate_str == null) {
					System.out.println("null! ---> rdate_str: " + rdate_str);
					System.out.println("null! ---> rdate_str: " + rdate_str);
					status = 2;
					request.setAttribute("status", status);
					response.sendRedirect("club_activity_update.do?club_id=" + club_id + "&act_id=" + act_id + "&club_name=" + club_name);
				} else if (rdate.before(adate)) { // 날짜 오기입 시 (마감일이 라운딩 날짜보다 늦을 경우)
					System.out.println("date setting error!");
					status = 3;
					request.setAttribute("status", status);
					response.sendRedirect("club_activity_update.do?club_id=" + club_id + "&&act_id=" + act_id + "&club_name=" + club_name);
				} else if (fname == null) {
					System.out.println("null! ---> rdate_str: " + rdate_str);
					status = 4;
					request.setAttribute("status", status);
					response.sendRedirect("club_activity_update.do?club_id=" + club_id + "&&act_id=" + act_id + "&club_name=" + club_name);
				} else {
					ActivityVO vo = new ActivityVO();
					// String으로 넘어온 Timestamp 값을 변경해주는 코드
					// System.out.println(rdate_str.replace("T", " " + ":00.00"));
					
					vo.setAct_id(act_id);
					vo.setAct_name(act_name);
					vo.setCc_id(cc_id);
					vo.setAct_content(act_content);
					vo.setGender(gender);
					vo.setAge(age);
					vo.setLocation(location);
					vo.setCost(cost);
					vo.setRdate(rdate);
					vo.setAdate(adate);
					vo.setAct_fname(fname.length() == 0 ? "golfclub.png": fname); // 사진 업로드가 되지 않았을 경우 기본 이미지로 변경

					System.out.println("vo: " + vo);
					int flag = dao.update(vo);
					System.out.println("flag: " + flag);

					if (flag == 0) {
						System.out.println("update failed!");
						status = 4;
						request.setAttribute("status", status);
						response.sendRedirect("club_activity_update.do?club_id=" + club_id + "&&act_id=" + act_id + "&club_name=" + club_name);
					} else {
						if (club_id == 0) {
							response.sendRedirect("club_activity.do?club_id=" + club_id + "&club_name=" + club_name);
						} else {
							response.sendRedirect("club_activity.do?club_id=" + club_id + "&club_name=" + club_name);
						}
					}
				}
			}
		}
	} // end doPost()
}
// end class