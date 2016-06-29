import java.sql.*;

public class SQLiteEx {
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
			SQLiteExSample sam = new SQLiteExSample(conn);
			sam.createTable("");
			sam.dumpMetaData();
			sam.populateTable();
			sam.viewTable();
			sam.updateTable();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
	}
}
