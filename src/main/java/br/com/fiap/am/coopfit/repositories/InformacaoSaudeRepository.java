package br.com.fiap.am.coopfit.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.am.coopfit.domain.InformacaoSaude;
import br.com.fiap.am.coopfit.domain.Pessoa;

@Repository
public interface InformacaoSaudeRepository extends JpaRepository<InformacaoSaude, Long>{

	@Transactional(readOnly = true)
	Page<InformacaoSaude> findByPessoa(Pessoa pessoa, Pageable pageRequest);
	
}
