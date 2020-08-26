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

@WebServlet("/finePage")
public class finePage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s1=request.getSession(false);
		int fine1=Integer.parseInt(request.getParameter("fine"));
		int fine=(int)s1.getAttribute("fine");
		int status=(int)s1.getAttribute("status");
		int uid=(int)s1.getAttribute("uid");
		String date=(String)s1.getAttribute("date");
		String c_date=(String)s1.getAttribute("c_date");
		fine1=fine-fine1;
		
		RequestDispatcher ds=request.getRequestDispatcher("/monthlyDeposit.html");
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		if(fine1==0)
			status=-1;
		else
			status=fine1/25;
		
		try
		{
			String msg="uid="+uid+"'s payment has been noted.";
			Connection con=Connection1.createConnection();
			Statement stmt=con.createStatement();
			System.out.println(date);
			String query="update ammount set fine="+fine+" where u_id="+uid+" and pay_date='"+date+"'";
			String query2="update users set payment_status="+status+" where u_id="+uid+" and c_date='"+c_date+"'";
			stmt.executeUpdate(query);
			stmt.executeUpdate(query2);
			
			
			pw.write("<h3>"+msg+"</h3>");
			ds.include(request, response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
