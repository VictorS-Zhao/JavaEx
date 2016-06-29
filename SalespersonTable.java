import java.sql.*;
import java.util.Properties;

public class SalespersonTable {
	private Connection conn;

	public SalespersonTable(Connection conn) {
		this.conn = conn;
	}

	public void createTable() throws SQLException {
		String createString = "create table Salesperson "+"(ID int NOT NULL, "
					+ "Name varchar(32) NOT NULL, "
					+ "Age integer NOT NULL, "
					+ "Salary numeric(10,2) NOT NULL, "
					+ "PRIMARY KEY (ID))";

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
		String queryString = "select ID, Name, Age, Salary from Salesperson";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			
			System.out.println("Table Salesperson dump: ");
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				float salary = rs.getInt("Salary");
				
				System.out.println(id+", "+name+", "+age+", "+salary);
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
			stmt.executeUpdate("insert into Salesperson "
				+"values(1, 'Abe', 61, 140000)");
			stmt.executeUpdate("insert into Salesperson "
				+"values(2, 'Bob', 34, 44000)");
			stmt.executeUpdate("insert into Salesperson "
				+"values(5, 'Chris', 34, 40000)");
			stmt.executeUpdate("insert into Salesperson "
				+"values(7, 'Dan', 41, 52000)");
			stmt.executeUpdate("insert into Salesperson "
				+"values(8, 'Ken', 57, 115000)");
			stmt.executeUpdate("insert into Salesperson "
				+"values(11, 'Joe', 38, 38000)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != stmt) {
				stmt.close();
			}
		}
	}

}

