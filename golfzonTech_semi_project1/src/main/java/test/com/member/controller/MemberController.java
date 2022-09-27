package test.com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.com.member.model.MemberDAO;
import test.com.member.model.MemberDAOimpl;
import test.com.member.model.MemberVO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet({ "/member_join.do", "/member_joinOK.do", "/member_selectOne.do", "/member_gender.do", "/member_location.do", "/member_handi.do" })
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO dao = new MemberDAOimpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberController() {
		super();
		System.out.println("MemberController()...");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sPath = request.getServletPath();

		System.out.print("doGet():");
		System.out.println(sPath);
		// 포워딩
		if (sPath.equals("/member_join.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("HTML/join.jsp");
			rd.forward(request, response);
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
		
		System.out.print("doPost():");
		System.out.println(sPath);
		if (sPath.equals("/member_joinOK.do")) {

			new JoinOKAction().execute(request, response);
		}

	}
	

}
