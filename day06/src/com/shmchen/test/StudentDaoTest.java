package com.shmchen.test;

import java.util.List;

import org.junit.Test;

import com.shmchen.dao.IStudentDao;
import com.shmchen.dao.impl.IStudentDaoImpl;
import com.shmchen.domain.Student;

public class StudentDaoTest {

	IStudentDao dao = new IStudentDaoImpl();
	
	@Test
	public void saveTest() {
		Student student = new Student("日了狗了", 19);
		dao.save(student);
	}
	
	@Test
	public void deleteTest() {
		dao.delete(14);
	}
	
	@Test
	public void editTest() {
		Student student = new Student(13, "我屮艸芔茻", 24);
		dao.edit(student);
	}
	
	@Test
	public void getTest() {
		Student student = dao.get(9);
		System.out.println(student);
	}
	
	@Test
	public void getAllTest() {
		List<Student> all = dao.getAll();
		System.out.println(all);
	}

}
