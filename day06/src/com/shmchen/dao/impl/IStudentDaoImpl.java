package com.shmchen.dao.impl;

import java.util.List;

import com.shmchen.dao.IResultSetHandle;
import com.shmchen.dao.IStudentDao;
import com.shmchen.domain.Student;
import com.shmchen.jdbc.TemplateUtil;

public class IStudentDaoImpl implements IStudentDao {

	@Override
	public void save(Student student) {				
		String sql = "INSERT INTO t_student(`name`, age) VALUES(?, ?)"; 
		TemplateUtil.excuteUpdate(sql, student.getName(), student.getAge());
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM t_student WHERE id = ?"; 
		TemplateUtil.excuteUpdate(sql, id);
	}

	@Override
	public void edit(Student student) {		
		String sql = "UPDATE t_student SET `name` = ?, age = ? WHERE id = ?"; 
		TemplateUtil.excuteUpdate(sql, student.getName(), student.getAge(), student.getId());
	}

	@Override
	public Student get(int id) {
		String sql = "SELECT * FROM t_student WHERE id = ?"; 
		IResultSetHandle<Student> handle = new StudentResultSetHandle<Student>(Student.class);
		List<Student> res = TemplateUtil.excuteQuery(handle, sql, id);
		return res != null ? res.get(0) : null;
	}

	@Override
	public List<Student> getAll() {		
		String sql = "SELECT * FROM t_student"; 
		IResultSetHandle<Student> handle = new StudentResultSetHandle<Student>(Student.class);
		List<Student> res = TemplateUtil.excuteQuery(handle, sql);
		return res != null ? res : null;
	}

}
