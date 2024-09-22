package Hack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Hack.db.Contact;
import Hack.model.dao.ContactDao;

/**
 * Servlet implementation class ContactAdmin
 */
@WebServlet("/Hack.controller.ContactAdmin")
public class ContactAdmin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String senderid=request.getParameter("emailid");
		String subject=request.getParameter("subject");
		String message=request.getParameter("message");
		Date date = new Date();
		 System.out.println(date);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
		 String strDate= sdf.format(date);
		 Contact c=new Contact();
		 c.setEmailid(senderid);
		 c.setMessage(message);
		 c.setSubject(subject);
		 c.setDate_time(strDate);
		 int status=ContactDao.registerRequest(c);
		 if(status>0)
		 {
			 response.sendRedirect("contact.jsp?msg=Your request has been added "  );
		 }
		 else
		 {
			 response.sendRedirect("contact.jsp?msg=your request cant be added");
		 }
		 
	}

}
