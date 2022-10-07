package test.com.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

import test.com.activity.model.ActivityDAOimpl;
import test.com.activity.model.ActivityVO;
import test.com.club.model.ClubDAO;
import test.com.club.model.ClubDAOimpl;
import test.com.club.model.ClubVO;
import test.com.member.model.MemberDAO;
import test.com.member.model.MemberDAOimpl;
import test.com.member.model.MemberVO;

/**
 * Servlet implementation class MypageController
 */
@WebServlet({ "/mypage.do", "/mypage_update.do", "/mypage_updateOK.do", "/mypage_delete.do", "/mypage_deleteOK.do",
      "/mypage_club.do", "/mypage_activity.do" })

public class MypageController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private MemberDAO dao = new MemberDAOimpl();

   /**
    * @see HttpServlet#HttpServlet()
    */
   public MypageController() {
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

      if (sPath.equals("/mypage.do")) {
         
         HttpSession session = request.getSession();
         String member_id = (String) session.getAttribute("member_id");
         System.out.println("member_id:" + member_id);

         if (member_id != null) {
         System.out.println(request.getParameter("member_id"));
         member_id = request.getParameter("member_id");
         MemberVO vo = new MemberVO();

         vo.setMember_id(member_id);
         MemberVO vo2 = dao.selectOne(vo);
         System.out.println("vo2:" + vo2);
         
         /**
          * 1. 작성자: 이주희 (백엔드)
          * 2. 기능: 마이페이지 접속자가 참여한 액티비티 모임 조회 기능
          */
         
         List<ActivityVO> activity_list = new ActivityDAOimpl().selectAllByID(member_id);
		 request.setAttribute("activity_list", activity_list);
         
         request.setAttribute("vo2", vo2);
         
         ClubVO vo1 = new ClubVO();
         vo1.setMember_id(member_id);
         ClubDAO dao1 = new ClubDAOimpl();
         List<ClubVO> myclublist = dao1.selectAllMyClub(vo1);
         System.out.println("myclublist:" + myclublist);
         request.setAttribute("myclublist", myclublist);
         request.getRequestDispatcher("MYPAGE/mypage.jsp").forward(request, response);
         } else
            request.getRequestDispatcher("HTML/login.jsp").forward(request, response);
//         
      } else if (sPath.equals("/mypage_update.do")) {

         System.out.println(request.getParameter("member_id"));

         MemberVO vo = new MemberVO();

         vo.setMember_id(request.getParameter("member_id"));

         MemberVO vo2 = dao.selectOne(vo);
         System.out.println("vo2:" + vo2);
         // db연결

         request.setAttribute("vo2", vo2);

         request.getRequestDispatcher("MYPAGE/update.jsp").forward(request, response);
      } else if (sPath.equals("/mypage_delete.do")) {
         request.getRequestDispatcher("MYPAGE/delete.jsp").forward(request, response);
      } else if (sPath.equals("/mypage_deleteOK.do")) {
         HttpSession session = request.getSession();
         String member_id = (String) session.getAttribute("member_id");
         System.out.println("member_id:" + member_id);

         if (member_id != null) {
            MemberVO vo = new MemberVO();
//            HttpSession session = request.getSession();
//            String member_id = (String) session.getAttribute("member_id");

            vo.setMember_id(member_id);

//         String member_id = request.getParameter("member_id");
            System.out.println(member_id);

            vo.setMember_id(request.getParameter("member_id"));

            int result = dao.delete(vo);
            System.out.println(result);
            if (result == 1) {
               session.removeAttribute("member_id");
               response.sendRedirect("home.do");
            } else
               response.sendRedirect("mypage_deleteOK.do?member_id=" + member_id);
         } else
            request.getRequestDispatcher("HTML/login.jsp").forward(request, response);
         }// end deleteOK

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
      if (sPath.equals("/mypage_updateOK.do")) {
         HttpSession session = request.getSession();
         String member_id = (String) session.getAttribute("member_id");
         System.out.println("member_id:" + member_id);

         if (member_id != null) {
//            String member_id = null;
            String pw = null;
            String location = null;
            String img_name = null;

            long checkFileSize = 0;

            String dir_path = request.getServletContext().getRealPath("/upload");
            System.out.println(dir_path);

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
                     if (item.isFormField()) { // name,age,name,gender,birth,location,handi받기
                        if (item.getFieldName().equals("member_id")) {
                           member_id = item.getString("UTF-8");
                        }
                        if (item.getFieldName().equals("pw")) {
                           pw = item.getString("UTF-8");

                        }

                        if (item.getFieldName().equals("location")) {
                           location = item.getString("UTF-8");

                        }

                     } else {// file 정보받기.
                        img_name = FilenameUtils.getName(item.getName());
                        if (item.getSize() != 0) {
                           img_name = member_id + "_" + img_name;
                           File saveFile = new File(dir_path, img_name);

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

            } // end if << isMultipart
            System.out.println("member_id:" + member_id);
            System.out.println("pw:" + pw);
            System.out.println("location:" + location);
            System.out.println("img_name:" + img_name);

            MemberVO vo = new MemberVO();

//            HttpSession session = request.getSession();
//            member_id = (String) session.getAttribute("member_id");
            vo.setPw(pw);
            vo.setLocation(location);
            vo.setMember_id(member_id);
            vo.setImg_name(img_name.length() == 0 ? "profill.png" : img_name);
            int result = dao.update(vo);
            System.out.println("result:" + result);

            if (result == 1) {
               response.sendRedirect("mypage.do?member_id=" + member_id);

            } else
               response.sendRedirect("mypage_update.do?member_id=" + member_id);
//         response.sendRedirect("home.do");
         } else
            request.getRequestDispatcher("HTML/login.jsp").forward(request, response);
      } // end updateok

   }// end dopost

}// end class