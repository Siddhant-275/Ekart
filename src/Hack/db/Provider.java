package Hack.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Provider {
	private static Connection con=null;
	public static Connection getConnection() 
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			if(con==null)
			{
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ecommerce","ecommerce");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
		
	}
	


}
