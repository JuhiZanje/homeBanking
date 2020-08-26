package homeBanking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;


@WebServlet("/InsertUser")
public class InsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int irole;
		String query;
		String nm=request.getParameter("name");
		String mob=(request.getParameter("mob"));
		String address=request.getParameter("add");
		String role=request.getParameter("role");
		String dt=request.getParameter("date");
		
		int total_users;
		
		
		RequestDispatcher ds1=request.getRequestDispatcher("/Register.html");
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		if(role.equals("user"))
			irole=0;
		else
			irole=1;
		try
		{
			query="insert into users values(null,'"+nm+"','"+mob+"','"+address+"','"+dt+"',1,"+irole+",0)";
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			stmt.executeUpdate(query);
			
			String query1="select * from fund where end_date='0'";			
			ResultSet rs=stmt.executeQuery(query1);
			rs.next();
			total_users=rs.getInt(4)+1;
			
			//ResultSet rs1=stmt.executeQuery("select * from users");
			stmt.executeUpdate("update fund set total_members="+total_users+" where end_date='0'");
			
			String msg="The member has successfully been added!";
			pw.write("<h3>"+msg+"</h3>");
			ds1.include(request, response);
			
//			if(!rs1.next())
//			{
//				System.out.print("null");
//			}
			
//			if(rs!=0)
//			{
//				System.out.print("done");
//			}
//			else
//			{
//				System.out.print("Not done");
//			}
				
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		
	}

}
