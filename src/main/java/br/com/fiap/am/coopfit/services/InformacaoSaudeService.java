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

import br.com.fiap.am.coopfit.domain.InformacaoSaude;
import br.com.fiap.am.coopfit.domain.Pessoa;
import br.com.fiap.am.coopfit.domain.enums.TipoUsuario;
import br.com.fiap.am.coopfit.repositories.InformacaoSaudeRepository;
import br.com.fiap.am.coopfit.security.UserSS;
import br.com.fiap.am.coopfit.services.exception.AuthorizationException;
import br.com.fiap.am.coopfit.services.exception.DataIntegrityException;
import br.com.fiap.am.coopfit.services.exception.ObjectNotFoundException;

@Service
public class InformacaoSaudeService {

	@Autowired
	private InformacaoSaudeRepository repo;

	@Autowired
	private PessoaService pessoaService;

	public InformacaoSaude find(Long id) {
		Optional<InformacaoSaude> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + InformacaoSaude.class.getName()));
	}

	@Transactional
	public InformacaoSaude insert(InformacaoSaude obj) {
		obj.setId(null);
		obj.setPessoa(pessoaService.find(obj.getPessoa().getId()));
		return repo.save(obj);
	}

	public InformacaoSaude update(InformacaoSaude obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException error) {
			throw new DataIntegrityException("Não é possiel excluir uma InformacaoSaude que possui produtos");
		}
	}

	public List<InformacaoSaude> findAll() {
		return repo.findAll();
	}

	public Page<InformacaoSaude> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(TipoUsuario.ADMINISTRADOR)) {
			throw new AuthorizationException("Acesso negado");
		}

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Pessoa pessoa = pessoaService.find(user.getId());
		return repo.findByPessoa(pessoa, pageRequest);
	}

}
