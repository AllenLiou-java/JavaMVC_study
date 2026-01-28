package model.dao;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.ProductBean;

public class ProductDAOJdbcTests {
	@Test
	public void testSelect() {
		ProductDAOJdbc productDao = new ProductDAOJdbc();
		ProductBean select = productDao.select(10);
		System.out.println("select=" + select);
	}
	@Test
	public void testSelectAll() {
		ProductDAOJdbc productDao = new ProductDAOJdbc();
		List<ProductBean> select = productDao.select();
		System.out.println("selectAll=" + select);
		
	}
	@Test
	public void testInsert() {
		ProductDAOJdbc productDao = new ProductDAOJdbc();
		ProductBean bean = new ProductBean();
		bean.setId(12);
		bean.setName("Water");
		bean.setPrice(10);
		bean.setMake(new java.util.Date(0));
		bean.setExpire(200);
		ProductBean insert = productDao.insert(bean);
		System.out.println("insert=" + insert);
	}
	@Test
	public void testUpdate() {
		ProductDAOJdbc productDao = new ProductDAOJdbc();
		ProductBean update = productDao.update("Yellow Rice", 60, new java.util.Date(0), 100, 11);
		System.out.println("update=" + update);
	}
}
