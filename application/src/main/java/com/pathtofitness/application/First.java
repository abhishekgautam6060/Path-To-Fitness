package com.pathtofitness.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class First {
	
	@PostMapping("/api/pathToFitness/sign_up")
	public String sign_up(String email, String password)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root","Gautam@6060");
			Statement stmt=con.createStatement();
			String query1="select * from credential where email='"+email+"'";
			ResultSet rs= stmt.executeQuery(query1);
			if(rs.next())
			{
				return "Your account is already exist, please try login your account";
			}
			else
			{
				PreparedStatement stmt1 =con.prepareStatement("insert into credential (email, password) values(?,?)");
				stmt1.setString(1, email);
				stmt1.setString(2, password);
				int i=stmt1.executeUpdate();
				if(i>0)
				{
					return "you are successfully registered";
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Oh Sorry, Some technical issue are there we will get back you soon, Try again after some time";
		
	}
	
	
	@PostMapping("/api/pathToFitness/Login")
	public String Login(String email , String password)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root", "Gautam@6060");
			Statement stmt = con.createStatement();
			String query = "Select password from credential where email = '"+email+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				String pwd = rs.getString("password");
				if(pwd.equals(password))
				{
					return "you are sucessfully logged in";	
				}
				else
					return "password is wrong";
			}
			else
			{
				return "you are not registered, register first and try again after sometime";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  "Oh sorry, Some technical issue are there we will get back you soon, Try again after some time";
	}
	
	@PostMapping("api/pathToFitness/Forget_password/Sending_otp")
	public String Sending_otp(String email, String otp)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root", "Gautam@6060");
			String newOtp= createNewOtp(6);
			PreparedStatement stmt= con.prepareStatement("update credential set otp= ? where email = ? ");
			stmt.setString(1, newOtp);
			stmt.setString(2, email);
			int i =stmt.executeUpdate();
			if(i>0)
			{	
				return "otp sucessfully sent to your register mobile number";
			}
			else
			{
				return "otp is not sent, please try again";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return"Oh sorry, "
				+ "Some technical issue are there we will get back you soon, "
				+ "Try again after some time";
	}
	public String createNewOtp(int targetStringLength) 
	{
		// TODO Auto-generated method stub
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	 
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
		
	    
		return generatedString;
		
	}
	
	@PostMapping("api/pathToFitness/Forget_password/Sending_otp/Confirm_Otp")
	public String Confirm_Otp(String email, String otp)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root", "Gautam@6060");
			Statement stmt= con.createStatement();
			String query="Select otp from credential where email ='"+email+"'";
			ResultSet rs= stmt.executeQuery(query);
			if(rs.next())
			{
			String pwd =rs.getString("otp");
				
				if(pwd.equals(otp)) 
				{
					return "Create new password";
				}
     			else
				{
					return "otp is incorrect";
				}
			}
			else
			{
				return "otp is not generated yet";			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Oh sorry"
				+ "Some technical issue are there we will get back you soon,"
				+ "Try again after some time";
	}
	
	
	@PostMapping("/api/pathToFitness/Forget_password/Sending_otp/Confirm_Otp/create_new_password")
	public String create_new_password(String email, String password)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root", "Gautam@6060");
			Statement stmt= con.createStatement();
			String query = "update credential set password ='"+password+"' where email = '"+email+"'";
			int i= stmt.executeUpdate(query);
			if(i>0)
			{
				return " Your new password is successfully generated";
			}
			else
			{
				return "passowrd not generated";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  "Oh sorry"
		+ "Some technical issue are there we will get back you soon,"
		+ "Try again after some time";
	}
	
	
	
	
	@GetMapping("/api/pathToFitness/Login/apparels")
	public Map apparels(products pr , int Product_id)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root", "Gautam@6060");
			Statement stmt = con.createStatement();
			String query = "select * from products where Product_id= '"+Product_id+"' ";
			ResultSet rs = stmt.executeQuery(query);
			ArrayList list = new ArrayList();
			while(rs.next()) 
			{
				Map map =new HashMap();
				map.put("Product_id", rs.getString("Product_id"));
				map.put("Product_name", rs.getString("Product_name"));
				map.put("Product_detail", rs.getString("Product_detail"));
				map.put("Price", rs.getString("Price"));
				map.put("Quantity", rs.getString("Quantity"));
				map.put("Size", rs.getString("Size"));
				map.put("colour", rs.getString("colour"));
				map.put("EDD", rs.getString("EDD"));
				
				list.add(map);
			}
			Map newmap = new HashMap();
			newmap.put("Products", list);
			newmap.put("Complete", "ok");
			return newmap;
			
		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	@PostMapping("/api/pathToFitness/Login/ChangePassword")
	public String ChangePassword(String email, String password , String password1)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root", "Gautam@6060");
			Statement stmt = con.createStatement();
			String query = "Select password from credential where email = '"+email+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				String pwd = rs.getString("password");
				if(pwd.equals(password))
				{
//					PreparedStatement stmt1=con.prepareStatement("update credential set password= ? where email=? ");
//					stmt1.setString(1, email);
//					stmt1.setString(2, password1);
//					int i= stmt1.executeUpdate();
//					if(i>0)
//					{
						return "Create new password";
//					}
				}
				else
					return "password is wrong";
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return"Some technically issue rising, try again sometime";
	}

	
	@PostMapping("api/pathToFitness/Login/ChangePassword/createNewPassword")
	public String createNewPassword(String email, String password)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root", "Gautam@6060");
			Statement stmt=con.createStatement();
			String query="update credential set password ='"+password+"' where email='"+email+"'";
			int i=stmt.executeUpdate(query);
			if(i>0)
			{
				return " your new password is successfully changed";
			}
			else
			{
				return "password is not changed please try again";			
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Some technically issue rising, try again sometime";
	}
	
	
	@Autowired
	JdbcTemplate jdbc;
	
	@GetMapping("/api/pathToFitness/Login/Equipements_Accessories")
	public Map Equipements_Accessories(products pr , int Product_id)
	{
		List<products> products =jdbc.query("select * from products where Product_id='"+Product_id+"'" , new BeanPropertyRowMapper(products.class));
		
		Map map = new HashMap();
		map.put("Data", products);
		return map;
	}
	
	
	@GetMapping("/api/pathToFitness/Login/Suppliments")
	public Map Suppliments(products pr, int Product_id)
	{
		List<products> products =jdbc.query("select * from products where Product_id='"+Product_id+"'" , new BeanPropertyRowMapper(products.class));
		
		Map data= new HashMap();
		data.put("Data", products);
		return data;
	}
	
	
