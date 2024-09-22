package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hack.model.dao.SellerDao;
import Hack.service.AdminService;
import Hack.service.SellerService;

/**
 * Servlet implementation class withdrawAccoount
 */
@WebServlet("/Hack.controller.withdrawAccoount")
public class withdrawAccoount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname=request.getParameter("fisrtname");
		String lastname=request.getParameter("lastname");
		String amount=request.getParameter("amount");
		String address=request.getParameter("address");
		HttpSession session = request.getSession();			
		String email=(String)session.getAttribute("selleremailid");
		
		if(Double.parseDouble(amount)>=100)
		{
			AdminService.sendAdminReply(email, amount,firstname,lastname,address);
			SellerDao.deductmoney(email,Double.parseDouble(amount));
			response.sendRedirect("SellerHome.jsp?msg=The amount has been debited successfully");
		}
		else
		{
			response.sendRedirect("withdraw.jsp?msg=Low accoint balance ");
		}
	}

}
