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

import Hack.db.product;
import Hack.db.solditem;
import Hack.model.dao.ProductDao;
import Hack.model.dao.SellerDao;
import Hack.model.dao.UserDao;
import Hack.model.dao.solditemDao;
import Hack.service.UserService;

/**
 * Servlet implementation class Buyitem
 */
@WebServlet("/Hack.controller.Buyitem")
public class Buyitem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buyitem() {
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
		HttpSession session=request.getSession();
		String buyerid=(String)session.getAttribute("email");
		String productid=(String)session.getAttribute("productid");
		System.out.println(productid);
		product p=ProductDao.getDetails(productid);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    String orderdate=formatter.format(date);
	    String orderid=UserService.otp();
	   
		System.out.println(p.getSeller_id());
		solditem e=new solditem();
		e.setSellerid(p.getSeller_id());
		e.setBuyerid(buyerid);
		e.setOrderid(orderid);
		e.setDate_time(orderdate);
		e.setProduct_id(productid);
		e.setProduct_name(p.getProduct_name());
		ProductDao.updateQuantity(productid);
		SellerDao.updatemoney(p.getSeller_id(),p.getPrice());
		int status=solditemDao.OrderUpdate(e);
		if (status>0)
		{
		
			response.sendRedirect("PostEnrollment.jsp");
		}
		else
		{
			response.sendRedirect("payment_gateway.jsp?msg=Payment Failed");
		}
		
	
	}

}
