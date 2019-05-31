package br.com.fiap.am.coopfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.am.coopfit.domain.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long>{

}
