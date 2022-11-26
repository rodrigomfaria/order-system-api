package br.com.rmf.ordersystemapi;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rmf.ordersystemapi.entities.Address;
import br.com.rmf.ordersystemapi.entities.Category;
import br.com.rmf.ordersystemapi.entities.City;
import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.entities.CostumerType;
import br.com.rmf.ordersystemapi.entities.Demand;
import br.com.rmf.ordersystemapi.entities.Payment;
import br.com.rmf.ordersystemapi.entities.PaymentBillet;
import br.com.rmf.ordersystemapi.entities.PaymentCard;
import br.com.rmf.ordersystemapi.entities.PaymentStatus;
import br.com.rmf.ordersystemapi.entities.Product;
import br.com.rmf.ordersystemapi.entities.State;
import br.com.rmf.ordersystemapi.repositories.AddressRepository;
import br.com.rmf.ordersystemapi.repositories.CategoryRepository;
import br.com.rmf.ordersystemapi.repositories.CityRepository;
import br.com.rmf.ordersystemapi.repositories.CostumerRepository;
import br.com.rmf.ordersystemapi.repositories.DemandRepository;
import br.com.rmf.ordersystemapi.repositories.PaymentRepository;
import br.com.rmf.ordersystemapi.repositories.ProductRepository;
import br.com.rmf.ordersystemapi.repositories.StateRepository;

@SpringBootApplication
public class OrderSystemApiApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CostumerRepository costumerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private DemandRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderSystemApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City ct1 = new City(null, "Uberlândia", st1);
		City ct2 = new City(null, "São Paulo", st2);
		City ct3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(ct1));
		st2.getCities().addAll(Arrays.asList(ct2, ct3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(ct1, ct2, ct3));

		Costumer ctm1 = new Costumer(null, "Maria Silva", "maria@gmail.com", "12345678910", CostumerType.PESSOAFISICA);

		ctm1.getPhoneNumbers().addAll(Arrays.asList("123456789", "987654321"));

		Address add1 = new Address(null, "Rua das Flores", "300", "Apto 303", "Jardim", "38400000", ctm1, ct1);
		Address add2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38401000", ctm1, ct2);

		ctm1.getAddresses().addAll(Arrays.asList(add1, add2));

		costumerRepository.saveAll(Arrays.asList(ctm1));

		addressRepository.saveAll(Arrays.asList(add1, add2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Demand dmd1 = new Demand(null, sdf.parse("30/09/2017 10:32"), ctm1, add1);
		Demand dmd2 = new Demand(null, sdf.parse("10/10/2017 19:35"), ctm1, add2);

		ctm1.getOrders().addAll(Arrays.asList(dmd1, dmd2));
		
		Payment pay1 = new PaymentCard(null, PaymentStatus.QUITADO, dmd1, 6);
		Payment pay2 = new PaymentBillet(null, PaymentStatus.PENDENTE, dmd2, sdf.parse("20/10/2017 00:00"), null);

		dmd1.setPayment(pay1);
		dmd2.setPayment(pay2);

		orderRepository.saveAll(Arrays.asList(dmd1, dmd2));

		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

	}

}
