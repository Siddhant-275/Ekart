package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hack.model.dao.AdminDao;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/Hack.controller.AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
		String username=request.getParameter("adminusername");
		String password=request.getParameter("adminpass");
	
		int status=AdminDao.logincheck(username,password);
		if(status>0) {
			HttpSession session  = request.getSession();
			session.setAttribute("adminEmailid", username);
			response.sendRedirect("adminindex1.jsp?msg=WelcomeAdmin");
		}
		else {
			response.sendRedirect("adminlogin.jsp?msg=Login Error");
		}
	}

}
