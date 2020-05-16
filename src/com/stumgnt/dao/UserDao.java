package com.stumgnt.dao;

import java.sql.SQLException;

public interface UserDao {
	boolean login(String userName, String passwd) throws SQLException;
}
