package Hack.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hack.db.product;
import Hack.model.dao.ProductDao;
import java.io.File;

import javax.servlet.http.Part;




/**
 * Servlet implementation class Addproduct
 */
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
maxFileSize = 1024*1024*5,
maxRequestSize = 1024*1024*10)
@WebServlet("/Hack.controller.Addproduct")
public class Addproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String SAVE_DIR= "UserPhotos";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productname = request.getParameter("productname");
		String product_category = request.getParameter("product_category");
		String product_quantity = request.getParameter("product_quantity");
		String product_description = request.getParameter("product_desc");
		double price=Double.parseDouble(request.getParameter("product_price"));
		HttpSession session=request.getSession();
		String sellerid=(String)session.getAttribute("selleremailid");
		
		String productid=otp();
		
		
		
		 
		product p=new product();
		 
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
				p.setPhoto(filename);
			}
			
		}
	
		
		
		
		
		p.setSeller_id(sellerid);
		p.setCategory(product_category);
		p.setProductquantity(product_quantity);
		
		p.setProduct_description(product_description);
		p.setProduct_name(productname);
		p.setProduct_id(productid);
		p.setPrice(price);
		
		
		//System.out.println(filename+qualification+college+branch+phoneno);
		int status=ProductDao.Updateproduct(p, sellerid);
		if(status>0)
		{
		response.sendRedirect("SellerHome.jsp?msg=product has been added");
		}
		else
		{
			response.sendRedirect("SellerHome.jsp?msg=product cannot be added");
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
	private static String otp() 
	{
		int length=4;
		Random rand=new Random();
		String numbers="0123456789";
		char otp[]=new char[length];
		for (int i = 0; i <length; i++) 
		{
			otp[i]=numbers.charAt(rand.nextInt(numbers.length()));
			System.out.println(otp[i]);
			
		}
		String otp1 = String.valueOf(otp);
		return otp1;
	}
}
