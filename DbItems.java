package demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbItems {
	DbConnect con;
	private String sqlSelect;
	private static ResultSet menu_RS;
	public DbItems(){
		con = new DbConnect();
		sqlSelect = "";
		menu_RS = null;
	}
	

	public ResultSet getData() {

		sqlSelect = "SELECT * FROM stock_details ";
		
		con.DBConnection();
		if (con.m_conn == null) // if Connection has not been set
			menu_RS = null;
		else {
			try {

				// Statement s=m_conn.createStatement();

				PreparedStatement p_store = con.m_conn
						.prepareStatement(sqlSelect);

				menu_RS = p_store.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}
		
		return (menu_RS);
	}
        
        public ResultSet get_Customer_Stocks(String uname,String cname) {
		// System.out.println(u1);
		ResultSet log_RS = null;
		String sqlSelect = "SELECT * FROM cust_stock where u_name=?, comp_name=?";

		if (con.m_conn == null) // if Connection has not been set
		{
			System.out.println("done");
			log_RS = null;
		} else {
			try {

				// Statement s=m_conn.createStatement();

				PreparedStatement ps = con.m_conn.prepareStatement(sqlSelect);
                                ps.setString(1, uname);
                                ps.setString(2, cname);
				log_RS = ps.executeQuery();
				/* System.out.println(log_RS.getString(1)); */
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}
		//System.out.println(log_RS);
		return (log_RS);
	}

	public Company getSelect(String c_name) {
		Company com = new Company();
		sqlSelect = "SELECT * FROM cust_stock where comp_name=? ";

		con.DBConnection();
		if (con.m_conn == null) // if Connection has not been set
			menu_RS = null;
		else {
			try {

				// Statement s=m_conn.createStatement();

				PreparedStatement p_store = con.m_conn
						.prepareStatement("SELECT * FROM stock_details where cname=?");
				p_store.setString(1, c_name);
				menu_RS = p_store.executeQuery();
				while(menu_RS.next()){
					com.setCompanyName(menu_RS.getString(1));
					com.setStockRate(menu_RS.getString(2));
					com.setSharePrice(menu_RS.getInt(4));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // if a SQL error occurs
		}

		return (com);


	}
        
        public String getDate() throws SQLException{
            if (con.m_conn == null) // if Connection has not been set

			return null;

		// ResultSet n_rs = null;

		try {

			PreparedStatement ps = con.m_conn.prepareStatement("Select sysdate from dual");

			menu_RS=ps.executeQuery();			

		} catch (SQLException e) {

			e.printStackTrace();

		} // if a SQL error occurs

		menu_RS.next();
                return menu_RS.getString(1);

        }

	public int add_cust_Stock_Details(String uname, String cname, int no_of_shares, int tcost) {
                String sysdate;
		String sqlInsert = "INSERT INTO cust_stock VALUES(?,?,?,?)";

		int n = 0;

		if (con.m_conn == null) // if Connection has not been set

			return n;

		// ResultSet n_rs = null;

		try {

			PreparedStatement ps = con.m_conn.prepareStatement(sqlInsert);

			ps.setString(1, uname);

			ps.setString(2, cname);

			ps.setInt(3, no_of_shares);

			ps.setInt(4, tcost);
                        
                        //ps.setString(5, getDate());

			n = ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} // if a SQL error occurs

		if (n != 0)

			return n;

		else

			return 0;

	}
        
        public int get_num_of_shares(String uname, String cname) throws SQLException {

		String sqlInsert = "SELECT num_of_shares from cust_stock where u_name=? and comp_name=?";

		int no_of_shares = 0;

		if (con.m_conn == null) // if Connection has not been set

			return no_of_shares;

		// ResultSet n_rs = null;

		try {

			PreparedStatement ps = con.m_conn.prepareStatement(sqlInsert);

			ps.setString(1, uname);

			ps.setString(2, cname);			

			menu_RS = ps.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();

		} // if a SQL error occurs
                
                no_of_shares=menu_RS.getInt(3);
                return no_of_shares;
	}        
        
        public ResultSet getStockRate(String cname) throws SQLException {		
                ResultSet log_RS=null;
		String sqlInsert = "SELECT * from stock_details WHERE cname=?";		

		if (con.m_conn == null) // if Connection has not been set
                    log_RS=null;
		// ResultSet n_rs = null;  
                else{
		try {			
				PreparedStatement ps = con.m_conn.prepareStatement(sqlInsert);
				ps.setString(1, cname);				
				log_RS=ps.executeQuery();			 
		} catch (SQLException e) {
			e.printStackTrace();
		} // if a SQL error occurs
                }                
		return (log_RS);
	}
        
        public int subtractStockRate(String cname, String boughtsr) throws SQLException {
                ResultSet StockRate=getStockRate(cname);
                StockRate.next();
                String newStockRate=String.valueOf(Integer.parseInt(StockRate.getString(2))-Integer.parseInt(boughtsr));
		String sqlInsert = "UPDATE stock_details set sr=? where cname=?";		
                int n=0;
		if (con.m_conn == null) // if Connection has not been set
			return n;
		// ResultSet n_rs = null;
                else{
		try {			
				PreparedStatement ps = con.m_conn.prepareStatement(sqlInsert);	
                                ps.setString(1, newStockRate);
				ps.setString(2, cname);				
				n=ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} // if a SQL error occurs }
                }
                if(n!=0){
                return 1;
                }
                return 0;
	}
                
        public int addStockRate(String cname, String boughtsr) throws SQLException {
                ResultSet StockRate=getStockRate(cname);
                StockRate.next();
                String newstockRate=String.valueOf(Integer.parseInt(StockRate.getString(2))+Integer.parseInt(boughtsr));
		String sqlInsert = "UPDATE stock_details set sr=? where cname=?";
		int n = 0;

		if (con.m_conn == null) // if Connection has not been set
			return n;
		// ResultSet n_rs = null;

		try {			
				PreparedStatement ps = con.m_conn.prepareStatement(sqlInsert);				
				ps.setString(1, newstockRate);		
                                ps.setString(2, cname);
				n = ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} // if a SQL error occurs

		if (n != 0)
			return n;
		else
			return 0;
	}   
        
        public int update_customer_shares(String uname, String cname, int numOfShares){ 
		String sqlInsert = "UPDATE cust_stock set num_of_shares=num_of_shares-? where u_name=? and comp_name=?";
		PreparedStatement ps;
                int n = 0;
                
		if (con.m_conn == null) // if Connection has not been set
			return n;
		// ResultSet n_rs = null;

		try {		                                
				ps = con.m_conn.prepareStatement(sqlInsert);	
                                ps.setInt(1, numOfShares);
				ps.setString(2, uname);		
                                ps.setString(3, cname);
                                
				n = ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} // if a SQL error occurs

		if (n != 0)
			return n;
		else
			return 0;
        }
}