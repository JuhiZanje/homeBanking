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



@WebServlet("/enterDetails")
public class enterDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=request.getParameter("sdate");
		
		RequestDispatcher ds1=request.getRequestDispatcher("/Register.html");
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			
			String query="insert into fund values(null,'"+date+"','0',0,0,0)";
			stmt.executeUpdate(query);
			
			String msg="Your Fund is successfully created";
			pw.write("<h3>"+msg+"</h3>");
			ds1.include(request, response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
