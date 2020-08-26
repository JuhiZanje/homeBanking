package homeBanking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/listFunds")
public class listFunds extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher ds=request.getRequestDispatcher("/blank.html");
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			ResultSet rs;
			
			rs=stmt.executeQuery("select * from fund");
			pw.write("<h3>List Of Funds</h3>");
			pw.write("<table border=\"2px solid black\">");
			pw.write("<tr><th> Fund Id </th><th> Start Date </th><th> End Date </th><th> Total Members </th><th> Total Amount </th><th> Loan Amount </th></tr>");
		
			while(rs.next())
			{
				pw.write("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5)+"</td><td>"+rs.getInt(6)+"</td></tr>");
			}
			pw.write("</table>");
			ds.include(request, response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

}
