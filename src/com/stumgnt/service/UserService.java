package com.stumgnt.service;

import java.sql.SQLException;

import com.stumgnt.domain.User;

public interface UserService {
	User login(String userName, String passwd) throws SQLException;

	boolean checkUserName(String userName) throws SQLException;

	void insert(User user) throws SQLException;
}
