package service;

import dao.UserDao;
import entity.User;
import util.DbUtil;

public class UserService {
	
	public UserService() {
		
	}
	
	public User login(String loginId, String pass) {
		UserDao dao = new UserDao(DbUtil.getConnection());
		return dao.findByLoginIdAndPass(loginId, pass);
		
	}
}