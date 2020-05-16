package com.stumgnt.service;

import java.sql.SQLException;
import java.util.List;

import com.stumgnt.domain.PageStudent;
import com.stumgnt.domain.Student;

/**
 * 
 * @author Eric
 *
 */
public interface StuService {

	List<Student> findAll() throws ClassNotFoundException, SQLException;

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
	
	PageStudent fiveStuByPage(int currentPage) throws SQLException;
}
