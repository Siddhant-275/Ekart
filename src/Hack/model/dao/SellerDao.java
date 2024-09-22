package Hack.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Hack.db.Provider;
import Hack.db.seller;


public class SellerDao {

	public static int logincheck(String username, String password) {
		int status = 0;
		
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "select * from seller where email =? and password =?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
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
	
	public static int checkEmailid(String emailid) 
	{
		int status = 0;
		
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "select * from seller where email =?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, emailid);
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



	public static int registerSeller(seller s) {
		int status = 0;
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "insert into seller(name,email,password,phone_no,photo,flat_no,city,pincode,state,wallet_balance,status) values(?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setString(2,s.getEmail());
			pst.setString(3,s.getPassword());
			pst.setString(4,s.getPhone_no());
			pst.setString(5,s.getPhoto());
			pst.setString(6,s.getFlat_no());
			pst.setString(7,s.getCity());
			pst.setString(8,s.getPincode());
			pst.setString(9,s.getState());
			pst.setDouble(10,s.getWallet_balance());
			pst.setInt(11,s.getStatus());
			
			
			
			
			
			status = pst.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}

	public static int updateOTP(String otp, String emailid) {
		int status=0;
		try
		{
			Connection con=Provider.getConnection();
			String sql="update seller set otp=? where email=?";
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
		try
		{
			Connection con = Provider.getConnection();
			String sql = "update seller set time_out=? where email=?";
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
		String otp = null;
		try
		{
			Connection con=Provider.getConnection();
			String sql="select otp from seller where email=?";
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
			String sql = "update seller set time_out =null where email = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, emailid);
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public static void updatePassword(String emailid, String newpassword) {
		String sql;
		try
		{
			Connection con=Provider.getConnection();
			sql="update seller set password=? where email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, newpassword);
			pst.setString(2,emailid);
			pst.executeUpdate();
			System.out.println("Password Updated");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	public static ArrayList<seller> requestedSellerInfo()
	{
		ArrayList<seller> list=new ArrayList<seller>();
		
		try
		{
			
			Connection con = Provider.getConnection();
			String sql = "select * from seller  where status=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,1);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) 
			{
				seller t=new seller();
				t.setName(rs.getString(1));
				t.setEmail(rs.getString(2));
				t.setPhone_no(rs.getString(4));
				t.setFlat_no(rs.getString(6));
				t.setCity(rs.getString(7));
				t.setPincode(rs.getString(8));
				t.setState(rs.getString(9));
				t.setWallet_balance(rs.getInt(10));
				t.setStatus(rs.getInt(11));
				list.add(t);
				System.out.println(t.getName());
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public static ArrayList<seller> requestedTeacherInfounadded()
	{
		ArrayList<seller> list=new ArrayList<seller>();
		
		try
		{
			
			Connection con = Provider.getConnection();
			String sql = "select * from teacher  where status=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,0);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) 
			{
				seller t=new seller();
				t.setName(rs.getString(1));
				t.setEmail(rs.getString(2));
				t.setFlat_no(rs.getString(6));
				t.setCity(rs.getString(7));
				t.setState(rs.getString(9));
				t.setWallet_balance(rs.getDouble(10));
				t.setStatus(rs.getInt(11));
			
				
				
				
				list.add(t);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public static int changeSellerStatus(String id) 
	{
		
		int status=0;
		try
		{
			Connection con=Provider.getConnection();
			String sql="update seller set status=? where email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setString(2, id);
			status = pst.executeUpdate();
			System.out.println("status changed");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
		
	}
	public static int Deleteseller(String id) 
	{
		int status=0;
		try
		{
			Connection con=Provider.getConnection();
			String sql="delete from seller where email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,id);
			status = pst.executeUpdate();
			System.out.println("seller id deleted");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	public static seller getSeller(String emailid)
	{
		seller user=null;
		try
		{
			Connection con=Provider.getConnection();
			String sql="select * from seller where email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,emailid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				user=new seller();
				user.setName(rs.getString(1));
				user.setEmail(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPhone_no(rs.getString(4));
				user.setPhoto(rs.getString(5));
				user.setFlat_no(rs.getString(6));
				user.setCity(rs.getString(7));
				user.setPincode(rs.getString(8));
				user.setState(rs.getString(9));
				user.setWallet_balance(Double.parseDouble(rs.getString(10)));
				user.setStatus(Integer.parseInt(rs.getString(11)));
				
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return user;
	}
	public static ArrayList<seller> requestedSellerInfounadded()
	{
		ArrayList<seller> list=new ArrayList<seller>();
		
		try
		{
			
			Connection con = Provider.getConnection();
			String sql = "select * from seller  where status=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,0);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) 
			{
				seller t=new seller();
				
				t.setName(rs.getString(1));
				t.setEmail(rs.getString(2));
				t.setPhone_no(rs.getString(4));
				t.setFlat_no(rs.getString(6));
				t.setCity(rs.getString(7));
				t.setPincode(rs.getString(8));
				t.setState(rs.getString(9));
				t.setWallet_balance(rs.getInt(10));
				t.setStatus(rs.getInt(11));
				
				
				list.add(t);
				System.out.println(t.getName());
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public static void updatemoney(String seller_id, double price) {
		int status=0;
		try
		{
			double currentbal=getCurrent(seller_id);
			currentbal+=price;
			Connection con=Provider.getConnection();
			String sql="update seller set wallet_balance=? where email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setDouble(1, currentbal);
			pst.setString(2, seller_id);
			status = pst.executeUpdate();
		
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void deductmoney(String seller_id, double price) {
		int status=0;
		try
		{
			double currentbal=getCurrent(seller_id);
			currentbal-=price;
			Connection con=Provider.getConnection();
			String sql="update seller set wallet_balance=? where email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setDouble(1, currentbal);
			pst.setString(2, seller_id);
			status = pst.executeUpdate();
		
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	private static double getCurrent(String seller_id) {
		// TODO Auto-generated method stub
		double balance=0;
		try
		{
		
		Connection con=Provider.getConnection();
		String sql="select wallet_balance from seller where email=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1,seller_id);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) 
		{
			
			balance=rs.getDouble(1);
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return balance;
	}
}
