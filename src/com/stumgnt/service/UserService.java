package com.stumgnt.service;

import java.sql.SQLException;

public interface UserService {
	boolean login(String userName, String passwd) throws SQLException;
}
