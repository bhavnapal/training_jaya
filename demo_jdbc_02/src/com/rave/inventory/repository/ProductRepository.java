package com.rave.inventory.repository;

import java.util.ArrayList;
import java.util.List;

import com.rave.inventory.dao.IDao;
import com.rave.inventory.model.Product;

public class ProductRepository implements IReposiotry<Product> {
	private static List<Product> PRODUCT_LIST=new ArrayList<>();
	private IDao<Product> productDao;
	
	
	public ProductRepository(IDao<Product> productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public List<Product> findAll() throws Exception {
		PRODUCT_LIST.clear();
		PRODUCT_LIST=productDao.selectAll();
		return PRODUCT_LIST;
	}

	@Override
	public String add(Product object) throws Exception {
		String msg="";
		if(!PRODUCT_LIST.contains(object)) {
			msg=productDao.insert(object);
			PRODUCT_LIST.add(object);
		}
		return msg;
	}

	@Override
	public String delete(int id) throws Exception {
		String msg="";
		msg=productDao.delete(id);
		return msg;
	}

	@Override
	public String update(int id, Product newObject) throws Exception {
		String msg="";
		msg=productDao.update(id,newObject);
		return msg;
	}

}
