package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Hack.db.Review;
import Hack.model.dao.ReviewDao;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/Hack.controller.AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String productid=request.getParameter("productid");
        String sellerid=request.getParameter("sellerid");
        String userid=request.getParameter("userid");
        String review=request.getParameter("userreview");
        System.out.println(productid);
        System.out.println(sellerid);
        System.out.println(userid);
        System.out.println(review);
      
      
        Review v=new Review();
        v.setBuyeremailid(userid);
        v.setSelleremailid(sellerid);
        v.setReview(review);
        v.setProduct_id(productid);
        int status=ReviewDao.registerReview(v);
        if(status>0)
        {
        	response.sendRedirect("result_detail.jsp?msg=Review has been successfully updated");
        }
        else
        {
        	response.sendRedirect("result_detail.jsp?msg=Review cannot be updated");
        }
      
    
	}


}
