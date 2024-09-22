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
import Hack.model.dao.UserDao;

/**
 * Servlet implementation class Buyitemnew
 */
@WebServlet("/Hack.controller.Buyitemnew")
public class Buyitemnew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buyitemnew() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String otp=request.getParameter("otp");
		
		HttpSession session = request.getSession();
		String emailid = (String)session.getAttribute("email");
		System.out.println(emailid+"888888888888");
		String otp_db = UserDao.getOtp(emailid);
		System.out.println(otp_db+"888888888888888888888888");
		
		
		String str1 = (String)session.getAttribute("time_out");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
		Date d1 = null;
		Date d2 = null;
		try
		{
		String str2= sdf.format(date);
		 d1 = sdf.parse(str1); 
		 d2 = sdf.parse(str2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();
			 System.out.println(d2.getTime());
			 System.out.println(d1.getTime());
			 System.out.println(diff);
			 
			 long diffMinutes = diff / (60 * 1000);
			 System.out.println(diffMinutes);
			 if( diffMinutes >= 2.0)
			 {
				 SellerDao.deleteTimeOut(emailid);
				 response.sendRedirect("payment_gateway.jsp?msg=Otp Time out");
			 }
			 else
			 {
			 
			
		if(otp.equals(otp_db))
		{
			System.out.println("Pin Matched");
			
				
				session.invalidate();
				response.sendRedirect("PostEnrollment.jsp?msg=Product has been added");
			}
			else
			{
				response.sendRedirect("sellerPasswordResetOTP.jsp?msg=Password mismatch");
			}
		}
		
		
	}
	}

