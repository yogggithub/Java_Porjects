package com.stumgnt.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.stumgnt.dao.UserDao;
import com.stumgnt.domain.User;
import com.stumgnt.util.JDBCUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean login(String userName, String passwd) throws SQLException {

		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "select * from t_user where username = ? and password = ?";
		String[] str = {userName, passwd};
		User user = runner.query(sql, new BeanHandler<User>(User.class), str);

		return user == null ? false : true;
	}
}
