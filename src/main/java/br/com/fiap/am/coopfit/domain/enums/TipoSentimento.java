package br.com.fiap.am.coopfit.domain.enums;

public enum TipoSentimento {
	
	RAIVA(1, "Raiva"),
	FURIA(2, "Fúria"),
	TRISTEZA(3, "Tristeza"),
	ALEGRIA(4, "Alegria"),
	EMPOLGACAO(5, "Empolgação"),
	MEDO(6, "Medo"),
	INVEJA(7, "Inveja"),
	TEDIO(8, "Tédio");
	
	
	private Integer codigo;
	private String descricao;
	
	private TipoSentimento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoSentimento toEnum(String desc) {
		if (desc == null) {
			return null;
		}
		
		for(TipoSentimento tu : TipoSentimento.values()) {
			if (desc.equals(tu.getDescricao())) {
				return tu;
			}
		}
		
		throw new IllegalArgumentException("Descrição inválido: " + desc);
	}
	
//	public static TipoSentimento toEnum(Integer codigo) {
//		if (codigo == null) {
//			return null;
//		}
//		
//		for(TipoSentimento tu : TipoSentimento.values()) {
//			if (codigo.equals(tu.getCodigo())) {
//				return tu;
//			}
//		}
//		
//		throw new IllegalArgumentException("Id inválido: " + codigo);
//	}


}
