package homeBanking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/endfund")
public class endfund extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String date=request.getParameter("edate");
		
		RequestDispatcher ds1=request.getRequestDispatcher("/createFund.html");
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			
			String query1="select * from fund where end_date='0'";
			ResultSet rs=stmt.executeQuery(query1);
			
			if(rs.next())
			{
				String query="update fund set end_date='"+date+"' where end_date='0'";
				stmt.executeUpdate(query);
				
				String msg="Your Fund is successfully deleted";
				pw.write("<h3>"+msg+"</h3>");
				ds1.include(request, response);
			}
			else
			{
				String msg="There is no existing fund to delete";
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
