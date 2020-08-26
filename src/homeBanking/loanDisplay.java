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


@WebServlet("/loanDisplay")
public class loanDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher ds=request.getRequestDispatcher("/blank.html");
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String id1=request.getParameter("id");
		
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			ResultSet rs;
			boolean condition=(id1==null);
			
			System.out.println(id1+(!condition && id1.equals("0")));
			
			if(!condition && !id1.equals("0"))
			{
				int id=Integer.parseInt(id1);
				
				System.out.println(id);
				
				ResultSet rs1=stmt.executeQuery("select * from loandetails where l_id="+id+"");
				pw.write("<h3>Loan Detail</h3>");
				pw.write("<table border=\"2px solid black\">");
				pw.write("<tr><th> Loan Details Id </th><th> Loan Id </th><th> Payed Amount </th><th> Interest </th><th> Return Date </th><th> Back </th></tr>");
			
				while(rs1.next())
				{				
					pw.write("<tr><td>"+rs1.getInt(1)+"</td><td>"+rs1.getInt(2)+"</td><td>"+rs1.getInt(3)+"</td><td>"+rs1.getInt(4)+"</td><td>"+rs1.getString(5)+"</td><td><button><a href=\"loanDisplay?id=0\">Back</a></button></td></tr>");
					
				}
				pw.write("</table>");
				ds.include(request, response);
				
			}
			else
			{
				rs=stmt.executeQuery("select * from loan");			
				pw.write("<h3>List Of Loans</h3>");
				pw.write("<table border=\"2px solid black\">");
				pw.write("<tr><th> Loan Id </th><th> User Id </th><th> Loan amount </th><th> Loan Date </th><th> Left Amount </th><th> isActive </th><th> More Information </th></tr>");
			
				while(rs.next())
				{				
					pw.write("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getInt(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getInt(5)+"</td><td>"+rs.getInt(6)+"</td><td><button><a href=\"loanDisplay?id="+rs.getInt(1)+"\">Info</a></button></td></tr>");
					
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
