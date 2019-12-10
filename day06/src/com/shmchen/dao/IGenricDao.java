package com.shmchen.dao;

import java.util.List;

public interface IGenricDao<T> {
	/**
	 * 增加学生信息
	 * @param student
	 */
	public void save(T obj);
	
	/**
	 * 根据学生id删除学生信息
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 修改学生信息
	 * @param student
	 */
	public void edit(T obj);

	/**
	 * 根据学生id获取学生信息
	 * @param id
	 * @return 学生domain
	 */
	public T get(int id);
	
	/**
	 * 获取所有学生信息
	 * @return 学生domain数组
	 */
	public List<T> getAll();	
}
