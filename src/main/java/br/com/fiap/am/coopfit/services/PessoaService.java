package br.com.fiap.am.coopfit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.am.coopfit.domain.Pessoa;
import br.com.fiap.am.coopfit.domain.enums.TipoUsuario;
import br.com.fiap.am.coopfit.dto.PessoaDTO;
import br.com.fiap.am.coopfit.dto.PessoaNewDTO;
import br.com.fiap.am.coopfit.repositories.PessoaRepository;
import br.com.fiap.am.coopfit.security.UserSS;
import br.com.fiap.am.coopfit.services.exception.AuthorizationException;
import br.com.fiap.am.coopfit.services.exception.DataIntegrityException;
import br.com.fiap.am.coopfit.services.exception.ObjectNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;

	public Pessoa find(Long id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(TipoUsuario.ADMINISTRADOR) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
	}

	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		System.out.println(obj.getNome());
		System.out.println(obj.getEmail());
		System.out.println(obj.getAlteracao());
		System.out.println(obj.getCadastro());
		return repo.save(obj);
	}

	public Pessoa update(Pessoa obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException error) {
			throw new DataIntegrityException("Não é possiel excluir uma Pessoa");
		}
	}

	public List<Pessoa> findAll() {
		return repo.findAll();
	}

	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Pessoa fromDTO(PessoaDTO objDto) {
		return new Pessoa(objDto.getId(), objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
	}

	public Pessoa fromNewDTO(PessoaNewDTO objDto) {
		return new Pessoa(objDto.getId(), objDto.getNome(), objDto.getNascimento(), objDto.getGenero(),
				objDto.getFoto(), objDto.getEmail(), pe.encode(objDto.getSenha()), objDto.getCadastro(),
				objDto.getAlteracao(), objDto.isNotificacao(), objDto.getAltura(), objDto.getPeso(),
				objDto.getObservacao());
	}
}
