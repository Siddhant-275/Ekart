package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hack.model.dao.UserDao;
import Hack.db.buyer;
import Hack.service.UserService;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/Hack.controller.UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration() {
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
		String username=request.getParameter("name");
		String email=request.getParameter("email");
		String phone_no=request.getParameter("phone_no");
		String password=request.getParameter("password");
		String confirm_password=request.getParameter("confirm_password");
			
		
		int status = UserDao.checkEmailid(email);
		if(status>0)
		{
			response.sendRedirect("buyerSignup.jsp?msg=Email already Exsits Please give a different Email Id");
		}
		else
		{
			if(password.equals(confirm_password))
			{
				buyer s=new buyer();
				s.setName(username);
				s.setEmail(email);
				s.setPhone_no(phone_no);
				s.setPassword(password);
				
				int store = UserDao.registerUsers(s);
				if(store>0)
				{
					UserService.sendUserRegisterMessage(s);
					
					HttpSession session1 = request.getSession();
					session1.setAttribute("email", s.getEmail());
					session1.setAttribute("password", s.getPassword());
					
					response.sendRedirect("index.jsp?msg=Welcome");
				}
				else
				{
					response.sendRedirect("buyerSignup.jsp?msg=Error in registration");
				}
			}
			else
				response.sendRedirect("buyerSignup.jsp?msg=Password and Confirm Password mismatch");
		}
	}

}
