package com.stumgnt.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Eric
 *
 */
public class JDBCUtil {
	private static ComboPooledDataSource dataSource = null;

	/**
	 * 
	 *
	 */
	static {
		dataSource = new ComboPooledDataSource();
	}

	/**
	 * @return a <code>Connection</code> object which is represents a connection to
	 *         the datebase
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return dataSource.getConnection();
	}

	/**
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

//	public static void releaseSource
//				(Connection conn, Statement st, ResultSet rs) {
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//			if (st != null) {
//				st.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			rs = null;
//			st = null;
//			conn = null;
//		}
//	}
//
//	public static void release
//				(Connection conn, PreparedStatement ps, ResultSet rs) {
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//			if (ps != null) {
//				ps.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			rs = null;
//			ps = null;
//			conn = null;
//		}
//	}

}
