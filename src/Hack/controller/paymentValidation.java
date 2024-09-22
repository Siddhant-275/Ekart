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
 * Servlet implementation class paymentValidation
 */
@WebServlet("/Hack.controller.paymentValidation")
public class paymentValidation extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String otpsending=UserService.otp();
		 HttpSession session=request.getSession();
		String buyerid=(String)session.getAttribute("email");
		 UserDao.updateOTP(otpsending, buyerid);
		 UserService.sendUserOTP1(buyerid,otpsending);
		 Date date = new Date();
		 System.out.println(date);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
		 String strDate= sdf.format(date);
		 UserDao.setOtpTimeOut(strDate, buyerid);
		 session.setAttribute("time_out", strDate);
		 response.sendRedirect("otpconfirm.jsp");
	}

}
