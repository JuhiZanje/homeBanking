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
import javax.servlet.http.HttpSession;


@WebServlet("/loanId")
public class loanId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int lid=Integer.parseInt(request.getParameter("lid"));
		int leftAmt,interest;
		
		RequestDispatcher rd=request.getRequestDispatcher("/loanId.html");
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		try
		{
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			String query="select * from loanDetails where l_id="+lid+"";
			String query1="select * from loan where l_id="+lid+"";
			ResultSet rs=stmt.executeQuery(query);
			ResultSet rs1=stmt.executeQuery(query1);
			if(rs1.next())
			{
				if(rs1.getInt(6)==1)
				{
					leftAmt=rs1.getInt(5);
					interest=leftAmt/100;
					String msg="Left amount:"+leftAmt+"\n Interest is:"+interest;
					pw.write("<h3>"+msg+"</h3>");
					HttpSession s1=request.getSession();
					s1.setAttribute("interest", interest);
					s1.setAttribute("lid", lid);
					s1.setAttribute("left_amt", leftAmt);
					RequestDispatcher rd1=request.getRequestDispatcher("/loandetails.html");
					rd1.include(request, response);
				}
				else
				{
					String msg="Whole loan amount is paid";
					pw.write("<h3>"+msg+"</h3>");
					rd.include(request, response);
					
				}
			}
			else
			{
				String msg="Wrong Loan ID";
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

