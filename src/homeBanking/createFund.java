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

@WebServlet("/createFund")
public class createFund extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher ds=request.getRequestDispatcher("/createFund.html");
		RequestDispatcher ds1=request.getRequestDispatcher("/enterDetails.html");
		
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			
			String query="select * from fund";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next())
			{
				String query1="select * from fund where end_date='0'";
				ResultSet rs1=stmt.executeQuery(query1);
				if(rs1.next())
				{
					String msg="One Fund is already active ,you need to deactivate it first";
					pw.write("<h3>"+msg+"</h3>");
					ds.include(request, response);
				}
				else
				{
					ds1.forward(request, response);
				}
			}
			else
			{
				String msg="Welcome to your First Fund!!";
				pw.write("<h3>"+msg+"</h3>");
				ds1.include(request, response);
			}
			

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}

}
