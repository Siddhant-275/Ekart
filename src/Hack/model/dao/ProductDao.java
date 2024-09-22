package Hack.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;

import Hack.db.Provider;
import Hack.db.product;

public class ProductDao {

	public static int Updateproduct(product p, String sellerid) 
	{
		int status=0;
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "insert into product(sellerid,category,photo,product_id,product_name,product_description,productquantity,price) values(?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, p.getSeller_id());
			pst.setString(2, p.getCategory());
			pst.setString(3, p.getPhoto());
			pst.setString(4, p.getProduct_id());
			pst.setString(5, p.getProduct_name());
			pst.setString(6, p.getProduct_description());	
			pst.setString(7, p.getProductquantity());
			pst.setDouble(8, p.getPrice());
			
			
			
			 status=pst.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
		
	}
	
	public static ArrayList<product> getSellerProduct(String emailid)
	{
		ArrayList<product> ar=new ArrayList<product>();
		try
		{
		Connection con=Provider.getConnection();
		String sql="select * from product where sellerid=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, emailid);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			product v=new product();
			v.setSeller_id(rs.getString(1));
			v.setCategory(rs.getString(2));
			v.setPhoto(rs.getString(3));
			v.setProduct_id(rs.getString(4));
			v.setProduct_name(rs.getString(5));
			v.setProduct_description(rs.getString(6));
			v.setProductquantity(rs.getString(7));
			v.setPrice(rs.getDouble(8));
			ar.add(v);
		}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ar;
		
	}

	public static int deleteProduct(String id, int quan) {
		int status=0;
		try
		{
			quan--;
			Connection con=Provider.getConnection();
			String sql="update product set productquantity=? where product_id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,quan );
			pst.setString(2,id);
			status = pst.executeUpdate();
			System.out.println("OTP Updated");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int addProduct(String id, int quan) {
		int status=0;
		try
		{
			
			Connection con=Provider.getConnection();
			String sql="update product set productquantity=? where product_id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,quan );
			pst.setString(2,id);
			status = pst.executeUpdate();
			System.out.println("OTP Updated");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	public static ArrayList<product> getProductInfo(String category,String price)
	{
		ArrayList<product> list=new ArrayList<product>();
		int status=1;
		category=category.toLowerCase();
		
		
		
		int low=0;int high=0;
		if(price.equals("100-500")) {
		low=100;
		high=500;
		}
		else if(price.equals("500-1000")){
			low=500;
			high=1000;
			}
		else if(price.equals(">1000")){
			low=1000;
			high=100000;
			}
		try
		{
			System.out.println("Now in dao");
			System.out.println(category+" "+price);
			Connection con = Provider.getConnection();
			String sql = "select * from product  where category=? or price between ? and ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,category);			
			pst.setInt(2,low);
			pst.setInt(3,high);
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()) 
			{
				product t=new product();
				t.setProduct_name(rs.getString(5));
				t.setPrice(rs.getDouble(8));
				t.setProductquantity(rs.getString(7));
				t.setProduct_description(rs.getString(6));
				t.setPhoto(rs.getString(3));		
				t.setCategory(rs.getString(2));	
				t.setSeller_id(rs.getString(1));
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
	public static product getDetails(String product_id) 
	{
		product user=null;
		try
		{
			Connection con=Provider.getConnection();
			String sql="select * from product where product_id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,product_id);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				user=new product();
				user.setSeller_id(rs.getString(1));
				user.setCategory(rs.getString(2));
				user.setPhoto(rs.getString(3));
				user.setProduct_id(rs.getString(4));
				user.setProduct_name(rs.getString(5));
				user.setProduct_description(rs.getString(6));
				user.setProductquantity(rs.getString(7));
				user.setPrice(rs.getDouble(8));
					
					
					
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return user;
		
	}

	public static void updateQuantity(String productid) {
		int status;
		try
		{
			int currentbal=getCurrent(productid);
			currentbal--;
			Connection con=Provider.getConnection();
			String sql="update product set productquantity=? where product_id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, currentbal);
			pst.setString(2, productid);
			status = pst.executeUpdate();
		
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
	private static int getCurrent(String seller_id) {
		
		int balance=0;
		try
		{
		
		Connection con=Provider.getConnection();
		String sql="select productquantity from product where product_id=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1,seller_id);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) 
		{
			
			balance=rs.getInt(1);
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return balance;
	}

}
