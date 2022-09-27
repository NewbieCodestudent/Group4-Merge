package test.com.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import test.com.club.model.ClubVO;
import test.com.member.model.MemberDAO;
import test.com.member.model.MemberDAOimpl;
import test.com.member.model.MemberVO;

public class JoinOKAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String member_id = null;
		String pw = null;
		String name = null;
		String gender = null;
		String birthday = null;
		String location = null;
		String img_name = null;
		long checkFileSize = 0;
		
		String dir_path = request.getServletContext().getRealPath("/upload");
		System.out.println(dir_path);
		
		
		int fileSizeMax = 1024 * 1024 * 100;
		
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		
		if(isMultipartContent) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(fileSizeMax);
			
			ServletFileUpload sfu = new ServletFileUpload(factory);
			sfu.setFileSizeMax(fileSizeMax);//파일 사이즈 제한

		
			
			try {
				List<FileItem> items = sfu.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) { // name,age,name,gender,birth,location,handi받기
						if(item.getFieldName().equals("member_id")) {
							member_id = item.getString("UTF-8");
						}
							if(item.getFieldName().equals("pw")) {
								pw = item.getString("UTF-8");
								
							}
							if(item.getFieldName().equals("name")) {
								name = item.getString("UTF-8");
								
							}
							if(item.getFieldName().equals("gender")) {
								gender = item.getString("UTF-8");
								
							}
							if(item.getFieldName().equals("birthday")) {
								birthday = item.getString("UTF-8");
								
							}
							if(item.getFieldName().equals("location")) {
								location = item.getString("UTF-8");
								
							}
							
//						
					}else {//file 정보받기.
//						

						img_name = FilenameUtils.getName(item.getName());
						if(item.getSize()!=0) {
							img_name = member_id;
							File saveFile = new File(dir_path, img_name);
							
							try {
								item.write(saveFile);
							} catch (Exception e) {							
								e.printStackTrace();
							}							
						}
						
						
						
					}//end else


				}//end for << items
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
			
		}//end if << isMultipart
		System.out.println("member_id:"+member_id);
		System.out.println("pw:"+pw);
		System.out.println("name:"+name);
		System.out.println("gender:"+gender);
		System.out.println("birthday:"+birthday);
		System.out.println("location:"+location);
		System.out.println("img_name:"+(img_name.length() == 0 ? "profill.png":img_name));
		
		MemberVO vo = new MemberVO();
		
		vo.setMember_id(member_id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setLocation(location);
//		vo.setImg_name(img_name);
		vo.setImg_name(img_name.length()==0? "profill.png":img_name);
		
		MemberDAO dao = new MemberDAOimpl();
		int result = dao.insert(vo);
		System.out.println("result:" + result);
		
		if(result==1) {
			int result1 = dao.insertage(vo);
			System.out.println("result1:" + result1);
			response.sendRedirect("home.do");
			
			
		}else
			response.sendRedirect("member_join.do");
	}

}
