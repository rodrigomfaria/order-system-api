package br.com.rmf.ordersystemapi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.entities.Address;
import br.com.rmf.ordersystemapi.entities.Category;
import br.com.rmf.ordersystemapi.entities.City;
import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.entities.CostumerType;
import br.com.rmf.ordersystemapi.entities.Demand;
import br.com.rmf.ordersystemapi.entities.DemandItem;
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
import br.com.rmf.ordersystemapi.repositories.DemandItemRepository;
import br.com.rmf.ordersystemapi.repositories.DemandRepository;
import br.com.rmf.ordersystemapi.repositories.PaymentRepository;
import br.com.rmf.ordersystemapi.repositories.ProductRepository;
import br.com.rmf.ordersystemapi.repositories.StateRepository;

@Service
public class DBService {

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

	@Autowired
	private DemandItemRepository demandItemRepository;

	public void instantiateTestDatabase() throws ParseException {

		Category category_1 = new Category(null, "Computing");
		Category category_2 = new Category(null, "Office");
		Category category_3 = new Category(null, "Bed, table and bath");
		Category category_4 = new Category(null, "Eletronics");
		Category category_5 = new Category(null, "Gardem");
		Category category_6 = new Category(null, "Decoration");
		Category category_7 = new Category(null, "Perfumery");

		Product product_1 = new Product(null, "Computer", 2000.00);
		Product product_2 = new Product(null, "Printer", 800.00);
		Product product_3 = new Product(null, "Mouse", 80.00);
		Product product_4 = new Product(null, "office desk", 300.00);
		Product product_5 = new Product(null, "towel", 50.00);
		Product product_6 = new Product(null, "quilt", 200.00);
		Product product_7 = new Product(null, "TV true color", 1200.00);
		Product product_8 = new Product(null, "brushcutter", 800.00);
		Product product_9 = new Product(null, "Abajour", 100.00);
		Product product_10 = new Product(null, "table lamp", 180.00);
		Product product_11 = new Product(null, "Shampoo", 90.00);

		category_1.getProducts().addAll(Arrays.asList(product_1, product_2, product_3));
		category_2.getProducts().addAll(Arrays.asList(product_2, product_4));
		category_3.getProducts().addAll(Arrays.asList(product_5, product_6));
		category_4.getProducts().addAll(Arrays.asList(product_1, product_2, product_3, product_7));
		category_5.getProducts().addAll(Arrays.asList(product_8));
		category_6.getProducts().addAll(Arrays.asList(product_9, product_10));
		category_7.getProducts().addAll(Arrays.asList(product_11));

		product_1.getCategories().addAll(Arrays.asList(category_1, category_4));
		product_2.getCategories().addAll(Arrays.asList(category_1, category_2, category_4));
		product_3.getCategories().addAll(Arrays.asList(category_1, category_4));
		product_4.getCategories().addAll(Arrays.asList(category_2));
		product_5.getCategories().addAll(Arrays.asList(category_3));
		product_6.getCategories().addAll(Arrays.asList(category_3));
		product_7.getCategories().addAll(Arrays.asList(category_4));
		product_8.getCategories().addAll(Arrays.asList(category_5));
		product_9.getCategories().addAll(Arrays.asList(category_6));
		product_10.getCategories().addAll(Arrays.asList(category_6));
		product_11.getCategories().addAll(Arrays.asList(category_7));

		categoryRepository.saveAll(
				Arrays.asList(category_1, category_2, category_3, category_4, category_5, category_6, category_7));
		productRepository.saveAll(Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6,
				product_7, product_8, product_9, product_10, product_11));

		State state_1 = new State(null, "Minas Gerais");
		State state_2 = new State(null, "S??o Paulo");

		City city_1 = new City(null, "Uberl??ndia", state_1);
		City city_2 = new City(null, "S??o Paulo", state_2);
		City city_3 = new City(null, "Campinas", state_2);

		state_1.getCities().addAll(Arrays.asList(city_1));
		state_2.getCities().addAll(Arrays.asList(city_2, city_3));

		stateRepository.saveAll(Arrays.asList(state_1, state_2));
		cityRepository.saveAll(Arrays.asList(city_1, city_2, city_3));

		Costumer costumer_1 = new Costumer(null, "Maria Silva", "maria@gmail.com", "12345678910",
				CostumerType.PESSOAFISICA);

		costumer_1.getPhoneNumbers().addAll(Arrays.asList("123456789", "987654321"));

		Address address_1 = new Address(null, "Rua das Flores", "300", "Apto 303", "Jardim", "38400000", costumer_1,
				city_1);
		Address address_2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38401000", costumer_1,
				city_1);

		costumer_1.getAddresses().addAll(Arrays.asList(address_1, address_2));

		costumerRepository.saveAll(Arrays.asList(costumer_1));

		addressRepository.saveAll(Arrays.asList(address_1, address_2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Demand demand_1 = new Demand(null, sdf.parse("30/09/2017 10:32"), costumer_1, address_1);
		Demand demand_2 = new Demand(null, sdf.parse("10/10/2017 19:35"), costumer_1, address_2);

		costumer_1.getDemands().addAll(Arrays.asList(demand_1, demand_2));

		Payment payment_1 = new PaymentCard(null, PaymentStatus.QUITADO, demand_1, 6);
		Payment payment_2 = new PaymentBillet(null, PaymentStatus.PENDENTE, demand_2, sdf.parse("20/10/2017 00:00"),
				null);

		demand_1.setPayment(payment_1);
		demand_2.setPayment(payment_2);

		orderRepository.saveAll(Arrays.asList(demand_1, demand_2));

		paymentRepository.saveAll(Arrays.asList(payment_1, payment_2));

		DemandItem demandItem_1 = new DemandItem(demand_1, product_1, 0.00, 1, 2000.00);
		DemandItem demandItem_2 = new DemandItem(demand_1, product_3, 0.00, 2, 80.00);
		DemandItem demandItem_3 = new DemandItem(demand_2, product_2, 100.00, 1, 800.00);

		demand_1.getItems().addAll(Arrays.asList(demandItem_1, demandItem_2));
		demand_2.getItems().addAll(Arrays.asList(demandItem_3));

		product_1.getItems().addAll(Arrays.asList(demandItem_1));
		product_2.getItems().addAll(Arrays.asList(demandItem_3));
		product_3.getItems().addAll(Arrays.asList(demandItem_2));

		demandItemRepository.saveAll(Arrays.asList(demandItem_1, demandItem_2, demandItem_3));
	}
}
