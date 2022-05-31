package service;

import java.sql.Connection;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {
	
	public ProductService() {
		
	}
	
	public List<Product> getAll() {
		try (Connection con = DbUtil.getConnection()) {
			ProductDao dao = new ProductDao(con);
			return dao.findAll();
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public List<Product> getLikeKeyWord(String keyWord) {
		try (Connection con = DbUtil.getConnection()) {
			ProductDao dao = new ProductDao(con);
			return dao.findLikeKeyWord(keyWord);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Product getByProductId(int productId) {
		try (Connection con = DbUtil.getConnection()) {
			ProductDao dao = new ProductDao(con);
			return dao.findByProductId(productId);
			
		} catch (Exception e) {
			return null;
		}

	}
	
	public int insert(Product product) {
		try (Connection con = DbUtil.getConnection()) {
			ProductDao dao = new ProductDao(con);
			return dao.insert(product);
			
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	public int delete(int productId) {
		try (Connection con = DbUtil.getConnection()) {
			ProductDao dao = new ProductDao(con);
			return dao.delete(productId);
			
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	public int update(int productId, Product product) {
		try (Connection con = DbUtil.getConnection()) {
			ProductDao dao = new ProductDao(con);
			return dao.update(productId, product);
		} catch (Exception e) {
			return 0;
		}
		
	}
	
}