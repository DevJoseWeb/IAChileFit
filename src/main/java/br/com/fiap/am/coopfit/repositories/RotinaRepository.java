package br.com.fiap.am.coopfit.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.am.coopfit.domain.Pessoa;
import br.com.fiap.am.coopfit.domain.Rotina;

@Repository
public interface RotinaRepository extends JpaRepository<Rotina, Long>{

	@Transactional(readOnly = true)
	Page<Rotina> findByPessoa(Pessoa pessoa, Pageable pageRequest);
	
}
