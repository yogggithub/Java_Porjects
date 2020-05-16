package com.stumgnt.service.impl;

import java.sql.SQLException;

import com.stumgnt.dao.UserDao;
import com.stumgnt.dao.impl.UserDaoImpl;
import com.stumgnt.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean login(String userName, String passwd) throws SQLException {
		UserDao dao = new UserDaoImpl();
		return dao.login(userName, passwd);
	}

}
