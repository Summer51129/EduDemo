package com.aic.edudemo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.aic.edudemo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest測試執行中建置並導入框架設定與元件
//@Autowired框架的註冊的元件引入
@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void fetchAllEmployees(){
		assertNotNull(this.employeeRepository);
		this.employeeRepository.findAll().forEach(System.out::println);
	}
}
