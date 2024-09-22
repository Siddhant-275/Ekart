package Hack.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Hack.db.Provider;



public class AdminDao {

	public static int logincheck(String username, String password) 
	{
		int status=0;
		try
		{
			Connection con=Provider.getConnection();
			String sql = "select * from admin where adminid =? and password =?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				status =1;
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	

}
