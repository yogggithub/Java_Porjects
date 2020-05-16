package com.stumgnt.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.stumgnt.dao.StuDao;
import com.stumgnt.dao.impl.StuDaoImpl;
import com.stumgnt.domain.PageStudent;
import com.stumgnt.domain.Student;
import com.stumgnt.service.StuService;

public class StuServiceImpl implements StuService {

	@Override
	public List<Student> findAll() throws ClassNotFoundException, SQLException {
		StuDao dao = new StuDaoImpl();
		return dao.findAll();
	}

	@Override
	public void insert(Student stu) throws SQLException {
		StuDao dao = new StuDaoImpl();
		dao.insert(stu);
	}

	@Override
	public void delete(int stuid) throws SQLException {
		StuDao dao = new StuDaoImpl();
		dao.delete(stuid);
	}

	@Override
	public Student findStudentById(int stuid) throws SQLException {
		StuDao dao = new StuDaoImpl();
		return dao.findStudentById(stuid);
	}

	@Override
	public void update(Student stu) throws SQLException {
		StuDao dao = new StuDaoImpl();
		dao.update(stu);
	}


	public List<Student> searchStudent(String searchName, String searchGender) throws SQLException {
		StuDao dao = new StuDaoImpl();
		return dao.searchStudent(searchName,searchGender);
	}

	@Override
	public PageStudent fiveStuByPage(int currentPage) throws SQLException {
		
		StuDao dao = new StuDaoImpl();
		PageStudent<Student> pageStudent = new PageStudent<Student>();
		pageStudent.setCurrentPage(currentPage);
		pageStudent.setPageSize(dao.PAGE_SIZE);
		List<Student> currentList = dao.fiveStuByPage(currentPage);
		pageStudent.setCurrentList(currentList);
		int allCount = dao.allCount();
		pageStudent.setTotalSize(allCount);
		pageStudent.setTotalPages(
				allCount % dao.PAGE_SIZE == 0 ?
						allCount / dao.PAGE_SIZE : allCount / dao.PAGE_SIZE + 1);
		return pageStudent;
		
	}
}
