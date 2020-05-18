package com.stumgnt.dao;

import java.sql.SQLException;

import com.stumgnt.domain.User;

public interface UserDao {

	boolean checkUserName(String userName) throws SQLException;

	User login(String userName, String passwd) throws SQLException;

	void insert(User user) throws SQLException;
	
	User findUser(String userName) throws SQLException;
}
