package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.techpalle.model.Customer;

public class CustomerDao 
{
	
	private static final  String dbUrl = "jdbc:mysql://localhost:3306/customer_management";
	private static final  String dbUsername = "root";
	private static final  String dbPassword = "Shravan@844";
	
	private static Connection con = null;
	private static Statement stm = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs=null;
	
	private static final String customersListQuery = "select * from customer";
	private static final String customerInsert =  "insert into customer(name, email, mobile) values(?,?,?)";
	private static final String customerEditQuery = "select * from customer where id=?";
	private static final String customerUpdateQuery = "update customer set name=?, email=?, mobile=? where id=?";
	private static final String customerDeleteQuery = "delete from customer where id=?";
	private static final String validateAdminQuery = "select email,password from admin where email=? and password=?";
	
	public static Connection getConnectionDef() 
	{
		try 
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean validateAdmin(String email, String password ) {
		boolean b = false;
		try {
			
			con = getConnectionDef();
			ps = con.prepareStatement(validateAdminQuery);
		 
			 
			ps.setString(1,email);
			ps.setString(2, password);
			 
			rs = ps.executeQuery();
			
			b = rs.next();
			 					
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		finally {
			 
				try {
					if(rs!=null)
						rs.close();
					if(ps!=null)
						ps.close();
					if(con!=null)
						con.close();
				} catch (SQLException e) {
					 
					e.printStackTrace();
				}
		}
		return b;
		
}
	
	public static void deleteCustomer(int id) {
		
		try 
		{
			con = getConnectionDef();
			ps = con.prepareStatement(customerDeleteQuery);
			ps.setInt(1, id);
			 
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally 
		{
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
	}
	
	public static void editCustomer(Customer c) 
	{
		
		try 
		{
			con = getConnectionDef();
			ps = con.prepareStatement(customerUpdateQuery);
			ps.setString(1, c.getName());
			ps.setString(2,c.getEmail());
			ps.setLong(3,c.getMobile());
			ps.setInt(4, c.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally 
		{
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
		
	}
	
	public static Customer getOneCustomer(int id) {
		
		Customer c=null;
		
		try 
		{
			con = getConnectionDef();
			ps = con.prepareStatement(customerEditQuery);
			ps.setInt(1,id);
			 	
			rs=ps.executeQuery();
			
			rs.next();
			int i = rs.getInt("id");
			String n = rs.getString("name");
			String e = rs.getString("email");
			long m = rs.getLong("mobile");
			
			c = new Customer(i,n,e,m); 
			
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
	}
		return c;
		
	}
	
	public static void addCustomer(Customer customer) 
	{
		try 
		{
			con = getConnectionDef();
			ps = con.prepareStatement(customerInsert);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setLong(3, customer.getMobile());
			
			ps.executeUpdate();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
	}
			
	}
	
	
	
	
	public static ArrayList<Customer> getAllCustomers() 
	{
		ArrayList<Customer> listOfCustomer = new ArrayList<>();
		try 
		{
			con = getConnectionDef();
			stm = con.createStatement();
			
			rs = stm.executeQuery(customersListQuery);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				long mobile = rs.getLong("mobile");
				
				Customer c = new Customer(id,name,email,mobile);
				listOfCustomer.add(c);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			
				try {
					if(rs!=null)rs.close();
					if(stm!=null) stm.close();
					if(con!=null) con.close();
				} catch (SQLException e) {
					 
					e.printStackTrace();
				}
		}
		return listOfCustomer;	
	}
}
