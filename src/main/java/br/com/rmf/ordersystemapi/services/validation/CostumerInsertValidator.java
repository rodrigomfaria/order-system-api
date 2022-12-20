package br.com.rmf.ordersystemapi.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rmf.ordersystemapi.controllers.exceptions.FieldMessage;
import br.com.rmf.ordersystemapi.dtos.CostumerNewDto;
import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.entities.CostumerType;
import br.com.rmf.ordersystemapi.repositories.CostumerRepository;
import br.com.rmf.ordersystemapi.services.validation.utils.BR;

public class CostumerInsertValidator implements ConstraintValidator<CostumerInsert, CostumerNewDto> {

	@Autowired
	private CostumerRepository repo;
	
	@Override
	public void initialize(CostumerInsert ann) {
	}

	@Override
	public boolean isValid(CostumerNewDto objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getCostumerType().equals(CostumerType.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(objDto.getRegistrationNumber())) {
			list.add(new FieldMessage("registrationNumber", "Invalid CPF"));
		}

		if (objDto.getCostumerType().equals(CostumerType.PESSOAJURIDICA.getCod())
				&& !BR.isValidCNPJ(objDto.getRegistrationNumber())) {
			list.add(new FieldMessage("registrationNumber", "Invalid CNPJ"));
		}
		
		Costumer aux = repo.findByEmail(objDto.getEmail());
		
		if (aux != null) {
			list.add(new FieldMessage("email", "Email already exists"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
