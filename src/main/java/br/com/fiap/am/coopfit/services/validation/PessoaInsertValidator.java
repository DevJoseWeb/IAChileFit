package br.com.fiap.am.coopfit.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.am.coopfit.domain.Pessoa;
import br.com.fiap.am.coopfit.dto.PessoaDTO;
import br.com.fiap.am.coopfit.repositories.PessoaRepository;
import br.com.fiap.am.coopfit.resource.exception.FieldMessage;


public class PessoaInsertValidator implements ConstraintValidator<PessoaInsert, PessoaDTO> {

	@Autowired
	private PessoaRepository PessoaRepo;
	
	@Override
	public void initialize(PessoaInsert ann) {
	}

	@Override
	public boolean isValid(PessoaDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Pessoa aux =  PessoaRepo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
