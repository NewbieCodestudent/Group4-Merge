package test.com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.com.member.model.MemberDAO;
import test.com.member.model.MemberDAOimpl;
import test.com.member.model.MemberVO;

/**
 * Servlet implementation class IdCheckController
 */
@WebServlet("/idCheck.do")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdCheckController() {
		super();
		System.out.println("IdCheckController()..");
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

		System.out.println(request.getParameter("member_id"));
		MemberDAO dao = new MemberDAOimpl();
		MemberVO vo = new MemberVO();
		vo.setMember_id(request.getParameter("member_id"));
		MemberVO vo2 = dao.idCheck(vo);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Credentials", "true");

		PrintWriter out = response.getWriter();
		if (vo2 != null) {
			out.print("0");
		} else {
			out.print("1");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
