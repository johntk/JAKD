package db;

import java.sql.*;


import model.Employee;
import oracle.jdbc.pool.OracleDataSource;


public class AdminOperations {

	
	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public AdminOperations() {
		conn = openDB();
	}
	
	public Connection openDB()
	{
		
		try
		{
			// Load the Oracle JDBC driver
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:HR/pmagee@localhost:1521:XE");
			ods.setUser("johntk86");
			ods.setPassword("FuckYou");

//			 Tallaght Database
//			 ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//			 ods.setUser("x00097736");
//			 ods.setPassword("db02Jan86");

			conn = ods.getConnection();
			System.out.println("Connected.");
		}
		catch(Exception e)
		{
			System.out.println("unable to find driver " + e);
			System.exit(1);
		}
		return conn;
	}
	
	public ResultSet queryEmployee()
	{
		String sqlStatement = "SELECT * FROM Employee";
		try {
			pstmt = conn.prepareStatement(sqlStatement,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rset = pstmt.executeQuery();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}

		return rset;
	}
	
	public ResultSet getEmployee() {
		try {
			String queryString = "SELECT * FROM Employee";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(queryString);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	public void addEmployee(Employee e) {
		try {
			String sql = "INSERT INTO Employee(id, name, address,pnumber, email) "
					+ "VALUES (id_seq.nextVal,?,?,?,?) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getfName());
			pstmt.setString(2, e.getlName());
			pstmt.setString(3, e.getCity());
			pstmt.setString(4, e.getHouseNum());

			pstmt.executeUpdate();
		} catch (Exception se) {
			System.out.println(se);
		}
	}
	public void closeDB() {
		try {
			conn.close();
			System.out.print("Connection closed");
		} catch (SQLException e) {
			System.out.print("Could not close connection ");
			e.printStackTrace();
		}
	}
}
