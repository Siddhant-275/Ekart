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

import Hack.model.dao.UserDao;
import Hack.service.UserService;

/**
 * Servlet implementation class UserForgotPassword
 */
@WebServlet("/Hack.controller.UserForgotPassword")
public class UserForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserForgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String OTP;
		String emailid=request.getParameter("emailid");
		int status=UserDao.checkEmailid(emailid);
		if(status>0)
		{
			
			 OTP=UserService.sendUserOTP(emailid);
			 int updateOtp = UserDao.updateOTP(OTP,emailid);
			 HttpSession session = request.getSession();
			 session.setAttribute("email", emailid);
			 if(updateOtp>0)
			 {
				 Date date = new Date();
				 System.out.println(date);
				 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				 String strDate= sdf.format(date);
				 System.out.println(strDate);
				 UserDao.setOtpTimeOut(strDate,emailid);
				 session.setAttribute("time_out", strDate);
				 response.sendRedirect("buyerPasswordResetOTP.jsp?msg=OTP  sent");
			 }
			 else
				 response.sendRedirect("buyerForgotPassword.jsp?msg=Error");
		}
		else
		{
			response.sendRedirect("buyerSignup.jsp?msg=Email does not exist");
		}
	
	}

}
