package com.rave.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rave.inventory.model.Product;
import com.rave.inventory.util.DBConnection;

public class ProductDao implements IDao<Product>{
	private DBConnection dbConnection;
	private Connection connection;
	
	private static final List<Product> PRODUCT_LIST=new ArrayList<>();
	private static final String SELECT_ALL_PRODUCT="SELECT PRODUCTID, PRODUCTNAME,"
			+ "PRICEPERUNIT,QUANTITY FROM PRODUCT";
	public static final String ADD_NEW_PRODUCT="insert into "
			+ "	product(productName,priceperunit,quantity)"
			+ " values (?,?,?)";
	public static final String DELETE_PRODUCT="DELETE FROM PRODUCT WHERE PRODUCTID=?";
	public static final String UPDATE_PRODUCT="update PRODUCT "
			+ " set PRICEPERUNIT=? , QUANTITY=? "
			+ " WHERE PRODUCTID=? ";
	
	
	
	public ProductDao(DBConnection dbConnection) throws Exception {
		super();
		this.dbConnection = dbConnection;
		connection=this.dbConnection.getConnection();
	}

	@Override
	public List<Product> selectAll() throws Exception {
		PRODUCT_LIST.clear();
		Statement selectSTMT=connection.createStatement();
		ResultSet resultSet=selectSTMT.executeQuery(SELECT_ALL_PRODUCT);
		while(resultSet.next()) {
			Product p=new Product();
			p.setProductId(resultSet.getInt("productId"));
			p.setProductName(resultSet.getString("productName"));
			p.setPriceperunit(resultSet.getDouble("priceperunit"));
			p.setQuantity(resultSet.getInt("quantity"));
			PRODUCT_LIST.add(p);
		}
		return PRODUCT_LIST;
	}

	@Override
	public String insert(Product object) throws Exception {
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
