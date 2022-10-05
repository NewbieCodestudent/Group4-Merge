package test.com.clubmember.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.com.club.model.ClubVO;
import test.com.clubmember.model.ClubMemberDAO;
import test.com.clubmember.model.ClubMemberDAOimpl;
import test.com.clubmember.model.ClubMemberVO;

/**
 * Servlet implementation class ClubMemberController
 */
@WebServlet({ "/club_join_apply.do", "/club_join_applyOK.do", "/club_join_accept.do","/club_join_acceptOK.do", "/cm_leave.do",
		"/cm_leaveOK.do", "/cm_kick.do","/cm_kickOK.do","/cm_grant.do","/cm_grantOK.do","/cm_revoke.do","/cm_revokeOK.do" })
public class ClubMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubMemberDAO dao = new ClubMemberDAOimpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClubMemberController() {
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
		System.out.println("doGet:" + sPath);
		if (sPath.equals("/club_join_apply.do")) {
			HttpSession session = request.getSession();
			String member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);

			if (member_id != null) {
				request.getRequestDispatcher("CLUBMEMBER/club_join.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.do");
			}
		}else if(sPath.equals("/cm_leave.do")) {
			request.getRequestDispatcher("CLUBMEMBER/delete.jsp").forward(request, response);
		}else if (sPath.equals("/cm_leaveOK.do")) {
			String member_id = request.getParameter("member_id");
			long club_id = Long.parseLong(request.getParameter("club_id"));
			System.out.println(club_id);
			System.out.println(member_id);

			ClubMemberVO vo = new ClubMemberVO();
			vo.setClub_id(club_id);
			HttpSession session = request.getSession();
			member_id = (String) session.getAttribute("member_id");
			System.out.println("member_id:" + member_id);
			vo.setMember_id(member_id);
			
			int result = dao.delete(vo);
			System.out.println(result);
			if (result == 1)
				response.sendRedirect("club_selectAll.do");
			else
				response.sendRedirect("club_selectOne.do?club_id=" + club_id);
		}else if (sPath.equals("/club_join_accept.do")) {			
			
			request.getRequestDispatcher("CLUBMEMBER/join_accept.jsp").forward(request, response);
		}else if (sPath.equals("/cm_grant.do")) {
			request.getRequestDispatcher("CLUBMEMBER/grant.jsp").forward(request, response);
		}else if (sPath.equals("/cm_revoke.do")) {
			request.getRequestDispatcher("CLUBMEMBER/revoke.jsp").forward(request, response);
		}else if (sPath.equals("/cm_kick.do")) {
			request.getRequestDispatcher("CLUBMEMBER/kick.jsp").forward(request, response);
		}
	}// end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sPath = request.getServletPath();
		System.out.println("doPost:" + sPath);
		
		if (sPath.equals("/club_join_applyOK.do")) {
			
			long club_id = Long.parseLong(request.getParameter("club_id"));
			String member_id = request.getParameter("member_id");
			System.out.println(request.getParameter("club_id:"+club_id));
			System.out.println(request.getParameter("member_id:"+member_id));

			ClubMemberVO vo = new ClubMemberVO();
			
			vo.setClub_id(club_id);
			HttpSession session = request.getSession();
			member_id = (String) session.getAttribute("member_id");
			vo.setMember_id(member_id);
			
//			String location = request.getParameter("location");
//			int age = Integer.parseInt(request.getParameter("age"));
//			String gender = request.getParameter("gender");
//			String searchKey = request.getParameter("searchKey");
//			String searchWord = request.getParameter("searchWord");
//			
//			String query = dao.createQuery(location, gender, age);
//			
//			List<ClubMemberVO> vos = dao.compare(club_id, member_id);
//			request.setAttribute("vos", vos);
//			request.setAttribute("club_id", club_id);
			
			int result = dao.insert(vo);
			
			System.out.println("result:" + result);

			if (result == 1)
				response.sendRedirect("club_selectOne.do?club_id="+club_id);
			else
				response.sendRedirect("club_join_apply.do");

		} // end joinOK
		else if(sPath.equals("/club_join_acceptOK.do")) {
			long cm_id = Long.parseLong(request.getParameter("cm_id"));
			System.out.println(cm_id);
			long club_id = Long.parseLong(request.getParameter("club_id"));
			System.out.println(club_id);

			ClubMemberVO vo = new ClubMemberVO();
			vo.setClub_id(club_id);
			vo.setCm_id(cm_id);
			
			int result = dao.update(vo);
			System.out.println(result);

				response.sendRedirect("club_selectOne.do?club_id="+club_id);
			
		}else if(sPath.equals("/cm_grantOK.do")) {
			long cm_id = Long.parseLong(request.getParameter("cm_id"));
			System.out.println(cm_id);
			long club_id = Long.parseLong(request.getParameter("club_id"));
			System.out.println(club_id);

			ClubMemberVO vo = new ClubMemberVO();
			vo.setClub_id(club_id);
			vo.setCm_id(cm_id);
			vo.setCmtype(1);
			
			int result = dao.update_cmtype(vo);
			System.out.println(result);

				response.sendRedirect("club_selectOne.do?club_id="+club_id);			
		}else if(sPath.equals("/cm_revokeOK.do")) {
			long cm_id = Long.parseLong(request.getParameter("cm_id"));
			System.out.println(cm_id);
			long club_id = Long.parseLong(request.getParameter("club_id"));
			System.out.println(club_id);

			ClubMemberVO vo = new ClubMemberVO();
			vo.setClub_id(club_id);
			vo.setCm_id(cm_id);
			vo.setCmtype(0);
			
			int result = dao.update_cmtype(vo);
			System.out.println(result);

				response.sendRedirect("club_selectOne.do?club_id="+club_id);			
		}else if(sPath.equals("/cm_kickOK.do")) {
			long cm_id = Long.parseLong(request.getParameter("cm_id"));
			System.out.println(cm_id);
			long club_id = Long.parseLong(request.getParameter("club_id"));
			System.out.println(club_id);

			ClubMemberVO vo = new ClubMemberVO();
			vo.setClub_id(club_id);
			vo.setCm_id(cm_id);
			
			
			int result = dao.kick(vo);
			System.out.println(result);

				response.sendRedirect("club_selectOne.do?club_id="+club_id);			
		}
	}

}
