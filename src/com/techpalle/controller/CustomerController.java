package com.techpalle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.CustomerDao;
 
import com.techpalle.model.Customer;
 

 
@WebServlet("/")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 
		 String path = request.getServletPath();
		 
		 switch(path) 
		 {
		 case "/customer_list":
			 getCustomerListPage(request,response);
				break;
		 case "/logAdmin":
				validateAdmin(request,response);
				break;
		 case"/delete":
			 deleteCustomer(request,response);
			 break;
		 case "/edit":
			 editCustomer(request,response);
			 break;
		 case "/editForm":
			 getEditForm(request,response);
			 break;
		 case "/insertForm":
			 getInsertForm(request,response);
			 break;
			 
		 case "/add":
			 addCustomer(request,response);
			 break;
			 
		 default:
			 getStartUpPage(request,response);
			 break;
		 }
	}

	 
	private void validateAdmin(HttpServletRequest request, HttpServletResponse response) {
		 
		//read the email and password from jsp page
				String email =request.getParameter("tbEmail");
				String password = request.getParameter("tbPass");
				
				//call method present in dao
				
				boolean res =  CustomerDao.validateAdmin(email, password);
				 
				
				//condition and redirect user to destination page
				if(res) {
					ArrayList<Customer> alCustomer = CustomerDao.getAllCustomers();
					try {
						RequestDispatcher rd = request.getRequestDispatcher("customer_list.jsp");
						//store the successPage class data inside the rd
						//SuccessPage sp = new SuccessPage();
						//request.setAttribute("successDetails", sp);
						request.setAttribute("al", alCustomer);
						rd.forward(request, response);
					} catch (ServletException e) {
						 
						e.printStackTrace();
					} catch (IOException e) {
						 
						e.printStackTrace();
					}
				}else {
					getLoginPage(request,response);
				}
		
	}


	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) {
		 
		try {
			RequestDispatcher rd = request.getRequestDispatcher("admin_login.jsp");
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}
	
	

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
		 
		int i = Integer.parseInt(request.getParameter("id"));
		
		CustomerDao.deleteCustomer(i);
		
		getCustomerListPage(request,response);
		
	}


	private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
		
		 int i =Integer.parseInt(request.getParameter("tbId"));
		 String n = request.getParameter("tbName");
		 String e = request.getParameter("tbEmail");
		 long m = Long.parseLong(request.getParameter("tbMobile"));
		 
		 Customer c = new Customer(i,n,e,m);
		 CustomerDao.editCustomer(c);
		
		 //redirect user to cutomer_list.jsp
		  try 
		  {
			response.sendRedirect("list");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}


	private void getEditForm(HttpServletRequest request, HttpServletResponse response) {
		 
		//fetch the id from url
		
		int i = Integer.parseInt (request.getParameter("id"));
		Customer c = CustomerDao.getOneCustomer(i);
		
		
		try {
			RequestDispatcher rd = request.getRequestDispatcher("customer_form.jsp");
			
			request.setAttribute("customer", c);
			
			rd.forward(request,response);
		} catch (ServletException e) {
			 
			e.printStackTrace();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		
	}


	private void getInsertForm(HttpServletRequest request, HttpServletResponse response) 
	{
		
		try {
			RequestDispatcher rd = request.getRequestDispatcher("customer_form.jsp");
			rd.forward(request, response);
		} catch (ServletException e) 
		{ 
			e.printStackTrace();
		} catch (IOException e) 
		{		 
			e.printStackTrace();
		}
		
		 
		
	}


	private void addCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
			//reading data from customer_form.jsp
			String name = request.getParameter("tbName");
			String email = request.getParameter("tbEmail");
			long mobile = Long.parseLong(request.getParameter("tbMobile"));
			
			//store the admin given data into model/object
			Customer c = new Customer(name, email,mobile);
			
			//insert customer data to db
			CustomerDao.addCustomer(c);
			
			//redirect to Admin home page (customer_list.jsp)
			getCustomerListPage(request,response);
			
		  
	}


	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try {
			RequestDispatcher rd = request.getRequestDispatcher("admin_login.jsp");
			rd.forward(request, response);
		} catch (ServletException | IOException e) { 
			e.printStackTrace();
		}
		
	}
	
	private void getCustomerListPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try {
			ArrayList<Customer> alCustomer = CustomerDao.getAllCustomers();
			
			RequestDispatcher rd = request.getRequestDispatcher("customer_list.jsp");
			
			request.setAttribute("al", alCustomer);
			
			rd.forward(request,response);
				
		} catch (ServletException | IOException e) {
			 
			e.printStackTrace();
		}
		
	}
	
 



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
