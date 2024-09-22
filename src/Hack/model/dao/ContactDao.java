package Hack.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Hack.db.Contact;
import Hack.db.Provider;

public class ContactDao {

	public static int registerRequest(Contact c) 
	{
		int status = 0;
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "insert into contact(emailid,subject,message,date_time) values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, c.getEmailid());
			pst.setString(2, c.getSubject());
			pst.setString(3, c.getMessage());
			pst.setString(4, c.getDate_time());
		
			status = pst.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public static ArrayList<Contact> getContanctInfo()
	{
		ArrayList<Contact> list=new ArrayList<Contact>();
		
		try
		{
			
			Connection con = Provider.getConnection();
			String sql = "select * from contact  order by date_time desc";
			PreparedStatement pst = con.prepareStatement(sql);
		
			ResultSet rs=pst.executeQuery();
			while(rs.next()) 
			{
				Contact t=new Contact();
				t.setEmailid(rs.getString(1));
				t.setSubject(rs.getString(2));
				t.setMessage(rs.getString(3));
				t.setDate_time(rs.getString(4));
				list.add(t);
				
				
				
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public static void deleteOldRequest(String emailid) {
		// TODO Auto-generated method stub
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "delete from contact where emailid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,emailid);
			
		
			pst.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
