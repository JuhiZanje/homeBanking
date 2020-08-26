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
import javax.servlet.http.HttpSession;


@WebServlet("/loandetails")
public class loandetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int payedamt=Integer.parseInt(request.getParameter("amt"));
		String date=request.getParameter("date");
		HttpSession s1=request.getSession(false);
		int interest=(int)s1.getAttribute("interest");
		int lid=(int)s1.getAttribute("lid");
		int left_amt=(int)s1.getAttribute("left_amt");
		left_amt=left_amt-payedamt;
		int status;
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		try
		{
			
			
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			
			if(left_amt==0)
				status=0;
			else
				status=1;
			
			String query="insert into loandetails values(null,"+lid+","+payedamt+","+interest+",'"+date+"')";
									
			String query1="update loan set left_amt="+left_amt+",isActive="+status+" where l_id="+lid+"";
			stmt.executeUpdate(query);			
			
			stmt.executeUpdate(query1);
			
			String msg="Successfull!!!";
			pw.write("<h3>"+msg+"</h3>");
			RequestDispatcher rd1=request.getRequestDispatcher("/loandetails.html");
			rd1.include(request, response);	
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}

