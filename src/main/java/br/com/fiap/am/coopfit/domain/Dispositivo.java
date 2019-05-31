package br.com.fiap.am.coopfit.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_DISPOSITIVO")
public class Dispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_dispositivo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cd_pessoa")
	private Pessoa pessoa;

	@OneToMany(mappedBy = "dispositivo")
	private List<DispositivoSensor> dispositivoSensores = new ArrayList<>();

	public Dispositivo() {
		super();
	}

	public Dispositivo(String descricao, Pessoa pessoa) {
		super();
		this.descricao = descricao;
		this.pessoa = pessoa;
	}

	public Dispositivo(Long id, String descricao, Pessoa pessoa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<DispositivoSensor> getDispositivoSensores() {
		return dispositivoSensores;
	}

	public void setDispositivoSensores(List<DispositivoSensor> dispositivoSensores) {
		this.dispositivoSensores = dispositivoSensores;
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
		Dispositivo other = (Dispositivo) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
