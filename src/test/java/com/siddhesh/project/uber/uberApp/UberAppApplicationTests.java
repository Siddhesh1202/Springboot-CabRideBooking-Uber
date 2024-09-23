package com.siddhesh.project.uber.uberApp;

import com.siddhesh.project.uber.uberApp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendEmail("pogoyo9233@jofuso.com", "This Is My Subject", "This Is My Body");
	}

	@Test
	void contextLoads2() {
		String emails[] = {
				"pogoyo9233@jofuso.com",
				"siddheshdhonde12.sd@gmail.com",
				"sd1386@rit.edu"
		};
		emailSenderService.sendEmail(emails, "This Is My Subject", "This Is My Body");
	}

}
