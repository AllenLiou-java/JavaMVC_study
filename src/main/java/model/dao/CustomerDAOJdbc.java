package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerBean;

public class CustomerDAOJdbc implements CustomerDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/java";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	private static final String SELECT_BY_PK = "select * from customer where custid = ?";
	private static final String UPDATE = "update customer set password=?, email=?, birth=? where custid=?";
	
	@Override
	public CustomerBean update(byte[] password, 
			String email, java.util.Date birth, String custid) {
		CustomerBean result = null;
				try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
						PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
					stmt.setBytes(1, password);
					stmt.setString(2, email);
					if(birth != null) {
						long time = birth.getTime();
						stmt.setDate(3, new java.sql.Date(time));
					} else {
						stmt.setDate(3, null);
					}
					stmt.setString(4, custid);
					
					int i = stmt.executeUpdate();
					if(i == 1) {
						return this.select(custid);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return result;
	}
	
	@Override
	public CustomerBean select(String custid) {
		CustomerBean result = null;
		/*
		 * try-with-resource的語法，只要「Resource」實作過java.lang.AutoCloseable這個介面，
		 * 那麼在撰寫程式碼時可以讓整個例外處理的結構變得比較簡潔。
		 */
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_PK)) {

			stmt.setString(1, custid);
			
			// 將 ResultSet 放在第二個 try 中，它也會自動關閉
	        try (ResultSet rset = stmt.executeQuery()) {
	            if (rset.next()) {
	                result = new CustomerBean();
	                result.setCustid(rset.getString("custid"));
	                result.setPassword(rset.getBytes("password"));
	                result.setEmail(rset.getString("email"));
	                result.setBirth(rset.getDate("birth"));
	            }
	        } // rset 在這裡會自動關閉
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
