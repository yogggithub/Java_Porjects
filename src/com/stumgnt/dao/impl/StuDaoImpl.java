package com.stumgnt.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.stumgnt.dao.StuDao;
import com.stumgnt.domain.Student;
import com.stumgnt.util.JDBCUtil;
import com.stumgnt.util.TextUtil;

/**
 * Implement the <code>StuDao</code> interface.
 * 
 * @author Eric
 *
 */

/**
 * @author yogg1
 *
 */
public class StuDaoImpl implements StuDao {

	
	/**
	 * Override <code>findAll</code> method, search and show all of the students.
	 * 
	 * @return List<Student> a collection of students
	 * @throws SQLException           if SQL statement get error,
	 *                                like syntax mistake, wrong column name, etc.
	 * @throws ClassNotFoundException if program cannot find the class file
	 *                                of the Student class.
	 */
	public List<Student> findAll() throws SQLException, ClassNotFoundException {

		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "select * from t_student";
		return runner.query(sql, new BeanListHandler<Student>(Student.class));
	}

	/**
	 *
	 */
	@Override
	public void insert(Student stu) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "insert into t_student (stuname, stuage, gender, phone, hobby, comments)" + "values (?,?,?,?,?,?)";
		runner.update(sql, stu.getStuname(), stu.getStuage(), stu.getGender(), stu.getPhone(), stu.getHobby(),
				stu.getComments());
	}

	@Override
	public void delete(int stuid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "delete from t_student where stuid = ?";
		runner.update(sql, stuid);
	}

	public Student findStudentById(int stuid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "select * from t_student where stuid = ?";
		return runner.query(sql, new BeanHandler<Student>(Student.class), stuid);
	}

	@Override
	public void update(Student stu) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "update t_student set stuname = ?, stuage = ?, gender = ?, phone = ?, hobby = ?, comments =? where stuid = ?";
		runner.update(sql, stu.getStuname(), stu.getStuage(), stu.getGender(), stu.getPhone(), stu.getHobby(),
				stu.getComments(), stu.getStuid());
	}

	@Override
	public List<Student> searchStudent(String name, String gender) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		/*
		 * Consider SQL query: select table from table where name like %% and gender =
		 * ""
		 * It's complicate to decide whether there should be a "and" or not.
		 * So, I choose to build-in a where clause in order to let both name and gender
		 * have "and"
		 * 
		 * One possible alter way, is the check two parameter first
		 * Only when the two parameters both not null, "and" will be mandatory.
		 */
		String sql = "select * from t_student where true ";
		/*
		 * Search keywords may change, so the <code>query</code> need to flexible
		 * parameter.
		 * I use <code>List</code>, only add SQL which is going to be used into it.
		 */
		ArrayList<String> argList = new ArrayList<String>();
		if (!TextUtil.isEmpty(name)) {
			sql += "and stuname like ?";
			argList.add("%" + name + "%");
		}
		if (!TextUtil.isEmpty(gender)) {
			sql += "and gender = ?";
			argList.add(gender);
		}
		return runner.query(sql, new BeanListHandler<Student>(Student.class), argList.toArray());
	}

	/**
	 *	
	 */
	@Override
	public List<Student> fiveStuByPage(int currentPage) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		/*
		 * First parameter indicate how many Students appears on one page
		 * Second parameter refer to skip the students that been show in previous pages.
		 */
		String sql = "select * from t_student limit ? offset ?";
		return runner.query(sql, new BeanListHandler<Student>(Student.class), PAGE_SIZE, (currentPage - 1) * PAGE_SIZE);
	}

	/**
	 *
	 */
	@Override
	public int allCount() throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
		/*
		 * <code>ScalarHandler</code> convert one column to a object
		 * It is suitable for using with SQL aggregate clauses, like count() or average() .
		 */
		Long count = (long)runner.query("select count(stuid) from t_student", new ScalarHandler());
		return count.intValue();
		
	}
	
	

}
