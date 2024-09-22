package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Hack.model.dao.SellerDao;

/**
 * Servlet implementation class Deleteseller
 */
@WebServlet("/Hack.controller.Deleteseller")
public class Deleteseller extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("sellerid");
		int status=SellerDao.Deleteseller(email);
		if(status>0)
		{
			response.sendRedirect("admintable.jsp?msg=The seller has been deleted");
		}
		else
		{
			response.sendRedirect("admintable.jsp?msg=seller cant be deleted");
		}
	}

}
