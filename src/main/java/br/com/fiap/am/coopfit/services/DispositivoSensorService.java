package br.com.fiap.am.coopfit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.am.coopfit.domain.DispositivoSensor;
import br.com.fiap.am.coopfit.dto.DispositivoSensorDTO;
import br.com.fiap.am.coopfit.repositories.DispositivoSensorRepository;
import br.com.fiap.am.coopfit.services.exception.DataIntegrityException;
import br.com.fiap.am.coopfit.services.exception.ObjectNotFoundException;

@Service
public class DispositivoSensorService {
	
	@Autowired
	private DispositivoSensorRepository repo;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private DispositivoService dispositivoService;

	public DispositivoSensor find(Long id) {
		Optional<DispositivoSensor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + DispositivoSensor.class.getName()));
	}

	@Transactional
	public DispositivoSensor insert(DispositivoSensor obj) {
		obj.setId(null);
		obj.setPessoa(pessoaService.find(obj.getPessoa().getId()));
		obj.setDispositivo(dispositivoService.find(obj.getDispositivo().getId()));
		return repo.save(obj);
	}

	public DispositivoSensor update(DispositivoSensor obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException error) {
			throw new DataIntegrityException("Não é possiel excluir uma DispositivoSensor que possui produtos");
		}
	}

	public List<DispositivoSensor> findAll() {
		return repo.findAll();
	}

	public Page<DispositivoSensor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public DispositivoSensor fromDTO(DispositivoSensorDTO objDto) {
		return new DispositivoSensor(objDto.getId(), objDto.getValor(), objDto.getTipo());
	}

	public double maxValue(Long id, String tipo) {
		return repo.maxValue(id, tipo);
	}
	
}
