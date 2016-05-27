package calgps;
import java.sql.*;
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		        Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:\\D:/Thesis/Git/org.eclipse.om2m/org.eclipse.om2m.site.gscl/target/products/gscl/win32/win32/x86_64/database/gscldb", "om2m", "om2m");
		        // add application code here
		           conn.close();
		    }
}


