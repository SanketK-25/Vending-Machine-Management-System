package connect;
import java.sql.*;

public class connection {	
	// The Basic Connection Code
	public static Connection dbConnect() {
		Connection con = null;
		try{ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PBL","PBL");
		}	
		catch(Exception e){ 
			System.out.println(e);
		} 
		return con;  //Returns the object of connection
		}
}
