package com.stumgnt.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.stumgnt.dao.UserDao;
import com.stumgnt.domain.User;
import com.stumgnt.util.JDBCUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String userName, String passwd) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "select * from t_user where username = ? and password = ?";
		return runner.query(sql, new BeanHandler<User>(User.class), userName, passwd);
	}

	@Override
	public boolean checkUserName(String userName) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "select * from t_user where username = ?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), userName);
		return user == null;
	}

	@Override
	public void insert(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "insert into t_user (username, password) values (?,?)";
		runner.update(sql, user.getUsername(),user.getPassword());
	}

	@Override
	public User findUser(String userName) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "select * from t_user where username = ?";
		return runner.query(sql, new BeanHandler<User>(User.class), userName);

	}
	
	
}
