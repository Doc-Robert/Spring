package com.geek.springaop;

import com.geek.springaop.service.UserService;
import com.geek.springaop.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class SpringAopApplicationTests {

	@Test
	void contextLoads() {
		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
		//动态代理 代理的是接口
		UserService userService = (UserService) context.getBean("userService");

		userService.add();

	}

}
