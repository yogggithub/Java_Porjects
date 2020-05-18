package com.stumgnt.service.impl;

import java.sql.SQLException;

import com.stumgnt.dao.UserDao;
import com.stumgnt.dao.impl.UserDaoImpl;
import com.stumgnt.domain.User;
import com.stumgnt.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User login(String userName, String passwd) throws SQLException {
		UserDao dao = new UserDaoImpl();
		return dao.login(userName, passwd);
	}

	@Override
	public boolean checkUserName(String userName) throws SQLException {
		UserDao dao = new UserDaoImpl();
		return dao.checkUserName(userName);
	}

	@Override
	public void insert(User user) throws SQLException {
		UserDao dao = new UserDaoImpl();
		dao.insert(user);
	}
	
	

}
