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

@Entity
@Table(name="T_NOTIFICACAO")
public class Notificacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_notificacao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "data", nullable = false)
	private Date data;

	@ManyToOne
	@JoinColumn(name="cd_pessoa")
	private Pessoa pessoa;

	public Notificacao() {
		super();
	}

	public Notificacao(String descricao, Date data, Pessoa pessoa) {
		super();
		this.descricao = descricao;
		this.data = data;
		this.pessoa = pessoa;
	}

	public Notificacao(Long id, String descricao, Date data, Pessoa pessoa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		Notificacao other = (Notificacao) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
