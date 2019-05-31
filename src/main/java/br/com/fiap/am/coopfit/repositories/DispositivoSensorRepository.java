package br.com.fiap.am.coopfit.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.am.coopfit.domain.DispositivoSensor;

@Repository
public interface DispositivoSensorRepository extends JpaRepository<DispositivoSensor, Long> {

	@Transactional
	@Query("SELECT MAX(obj.valor) FROM DispositivoSensor obj WHERE obj.id = :id AND obj.tipo = :tipo")
	double maxValue(Long id, String tipo);
	// select max(valor) from tb_dispositivo_sensor where id_pessoa = 1 and
	// tipo_sensor = 2

}
