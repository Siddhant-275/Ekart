package Hack.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Hack.db.solditem;
import Hack.db.Provider;
import Hack.db.seller;

public class solditemDao {

	public static int OrderUpdate(solditem q)
	{
		int status = 0;
		
		try 
		{
			Connection con = Provider.getConnection();
			String sql = "insert into solditem(sellerid,buyerid,product_id,product_name,solddate,orderid) values(?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, q.getSellerid());
			pst.setString(2, q.getBuyerid());
			pst.setString(3, q.getProduct_id());
			pst.setString(4, q.getProduct_name());
			pst.setString(5, q.getDate_time());
			pst.setString(6, q.getOrderid());
			
			
			status = pst.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public static ArrayList<solditem> getProductInfo()
	{
		ArrayList<solditem> list=new ArrayList<solditem>();
		
		try
		{
			
			Connection con = Provider.getConnection();
			String sql = "select * from solditem";
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()) 
			{
				solditem t=new solditem();
				t.setSellerid(rs.getString(1));
				t.setBuyerid(rs.getString(2));
				t.setProduct_id(rs.getString(3));
				t.setProduct_name(rs.getString(4));
				t.setDate_time(rs.getString(5));
				t.setOrderid(rs.getString(6));
				t.setQuantity(rs.getInt(7));
			
				
				
				
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
