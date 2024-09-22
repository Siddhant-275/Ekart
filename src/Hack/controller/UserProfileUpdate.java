package Hack.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Hack.db.buyer;
import Hack.model.dao.UserDao;

/**
 * Servlet implementation class UserProfileEdit
 */
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
maxFileSize = 1024*1024*5,
maxRequestSize = 1024*1024*10)
@WebServlet("/Hack.controller.UserProfileUpdate")
public class UserProfileUpdate extends HttpServlet {
	private static final String SAVE_DIR= "UserPhotos";
    
    /**
     * @see HttpServlet#HttpServlet()
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String name = request.getParameter("newName");
		String phone_no = request.getParameter("newPhoneNo");
		String password = request.getParameter("newPassword");
		String flatno = request.getParameter("newflatno");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String pincode = request.getParameter("pincode");
		
		buyer u=new buyer();
		
		
		 
		
		 
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath+File.separator+SAVE_DIR;
		String filename = null;
		
		File fileSaveDir = new File(savePath);
		if(!fileSaveDir.exists())
		{
			fileSaveDir.mkdir();
		}
		
		for (Part part : request.getParts()) 
		{
			
			filename = extractFileName(part);
			if (filename != null && !"".equals(filename)) 
			{
				part.write(savePath+File.separator+filename);
				u.setPhoto(filename);
			}
			
		}
	
		
		
		
		u.setName(name);
		u.setFlat_no(flatno);
		u.setCity(city);
		u.setState(state);
		u.setPincode(pincode);
		u.setPhone_no(phone_no);
		u.setPassword(password);
		
		
		//System.out.println(filename+qualification+college+branch+phoneno);
		UserDao.UpdateUser(u, email);
		HttpSession session=request.getSession();
		String url=(String)session.getAttribute("url");
		if(url!=null)
		{
			String[] url1=url.split("/");
			url=url1[url1.length-1];
			response.sendRedirect(url);
		}
		else
		{
		response.sendRedirect("profile.jsp?msg=Profile Updated");
		}
		}
	private String extractFileName(Part part)
	{
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) 
        {
            if (s.trim().startsWith("filename")) 
            {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
