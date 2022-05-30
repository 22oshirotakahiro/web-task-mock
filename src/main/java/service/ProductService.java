package service;

import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {
	
	public ProductService() {
		
	}
	
	public List<Product> getAll() {
		ProductDao dao = new ProductDao(DbUtil.getConnection());
		return dao.findAll();
		
	}
	
	public List<Product> getLikeKeyWord(String keyWord) {
		ProductDao dao = new ProductDao(DbUtil.getConnection());
		return dao.findLikeKeyWord(keyWord);
		
	}
	
	public Product getByProductId(int productId) {
		ProductDao dao = new ProductDao(DbUtil.getConnection());
		return dao.findByProductId(productId);

	}
	
	public int insert(Product product) {
		ProductDao dao = new ProductDao(DbUtil.getConnection());
		return dao.insert(product);
		
	}
	
	public int delete(int productId) {
		ProductDao dao = new ProductDao(DbUtil.getConnection());
		return dao.delete(productId);
		
	}
	
	public int update(int productId, Product product) {
		ProductDao dao = new ProductDao(DbUtil.getConnection());
		return dao.update(productId, product);
		
	}
	
}