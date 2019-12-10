package com.shmchen.dao.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shmchen.dao.IResultSetHandle;

public class StudentResultSetHandle<T> implements IResultSetHandle<T> {

	private Class<T> clazz;
	
	public StudentResultSetHandle(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public List<T> handle(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();		
		 try {
			 while (resultSet.next()) {
				 T obj = clazz.newInstance();
				 BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
				 PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
				 for (PropertyDescriptor descriptor : descriptors) {
					 Method method = descriptor.getWriteMethod();
					 Object value = resultSet.getObject(descriptor.getName());
					 method.invoke(obj, value);
				}
				list.add(obj);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return list;
	}
}
