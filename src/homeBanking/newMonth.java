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

@WebServlet("/newMonth")
public class newMonth extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd=request.getRequestDispatcher("/adminPage.html");
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();			
			
			String query1="select * from users";
			ResultSet rs1=stmt.executeQuery(query1);
			String query2;
			int status[]=new int[1000];
			int id[]=new int[1000];
			int i=0;
			
			while(rs1.next())
			{
				status[i]=rs1.getInt(8)+1;
				id[i]=rs1.getInt(1);
				i++;
			}
			
			//ResultSet rs2=stmt.executeQuery(query1);
			int j=i-1;
			i=0;
			while(i<=j)
			{
				query2="update users set payment_status="+status[i]+" where u_id="+id[i]+"";
				stmt.executeUpdate(query2);
				i++;
			}
			
			String msg="Welcome to your New Month";
			pw.write("<h3>"+msg+"</h3>");
			rd.include(request, response);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
