package br.com.fiap.am.coopfit.dto;

import java.io.Serializable;

import br.com.fiap.am.coopfit.domain.DispositivoSensor;
import br.com.fiap.am.coopfit.domain.enums.TipoSensor;

public class DispositivoSensorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private double valor;
	private String tipo;

	public DispositivoSensorDTO() {
		super();
	}

	public DispositivoSensorDTO(DispositivoSensor obj) {
		super();
		this.id = obj.getId();
		this.valor = obj.getValor();
		this.tipo = obj.getTipo().getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public TipoSensor getTipo() {
		return TipoSensor.toEnum(tipo);
	}

	public void setTipo(TipoSensor tipo) {
		this.tipo = tipo.getDescricao();
	}

}
