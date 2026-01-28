package model;

import org.junit.jupiter.api.Test;

public class CustomerServiceTests {
	@Test
	public void testLogin() {
		CustomerService customerService = new CustomerService();
		CustomerBean login = customerService.login("Alex", "A");
		System.out.println("login=" + login);
	}
	@Test
	public void testChangePassword() {
		CustomerService customerService = new CustomerService();
		boolean change = customerService.changePassword("Ellen", "EEE", "ABC");
		System.out.println("change=" + change);
	}
}
