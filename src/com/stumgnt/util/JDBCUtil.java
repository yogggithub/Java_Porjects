package com.stumgnt.util;

import java.sql.Connection;
import java.sql.SQLException;

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
}
