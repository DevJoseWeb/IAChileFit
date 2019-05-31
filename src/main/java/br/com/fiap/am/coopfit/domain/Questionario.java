package br.com.fiap.am.coopfit.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.fiap.am.coopfit.domain.enums.TipoSentimento;

@Entity
@Table(name = "T_QUESTIONARIO")
public class Questionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_questionario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "qt_copo_agua")
	private Integer qtdCopoAgua;

	@Column(name = "tp_Sentimento")
	private String tipoSentimento;

	@Column(name = "qt_estresse")
	private Integer estresse;

	@Column(name = "dt_questionario")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "cd_pessoa")
	private Pessoa pessoa;

	public Questionario() {
		super();
	}

	public Questionario(Integer qtdCopoAgua, TipoSentimento tipoSentimento, Integer estresse, Date data, Pessoa pessoa) {
		super();
		this.qtdCopoAgua = qtdCopoAgua;
		this.tipoSentimento = tipoSentimento.getDescricao();
		this.estresse = estresse;
		this.data = data;
		this.pessoa = pessoa;
	}

	public Questionario(Long id, Integer qtdCopoAgua, TipoSentimento tipoSentimento, Integer estresse, Date data,
			Pessoa pessoa) {
		super();
		this.id = id;
		this.qtdCopoAgua = qtdCopoAgua;
		this.tipoSentimento = tipoSentimento.getDescricao();
		this.estresse = estresse;
		this.data = data;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getqtdCopoAgua() {
		return qtdCopoAgua;
	}

	public void setqtdCopoAgua(Integer qtdCopoAgua) {
		this.qtdCopoAgua = qtdCopoAgua;
	}

	public TipoSentimento gettipoSentimento() {
		return TipoSentimento.toEnum(tipoSentimento);
	}

	public void settipoSentimento(TipoSentimento tipoSentimento) {
		this.tipoSentimento = tipoSentimento.getDescricao();
	}

	public Integer getEstresse() {
		return estresse;
	}

	public void setEstresse(Integer estresse) {
		this.estresse = estresse;
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
		Questionario other = (Questionario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		builder.append("Nome Pessoa: ");
		builder.append(pessoa.getNome());
		builder.append(", Quantidade Copo d'Agua: ");
		builder.append(qtdCopoAgua);
		builder.append(", Tipo Sentimento: ");
		builder.append(tipoSentimento);
		builder.append(", Quantidade de estresse: ");
		builder.append(estresse);
		builder.append(", Data QUestionario: ");
		builder.append(sdf.format(data));
		return builder.toString();
	}
	
	

}
