package com.rave.inventory.dao;

import java.util.List;

public interface IDao<T> {
	List<T> selectAll() throws Exception;
	String insert(T object)throws Exception;
	String delete(int id)throws Exception;
	String update(int id,T newObject)throws Exception;
}
