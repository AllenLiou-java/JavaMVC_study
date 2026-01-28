package model.dao;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import model.CustomerBean;
import model.dao.CustomerDAOJdbc;

public class CustomerDAOJdbcTests {
	@Test
	public void testSelect() {
		CustomerDAO customerDao = new CustomerDAOJdbc();
		CustomerBean select = customerDao.select("Alex");
		System.out.println("select:" + select);
	}
	@Test
	public void testUpdate() {
		CustomerDAO customerDao = new CustomerDAOJdbc();
		
		// 將字串格式（如 2010-01-02）轉換為 java.util.Date 物件
		String dateString = "1990-01-02";
		// 1. 先轉為現代的 LocalDate (預設就支援 yyyy-MM-dd 格式)
		LocalDate localDate = LocalDate.parse(dateString);
		// 2. 將 LocalDate 轉換回 java.util.Date
		java.util.Date date = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		CustomerBean update = customerDao.update("EEE".getBytes(),
				"ellen@gmail.com", date, "Ellen");
		System.out.println("update=" + update);
	}
}
