import java.sql.*;

/**
* refer to http://www.tutorialspoint.com/sqlite/sqlite_java.htm
* http://www.runoob.com/sqlite/sqlite-java.html
* http://stackoverflow.com/questions/41233/java-and-sqlite
*/

public class SQLiteExSample {
	private Connection conn;

	public SQLiteExSample(Connection conn) {
		this.conn = conn;
	}

	public void createTable2() {
		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists person");
			statement.executeUpdate("create table person (id integer, name string)");
			statement.executeUpdate("insert into person values(1, 'leo')");
			statement.executeUpdate("insert into person values(2, 'yui')");
			ResultSet rs = statement.executeQuery("select * from person");
			while(rs.next())
			{
				// read the result set
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	public void createTable(String tableName) {
		String dropSql = "drop table if exists company";

		String sql = "CREATE TABLE COMPANY "+
			"(ID INT PRIMARY KEY NOT NULL, "+
			"NAME TEXT NOT NULL, "+
			"AGE INT NOT NULL, "+
			"ADDRESS CHAR(50), "+
			"SALARY REAL)";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(dropSql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if (null != stmt) {
				stmt.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void populateTable() {
		String[] data = {"1, 'Paul', 32, 'California', 20000.00",
			"2, 'Allen', 25, 'Texas', 15000.00",
			"3, 'Teddy', 23, 'Norway', 20000.00",
			"4, 'Mark', 25, 'Rich-Mond ', 65000.00"};

		String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                   "VALUES (?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for (String d : data) {
				String[] s = d.split(",");
				pstmt.setInt(1, Integer.valueOf(s[0].trim()));
				pstmt.setString(2, s[1].trim());
				pstmt.setInt(3, Integer.valueOf(s[2].trim()));
				pstmt.setString(4, s[3].trim());
				pstmt.setFloat(5, Float.valueOf(s[4].trim()));
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (null != pstmt) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
				
	}
	
	public void dumpMetaData() throws SQLException {
		DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
	}

	public void viewTable() throws SQLException {
		String sql = "select * from company";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				
				System.out.println(id+", "+name+", "+age+", "+address+", "+salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != stmt) {
				stmt.close();
			}
		}
	}
	
	public void updateTable() throws SQLException {
		String updateSql = "update company set salary = 25000.00 where id=1";
		String deleteSql = "delete from company where id=2";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(updateSql);
			viewTable();
			stmt.executeUpdate(deleteSql);
			viewTable();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != stmt) {
				stmt.close();
			}
		}
	}
		
}
