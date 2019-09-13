package com.rave.inventory.repository;

import java.util.List;

import com.rave.inventory.model.Product;

public interface IReposiotry<T> {

	List<T> findAll() throws Exception;
	String add(T object)throws Exception;
	String delete(int id)throws Exception;
	String update(int id,T newObject)throws Exception;
}
