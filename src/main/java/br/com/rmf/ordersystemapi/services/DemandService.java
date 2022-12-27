package br.com.rmf.ordersystemapi.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.entities.Demand;
import br.com.rmf.ordersystemapi.entities.DemandItem;
import br.com.rmf.ordersystemapi.entities.PaymentBillet;
import br.com.rmf.ordersystemapi.entities.PaymentStatus;
import br.com.rmf.ordersystemapi.repositories.DemandItemRepository;
import br.com.rmf.ordersystemapi.repositories.DemandRepository;
import br.com.rmf.ordersystemapi.repositories.PaymentRepository;
import br.com.rmf.ordersystemapi.services.exceptions.ObjectNotFoundException;

@Service
public class DemandService {

	@Autowired
	private DemandRepository demandRepository;

	@Autowired
	private BilletService billetService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private DemandItemRepository demandItemRepository;

	public Demand find(Integer id) {
		Optional<Demand> obj = demandRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Demand.class.getName()));
	}

	@Transactional
	public Demand insert(Demand obj) {
		obj.setId(null);
		obj.setCreateAt(new Date());
		obj.getPayment().setPaymentStatus(PaymentStatus.PENDENTE);
		obj.getPayment().setDemand(obj);
		if (obj.getPayment() instanceof PaymentBillet) {
			PaymentBillet payment = (PaymentBillet) obj.getPayment();
			billetService.fillPaymentWithBillet(payment, obj.getCreateAt());
		}
		obj = demandRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		for (DemandItem demandItem : obj.getItems()) {
			demandItem.setDiscount(0.0);
			demandItem.setPrice(productService.find(demandItem.getProduct().getId()).getPrice());
			demandItem.setDemand(obj);
		}
		demandItemRepository.saveAll(obj.getItems());
		return obj;
	}

}
