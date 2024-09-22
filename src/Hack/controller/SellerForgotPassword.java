package Hack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hack.model.dao.SellerDao;
import Hack.service.SellerService;

/**
 * Servlet implementation class SellerForgotPassword
 */
@WebServlet("/Hack.controller.SellerForgotPassword")
public class SellerForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String emailid=request.getParameter("emailid");
		String otp;
		int status=SellerDao.checkEmailid(emailid);
		if(status>0)
		{
			otp=SellerService.sendSellerOTP(emailid);
			 int updateOtp = SellerDao.updateOTP(otp,emailid);
			 HttpSession session = request.getSession();
			 session.setAttribute("selleremailid", emailid);
			 if(updateOtp>0)
			 {
				 Date date = new Date();
				 System.out.println(date);
				 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				 String strDate= sdf.format(date);
				 System.out.println(strDate);
				 SellerDao.setOtpTimeOut(strDate,emailid);
				 session.setAttribute("time_out", strDate);
				 response.sendRedirect("sellerPasswordResetOTP.jsp?msg=OTP  sent");
			 }
			 else
				 response.sendRedirect("sellerForgotPassword.jsp?msg=Error");
		}
		else
		{
			response.sendRedirect("studentSignup.jsp?msg=Email does not exist");
		}
	
	}

}