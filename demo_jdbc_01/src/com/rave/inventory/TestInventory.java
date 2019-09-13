package com.rave.inventory;

import java.sql.Connection;
import java.util.List;

import com.rave.inventory.model.Product;
import com.rave.inventory.repository.ProductRepository;
import com.rave.inventory.util.DBConnection;

public class TestInventory {

	public static void main(String[] args) {
		//CRUD
		//1. Retrive Data 
		//Display all products
		try {
			DBConnection dbConnection =new DBConnection();
			ProductRepository productrepository=new ProductRepository(dbConnection);
			List<Product> productList=productrepository.findAll();
			//lambda expresssion
			System.out.println("Before insert ");
			productList.forEach((p)->{System.out.println(p);});
			
			String msg=productrepository.add(new Product(0,"Tropicana",120.00,30));
			System.out.println(msg);
			System.out.println("After insert ");
			productList.forEach((p)->{System.out.println(p);});
			msg=productrepository.delete(8);
			System.out.println(msg);
			
			productList=productrepository.findAll();
			System.out.println("after  delete ");
			productList.forEach((p)->{System.out.println(p);});
			
			msg=productrepository.update(9,new Product(0,null,56.00,123));
			System.out.println(msg);
			System.out.println("after  update ");
			productList=productrepository.findAll();
			productList.forEach((p)->{System.out.println(p);});
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testConnectiong() {
		try {
			Connection connection = DBConnection.getConnection();
			if (connection.isClosed()) {
				System.out.println("Connection Closed");
			} else {
				System.out.println("Connected to inventory db");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
