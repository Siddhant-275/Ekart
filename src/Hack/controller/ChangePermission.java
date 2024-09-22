package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Hack.model.dao.SellerDao;

/**
 * Servlet implementation class ChangePermission
 */
@WebServlet("/Hack.controller.ChangePermission")
public class ChangePermission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("sellerid");
		int status=SellerDao.changeSellerStatus(email);
		if(status>0)
		{
			response.sendRedirect("admintable.jsp?msg=The seller has been approved");
		}
		else
		{
			response.sendRedirect("admintable.jsp?msg=seller cant be approved");
		}
	}

}
