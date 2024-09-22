package Hack.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Hack.db.Provider;
import Hack.db.Review;
import Hack.db.product;

public class ReviewDao 
{
	public static int reviewUpdate(Review v)
	{
		int status = 0;
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "insert into review(buyeremailid,review,selleremailid) values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, v.getBuyeremailid());
			pst.setString(2, v.getReview());
			pst.setString(3, v.getSelleremailid());
			
			
			status = pst.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public static ArrayList<Review> getReview(String productid)
	{
		ArrayList<Review> ar=new ArrayList<Review>();
		try
		{
		Connection con=Provider.getConnection();
		String sql="select * from review where product_id=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, productid);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			Review v=new Review();
			v.setBuyeremailid(rs.getString(1));
			v.setReview(rs.getString(2));
			v.setSelleremailid(rs.getString(3));
			v.setProduct_id(rs.getString(4));
			ar.add(v);
		}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ar;
		
	}
	public static ArrayList<Review> getTopReview()
	{
		ArrayList<Review> ar=new ArrayList<Review>();
		try
		{
		Connection con=Provider.getConnection();
		String sql="select * from review ";
		PreparedStatement pst=con.prepareStatement(sql);
		
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			Review v=new Review();
			v.setBuyeremailid(rs.getString(1));
			v.setReview(rs.getString(2));
			v.setSelleremailid(rs.getString(3));
			ar.add(v);
		}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ar;
		
	}

	public static int registerReview(Review v) 
	{
		int status=0;
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "insert into review(buyeremailid,review,selleremailid,product_id) values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,  v.getBuyeremailid());
			pst.setString(2, v.getReview());
			pst.setString(3, v.getSelleremailid());
			pst.setString(4, v.getProduct_id());
			status=pst.executeUpdate();
			} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	public static ArrayList<Review> getDetails(String email)
	{
		ArrayList<Review> list=new ArrayList<Review>();		
		try
		{
			
			
			Connection con = Provider.getConnection();
			String sql = "select * from review  where selleremailid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,email);			
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()) 
			{
				Review t=new Review();
				t.setBuyeremailid(rs.getString(1));
				t.setReview(rs.getString(2));
				t.setSelleremailid(rs.getString(3));
				t.setProduct_id(rs.getString(4));
				list.add(t);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	
}
