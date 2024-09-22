package Hack.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Hack.db.Provider;
import Hack.db.buyer;


public class UserDao {

	public static int checkEmailid(String email) 
	{
		int status = 0;
		
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "select * from buyer where email =?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				status =1;
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	public static int registerUsers(buyer s) 
	{
		int status = 0;
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "insert into buyer(name,email,phone_no,password) values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setString(2, s.getEmail());
			pst.setString(3, s.getPhone_no());
			pst.setString(4, s.getPassword());
			
			status = pst.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}
	public static int logincheck(String emailid,String password) 
	{
		int status = 0;
		
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "select * from buyer where email =? and password =?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, emailid);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				status =1;
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	public static buyer getDetails(String emailid) 
	{
		buyer user=null;
		try
		{
			Connection con=Provider.getConnection();
			String sql="select * from buyer where email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,emailid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				user=new buyer();
				user.setName(rs.getString(1));
				user.setEmail(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPhone_no(rs.getString(4));
				user.setPhoto(rs.getString(5));
				user.setFlat_no(rs.getString(6));
				user.setCity(rs.getString(7));
				user.setPincode(rs.getString(8));
				user.setState(rs.getString(9));
				user.setCard_no(rs.getString(10));
				user.setExpiry_date(rs.getString(11));
				user.setName_on_card(rs.getString(12));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return user;
		
	}
public static void UpdateUser(buyer u, String email) {
		
		try
		{
			Connection con = Provider.getConnection();
			if(u.getPhoto()==null) {
				String sql = "update buyer SET name = ? , phone_no = ?, password = ?,flat_no=?,city=?,pincode=?,state=?  where email = ? ";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,u.getName());
				pst.setString(2,u.getPhone_no());
				pst.setString(3,u.getPassword());
				pst.setString(4,u.getFlat_no());
				pst.setString(5,u.getCity());
				pst.setString(6,u.getState());
				pst.setString(7,u.getPincode());
			
				
				pst.setString(8,email);
				pst.executeUpdate();
			}
			else {
			String sql = "update buyer SET name = ? , phone_no = ?, password = ?,flat_no=?,city=?,pincode=?,state=?,photo=?  where email = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,u.getName());
			pst.setString(2,u.getPhone_no());
			pst.setString(3,u.getPassword());
			pst.setString(4,u.getFlat_no());
			pst.setString(5,u.getCity());
			pst.setString(6,u.getState());
			pst.setString(7,u.getPincode());
			pst.setString(8,u.getPhoto());
			pst.setString(9,email);
			pst.executeUpdate();
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
public static int updateOTP(String otp, String emailid) {
	// TODO Auto-generated method stub
	int status=0;
	try
	{
		Connection con=Provider.getConnection();
		String sql="update buyer set otp=? where email=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, otp);
		pst.setString(2,emailid);
		status = pst.executeUpdate();
		System.out.println("OTP Updated");
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
	return status;
	
}
public static void setOtpTimeOut(String strDate, String emailid) {
	// TODO Auto-generated method stub
	try
	{
		Connection con = Provider.getConnection();
		String sql = "update buyer set time_out=? where email=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, strDate);
		pst.setString(2, emailid);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
public static String getOtp(String emailid) {
	// TODO Auto-generated method stub
	String otp = null;
	try
	{
		Connection con=Provider.getConnection();
		String sql="select otp from buyer where email=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1,emailid);
		ResultSet rs = pst.executeQuery();
		if(rs.next())
		{
			otp = rs.getString(1);
		}
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return otp;
}
public static void deleteTimeOut(String emailid) {
	
	try
	{
		Connection con = Provider.getConnection();
		String sql = "update buyer set time_out =null where email = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, emailid);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
public static void updatePassword(String email, String newpassword) 
{
	String sql;
	try
	{
		Connection con=Provider.getConnection();
		sql="update buyer set password=? where email=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, newpassword);
		pst.setString(2,email);
		pst.executeUpdate();
		System.out.println("Password Updated");
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
}
public static ArrayList<buyer> findBuyerInfo()
{
	ArrayList<buyer> list=new ArrayList<buyer>();
	
	try
	{
		
		Connection con = Provider.getConnection();
		String sql = "select * from buyer";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) 
		{
			buyer s=new buyer();
			s.setName(rs.getString(1));
			s.setEmail(rs.getString(2));
			s.setPhone_no(rs.getString(4));
			s.setFlat_no(rs.getString(6));
			s.setCity(rs.getString(7));
			s.setPincode(rs.getString(8));
			s.setState(rs.getString(9));
			
			list.add(s);				
		}
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return list;
}
}
