package model;

import java.util.Arrays;

import model.dao.CustomerDAO;
import model.dao.CustomerDAOJdbc;

//商業邏輯需站在使用者的角度，思考商業邏輯程式如何提供給使用者，包括使用者會傳什麼參數進來，想收到什麼結果
public class CustomerService {
	private CustomerDAO customerDao = new CustomerDAOJdbc();
	
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if(bean != null) {
			CustomerBean update = customerDao.update(newPassword.getBytes(), 
					bean.getEmail(), bean.getBirth(), username);
			if(update != null) {
				return true;
			}
		}
		return false;
	}
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerDao.select(username);
		
		if (bean != null) {
			if (password != null && password.length() != 0) {
				byte[] temp = bean.getPassword();	// 資料庫抓出
				byte[] pass = password.getBytes();	// 使用者輸入
				if (Arrays.equals(temp, pass)) {
					return bean;
				}
			} 
		}
		return null;
	}
	
}
