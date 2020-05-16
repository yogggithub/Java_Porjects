package com.stumgnt.dao;

import java.sql.SQLException;
import java.util.List;

import com.stumgnt.domain.Student;

/**
 * User to define actions related to database.
 * 
 * @author Eric
 *
 */

public interface StuDao {

	int PAGE_SIZE = 3;
	
	/**
	 * @return List<Student> a collection of students
	 * @throws SQLException           if SQL statement get error,
	 *                                like syntax mistake, wrong column name, etc.
	 * @throws ClassNotFoundException if program cannot find the class file
	 *                                of the Student class.
	 */
	List<Student> findAll() throws SQLException, ClassNotFoundException;

	/**
	 * Add a student to the database
	 * 
	 * @param stu a <code>Student</code> objects that going to insert into database
	 */
	void insert(Student stu) throws SQLException;

	void delete(int stuid) throws SQLException;

	Student findStudentById(int stuid) throws SQLException;

	void update(Student stu) throws SQLException;

	List<Student> searchStudent(String name, String gender) throws SQLException;

	List<Student> fiveStuByPage(int currentPage) throws SQLException;
	
	public int allCount() throws SQLException;

}
