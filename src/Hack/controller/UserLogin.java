package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hack.model.dao.UserDao;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/Hack.controller.UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
		String username=request.getParameter("username");
		String password=request.getParameter("pass");
		int status=UserDao.logincheck(username,password);
		if(status>0) {
			HttpSession session = request.getSession();
			
			session.setAttribute("email", username);
			session.setAttribute("password", password);
			//String url = (String)session.getAttribute("url");
			/*if(url!=null)
			{
				request.setAttribute("email", (String)session.getAttribute("teacheremailid"));
				//request.getRequestDispatcher(url).forward(request, response);
				String[] ur1=url.split("/");
				url=ur1[ur1.length-1];
				response.sendRedirect(url);
				
			}
			else*/
			response.sendRedirect("index.jsp?msg=WelcomeUser");
		}
		else {
			response.sendRedirect("buyerLogin.jsp?msg=Login Error");
		}
	}

}
