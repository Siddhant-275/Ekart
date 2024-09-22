package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hack.model.dao.SellerDao;

/**
 * Servlet implementation class sellerLogin
 */
@WebServlet("/Hack.controller.sellerLogin")
public class sellerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username=request.getParameter("sellername");
		String password=request.getParameter("sellerpass");
		int status=SellerDao.logincheck(username,password);
		if(status>0) {
			HttpSession session = request.getSession();			
			session.setAttribute("selleremailid", username);
			session.setAttribute("sellerpassword", password);
			response.sendRedirect("SellerHome.jsp?msg=WelcomeUser");
		}
		else {
			response.sendRedirect("sellerlogin.jsp?msg=Login Error");
		}
	}

	}


