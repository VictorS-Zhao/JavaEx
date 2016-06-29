import java.sql.*;
import java.util.Properties;

public class CustomerTable {
	private Connection conn;

	public CustomerTable(Connection conn) {
		this.conn = conn;
	}

	public void createTable() throws SQLException {
		String createString = "create table Customer "+"(ID int NOT NULL, "
			+"Name varchar(32) NOT NULL, "
			+"City varchar(32) NOT NULL, "
			+"Industry_Type char(1) NOT NULL, "
			+"PRIMARY KEY (ID))";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(createString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	public void viewTable() throws SQLException {
		String queryString = "select ID, Name, City, Industry_Type from Customer";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			System.out.println("Table Customer dump: ");
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String city = rs.getString("City");
				String industryType = rs.getString("Industry_Type");
		
				System.out.println(id+", "+name+", "+city+", "+industryType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	public void populateTable() throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into Customer "
				+"values(4, 'Samsonic', 'pleasant', 'J')");
			stmt.executeUpdate("insert into Customer "
				+"values(6, 'Panasung', 'oaktown', 'J')");
			stmt.executeUpdate("insert into Customer "
				+"values(7, 'Samony', 'jackson', 'B')");
			stmt.executeUpdate("insert into Customer "
				+"values(9, 'Orange', 'Jackson', 'B')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != stmt) {
				stmt.close();
			}
		}
	}
}
