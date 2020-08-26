package homeBanking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listUsers")
public class listUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher ds=request.getRequestDispatcher("/blank.html");
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			ResultSet rs;
			String id1=request.getParameter("id");
			if(id1!=null)
			{
				int id=Integer.parseInt(id1);
				
				System.out.println(id);
				rs=stmt.executeQuery("select * from ammount where u_id="+id+"");
				pw.write("<h3>Information</h3>"+name);
				pw.write("<table  border=\"2px solid black\"  > ");
				pw.write("<tr><th> Amount Id </th><th> User Id </th><th> Given Amount </th><th> Fund Start Date </th><th> Pay Date </th><th> Fine </th>></tr>");
				
				while(rs.next())
				{
					pw.write("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getInt(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getInt(6)+"</td></tr>");
				}
				pw.write("</table>");
				ds.include(request, response);
				
			}
			else
			{
				if(name.equals("user"))
				{
					rs=stmt.executeQuery("select * from users where isAdmin=0");
					pw.write("<h3>List Of Users</h3>"+name);
					pw.write("<table border=\"2px solid black\">");
					pw.write("<tr><th> User Id </th><th> Name </th><th> Mobile Number </th><th> Address </th><th> Joining Date </th><th> isActive </th><th> Payment Status </th><th> More Info </th></tr>");
					
				}
				else
				{
					rs=stmt.executeQuery("select * from users where isAdmin=1");
					pw.write("<h3>List Of Admins</h3>"+name);
					pw.write("<table border=\"2px solid black\">");
					pw.write("<tr><th> Admin Id </th><th> Name </th><th> Mobile Number </th><th> Address </th><th> Joining Date </th><th> isActive </th><th> Payment Status </th><th> More Info </th></tr>");
				}
				while(rs.next())
				{
					pw.write("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getInt(6)+"</td><td>"+rs.getInt(8)+"</td><td><button><a href=\"listUsers?id="+rs.getInt(1)+"\">Info</a></button></td></tr>");
				}
				pw.write("</table>");
				ds.include(request, response);
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
