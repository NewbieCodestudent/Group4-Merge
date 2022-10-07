package test.com.club.controller;

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
import test.com.album.model.AlbumDAOimpl;
import test.com.album.model.AlbumVO;
import test.com.board.model.BoardDAOimpl;
import test.com.board.model.BoardVO;
import test.com.club.model.ClubDAO;
import test.com.club.model.ClubDAOimpl;
import test.com.club.model.ClubVO;
import test.com.clubmember.model.ClubMemberDAO;
import test.com.clubmember.model.ClubMemberDAOimpl;
import test.com.clubmember.model.ClubMemberVO;

/**
 * Servlet implementation class ClubController
 */
@WebServlet({ "/club_insert.do", "/club_insertOK.do", "/club_update.do", "/club_updateOK.do", "/club_delete.do",
      "/club_deleteOK.do", "/club_selectAll.do", "/club_searchList.do", "/club_selectOne.do" })
public class ClubController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   ClubDAO dao = new ClubDAOimpl();

   /**
    * @see HttpServlet#HttpServlet()
    */
   public ClubController() {
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
      if (sPath.equals("/club_insert.do")) {
         HttpSession session = request.getSession();
         String member_id = (String) session.getAttribute("member_id");
         System.out.println("member_id:" + member_id);

         if (member_id != null) {
            request.getRequestDispatcher("CLUB/insert.jsp").forward(request, response);
         } else {
            response.sendRedirect("login.do");
         }
      } else if (sPath.equals("/club_selectAll.do")) {
         String order = request.getParameter("order") == null ? "desc" : request.getParameter("order");
         System.out.println(order);
         
         List<ClubVO> vos = dao.selectAll(order);
         System.out.println("vos.size():" + vos.size());

         request.setAttribute("vos", vos);

         request.getRequestDispatcher("CLUB/selectAll.jsp").forward(request, response);

      } else if (sPath.equals("/club_selectOne.do")) {
    	  
    	  HttpSession session = request.getSession();
          String member_id = (String) session.getAttribute("member_id");
          System.out.println("doPost:" + sPath);

          if (member_id != null) {
         System.out.println(request.getParameter("club_id"));

         ClubVO vo = new ClubVO();
         vo.setClub_id(Long.parseLong(request.getParameter("club_id")));

         ClubDAO dao = new ClubDAOimpl();
         ClubVO vo2 = dao.selectOne(vo);
         System.out.println("vo2:" + vo2);
         // db연결
         request.setAttribute("vo2", vo2);

         ClubVO vo3 = dao.leaderinfo(vo);
         System.out.println("vo3:" + vo3);
         request.setAttribute("vo3", vo3);

         ClubMemberDAO dao1 = new ClubMemberDAOimpl();
         List<ClubMemberVO> vos = dao1.clubmembers(vo);//모임 가입 멤버목록
         System.out.println("vos:" + vos);
         request.setAttribute("vos", vos);

         List<ClubMemberVO> vos2 = dao1.waiting(vo);//모임가입 대기 중 멤버목록
         System.out.println("vos2:" + vos2);
         request.setAttribute("vos2", vos2);

         String location = vo2.getLocation();
         int age = vo2.getAge();
         String gender = vo2.getGender();

         ClubMemberVO vo1 = new ClubMemberVO();
         ClubMemberDAO dao2 = new ClubMemberDAOimpl();
         String query = dao2.createQuery(location, gender, age);
         System.out.println(query);
         long club_id = Long.parseLong(request.getParameter("club_id"));

         boolean isQualified = dao2.isqualified(club_id, member_id, query);
         
         String club_leader = vo3.getClub_leader();
         boolean isLeader = club_leader.equals(member_id);
         
         ClubMemberVO vo5 = new ClubMemberVO();
         ClubMemberDAO dao4 = new ClubMemberDAOimpl();
         vo5.setClub_id(club_id);
         vo5.setMember_id(member_id);
         dao4.selectStatus(vo5);
         
         request.setAttribute("vo5", vo5);

         
         ClubVO vo4 = new ClubVO();
         ClubMemberDAO dao3 = new ClubMemberDAOimpl();
         vo4.setClub_id(club_id);
         vo4.setMember_id(member_id);
         System.out.println("vo4club_id:"+club_id);
         System.out.println("vo4member_id:"+member_id);
         
         boolean isMember = dao3.isMember(vo4);//접속자가 모임가입신청을 했는지 여부
         
         /**
          * 1. 작성자: 이주희 (백엔드)
          * 2. 기능: 모임 상세페이지 상 게시글, 액비티비, 앨범 미리보기 조회 기능
          */
        // 해당 모임의 공지글을 호출
		List<BoardVO> notices = new BoardDAOimpl().selectAll_notice(Long.parseLong(request.getParameter("club_id")));
		// 해당 모임의 게시글을 호출
		List<BoardVO> commons = new BoardDAOimpl().selectAll_common(Long.parseLong(request.getParameter("club_id")), "desc");
		// 해당 모임의 비공개 액티비티를 호출
		List<ActivityVO> activity_list = new ActivityDAOimpl().selectAllByClub(Long.parseLong(request.getParameter("club_id")));
		// 해당 모임의 앨범 게시물을 호출
		AlbumVO vo_album = new AlbumVO();
		vo_album.setClub_id(Long.parseLong(request.getParameter("club_id")));
		List<AlbumVO> album_list = new AlbumDAOimpl().selectAll(vo_album, "desc");
         
         request.setAttribute("isMember", isMember);
         request.setAttribute("isQualified", isQualified);
         request.setAttribute("club_id", club_id);
         request.setAttribute("isLeader", isLeader);
         request.setAttribute("notices", notices);
		 request.setAttribute("commons", commons);
		 request.setAttribute("activity_list", activity_list);
		 request.setAttribute("album_list", album_list);
         System.out.println("isLeader?:"+isLeader);
         System.out.println("isQualified?:" + isQualified);
         System.out.println("isMember:" + isMember);
         request.getRequestDispatcher("CLUB/selectOne.jsp").forward(request, response);
          }else
        	  request.getRequestDispatcher("HTML/login.jsp").forward(request, response);
      } else if (sPath.equals("/club_update.do")) {

         System.out.println("doGet:" + sPath);
         System.out.println(request.getParameter("club_id"));

         ClubVO vo = new ClubVO();
         vo.setClub_id(Integer.parseInt(request.getParameter("club_id")));

         ClubVO vo2 = dao.selectOne(vo);
         System.out.println("vo2:" + vo2);
         // db연결

         request.setAttribute("vo2", vo2);

         request.getRequestDispatcher("CLUB/update.jsp").forward(request, response);
      } else if (sPath.equals("/club_delete.do")) {
         request.getRequestDispatcher("CLUB/delete.jsp").forward(request, response);
      } else if (sPath.equals("/club_deleteOK.do")) {
         long club_id = 0l;
         club_id = Long.parseLong(request.getParameter("club_id"));
         System.out.println(club_id);
         
         HttpSession session = request.getSession();
         String member_id = (String) session.getAttribute("member_id");
         System.out.println("member_id:" + member_id);
         
         ClubVO vo = new ClubVO();
         vo.setClub_id(club_id);
         
         int result_delete = new ActivityDAOimpl().deleteAll(vo);
		 System.out.println("result_delete: "+result_delete);
         
         int result = dao.delete(vo);
         System.out.println(result);
         if (result == 1)
            response.sendRedirect("club_selectAll.do");
         else
            response.sendRedirect("club_selectOne.do?club_id=" + club_id);
      } else if (sPath.equals("/club_searchList.do")) {
         long club_id=0l;
         if (request.getParameter("club_id") != null) {
            club_id = Long.parseLong(request.getParameter("club_id"));
         }
         String searchWord = request.getParameter("searchWord");
         String searchKey = request.getParameter("searchKey");
         String gender = request.getParameter("gender");
         int age = Integer.parseInt(request.getParameter("age"));
         String location = request.getParameter("location");

         System.out.println(searchWord);
         System.out.println(searchKey);
         System.out.println(gender);
         System.out.println(age);
         System.out.println(location);

         String query = dao.createQuery_searchList(location, gender, age, searchKey);
         
         ClubDAO dao = new ClubDAOimpl();
         // db연결
         List<ClubVO> vos = dao.searchList(query,searchWord);
         System.out.println("vos.size():" + vos.size());

         request.setAttribute("vos", vos);
         request.setAttribute("club_id", club_id);
         request.getRequestDispatcher("CLUB/selectAll.jsp").forward(request, response);

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
      if (sPath.equals("/club_insertOK.do")) {
         HttpSession session = request.getSession();
         String member_id = (String) session.getAttribute("member_id");
         System.out.println("doPost:" + sPath);

         if (member_id != null) {

            String dir_path = request.getServletContext().getRealPath("/upload");
            System.out.println(dir_path);

            ClubDAOimpl dao2 = new ClubDAOimpl();
            long club_id = dao2.club_id();
            System.out.println("club_id:" + club_id);
            System.out.println("member_id:" + member_id);

            int fileSizeMax = 1024 * 1024 * 100;
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

            if (isMultipartContent) {
               DiskFileItemFactory factory = new DiskFileItemFactory();
               factory.setSizeThreshold(fileSizeMax);

               ServletFileUpload sfu = new ServletFileUpload(factory);
               sfu.setFileSizeMax(fileSizeMax);// 파일 사이즈 제한
            
               String club_leader = null;
               String club_name = null;
               String club_desc = null;
               String gender = null;
               String age = null;
               String location = null;
               String close = null;
               String club_img = null;

               try {
                  List<FileItem> items = sfu.parseRequest(request);
                  for (FileItem item : items) {
                     if (item.isFormField()) { // name,age 받기
                        if (item.getFieldName().equals("club_leader")) {
                           club_leader = item.getString("UTF-8");
                        }
                        if (item.getFieldName().equals("club_leader")) {
                           club_leader = item.getString("UTF-8");
                        }
                        if (item.getFieldName().equals("club_name")) {
                           club_name = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("club_desc")) {
                           club_desc = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("gender")) {
                           gender = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("age")) {
                           age = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("location")) {
                           location = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("close")) {
                           close = item.getString("UTF-8");

                        }

                     } else {// file 정보받기.
//               

                        club_img = FilenameUtils.getName(item.getName());
                        if (item.getSize() != 0)
                           club_img = club_name + "_" + club_img;
                        File saveFile = new File(dir_path, club_img);

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

               System.out.println(club_leader);
               System.out.println(club_name);
               System.out.println(club_desc);
               System.out.println(gender);
               System.out.println(age);
               System.out.println(location);
               System.out.println(close);
               System.out.println("club_img:"+(club_img.length() == 0 ? "golfclub.png":club_img));

               ClubVO vo = new ClubVO();

               vo.setClub_id(club_id);
               
               vo.setClub_leader(club_leader);
               vo.setClub_name(club_name);
               vo.setClub_desc(club_desc);
               vo.setGender(gender);
               vo.setAge(Integer.parseInt(age));
               vo.setLocation(location);
               vo.setClose(Integer.parseInt(close));
               vo.setClub_img(club_img.length() ==0? "golfclub.png" : club_img);

               int result = dao.insert(vo);

               System.out.println("result:" + result);
               if (result == 1) {
                  System.out.println(club_id);

                  System.out.println(request.getParameter("cm_id"));
                  System.out.println(request.getParameter("club_id"));
                  System.out.println(request.getParameter("member_id"));

                  ClubMemberVO vo1 = new ClubMemberVO();
                  vo1.setClub_id(club_id);
                  vo1.setMember_id(member_id);

                  dao.insertleader(vo1);

                  response.sendRedirect("club_selectAll.do");

               } else
                  response.sendRedirect("club_insert.do");

            } // end isMultipartContent
         } else
            request.getRequestDispatcher("HTML/login.jsp").forward(request, response);
      } // end insertOK
      else if (sPath.equals("/club_updateOK.do")) {

         HttpSession session = request.getSession();
         String member_id = (String) session.getAttribute("member_id");

         if (member_id != null) {

            String club_id = null;
            String club_name = null;
            String club_desc = null;
            String gender = null;
            String age = null;
            String location = null;
            String close = null;
            String club_img = null;
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
                     if (item.isFormField()) { // name,age,name,tel 받기
                        if (item.getFieldName().equals("club_id")) {
                           club_id = item.getString("UTF-8");
                        }
                        if (item.getFieldName().equals("club_name")) {
                           club_name = item.getString("UTF-8");
                        }
                        if (item.getFieldName().equals("club_desc")) {
                           club_desc = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("gender")) {
                           gender = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("age")) {
                           age = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("location")) {
                           location = item.getString("UTF-8");

                        }
                        if (item.getFieldName().equals("close")) {
                           close = item.getString("UTF-8");

                        }

//                     
                     } else {// file 정보받기.
//                     
//                     System.out.println("파일의 키 : " + item.getFieldName());
//                     System.out.println("파일 파일명 : " + item.getName());
                        // 플젝은 타입+사이즈로 빈이미지체크하기System.out.println("파일 컨텐츠 타입 : " +
                        // item.getContentType());
//                     System.out.println("파일 사이즈  : " + item.getSize());
                        checkFileSize = item.getSize();
                        System.out.println(checkFileSize);
                        club_img = FilenameUtils.getName(item.getName());
                        if (checkFileSize != 0) {

                           club_img = club_name + "_" + club_img;
                           File saveFile = new File(dir_path, club_img);

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
            System.out.println("club_id:" + club_id);
            System.out.println("club_name:" + club_name);
            System.out.println("club_desc:" + club_desc);
            System.out.println("gender:" + gender);
            System.out.println("age:" + age);
            System.out.println("location:" + location);
            System.out.println("close:" + close);

            System.out.println("club_img:" + club_img);

            ClubVO vo = new ClubVO();
            vo.setClub_id(Long.parseLong(club_id));
            vo.setClub_name(club_name);
            vo.setClub_desc(club_desc);
            vo.setGender(gender);
            vo.setAge(Integer.parseInt(age));
            vo.setLocation(location);
            vo.setClose(Integer.parseInt(close));
            vo.setClub_img(club_img.length() == 0 ? "golfclub.png" : club_img);

            int result = dao.update(vo);
            System.out.println("result:" + result);

            if (result == 1)
               response.sendRedirect("club_selectOne.do?club_id=" + club_id);
            else
               response.sendRedirect("club_update.do?club_id=" + club_id);
         } else
            request.getRequestDispatcher("HTML/login.jsp").forward(request, response);

      } // end else if updateok

   }// end doPost

}// end class