package br.com.rmf.ordersystemapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.dtos.CostumerDto;
import br.com.rmf.ordersystemapi.dtos.CostumerNewDto;
import br.com.rmf.ordersystemapi.entities.Address;
import br.com.rmf.ordersystemapi.entities.City;
import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.entities.CostumerType;
import br.com.rmf.ordersystemapi.repositories.AddressRepository;
import br.com.rmf.ordersystemapi.repositories.CityRepository;
import br.com.rmf.ordersystemapi.repositories.CostumerRepository;
import br.com.rmf.ordersystemapi.services.exceptions.DataIntegrityException;
import br.com.rmf.ordersystemapi.services.exceptions.ObjectNotFoundException;

@Service
public class CostumerService {

	@Autowired
	private CostumerRepository costumerRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private AddressRepository addressRepository;

	public Costumer find(Integer id) {
		Optional<Costumer> obj = costumerRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Tipo: " + Costumer.class.getName()));
	}

	public Costumer insert(Costumer obj) {
		obj.setId(null);
		obj = costumerRepository.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}

	public Costumer update(Costumer obj) {
		Costumer newObj = find(obj.getId());
		UpdateData(newObj, obj);
		return costumerRepository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			costumerRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete because there are related entities");
		}
	}

	public List<Costumer> findAll() {
		return costumerRepository.findAll();
	}

	public Page<Costumer> findPage(Integer page, Integer linesPerPage, String direction, String orderby) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
		return costumerRepository.findAll(pageRequest);
	}

	public Costumer fromDto(CostumerDto objDto) {
		return new Costumer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}

	public Costumer fromDto(CostumerNewDto objDto) {
		Costumer costumer = new Costumer(null, objDto.getName(), objDto.getEmail(), objDto.getRegistrationNumber(),
				CostumerType.toEnum(objDto.getCostumerType()));
		Optional<City> city = cityRepository.findById(objDto.getCityId());
		Address address = new Address(null, objDto.getPublicPlace(), objDto.getNumber(), objDto.getComplement(),
				objDto.getDistrict(), objDto.getZipCode(), costumer, city.get());
		costumer.getAddresses().add(address);
		costumer.getPhoneNumbers().add(objDto.getPhoneNumbers1());
		if (objDto.getPhoneNumbers2() != null) {
			costumer.getPhoneNumbers().add(objDto.getPhoneNumbers2());
		}
		if (objDto.getPhoneNumbers3() != null) {
			costumer.getPhoneNumbers().add(objDto.getPhoneNumbers3());
		}
		return costumer;
	}

	private void UpdateData(Costumer newObj, Costumer obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
