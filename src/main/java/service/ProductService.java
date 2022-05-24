package service;

import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {
	
	public ProductService() {
		
	}
	
	public List<Product> getAllOrderBy(String column) {
		ProductDao dao = new ProductDao(DbUtil.getConnection());
		return dao.findAll(column);
		
	}
}