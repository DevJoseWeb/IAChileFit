package br.com.fiap.am.coopfit.domain.enums;

public enum TipoRotina {
	
	ATIVO(1, "Ativo"),
	MODERADO(2, "Moderado"),
	FREE_TIME(3, "Tempo livre");
	
	private Integer codigo;
	private String descricao;
	
	private TipoRotina(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoRotina toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for(TipoRotina tr : TipoRotina.values()) {
			if (codigo.equals(tr.getCodigo())) {
				return tr;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

}
