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
import Hack.db.seller;
import Hack.model.dao.SellerDao;
import Hack.model.dao.UserDao;

/**
 * Servlet implementation class selllerProfileUpdate
 */
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
maxFileSize = 1024*1024*5,
maxRequestSize = 1024*1024*10)
@WebServlet("/Hack.controller.selllerProfileUpdate")
public class selllerProfileUpdate extends HttpServlet {
	private static final String SAVE_DIR= "SellerPhotos";


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("newName");
		String phoneno=request.getParameter("newPhoneNo");
		String password=request.getParameter("newPassword");
		String flatno=request.getParameter("newFlatNo");
		String city=request.getParameter("newCity");
		String state=request.getParameter("newState");
		String pincode=request.getParameter("newPincode");
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("selleremail");
	
		seller u=new seller();
		
		
		 
		
		 
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
		u.setPhone_no(phoneno);
		u.setPassword(password);
		u.setFlat_no(flatno);
		u.setCity(city);
		u.setPincode(pincode);
		u.setState(state);
		
		
		
		
		//System.out.println(filename+qualification+college+branch+phoneno);
	//	SellerDao.UpdateSeller(u, email);
		response.sendRedirect("SellerHome.jsp?msg=Profile Updated");
		
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
