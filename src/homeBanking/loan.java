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

@WebServlet("/loan")
public class loan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid=Integer.parseInt(request.getParameter("uid"));
		int amt=Integer.parseInt(request.getParameter("amt"));
		int loan_amt;
		
		String date=request.getParameter("date");
		RequestDispatcher rd=request.getRequestDispatcher("/loan.html");
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			String query="select * from users where u_id="+uid+"";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next())
			{
				if(rs.getInt(6)==1)
				{
					String query1="insert into loan values(null,"+uid+","+amt+",'"+date+"',"+amt+",1)";
					stmt.executeUpdate(query1);
					
					String query2="select * from fund where end_date='0'";
					ResultSet rs1=stmt.executeQuery(query2);
					rs1.next();
					loan_amt=rs1.getInt(6)+amt;
					stmt.executeUpdate("update fund set loan_amt="+loan_amt+" where end_date='0'");
					
					String msg="Success!";
					pw.write("<h3>"+msg+"</h3>");
					rd.include(request, response);
					
				}
				else
				{
					String msg="User ID is InActive!";
					pw.write("<h3>"+msg+"</h3>");
					rd.include(request, response);
					
				}
				
			}
			else
			{
				String msg="Wrong User ID";
				pw.write("<h3>"+msg+"</h3>");
				rd.include(request, response);
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
