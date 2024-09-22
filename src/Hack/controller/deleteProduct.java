package Hack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Hack.model.dao.ProductDao;

/**
 * Servlet implementation class deleteProduct
 */
@WebServlet("/Hack.controller.deleteProduct")
public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id=request.getParameter("pid");
		String quantity=request.getParameter("quantity");
		int quan=Integer.parseInt(quantity);
		if(quan>0)
		{
		int status=ProductDao.deleteProduct(id,quan);
		if(status>0)
		{
			response.sendRedirect("SellerHome.jsp?msg=One quantity of the item has been renomved");
			
		}
		else
		{
			response.sendRedirect("SellerHome.jsp?msg=Item cant be removed");
		}
		}
		else
		{
			response.sendRedirect("SellerHome.jsp?msg=No more Item to remove");
		}
		
	}

}
