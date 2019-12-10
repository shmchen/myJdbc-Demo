package com.shmchen.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtil {
	
	private static Properties properties;
	
	static {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream in = loader.getResourceAsStream("db.properties");
		properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e1) {			
			e1.printStackTrace();
			throw new RuntimeException("数据库配置文件加载异常");
		}
	}	
	
	public static Connection getConnection() {		
		
		try {
			Class.forName(properties.getProperty("driverClassName"));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException("加载数据库驱动异常");
		}
		
		try {
			DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		throw new RuntimeException("获取数据库连接异常");
	}
	
	public static void close(ResultSet res, Statement statement, Connection connection) {		
		try {
			if (res != null) {
				res.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}		
	}
	
}
