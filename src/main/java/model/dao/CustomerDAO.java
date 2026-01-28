package model.dao;

import model.CustomerBean;

public interface CustomerDAO {

	CustomerBean update(byte[] password, String email, java.util.Date birth, String custid);

	CustomerBean select(String custid);

}