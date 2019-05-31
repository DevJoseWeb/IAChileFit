package br.com.fiap.am.coopfit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.am.coopfit.domain.Dispositivo;
import br.com.fiap.am.coopfit.domain.enums.TipoUsuario;
import br.com.fiap.am.coopfit.repositories.DispositivoRepository;
import br.com.fiap.am.coopfit.security.UserSS;
import br.com.fiap.am.coopfit.services.exception.AuthorizationException;
import br.com.fiap.am.coopfit.services.exception.DataIntegrityException;
import br.com.fiap.am.coopfit.services.exception.ObjectNotFoundException;

@Service
public class DispositivoService {

	@Autowired
	private DispositivoRepository repo;

	public Dispositivo find(Long id) {
		
		UserSS user = UserService.authenticated();
		if( user==null || !user.hasRole(TipoUsuario.ADMINISTRADOR) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Dispositivo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Dispositivo.class.getName()));
	}

	@Transactional
	public Dispositivo insert(Dispositivo obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Dispositivo update(Dispositivo obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException error) {
			throw new DataIntegrityException("Não é possiel excluir um Dispositivo");
		}
	}

	public List<Dispositivo> findAll() {
		return repo.findAll();
	}

//	public Dispositivo fromDTO(DispositivoDTO objDto) {
//		return new Dispositivo(objDto.getId(), objDto.getNome(), objDto.getEmail());
//	}
	
}
