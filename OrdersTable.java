import java.sql.*;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class OrdersTable {
	private Connection conn;
	
	public OrdersTable(Connection conn) {
		this.conn = conn;
	}

	public void createTable() throws SQLException {
		String createString = "create table Orders "+"(Number int NOT NULL, "
			+"order_date DATE NOT NULL DEFAULT CURRENT_DATE, "
			+"cust_id int NOT NULL, "
			+"salesperson_id int NOT NULL, "
			+"Amount int NOT NULL, "
			+"FOREIGN KEY (cust_id) REFERENCES Customer (ID), "
			+"FOREIGN KEY (salesperson_id) REFERENCES Salesperson (ID))";

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
		String queryString = "select Number, order_date, cust_id, salesperson_id, Amount from Orders";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			
			System.out.println("Table Orders dump: ");
			while (rs.next()) {
				int num = rs.getInt("Number");
				java.sql.Date sqlDate = rs.getDate("order_date");
				String orderDate = new SimpleDateFormat("MM/dd/yy").format(sqlDate);
				int custId = rs.getInt("cust_id");
				int salespersonId = rs.getInt("salesperson_id");
				
				System.out.println(num+", "+orderDate+", "+custId+", "+salespersonId);
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
		String[] data = {
			"10, '8/2/96', 4, 2, 540",
			"20, '1/30/99', 4, 8, 1800",
			"30, '7/14/95', 9, 1, 460",
			"40, '1/29/98', 7, 2, 2400",
			"50, '2/3/98', 6, 7, 600",
			"60, '3/2/98', 6, 7, 720",
			"70, '5/6/98', 9, 7, 150"
		};

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		PreparedStatement pstmt = null;
		String insertString = 
			"insert into Orders (Number, order_date, cust_id, salesperson_id, Amount) " + 
			"values (?, ?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(insertString);
			
			for (String d : data) {
				String[] s = d.split(",");
				pstmt.setInt(1, Integer.valueOf(s[0].trim()));
				java.util.Date myDate = sdf.parse(s[1].trim().replaceAll("\'", ""));
				java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
				pstmt.setDate(2, sqlDate);
				pstmt.setInt(3, Integer.valueOf(s[2].trim()));
				pstmt.setInt(4, Integer.valueOf(s[3].trim()));
				pstmt.setInt(5, Integer.valueOf(s[4].trim()));

				pstmt.executeUpdate();
				conn.commit();
			}

		} catch (ParseException parseEx) {
			parseEx.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			if (null != pstmt) {
				pstmt.close();
			}

			conn.setAutoCommit(true);
		}
	}

}
