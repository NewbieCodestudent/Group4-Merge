package test.com.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.com.member.model.MemberDAO;
import test.com.member.model.MemberDAOimpl;
import test.com.member.model.MemberVO;

public class LoginAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(request.getParameter("member_id"));
		System.out.println(request.getParameter("pw"));

		MemberDAO dao = new MemberDAOimpl();
		MemberVO vo = new MemberVO();
		vo.setMember_id(request.getParameter("member_id"));
		vo.setPw(request.getParameter("pw"));

		MemberVO vo2 = dao.login(vo);
		System.out.println("login:" + vo2);

		if (vo2 != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member_id", vo2.getMember_id());			
			response.sendRedirect("home.do");
		} else {
			response.sendRedirect("login.do");
		}
	}
	
	

}
