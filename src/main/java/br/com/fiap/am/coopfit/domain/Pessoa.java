package br.com.fiap.am.coopfit.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.am.coopfit.domain.enums.Genero;
import br.com.fiap.am.coopfit.domain.enums.TipoUsuario;
import br.com.fiap.am.coopfit.dto.PessoaNewDTO;

@Entity
@Table(name = "T_PESSOA")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_pessoa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "dt_nascimento", nullable = false)
	private Date nascimento;
	@Column(name = "ds_genero", nullable = false)
	private Integer genero;
	@Column(name = "foto")
	private byte[] foto;
	@Column(name = "email", nullable = false)
	private String email;
	@JsonIgnore
	@Column(name = "senha", nullable = false)
	private String senha;
	@Column(name = "dt_cadastro", nullable = false)
	private Date cadastro;
	@Column(name = "dt_alteracao")
	private Date alteracao;
	@Column(name = "recebe_notificacao", nullable = false)
	private boolean notificacao;
	@Column(name = "altura", nullable = false)
	private double altura;
	@Column(name = "peso", nullable = false)
	private double peso;
	@Column(name = "observacao")
	private String observacao;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "T_PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<InformeTratativo> informativos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<Notificacao> notificacoes = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<Rotina> rotinas = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<InformacaoSaude> informacoesSaude = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<Questionario> questionarios = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa")
	private List<Dispositivo> dispositivos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<DispositivoSensor> dispositivoSensores = new ArrayList<>();

	public Pessoa() {
		super();
	}

	public Pessoa(PessoaNewDTO objDto) {
		super();
		addPerfil(TipoUsuario.ADMINISTRADOR);
	}

	public Pessoa(Long id, String nome, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Pessoa(String nome, Date nascimento, Integer genero, byte[] foto, String email, String senha, Integer tipo,
			Date cadastro, Date alteracao, boolean notificacao, double altura, double peso, String observacao) {
		super();
		this.nome = nome;
		this.nascimento = nascimento;
		this.genero = genero;
		this.foto = foto;
		this.email = email;
		this.senha = senha;
		this.cadastro = cadastro;
		this.alteracao = alteracao;
		this.notificacao = notificacao;
		this.altura = altura;
		this.peso = peso;
		this.observacao = observacao;
		addPerfil(TipoUsuario.ADMINISTRADOR);
	}

	public Pessoa(String nome, Date nascimento, Genero genero, String email, String senha, Date cadastro,
			Date alteracao, boolean notificacao, double altura, double peso, String observacao) {
		super();
		this.nome = nome;
		this.nascimento = nascimento;
		this.genero = genero.getCodigo();
		this.email = email;
		this.senha = senha;
		this.cadastro = cadastro;
		this.alteracao = alteracao;
		this.notificacao = notificacao;
		this.altura = altura;
		this.peso = peso;
		this.observacao = observacao;
		addPerfil(TipoUsuario.ADMINISTRADOR);
	}

	public Pessoa(Long id, String nome, Date nascimento, Genero genero, String email, String senha, Date cadastro,
			Date alteracao, boolean notificacao, double altura, double peso, String observacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.genero = genero.getCodigo();
		this.email = email;
		this.senha = senha;
		this.cadastro = cadastro;
		this.alteracao = alteracao;
		this.notificacao = notificacao;
		this.altura = altura;
		this.peso = peso;
		this.observacao = observacao;
		addPerfil(TipoUsuario.ADMINISTRADOR);
	}

	public Pessoa(Long id, String nome, Date nascimento, Integer genero, byte[] foto, String email, String senha,
			Date cadastro, Date alteracao, boolean notificacao, double altura, double peso, String observacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.genero = genero;
		this.foto = foto;
		this.email = email;
		this.senha = senha;
		this.cadastro = cadastro;
		this.alteracao = alteracao;
		this.notificacao = notificacao;
		this.altura = altura;
		this.peso = peso;
		this.observacao = observacao;
		addPerfil(TipoUsuario.ADMINISTRADOR);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Genero getGenero() {
		return Genero.toEnum(genero);
	}

	public void setGenero(Genero genero) {
		this.genero = genero.getCodigo();
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public Date getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(Date alteracao) {
		this.alteracao = alteracao;
	}

	public boolean isNotificacao() {
		return notificacao;
	}

	public void setNotificacao(boolean notificacao) {
		this.notificacao = notificacao;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<InformeTratativo> getInformativos() {
		return informativos;
	}

	public void setInformativos(List<InformeTratativo> informativos) {
		this.informativos = informativos;
	}

	public List<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<Notificacao> notificacoes) {
		this.notificacoes = notificacoes;
	}

	public List<Rotina> getRotinas() {
		return rotinas;
	}

	public void setRotinas(List<Rotina> rotinas) {
		this.rotinas = rotinas;
	}

	public List<InformacaoSaude> getInformacoesSaude() {
		return informacoesSaude;
	}

	public void setInformacoesSaude(List<InformacaoSaude> informacoesSaude) {
		this.informacoesSaude = informacoesSaude;
	}

	public List<Questionario> getQuestionarios() {
		return questionarios;
	}

	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public List<DispositivoSensor> getDispositivoSensores() {
		return dispositivoSensores;
	}

	public void setDispositivoSensores(List<DispositivoSensor> dispositivoSensores) {
		this.dispositivoSensores = dispositivoSensores;
	}

	public Set<TipoUsuario> getPerfis() {
		return perfis.stream().map(perfil -> TipoUsuario.toEnum(perfil)).collect(Collectors.toSet());
	}

	public void addPerfil(TipoUsuario perfil) {
		perfis.add(perfil.getCodigo());
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
		Pessoa other = (Pessoa) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
