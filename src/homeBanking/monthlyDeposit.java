package homeBanking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/monthlyDeposit")
public class monthlyDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid=Integer.parseInt(request.getParameter("uid"));
		int amt=Integer.parseInt(request.getParameter("amt"));		
		String date=request.getParameter("date");
		int total_amt,p_status;
		
		HttpSession s1=request.getSession();
		int fine=0;
		String msg="";
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();			
			
			String query1="select * from fund where end_date='0'";
			ResultSet rs1=stmt.executeQuery(query1);
			rs1.next();
			total_amt=rs1.getInt(5)+amt;
			
			
			
			String query="select * from users where u_id="+uid+" and isActive=1";
			ResultSet rs=stmt.executeQuery(query);
			
			
			RequestDispatcher ds=request.getRequestDispatcher("/finePage.html");
			
			
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			
			if(rs.next() && rs.getInt(8)!=-1)
			{				
				
				fine+=(rs.getInt(8)*25);
				String date1=rs.getString(5);
				s1.setAttribute("fine", fine);
				s1.setAttribute("status", rs.getInt(8));
				s1.setAttribute("uid", rs.getInt(1));
				s1.setAttribute("date",date);
				s1.setAttribute("c_date",date1);
				msg+=("Your fine to pay is: rs "+fine);
				
				String query2="insert into ammount(amt_id,u_id,given_amt,c_date,pay_date) values(null,"+uid+","+amt+",'"+date1+"','"+date+"')";
				stmt.executeUpdate(query2);
				
				
				
				
				stmt.executeUpdate("update fund set total_amt="+total_amt+" where end_date='0'");
				
				pw.write("<h3>"+msg+"</h3>");
				ds.include(request, response);
				
			}
			else if(rs.getInt(7)!=-1)
			{
				msg+="This month payment's is already DONE";
				pw.write("<h3>"+msg+"</h3>");
				ds.include(request, response);
			}
			else
			{
				msg+="User does not exist";
				pw.write("<h3>"+msg+"</h3>");
				ds.include(request, response);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
	}

}