//	Facing an issue to inserting the data into the table
	@GetMapping("/api/pathToFitness/Login/Products/AddToCart")
	public String Cart(cart cr)
	
	{
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root" , "Gautam@6060");
			PreparedStatement stmt=con.prepareStatement("insert into MyCart values(?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, cr.getCustomer_id());
			stmt.setInt(2, cr.getItem_no());
			stmt.setString(3, cr.getItem_Name());
			stmt.setString(4, cr.getColour());
			stmt.setString(5, cr.getSize());
			stmt.setInt(6, cr.getQuantity());
			stmt.setInt(7, cr.getItem_price());
			stmt.setString(8, cr.getItem_detail());
			stmt.setString(9, cr.getEDD());
			int i= stmt.executeUpdate();
			if(i>0)
			{
				return "Your product is sucessfully inserted into the cart";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "Try again, Some technical issue rising";
	}
	

	@GetMapping("/api/pathToFitness/Login/Products/AddToCart/MyCart")
	public List MyCart(cart cr, int Customer_id)
	{
		List<cart> cart =jdbc.query("select Item_Name,colour, Item_no, size, quantity Item_price, EDD from Mycart where Customer_id='"+Customer_id+"' " , new BeanPropertyRowMapper(cart.class));
		
		return cart;
	}
	
	
	@GetMapping("/api/pathToFitness/Login/Products/AddToCart/MyCart/RemoveFromCart")
	public String RemoveFromCart(cart cr, int Item_no)
	{
		jdbc.execute("delete from MyCart where Item_no= '"+Item_no+"' ");
		
		return "Data sucessfully removed";
	}
	
	@GetMapping("/api/Order_placed")
	public String Order_placed(orders or, int Item_no)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root" , "Gautam@6060" );
			PreparedStatement stmt1 =con.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			stmt1.setInt(1, or.getOrder_id());
			stmt1.setInt(2, or.getCustomer_id());
			stmt1.setString(3, or.getItem_Name());
			stmt1.setInt(4, or.getItem_no());
			stmt1.setInt(5, or.getItem_price());
			stmt1.setInt(6, or.getShipping_Charges());
			stmt1.setInt(7, or.getTotal_amount());
			stmt1.setString(8, or.getCustomer_name());
			stmt1.setString(9, or.getShip_to_address());
			stmt1.setInt(10, or.getPincode());
			stmt1.setString(11, or.getPayment_mode());
			stmt1.setString(12, or.getOrder_placed());
			stmt1.setString(13, or.getDelivered_date());
			stmt1.setString(14, or.getSize());
			stmt1.setString(15, or.getItem_detail());
			
			int i = stmt1.executeUpdate();
			if(i>0)
			{	
//				Statement stmt = con.createStatement();
//				String query1 = "delete from MyCart where Item_no='"+Item_no+"'";
//				int i1 = stmt.executeUpdate(query1);
//				if(i1>0)
//				{
					return "Your order is successfully placed";
					
//				}
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "function is not working, try again";
	}
	
	@GetMapping("/api/pathToFitness/Login/Products/AddToCart/MyCart/MyOrders")
	public List MyOrders(orders or)
	{
		List<orders> orders =jdbc.query("select Order_id, Item_Name, Total_amount, Customer_name, Order_placed, Delivered_date from Orders" , new BeanPropertyRowMapper(orders.class));
		
		return orders;
	}
	
	@GetMapping("/api/pathToFitness/Login/Products/AddToCart/MyCart/MyOrders/OrderDetail")
	public List OrderDetail(orders or, int Order_id)
	{
		List<orders> orders =jdbc.query("select * from orders where Order_id='"+Order_id+"' " , new BeanPropertyRowMapper(orders.class));
		
		return orders;
	}
	
	
	@GetMapping("/api/pathToFitness/Login/BuyPackage")
	
	public String BuyPackage(packages pr )
	{
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root" , "Gautam@6060" );
			PreparedStatement stmt1 =con.prepareStatement("insert into orders1 values(?,?,?,?,?,?) ");
			stmt1.setInt(1, pr.getCustomer_id());
			stmt1.setString(2, pr.getBuyer_Name());
			stmt1.setString(3, pr.getPackages());
			stmt1.setString(4, pr.getPurchasing_date());
			stmt1.setString(5, pr.getEnding_date());
			stmt1.setInt(6, pr.getPrice());
			int i = stmt1.executeUpdate();
			if(i>0)
			{
				return "Inserted";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		return "try again";
	}
	
	
	@GetMapping("/api/pathToFitness/Login/MyPackage")
	public List MyPackage(packages pr, int Customer_id)
	{
		List<packages> packages =jdbc.query("select * from packages where customer_id='"+Customer_id+"' " , new BeanPropertyRowMapper(packages.class));
		
		return packages;
	}
	
	@GetMapping("/api/pathToFitness/Login/MyAccount")
	public List MyAccount(account ar, int Customer_id)
	{
		List<account> account =jdbc.query("select * from customer_detail where customer_id='"+Customer_id+"' " , new BeanPropertyRowMapper(account.class));
		
		return account;
	}
	
	
	@GetMapping("/api/pathToFitness/Login/Care/test")
	public List test(tests tr, int test_no)
	{
		List<tests> tests=jdbc.query("select * from tests where test_no='"+test_no+"'", new BeanPropertyRowMapper(tests.class));
		
		return tests;
	}
	
	
	@GetMapping("/api/pathToFitness/Login/Care/test/addToCart")
	public String addToCart(MyCart2 cr)
	{
		
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root" , "Gautam@6060" );
			PreparedStatement stmt1 =con.prepareStatement("insert into MyCart2 values(?,?,?,?,?,?,?,?)");
			stmt1.setInt(1, cr.getCustomer_id());
			stmt1.setString(2, cr.getTest_name());
			stmt1.setInt(3, cr.getTest_no());
			stmt1.setInt(4, cr.getNo_tests());
			stmt1.setInt(5, cr.getTest_price());
			stmt1.setString(6, cr.getCustomer_name());
			stmt1.setString(7, cr.getTest_description());
			stmt1.setString(8, cr.getReport_ready());
			int i = stmt1.executeUpdate();
			if(i>0)
			{
				return "Data inserted successfully";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Try again ";
	}
	
	@GetMapping("/api/pathToFitness/Login/Care/test/addToCart/MyCart2")
	public List MyCart2(MyCart2 cr, int test_no)
	{
		List<MyCart2> MyCart2=jdbc.query("select Customer_id, Customer_name,test_name, No_tests, test_price from MyCart2 where test_no='"+test_no+"'", new BeanPropertyRowMapper(MyCart2.class));
		
		return MyCart2;
	}
	
	
	@GetMapping("/api/pathToFitness/Login/Care/test/addToCart/MyCart2/BuyNow")
	public String BuyNow(test_Orders to)
	{
		
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pathToFitness", "root" , "Gautam@6060" );
			PreparedStatement stmt1 =con.prepareStatement("insert into test_Orders values(?,?,?,?,?,?,?,?,?)");
			stmt1.setInt(1, to.getCustomer_id());
			stmt1.setString(2, to.getTest_name());
			stmt1.setInt(3, to.getTest_no());
			stmt1.setInt(4, to.getNo_tests());
			stmt1.setInt(5, to.getTest_price());
			stmt1.setString(6, to.getCustomer_name());
			stmt1.setString(7, to.gettest_description());
			stmt1.setString(8, to.getReport_ready());
			stmt1.setString(9, to.getCheckup_date());
			int i = stmt1.executeUpdate();
			if(i>0)
			{
				return "Data inserted successfully";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Data successfully inserted";
	}
	
	@GetMapping("/api/pathToFitness/Login/Care/test/addToCart/MyCart2/MyOrders")
	public List MyOrders(test_Orders to, int test_no)
	{
		List<test_Orders> test_Orders=jdbc.query("select * from test_Orders where test_no='"+test_no+"'", new BeanPropertyRowMapper(test_Orders.class));
		
		return test_Orders;
	}

	@GetMapping("/api/pathToFitness/Login/Delete_account")
	public String Delete_account(credential cr, String Customer_id)
	{
		jdbc.execute("delete from credential where Customer_id= '"+Customer_id+"' ");
		
		
		return "Your account is sucessfully delete";
	}

	
	

}
