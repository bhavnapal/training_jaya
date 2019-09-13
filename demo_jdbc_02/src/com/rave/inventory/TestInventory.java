package com.rave.inventory;

import java.util.List;

import org.omg.CORBA.RepositoryIdHelper;

import com.rave.inventory.dao.IDao;
import com.rave.inventory.dao.ProductDao;
import com.rave.inventory.model.Product;
import com.rave.inventory.repository.IReposiotry;
import com.rave.inventory.repository.ProductRepository;
import com.rave.inventory.util.DBConnection;

public class TestInventory {

	public static void main(String[] args) {
		DBConnection dbConnection = new DBConnection();
		
		try {
			IDao<Product> dao = new ProductDao(dbConnection);
			IReposiotry<Product> reposiotry = new ProductRepository(dao);
			List<Product> productList = reposiotry.findAll();
			productList.forEach(p -> {
				System.out.println(p);
			});
			//add
//			System.out.println(reposiotry.add(new Product(0,"Seven up",30,100)));
//			
//			//update
//			System.out.println(reposiotry.delete(4));
//			//delete
//			System.out.println(reposiotry.update(1, new Product(0,null,10,10)));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
