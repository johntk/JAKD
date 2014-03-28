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
			ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
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
	
	public int getId()
	{
		int myId = 0;
		try {
		String seq_val = "Select emp_id from Employee ORDER BY emp_id";
		pstmt = conn.prepareStatement(seq_val, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rset = pstmt.executeQuery();
		
		rset.last();
		myId = rset.getInt(1);
		
		System.out.println(myId);
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return myId +1;
	}
	
	
	public ResultSet getEmployee() {
		try {
			String queryString = "SELECT * FROM Employee ORDER BY emp_id";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(queryString);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	public void addEmployee(Employee e) {
		try {
			String sql = "INSERT INTO Employee(emp_id, f_name, l_name, house_number, street, town,"
					+ " city, pps_num, pin_num, manager) "
					+ "VALUES (empId_seq.nextVal,?,?,?,?,?,?,?,?,?) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getfName());
			pstmt.setString(2, e.getlName());
			pstmt.setString(3, e.getHouseNum());
			pstmt.setString(4, e.getStreet());
			pstmt.setString(5, e.getTown());
			pstmt.setString(6, e.getCity());
			pstmt.setString(7, e.getPPS());
			pstmt.setInt(8, e.getPin());
			pstmt.setString(9, e.getManager());

			pstmt.executeUpdate();
		} catch (Exception se) {
			System.out.println(se);
		}
	}
	
	public ResultSet getLastRow() {
		String sqlStatement = "SELECT * FROM Employee ORDER BY emp_id";
		try {
			pstmt = conn.prepareStatement(sqlStatement,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rset = pstmt.executeQuery();
			rset.last();
			System.out.println(rset.getInt(1)+","+rset.getString(2));
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		
		return rset;
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
