package br.com.fiap.am.coopfit.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.am.coopfit.domain.enums.TipoSensor;

@Entity
@Table(name = "T_DISPOSITIVO_SENSOR")
public class DispositivoSensor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_dispositivo_sensor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "valor", nullable = false)
	private double valor;

	@Column(name = "tipo", nullable = false)
	private String tipo;

	@Column(name = "data", nullable = false)
	private Date data;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_dispositivo")
	private Dispositivo dispositivo;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cd_pessoa")
	private Pessoa pessoa;

	public DispositivoSensor() {
		super();
	}

	public DispositivoSensor(Long id, double valor, TipoSensor tipo) {
		super();
		this.id = id;
		this.valor = valor;
		this.tipo = tipo.getDescricao();
	}

	public DispositivoSensor(double valor, TipoSensor tipo, Date data, Dispositivo dispositivo, Pessoa pessoa) {
		super();
		this.valor = valor;
		this.tipo = tipo.getDescricao();
		this.data = data;
		this.dispositivo = dispositivo;
		this.pessoa = pessoa;
	}

	public DispositivoSensor(Long id, double valor, TipoSensor tipo, Date data, Dispositivo dispositivo,
			Pessoa pessoa) {
		super();
		this.id = id;
		this.valor = valor;
		this.tipo = tipo.getDescricao();
		this.data = data;
		this.dispositivo = dispositivo;
		this.pessoa = pessoa;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DispositivoSensor other = (DispositivoSensor) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
