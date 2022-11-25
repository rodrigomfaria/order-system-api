package br.com.rmf.ordersystemapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rmf.ordersystemapi.entities.Category;
import br.com.rmf.ordersystemapi.repositories.CategoryRepository;

@SpringBootApplication
public class OrderSystemApiApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderSystemApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));

	}

}
