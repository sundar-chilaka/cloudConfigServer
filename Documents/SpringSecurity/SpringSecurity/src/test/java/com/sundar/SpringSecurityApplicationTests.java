package com.sundar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sundar.controller.ProdectController;
import com.sundar.model.Prodect;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	ProdectController controller;

	@Test
	void contextLoads() {

		Prodect prodect = new Prodect();
		prodect.setDept("Elactronics");
		prodect.setPrice(500.0);
		prodect.setProdectName("Charger");

		controller.saveProdect(prodect);

	}

}
