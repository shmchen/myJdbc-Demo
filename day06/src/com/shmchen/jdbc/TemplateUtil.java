package com.shmchen.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shmchen.dao.IResultSetHandle;
import com.shmchen.domain.Student;

public class TemplateUtil {
	
	public static void excuteUpdate(String sql, Object...params) {		
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				statement.setObject((i + 1), params[i]);
			}
			int res = statement.executeUpdate();
			System.out.println("受影响 " + res + "行");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(null, statement, connection);
		}		
	}
	
	public static List<Student> excuteQuery(String sql, Object...params) {		
		
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				System.out.println(params);
				statement.setObject((i + 1), params[i]);
			}
			resultSet = statement.executeQuery();
			
			List<Student> list = new ArrayList<Student>();
			while (resultSet.next()) {
				int sid = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				Student student = new Student(sid, name, age);
				list.add(student);
			}			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(resultSet, statement, connection);
		}			
		return null;
	}
	
	public static <T> List<T> excuteQuery(IResultSetHandle<T> handle, String sql, Object...params) {		
		
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				System.out.println(params);
				statement.setObject((i + 1), params[i]);
			}
			resultSet = statement.executeQuery();			
			return handle.handle(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(resultSet, statement, connection);
		}			
		return null;
	}
	
}
