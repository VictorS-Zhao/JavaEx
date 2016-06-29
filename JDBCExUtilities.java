import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.util.Properties;

public class JDBCExUtilities {
	public Connection getConnection() throws SQLException {
		
		Connection conn = null;
		Properties connProps = new Properties();
		connProps.put("user", "ll");
		connProps.put("password", "LL");

		conn = DriverManager.getConnection("jdbc:derby:lldb;create=true", connProps);

		System.out.println("Connected to database");

		return conn;
	}

	public void closeConnection(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
	}

	public boolean isTableExist(Connection conn, String tableName) {
		try {
		DatabaseMetaData dbm = conn.getMetaData();
//		ResultSet res = dbm.getTables(null, null, "%", null);
//		while (res.next()) {
//			System.out.println(res.getString(3));
//		}

		ResultSet rs = dbm.getTables(null, null, tableName.toUpperCase(), null);
		if (rs.next()) {
			System.out.println("Table "+tableName+" is exist!");
			return true;
		} else {
			return false;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		return false;
	}

	public void initializeTables(Connection conn) {
	}

	public void createDatabase() {
	}
}
