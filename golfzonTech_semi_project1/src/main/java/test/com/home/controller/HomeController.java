package test.com.home.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.com.activity.model.ActivityDAO;
import test.com.activity.model.ActivityDAOimpl;
import test.com.activity.model.ActivityVO;
import test.com.club.model.ClubDAOimpl;
import test.com.club.model.ClubVO;

/**
 * 1. 작성자: 이주희 (백엔드)
 * 2. 기능: 메인 페이지 추천 모임/ 액티비티 조회 기능
 */
@WebServlet({"/home.do", "/AD_scr.do", "/event_selectAll.do", "/event_selectOne.do"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sPath = request.getServletPath();
		System.out.println("doGet: "+sPath);
		
		if (sPath.equals("/home.do")) {
			
			// 메인페이지 추천 모임 => 최근 신설된 모임 5개
			List<ClubVO> club_list = new ClubDAOimpl().selectAllByID();
			System.out.println("club_list: "+club_list);
			request.setAttribute("club_list", club_list);
			
			ActivityDAO dao = new ActivityDAOimpl();
			
			List<ActivityVO> act_list_loc = dao.selectAllByLoc("강원");
			System.out.println("act_list_loc: "+act_list_loc);
			request.setAttribute("act_list_loc", act_list_loc);
			
			List<ActivityVO> act_list_date = dao.selectAllByDate();
			System.out.println(act_list_date);
			System.out.println("act_list_date: "+act_list_date);
			request.setAttribute("act_list_date", act_list_date);
			
			request.getRequestDispatcher("HTML/home.jsp").forward(request, response);
		} else if (sPath.equals("/AD_scr.do")) {
			request.getRequestDispatcher("EVENT/AD_scr.jsp").forward(request, response);
		} else if (sPath.equals("/event_selectAll.do")) {
			request.getRequestDispatcher("EVENT/selectAll.jsp").forward(request, response);
		} else if (sPath.equals("/event_selectOne.do")) {
			request.getRequestDispatcher("EVENT/selectOne.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sPath = request.getServletPath();
		System.out.println("doPost: "+sPath);
	}

}
