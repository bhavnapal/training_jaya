package com.rave.inventory.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rave.inventory.model.Product;
import com.rave.inventory.util.DBConnection;

public class ProductRepository implements IReposiotry<Product> {

	private static final List<Product> productList=new ArrayList<>();
	private Connection connection;
	public ProductRepository(DBConnection dbConnection) {
		try {
			connection=dbConnection.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	private static final String SELECT_ALL_PRODUCT="SELECT PRODUCTID, PRODUCTNAME,"
			+ "PRICEPERUNIT,QUANTITY FROM PRODUCT";
	
	@Override
	public List<Product> findAll() throws Exception {
		productList.clear();
		Statement selectSTMT=connection.createStatement();
		ResultSet resultSet=selectSTMT.executeQuery(SELECT_ALL_PRODUCT);
		while(resultSet.next()) {
			Product p=new Product();
			p.setProductId(resultSet.getInt("productId"));
			p.setProductName(resultSet.getString("productName"));
			p.setPriceperunit(resultSet.getDouble("priceperunit"));
			p.setQuantity(resultSet.getInt("quantity"));
			productList.add(p);
		}
		return productList;
	}
	
	public static final String ADD_NEW_PRODUCT="insert into "
			+ "	product(productName,priceperunit,quantity)"
			+ " values (?,?,?)";
	
	@Override
	public String add(Product object) throws Exception {
		PreparedStatement insertStmt=connection.prepareStatement(ADD_NEW_PRODUCT);
		
		insertStmt.setString(1, object.getProductName());
		insertStmt.setDouble(2,object.getPriceperunit());
		insertStmt.setInt(3,object.getQuantity());
		
		int noOfRowsUpdated=insertStmt.executeUpdate();//Insert/ update/ delete
		
		if(noOfRowsUpdated>0)
		return "New Product Added successfully";
		else 
			return "Not added";	
	}

	public static final String DELETE_PRODUCT="DELETE FROM PRODUCT WHERE PRODUCTID=?";
	@Override
	public String delete(int id) throws Exception {
		PreparedStatement insertStmt=connection.prepareStatement(DELETE_PRODUCT);
		insertStmt.setInt(1,id);		
		int noOfRowsUpdated=insertStmt.executeUpdate();//Insert/ update/ delete
		
		if(noOfRowsUpdated>0)
		return "New Product "+id+" deleted successfully";
		else 
			return "Not deleted ";
	}
	public static final String UPDATE_PRODUCT="update PRODUCT "
			+ " set PRICEPERUNIT=? , QUANTITY=? "
			+ " WHERE PRODUCTID=? ";
	@Override
	public String update(int id, Product newObject) throws Exception {
		PreparedStatement insertStmt=connection.prepareStatement(UPDATE_PRODUCT);
		insertStmt.setDouble(1,newObject.getPriceperunit());
		insertStmt.setInt(2,newObject.getQuantity());
		insertStmt.setInt(3,id);		
		int noOfRowsUpdated=insertStmt.executeUpdate();//Insert/ update/ delete
		
		if(noOfRowsUpdated>0)
		return "New Product "+id+" UPDATED successfully";
		else 
			return "Not UPDATED ";
	}

	
}
