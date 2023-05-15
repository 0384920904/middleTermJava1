package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc {
	public static Connection getConnection() {
		Connection c = null;

		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentDB2022;encrypt = false";
			String userName = "sa";
			String pass = "Giahoang2004";

			c = DriverManager.getConnection(url, userName, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
