package com.shmchen.dao;

import java.sql.ResultSet;
import java.util.List;

public interface IResultSetHandle<T> {
	public List<T> handle(ResultSet resultSet);
}
