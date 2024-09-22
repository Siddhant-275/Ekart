package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hack.db.seller;
import Hack.model.dao.SellerDao;
import Hack.service.SellerService;


@WebServlet("/Hack.controller.sellerRegistration")
public class sellerRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name=request.getParameter("seller_name");
		String email=request.getParameter("seller_email");
		String phone_no=request.getParameter("seller_phone_no");
		String flat_no=request.getParameter("flat_no");
		String State=request.getParameter("state");
		String pincode=request.getParameter("pincode");
		String password=request.getParameter("seller_password");
		String confirmpasword=request.getParameter("seller_confirm_password");
		String city=request.getParameter("city");
		
		int status = SellerDao.checkEmailid(email);
		if(status>0)
		{
			response.sendRedirect("sellersignup.jsp?msg=Email is already Exsits Please give a different Email Id");
		}
		else
		{
			if(password.equals(confirmpasword))
			{
				seller s=new seller();
				s.setName(name);
				s.setEmail(email);
				s.setPhone_no(phone_no);
				s.setCity(city);
				s.setState(State);
				s.setFlat_no(flat_no);
				s.setPincode(pincode);
				s.setPassword(password);
				s.setStatus(0);
				s.setWallet_balance(0.0);
				int store = SellerDao.registerSeller(s);
				if(store>0)
				{
					
					
					SellerService.sendUserRegisterMessage(s);
					response.sendRedirect("SellerHome.jsp?msg=Welcome");
					HttpSession sellersession = request.getSession();
					sellersession.setAttribute("selleremailid", s.getEmail());
					sellersession.setAttribute("sellerpassword", s.getPassword());
				
				}
				else
				{
					response.sendRedirect("sellersignup.jsp?msg=Error in registration");
				}
			}
			else
				response.sendRedirect("sellersignup.jsp?msg=Password and Confirm Password mismatch");
		}

	}

	}
