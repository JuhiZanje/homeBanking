package homeBanking;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class Connection1 {
	public static Connection createConnection() throws SQLException, ClassNotFoundException
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/homebanking","","");
			System.out.println("connection created");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return con;
	}
	
}
