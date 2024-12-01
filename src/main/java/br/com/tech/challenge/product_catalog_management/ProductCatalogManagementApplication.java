package br.com.tech.challenge.product_catalog_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductCatalogManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogManagementApplication.class, args);
	}

}
