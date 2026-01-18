package model;

import org.junit.jupiter.api.Test;

import model.dao.CustomerDAOJdbc;

public class CustomerDAOJdbcTests {
	@Test
	public void testSelect() {
		CustomerDAOJdbc customerDao = new CustomerDAOJdbc();
		CustomerBean select = customerDao.select("Alex");
		System.out.println("select:" + select);
	}
}
