package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	private String m_DBDriver = "oracle.jdbc.driver.OracleDriver";
	private ResultSet m_RS = null; // RecordSet Variable
	Connection m_conn = null;

	public String DBConnection() {
		String retVal = ""; // there are no errors yet
		try // try to connect to the database
		{
			Class.forName(m_DBDriver);
			m_conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
		}

		// if the driver class isn't found
		catch (ClassNotFoundException e) {
			retVal = e.toString();
			e.printStackTrace();
		}

		catch (SQLException e) {
			retVal = e.toString();
			e.printStackTrace();
		} // if a SQL error occurs

		return (retVal); // returns error messages or an empty string
		// that the caller must print.
	}

	public int add_Customer_Details(String fname, String dob, String uname, String password,
			long contact, String email) {
		String sqlInsert = "INSERT INTO Register VALUES(?,?,?,?,?,?)";
		int n = 0;

		if (m_conn == null) // if Connection has not been set
			return n;
		// ResultSet n_rs = null;

		try {
			PreparedStatement ps = m_conn.prepareStatement(sqlInsert);
			ps.setString(1, fname);
			ps.setString(2, dob);
			ps.setString(3, uname);
			ps.setString(4, password);
			ps.setLong(5, contact);
			ps.setString(6, email);
			n = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} // if a SQL error occurs

		if (n != 0)
			return n;
		else
			return 0;
	}

	public int add_Stock_Detail(String cname, String sr, int pc, int cr, int pcg) {
		String sqlInsert1 = "INSERT INTO stock_details (cname,pc,cr,pcg) VALUES(?,?,?,?)";
		String sqlInsert = "INSERT INTO stock_details VALUES(?,?,?,?,?)";
		int n = 0;

		if (m_conn == null) // if Connection has not been set
			return n;
		// ResultSet n_rs = null;

		try {
			if (sr.equals("") || sr.equals(null)) {
				PreparedStatement ps = m_conn.prepareStatement(sqlInsert1);
				ps.setString(1, cname);
				// ps.setString(2, sr);
				ps.setInt(2, pc);
				ps.setInt(3, cr);
				ps.setInt(4, pcg);
				n = ps.executeUpdate();
			} else {
				PreparedStatement ps = m_conn.prepareStatement(sqlInsert);
				ps.setString(1, cname);
				ps.setString(2, sr);
				ps.setInt(3, pc);
				ps.setInt(4, cr);
				ps.setInt(5, pcg);
				n = ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // if a SQL error occurs

		if (n != 0)
			return n;
		else
			return 0;
	}
        
        
	public ResultSet get_stock_Detail() {

		ResultSet log_RS = null;
		String sqlSelect = "SELECT * FROM stock_details";

		if (m_conn == null) // if Connection has not been set
			log_RS = null;
		else {
			try {

				// Statement s=m_conn.createStatement();

				PreparedStatement ps = m_conn.prepareStatement(sqlSelect);
				log_RS = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}
		return (log_RS);
	}

	public ResultSet get_Customer_Stocks(String u1) {
		// System.out.println(u1);
		ResultSet log_RS = null;
		String sqlSelect = "SELECT * FROM cust_stock where u_name=?";

		if (m_conn == null) // if Connection has not been set
		{
			System.out.println("done");
			log_RS = null;
		} else {
			try {

				// Statement s=m_conn.createStatement();

				PreparedStatement ps = m_conn.prepareStatement(sqlSelect);
				ps.setString(1, u1);
				/* ps.setString(2, pwd); */

				log_RS = ps.executeQuery();
				/* System.out.println(log_RS.getString(1)); */
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}
		System.out.println(log_RS);
		return (log_RS);
	}
        
        public ResultSet get_Customer_Stocks() {
		// System.out.println(u1);
		ResultSet log_RS = null;
		String sqlSelect = "SELECT * FROM cust_stock";

		if (m_conn == null) // if Connection has not been set
		{
			System.out.println("done");
			log_RS = null;
		} else {
			try {

				// Statement s=m_conn.createStatement();

				PreparedStatement ps = m_conn.prepareStatement(sqlSelect);								
				log_RS = ps.executeQuery();
				/* System.out.println(log_RS.getString(1)); */
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}
		System.out.println(log_RS);
		return (log_RS);
	}
        
        
	public ResultSet get_Customer_Details(String uname) {

		ResultSet log_RS = null;
		String sqlSelect = "SELECT uname,dob,contact,email FROM register where uname=?";

		if (m_conn == null) // if Connection has not been set
			log_RS = null;
		else {
			try {

				// Statement s=m_conn.createStatement();

				PreparedStatement ps = m_conn.prepareStatement(sqlSelect);
				ps.setString(1, uname);
				log_RS = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}
		return (log_RS);
	}

	public ResultSet login_Customer(String uname, String pwd) {

		ResultSet log_RS = null;
		String sqlSelect = "SELECT uname,password FROM register where uname=? and password=?";
		System.out.println(uname + pwd);
		if (m_conn == null) // if Connection has not been set
		{
			System.out.println("done");
			log_RS = null;
		} else {
			try {

				PreparedStatement ps = m_conn.prepareStatement(sqlSelect);
				ps.setString(1, uname);
				ps.setString(2, pwd);

				log_RS = ps.executeQuery();
				System.out.println(log_RS.getString(1));
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}
		return (log_RS);
	}

	/*public ResultSet getData4(String uname, String pwd) {
		ResultSet log_RS = null;
		String sqlSelect = "SELECT a_id,password FROM admin where a_id=? and password=?";

		if (m_conn == null) // if Connection has not been set
			log_RS = null;
		else {
			try {

				// Statement s=m_conn.createStatement();

				PreparedStatement ps = m_conn.prepareStatement(sqlSelect);
				ps.setString(1, uname);
				ps.setString(2, pwd);
				log_RS = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}
		return (log_RS);
	}*/

}
