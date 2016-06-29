import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
* refer to http://www.programmerinterview.com/index.php/database-sql/practice-interview-question-1/
*/
public class JDBCEx {
	public static void main(String[] args) {
		Connection conn = null;
		JDBCExUtilities util = new JDBCExUtilities();
		try {
			conn = util.getConnection();
			SalespersonTable s = new SalespersonTable(conn);
			if (!util.isTableExist(conn, "Salesperson")) {
			s.createTable();
			s.populateTable();
			}
			s.viewTable();
			CustomerTable c = new CustomerTable(conn);
			if (!util.isTableExist(conn, "Customer")) {
			c.createTable();
			c.populateTable();
			}
			c.viewTable();
			OrdersTable o = new OrdersTable(conn);
			if (!util.isTableExist(conn, "Orders")) {
			o.createTable();
			o.populateTable();
			}
			o.viewTable();

			System.out.println("The names of all salespeople that have an order with Samsonic: ");
			/*
			select id from customer where name = Samsonic;
			select salesperson_id from orders where cust_id = (select id from customer where name = Samsonic);
			select name from salesperson where salesperson_id in (select salesperson_id from orders where cust_id = (select id from customer where name = Samsonic));

			select salesperson.name
from salesperson, orders, customer
where salesperson.id in (
where customer.name = 'Samsonic'
and orders.cust_id = customer.id
)
			*/
			Statement stmt = null;
			String queryString1 = "select salesperson.name " + 
"from salesperson " +
"where salesperson.id in ( " +
"select orders.salesperson_id from customer, orders " +
"where customer.name = 'Samsonic' " +
"and orders.cust_id = customer.id )";

			String queryString = "select name from salesperson where id in (select salesperson_id from orders where cust_id = (select id from customer where name = 'Samsonic'))";
			String queryString2 = "select id from customer where name = 'Samsonic'";
			String queryString3 = "select salesperson_id from orders where cust_id = (select id from customer where name = 'Samsonic')";

			try {
				stmt = conn.createStatement();
				ResultSet rs2 = stmt.executeQuery(queryString2);
				while (rs2.next()) {
					int id = rs2.getInt("id");
					System.out.println(id);
				}

				ResultSet rs3 = stmt.executeQuery(queryString3);
				while (rs3.next()) {
					int salespersonId = rs3.getInt("salesperson_id");
					System.out.println(salespersonId);
				}

				ResultSet rs = stmt.executeQuery(queryString);
				while (rs.next()) {
					String name = rs.getString("Name");
					System.out.println(name);
				}

				ResultSet rs1 = stmt.executeQuery(queryString1);
				while (rs1.next()) {
					String name = rs1.getString("Name");
					System.out.println(name);
				}

				System.out.println("The names of all salespeople that do not have any order with Samsonic: ");
				String queryString5 = "select salesperson.name " + 
"from salesperson " +
"where salesperson.id not in ( " +
"select orders.salesperson_id from customer, orders " +
"where customer.name = 'Samsonic' " +
"and orders.cust_id = customer.id )";

				ResultSet rs5 = stmt.executeQuery(queryString5);
				while (rs5.next()) {
					String name = rs5.getString("Name");
					System.out.println(name);
				}
				
				System.out.println("The names of salespeople that have 2 or more orders: ");
				String queryString6 = "select salesperson_id "+
"from orders "+
"group by salesperson_id "+
"having count(salesperson_id) > 1";
				ResultSet rs6 = stmt.executeQuery(queryString6);
				while (rs6.next()) {
					int id = rs6.getInt("salesperson_id");
					System.out.println(id);
				}
					String queryString7 = "select salesperson.name "+
"from salesperson "+
"where salesperson.id in ( "+
"select salesperson_id "+
"from orders "+
"group by salesperson_id "+
"having count(salesperson_id) > 1 )";
				ResultSet rs7 = stmt.executeQuery(queryString7);
				while (rs7.next()) {
					String name = rs7.getString("name");
					System.out.println(name);
				}
			
				System.out.println("Write a SQL statement to insert rows into a table called highAchiever(Name, Age), where a salesperson must have a salary of 100,000 or greater to be included in the table: ");
				String createString = "create table highAchiever("+
"Name varchar(32) not null, "+
"Age int not null)";
				String queryString8 = "select name, age "+
"from salesperson "+
"where salary >= 100000";
				String insertString = "insert into highAchiever(Name, Age) "+
"values (?, ?)";
				String insertString2 = "insert into highAchiever (name, age) (select name, age from salesperson where salary > 100000)";

				//stmt.executeUpdate("drop table if exists highAchiever ");
				stmt.executeUpdate("drop table highAchiever ");
				stmt.executeUpdate(createString);

				PreparedStatement pstmt = conn.prepareStatement(insertString);
				ResultSet rs8 = stmt.executeQuery(queryString8);
				while (rs8.next()) {
					String name = rs8.getString("name");
					int age = rs8.getInt("age");

					pstmt.setString(1, name);
					pstmt.setInt(2, age);
					pstmt.executeUpdate();
				}

				String queryString9 = "select name, age "+
"from highAchiever";
				ResultSet rs9 = stmt.executeQuery(queryString9);
				System.out.println("Table highAchiever dump: ");
				while (rs9.next()) {
					String name = rs9.getString("name");
					int age = rs9.getInt("age");
					
					System.out.println(name+", "+age);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (null != stmt) {
					stmt.close();
				}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConnection(conn);
		}
	}
}
	
