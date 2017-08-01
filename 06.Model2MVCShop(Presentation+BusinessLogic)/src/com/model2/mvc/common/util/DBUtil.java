package com.model2.mvc.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model2.mvc.common.Search;


public class DBUtil {
	
	/*Field*/
	private final static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String JDBC_URL = "jdbc:oracle:thin:scott/tiger@localhost:1521:xe";

	/*Constructor*/
	private DBUtil(){
	}
	
	/*Method*/
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static int getTotalCount(String sql) throws Exception{
		
		sql = "SELECT COUNT(*) FROM ( "+sql+" ) countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if(rs.next()){
			totalCount = rs.getInt(1);
		}
		
		rs.close();
		pStmt.close();
		con.close();
		
		return totalCount;
	}
	
	public static String makeCurrentPageSql(String sql, Search search){
		sql =	" SELECT * "+
				" FROM (	SELECT inner_table.* , ROWNUM AS row_seq " +
							" FROM ( "+sql+" ) inner_table " +
							" WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
				" WHERE row_seq BETWEEN " +((search.getCurrentPage()-1)*search.getPageSize()+1)+" AND "+search.getCurrentPage()*search.getPageSize();
		
		return sql;
	}

}