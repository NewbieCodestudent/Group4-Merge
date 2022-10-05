package test.com.activitymember.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.com.activity.model.ActivityDAOimpl;
import test.com.activity.model.ActivityVO;
import test.com.activitymember.model.ActivityMemberDAO;
import test.com.activitymember.model.ActivityMemberDAOimpl;
import test.com.activitymember.model.ActivityMemberVO;

/**
 * Servlet implementation class ActivityMemberController
 */
@WebServlet({ "/activity_member_insertOK.do", "/activity_member.updateOK.do", "/activity_member.deleteOK.do",
	"/club_activity_member_insertOK.do", "/club_activity_member.updateOK.do", "/club_activity_member.deleteOK.do"})
public class ActivityMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ActivityMemberDAO dao = new ActivityMemberDAOimpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivityMemberController() {
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
		if (sPath.equals("/activity_member.deleteOK.do")) {
			Long am_id = Long.parseLong(request.getParameter("am_id"));
//			System.out.println("am_id: " + am_id);
			Long act_id = Long.parseLong(request.getParameter("act_id"));
//			System.out.println("act_id: " + act_id);
			int size = Integer.parseInt(request.getParameter("size"));
//			System.out.println("size: "+size);

			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				ActivityMemberVO vo = new ActivityMemberVO();
				vo.setAm_id(am_id); // (am_id)번의 값을 가진 액티비티 참가자의 정보를 삭제
				int flag = dao.deleteOne(vo);
//				System.out.println("flag: " + flag);
				if (size - flag < 4) { // 강퇴 성공 후 액티비티 참여인원수가 4보다 작을 경우 마감 상태(status)를 0로 변경
					ActivityVO vo1 = new ActivityVO();
					vo1.setAct_id(act_id);
					vo1.setStatus(0);
					int result = new ActivityDAOimpl().changeStatusBySize(vo1);
//					System.out.println(result);
				}
				if (flag == 1) {
					response.sendRedirect("activity_selectOne.do?act_id=" + act_id);
				} else {
					response.sendRedirect("activity_selectAll.do");
				}
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/club_activity_member.deleteOK.do")) {
			long am_id = Long.parseLong(request.getParameter("am_id"));
//			System.out.println("am_id: " + am_id);
			long act_id = Long.parseLong(request.getParameter("act_id"));
//			System.out.println("act_id: " + act_id);
			long club_id = Long.parseLong(request.getParameter("club_id"));
//			System.out.println("act_id: " + act_id);
			String club_name = request.getParameter("club_name");
			club_name = URLEncoder.encode(club_name, "UTF-8");
//			System.out.println("act_id: " + act_id);
			int size = Integer.parseInt(request.getParameter("size"));
//			System.out.println("size: "+size);

			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				ActivityMemberVO vo = new ActivityMemberVO();
				vo.setAm_id(am_id); // (am_id)번의 값을 가진 액티비티 참가자의 정보를 삭제
				int flag = dao.deleteOne(vo);
//				System.out.println("flag: " + flag);
				if (size - flag < 4) { // 강퇴 성공 후 액티비티 참여인원수가 4보다 작을 경우 마감 상태(status)를 0로 변경
					ActivityVO vo1 = new ActivityVO();
					vo1.setAct_id(act_id);
					vo1.setStatus(0);
					int result = new ActivityDAOimpl().changeStatusBySize(vo1);
//					System.out.println(result);
				}
				if (flag == 1) {
					response.sendRedirect("club_activity_selectOne.do?act_id=" + act_id + "&club_id=" + club_id+"&club_name="+club_name);
				} else {
					response.sendRedirect("club_activity_selectOne.do?act_id=" + act_id + "&club_id=" + club_id+"&club_name="+club_name);
				}
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
		if (sPath.equals("/activity_member_insertOK.do")) {
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				long act_id = Long.parseLong(request.getParameter("act_id"));
//				System.out.println("act_id: " + act_id);
//				String commenter = (String) session.getAttribute("user_id");

				ActivityMemberVO vo = new ActivityMemberVO();
				vo.setAct_id(act_id);
				vo.setMember_id(member_id);
				vo.setQualified(1); // 액티비티 참여 조건의 경우 추후에 진행할 예정으로 더미 변수(1)를 넣어둠.
				vo.setApproved(0);
				vo.setAmtype(0);

				int flag = dao.insert(vo);
//				System.out.println("flag: " + flag);

				if (flag == 1) {
					response.sendRedirect("activity_selectOne.do?act_id=" + act_id);
				} else {
					response.sendRedirect("activity_selectAll.do");
				}
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/activity_member.updateOK.do")) {

			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				Long am_id = Long.parseLong(request.getParameter("am_id"));
//				System.out.println("am_id: " + am_id);
				Long act_id = Long.parseLong(request.getParameter("act_id"));
//				System.out.println("act_id: " + act_id);
				int size = Integer.parseInt(request.getParameter("size"));
//				System.out.println("size: " + size);

				ActivityMemberVO vo = new ActivityMemberVO();
				vo.setAct_id(act_id); // (act_id)번의 액티비티에서
				vo.setAm_id(am_id); // (am_id)번의 값을 가진 액티비티 참가자의
				vo.setApproved(1); // 가입 상태인 approved를 1(가입)로 변경
				int flag = dao.update(vo);
//				System.out.println("flag: " + flag);
				if (size + flag == 4) { // 승인 성공 후 액티비티 참여인원수가 4일 경우 마감 상태(status)를 1로 변경
					ActivityVO vo1 = new ActivityVO();
					vo1.setAct_id(act_id);
					vo1.setStatus(1);
					int result = new ActivityDAOimpl().changeStatusBySize(vo1);
//					System.out.println(result);
				}
				if (flag == 1) {
					response.sendRedirect("activity_selectOne.do?act_id=" + act_id);
				} else {
					response.sendRedirect("activity_selectAll.do");
				}
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/club_activity_member_insertOK.do")) {
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				long act_id = Long.parseLong(request.getParameter("act_id"));
				long club_id = Long.parseLong(request.getParameter("club_id"));
				String club_name = request.getParameter("club_name");
				club_name = URLEncoder.encode(club_name, "UTF-8");
//				System.out.println("act_id: " + act_id);
//				String commenter = (String) session.getAttribute("user_id");

				ActivityMemberVO vo = new ActivityMemberVO();
				vo.setAct_id(act_id);
				vo.setMember_id(member_id);
				vo.setQualified(1); // 액티비티 참여 조건의 경우 추후에 진행할 예정으로 더미 변수(1)를 넣어둠.
				vo.setApproved(0);
				vo.setAmtype(0);

				int flag = dao.insert(vo);
//				System.out.println("flag: " + flag);

				if (flag == 1) {
					response.sendRedirect("club_activity_selectOne.do?act_id=" + act_id + "&club_id=" + club_id+"&club_name="+club_name);
				} else {
					response.sendRedirect("club_activity_selectOne.do?act_id=" + act_id + "&club_id=" + club_id+"&club_name="+club_name);
				}
			} else {
				response.sendRedirect("login.do");
			}
		} else if (sPath.equals("/club_activity_member.updateOK.do")) {

			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				Long am_id = Long.parseLong(request.getParameter("am_id"));
//				System.out.println("am_id: " + am_id);
				long act_id = Long.parseLong(request.getParameter("act_id"));
				long club_id = Long.parseLong(request.getParameter("club_id"));
				String club_name = request.getParameter("club_name");
				club_name = URLEncoder.encode(club_name, "UTF-8");
//				System.out.println("act_id: " + act_id);
				int size = Integer.parseInt(request.getParameter("size"));
//				System.out.println("size: " + size);

				ActivityMemberVO vo = new ActivityMemberVO();
				vo.setAct_id(act_id); // (act_id)번의 액티비티에서
				vo.setAm_id(am_id); // (am_id)번의 값을 가진 액티비티 참가자의
				vo.setApproved(1); // 가입 상태인 approved를 1(가입)로 변경
				int flag = dao.update(vo);
//				System.out.println("flag: " + flag);
				if (size + flag == 4) { // 승인 성공 후 액티비티 참여인원수가 4일 경우 마감 상태(status)를 1로 변경
					ActivityVO vo1 = new ActivityVO();
					vo1.setAct_id(act_id);
					vo1.setStatus(1);
					int result = new ActivityDAOimpl().changeStatusBySize(vo1);
//					System.out.println(result);
				}
				if (flag == 1) {
					response.sendRedirect("club_activity_selectOne.do?act_id=" + act_id + "&club_id=" + club_id+"&club_name="+club_name);
				} else {
					response.sendRedirect("club_activity_selectOne.do?act_id=" + act_id + "&club_id=" + club_id+"&club_name="+club_name);
				}
			} else {
				response.sendRedirect("login.do");
			}
		}
	}
}